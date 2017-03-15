/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Enums.UserRole;
import Enums.UserStatus;
import Models.Event;
import Models.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author David
 */
public class DBUserModifier implements IModUser {

    private static Firebase firebase;
    private Object lock;
    private boolean done = false;
    private User user;
    
    public DBUserModifier() {
        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (Firebase) connector.getConnectionObject();
    }
    
    @Override
    public void insertUser(User user) {
        Map<String, String> data = new HashMap();
        data.put("Name", user.getName());
        data.put("Email", user.getEmail());
        data.put("Image", user.getImage());
        data.put("Role", user.getUserRole().toString());
        data.put("Status", user.getUserStatus().toString());
        Firebase ref = firebase.child("User").child(user.getPcn());
        ref.setValue(data);
    }

    @Override
    public void removeUser(User user) {
        Firebase ref = firebase.child("User").child(user.getPcn());
        ref.removeValue();
    }

    @Override
    public User getUser(final String pcn) {
        user = null;
        Firebase ref = firebase.child("User");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.getKey().equals(pcn)) {
                        String name = (String) ds.child("Name").getValue();
                        String email = (String) ds.child("Email").getValue();
                        String image = (String) ds.child("Image").getValue();
                        UserRole userRole = UserRole.valueOf((String) ds.child("Role").getValue());
                        UserStatus userStatus = UserStatus.valueOf((String) ds.child("Status").getValue());
                        
                        user = new User(pcn);
                        user.setName(name);
                        user.setEmail(email);
                        user.setImage(image);
                        user.setUserRole(userRole);
                        user.setUserStatus(userStatus);
                        break;
                    }
                }
                unlockFXThread();
            }
            
            @Override
            public void onCancelled(FirebaseError fe) {
                System.out.println(fe.toException().toString());
            }
        });
        
        lockFXThread();
        return user;
    }
    
    /**
     * Tells a random object to wait while in a loop.
     * The loop stops, and won't cause any unnecessary cpu use.
     */
    private void lockFXThread() {
        lock = new Object();
        synchronized (lock) {
            while (!done) {
                try {
                    lock.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(DBWorkshopModifier.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }
        done = false;
    }
    
    /**
     * Wakes the lock. The while loop in the method 'lockFXThread' will proceed and break free.
     */
    private void unlockFXThread() {
        synchronized (lock) {
            done = true;
            lock.notifyAll();
        }
    }
}
