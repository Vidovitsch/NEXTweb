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
import Models.Utility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class DBUserModifier implements IModUser {

    private static DatabaseReference firebase;
    private Object lock;
    private boolean done = false;
    private User user;

    public DBUserModifier() {

        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (DatabaseReference) connector.getConnectionObject();

    }

    @Override
    public void insertUser(User user) {
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

    @Override
    public void removeUser(User user) {
        DatabaseReference ref = firebase.child("User").child(user.getUid());
        ref.removeValue();
    }

    @Override
    public User getUser(final String uid) {
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
                Utility.unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError fe) {
                System.out.println(fe.toException().toString());
            }
        });

        Utility.lockFXThread();
        return user;
    }
}
