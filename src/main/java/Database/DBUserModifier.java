/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Enums.Course;
import Enums.UserRole;
import Enums.UserStatus;
import Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to comunicate with the firebase,
 * specifically the child User implements IModUser
 * @author David
 */
public class DBUserModifier implements IModUser {

    private static DatabaseReference firebase;
    private Object lock;
    private boolean done = false;
    private User user;

    /**
     * The constructor of the DBUserModifier class, The method takes no arguments.
     * It initiates the field firebase by creating a connection using the FBConnector class
     */
    public DBUserModifier() {
        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (DatabaseReference) connector.getConnectionObject();

    }

    /**
     * This method is used to add a new user to the firebase
     * The method takes the parameter user which contains the data of the user that has to be added
     */
    @Override
    public void insertUser(User user) {
        if(user == null || "".equals(user.getUid())){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " tried adding user null to firebase");
        }
        Map<String, String> data = new HashMap();
        data.put("Mail", user.getEmail());
        data.put("Name", user.getName());
        data.put("Role", user.getUserRole().toString());
        data.put("Status", user.getUserStatus().toString());
        data.put("Course", user.getCourse().toString());
        data.put("Semester", String.valueOf(user.getSemester()));
        data.put("GroupID", String.valueOf(user.getGroupID()));
        DatabaseReference ref = firebase.child("User").child(user.getUid());
        ref.setValue(data);
    }

    /**
     * This method is used to remove a specific user from the firebase
     * it takes a parameter user that specifies the user to be deleted
     * if the uid field of the user instance is null an exception is thrown
     * @param user not null
     */
    @Override
    public void removeUser(User user) {
        if(user == null || "".equals(user.getUid())){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " Tried removing user null from firebase");
        }
        DatabaseReference ref = firebase.child("User").child(user.getUid());
        ref.removeValue();
    }

    /**
     * This method is used to retrive a user instance from the firebase using a uid
     * @param uid
     * @return 
     */
    @Override
    public User getUser(final String uid) {
        if(uid.isEmpty()){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " tried to retrieve user null from firebase");
        }
        user = null;
        DatabaseReference ref = firebase.child("User");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.getKey().equals(uid)) {
                        String name = (String) ds.child("Name").getValue();
                        String email = (String) ds.child("Mail").getValue();

                        UserRole userRole = UserRole.valueOf((String) ds.child("Role").getValue());
                        UserStatus userStatus = UserStatus.valueOf((String) ds.child("Status").getValue());
                        Course course = Course.valueOf((String) ds.child("Course").getValue());
                        int semester = Integer.valueOf((String) ds.child("Semester").getValue());
                        int groupID = Integer.valueOf((String) ds.child("GroupID").getValue());
                        user = new User(uid);
                        user.setName(name);
                        user.setEmail(email);
                        user.setUserRole(userRole);
                        user.setUserStatus(userStatus);
                        user.setCourse(course);
                        user.setSemester(semester);
                        user.setGroupID(groupID);
                        break;
                    }
                }
                unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError fe) {
                System.out.println(fe.toException().toString());
            }
        });

        lockFXThread();
        return user;
    }

    /**
     * Tells a random object to wait while in a loop. The loop stops, and won't
     * cause any unnecessary cpu use.
     */
    private void lockFXThread() {
        lock = new Object();
        synchronized (lock) {
            while (!done) {
                try {
                    lock.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(DBUserModifier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        done = false;
    }

    /**
     * Wakes the lock. The while loop in the method 'lockFXThread' will proceed
     * and break free.
     */
    private void unlockFXThread() {
        synchronized (lock) {
            done = true;
            lock.notifyAll();
        }
    }
}
