/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.EventDay;
import Models.Utility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arno Dekkers Los
 */
public class DBDayModifier implements IModDay {

    private static DatabaseReference firebase;
    private boolean done = false;
    private Object lock;

    public DBDayModifier() {
        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (DatabaseReference) connector.getConnectionObject();;
    }

    @Override
    public void insertDay(EventDay day) {
        Map<String, String> data = new HashMap();
        putDayValues(data, day);
        DatabaseReference ref = firebase.child("Days").push();
        ref.setValue(data);
    }

    public void putDayValues(Map<String, String> data, EventDay day) {
        data.put("EventName", day.getEventName());
        data.put("StartTime", day.getStartTime());
        data.put("EndTime", day.getEndTime());
        data.put("Date", day.getDate());
        data.put("LocationName", day.getLocationName());
        data.put("Description", day.getDescription());
    }

    @Override
    public void removeDay(EventDay day) {
        DatabaseReference ref = firebase.child("Days").child(day.getId());
        ref.removeValue();
    }

    @Override
    public ArrayList<EventDay> getDays() {
        System.out.println("in getdays()");
        final ArrayList<EventDay> days = new ArrayList();
        System.out.println("creating daysRef");
        DatabaseReference ref = firebase.child("Days");
        System.out.println(ref.getRoot().toString());
        System.out.println("adding Listernerforsinglevalueevnt");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                System.out.println("in ondatachange");
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("in onDataChange");
                for (DataSnapshot ds : snapshot.getChildren()) {
                    days.add(dsToEventDay(ds));
                }
                Utility.unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError fe) {
                System.out.println("in oncancelled");
                System.out.println(fe.toException().toString());
            }
        });
        System.out.println("locking fxthread");
        Utility.lockFXThread();
        System.out.println("returning days");
        return days;
    }

    private EventDay dsToEventDay(DataSnapshot ds) {
        String date = (String) ds.child("Date").getValue();
        String startTime = (String) ds.child("StartTime").getValue();
        String endTime = (String) ds.child("EndTime").getValue();
        String locationName = (String) ds.child("LocationName").getValue();
        String eventName = (String) ds.child("EventName").getValue();
        String description = (String) ds.child("Description").getValue();
        String id = (String) ds.getKey();

        EventDay day = new EventDay(eventName);
        day.setStartTime(startTime);
        day.setEndTime(endTime);
        day.setDate(date);
        day.setLocationName(locationName);
        day.setDescription(description);
        day.setId(id);
        return day;
    }

        public EventDay getDay(final String id) {
        System.out.println("in db method");
        final ArrayList<EventDay> days = new ArrayList();
        DatabaseReference ref = firebase.child("Days/" + id);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("found my match");
                EventDay day = dsToEventDay(snapshot);
                day.setId(id);
                days.add(day);
                Utility.unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError fe) {
                System.out.println(fe.toException().toString());
            }
        });
        Utility.lockFXThread();
        return days.get(0);
    }
}
