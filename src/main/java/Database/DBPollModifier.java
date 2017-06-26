/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Announcement;
import Models.Poll;
import Models.PollIdea;
import Models.Utility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
    private boolean submitted;

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
                Collections.sort(ideas, new PollIdeaComparator());
                poll.setIdeas(ideas);
                Utility.unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        Utility.lockFXThread();
        return this.poll;
    }

    @Override
    public boolean submitted(String UID) {
        final String uid = UID;
        DatabaseReference checkRef = firebase.child("User/" + uid);
        checkRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                if (ds.hasChild("Submitted")) {
                    DatabaseReference ref = firebase.child("User/" + uid + "/Submitted");
                    System.out.println(ref.getKey());
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot ds) {
                            int s = Integer.valueOf(String.valueOf(ds.getValue()));
                            if (s == 0) {
                                submitted = false;
                            } else {
                                submitted = true;
                            }
                            Utility.unlockFXThread();
                        }

                        @Override
                        public void onCancelled(DatabaseError de) {
                            submitted = false;
                            Utility.unlockFXThread();
                        }
                    });  
                }
                else{
                    submitted = false;
                    Utility.unlockFXThread();
                }
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Utility.lockFXThread();
        return submitted;
    }

    private class PollIdeaComparator implements Comparator<PollIdea> {

        @Override
        public int compare(PollIdea o1, PollIdea o2) {
            int v1 = o1.getVotes();
            int v2 = o2.getVotes();
            return Integer.compare(v2, v1);
        }
    }
}
