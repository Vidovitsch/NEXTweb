/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Models.Announcement;
import Models.Poll;
import Models.PollIdea;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author David
 */
public class DBPollModifier implements IModPoll {

    private static DatabaseReference firebase;
    private Object lock;
    private boolean done = false;
    private Poll poll;
    
    public DBPollModifier() {
        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (DatabaseReference) connector.getConnectionObject();
    }
    
    @Override
    public Poll fetchPoll() {
        final ArrayList<PollIdea> ideas = new ArrayList();
        DatabaseReference ref = firebase.child("Poll");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                int phase = Integer.valueOf(String.valueOf(ds.child("Phase").getValue()));
                DataSnapshot datasnapashot = ds.child("Ideas");
                for (DataSnapshot dSnapshot : datasnapashot.getChildren()) {
                    String key = dSnapshot.getKey();
                    String content = String.valueOf(dSnapshot.child("Content").getValue());
                    int votes = Integer.valueOf(String.valueOf(dSnapshot.child("Votes").getValue()));

                    PollIdea idea = new PollIdea(key, content);
                    idea.setVotes(votes);
                    ideas.add(idea);
                }
                
                poll = new Poll(phase);
                poll.setIdeas(ideas);
                
                unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        lockFXThread();
        return this.poll;
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
