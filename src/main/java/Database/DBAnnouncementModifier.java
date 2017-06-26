/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Models.Announcement;
import Models.Utility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.TreeSet;
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
    public TreeSet<Announcement> fetchAnnouncements() {
        final TreeSet<Announcement> announcements = new TreeSet(new AnnouncementComparator());
        DatabaseReference ref = firebase.child("Announcement");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot snapshot : ds.getChildren()) {
                    String id = snapshot.getKey();
                    String content = String.valueOf(snapshot.child("Text").getValue());
                    String dateTime = String.valueOf(snapshot.child("DateTime").getValue());
                    announcements.add(new Announcement(id, content, dateTime));
                }
                Utility.unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Utility.lockFXThread();
        
        return announcements;
    }
    
    private class AnnouncementComparator implements Comparator<Announcement> {

        @Override
        public int compare(Announcement o1, Announcement o2) {
            try {
                Date date1 = o1.toDate(o1.getDateTime());
                Date date2 = o2.toDate(o2.getDateTime());
                
                return date2.compareTo(date1);
            } catch (ParseException ex) {
                //Exception has been thrown, returns a non-existant comparable value (-2)
                Logger.getLogger(Announcement.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
    }
}
