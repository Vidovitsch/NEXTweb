/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Models.Event;
import Models.Lecture;
import Models.Performance;
import Models.User;
import Models.Workshop;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author David
 */
public class DBWorkshopModifier implements IModWorkshop {
    
    private static Firebase firebase;

    public DBWorkshopModifier() {
        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (Firebase) connector.getConnectionObject();
    }

    @Override
    public void insertEvent(Event event) {
        Map<String, String> data = new HashMap();
        data.put("EventName", event.getEventName());
        data.put("StartTime", event.getStartTime());
        data.put("EndTime", event.getEndTime());
        data.put("Date", event.getDate());
        data.put("ImageURL", event.getImageURL());
        data.put("LocationName", event.getLocationName());
        data = putEventTypeValues(event, data);
        
        Firebase ref = firebase.child("Event").push();
        ref.setValue(data);
    }

    @Override
    public ArrayList<Event> getEvents() {
        final ArrayList<Event> events = new ArrayList();

        Firebase ref = firebase.child("Event");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String id = ds.getKey();
                    String startTime = (String) ds.child("StartTime").getValue();
                    String endTime = (String) ds.child("EndTime").getValue();
                    String date = (String) ds.child("Date").getValue();
                    String imageURL = (String) ds.child("ImageURL").getValue();
                    String locationName = (String) ds.child("LocationName").getValue();
                    
                    Event event = specifyEvent(ds);
                    event.setId(id);
                    event.setStartTime(startTime);
                    event.setEndTime(endTime);
                    event.setDate(date);
                    event.setImageURL(imageURL);
                    event.setLocationName(locationName);
                    
                    events.add(event);
                }
            }
            
            @Override
            public void onCancelled(FirebaseError fe) {
                System.out.println(fe.toException().toString());
            }
        });
        
        return events;
    }
    
    private Event specifyEvent(DataSnapshot ds) {
        String eventType = (String) ds.child("EventType").getValue();
        if (eventType.equals("Workshop")) {
            Workshop event = new Workshop((String) ds.child("EventName").getValue());
            String presenter = (String) ds.child("Presenter").getValue();
            int maxUsers = (Integer) ds.child("MaxUsers").getValue();
            event.setPresenter(presenter);
            event.setMaxUsers(maxUsers);
            
            return event;
        } else if (eventType.equals("Lecture")) {
            Lecture event = new Lecture((String) ds.child("EventName").getValue());
            String presenter = (String) ds.child("Presenter").getValue();
            event.setPresenter(presenter);
            
            return event;
        } else {
            Performance event = new Performance((String) ds.child("EventName").getValue());
            
            return event;
        }
    }
    
    private Map<String, String> putEventTypeValues(Event event, Map<String, String> data) {
        if (event instanceof Workshop) {
            data.put("Presenter", ((Workshop) event).getPresenter());
            data.put("MaxUsers", String.valueOf(((Workshop) event).getMaxUsers()));
            data.put("EventType", "Workshop");
        } else if (event instanceof Lecture) {
            data.put("Presenter", ((Lecture) event).getPresenter());
            data.put("EventType", "Lecture");
        } else {
            data.put("EventType", "Performance");
        }
        return data;
    }

    @Override
    public void insertUser(User user, Workshop workshop) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
