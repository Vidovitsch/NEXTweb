/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Models.Announcement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author David
 */
public class DBAnnouncementModifier implements IModAnnouncement {

    private static DatabaseReference firebase;
    private Object lock;
    private boolean done = false;
    
    public DBAnnouncementModifier() {
        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (DatabaseReference) connector.getConnectionObject();
    }
    
    @Override
    public ArrayList<Announcement> fetchAnnouncements() {
        final ArrayList<Announcement> announcements = new ArrayList();
        DatabaseReference ref = firebase.child("Announcement");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot snapshot : ds.getChildren()) {
                    String id = snapshot.getKey();
                    String content = String.valueOf(snapshot.child("Text").getValue());
                    announcements.add(new Announcement(id, content));
                }
                unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        lockFXThread();
        
        return announcements;
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
                    Logger.getLogger(DBEventModifier.class.getName()).log(Level.SEVERE, null, ex);
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
