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
import Models.Utility;
import Models.Workshop;
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
 * This class is used to comunicate with the firebase,
 * specifically the child Events implements IModEvent
 * @author David
 */
public class DBEventModifier implements IModEvent {

    private static DatabaseReference firebase;
    private Object lock;
    private boolean done = false;

    /**
     * The constructor of the DBEventModifier class, The method takes no arguments.
     * It initiates the field firebase by creating a connection using the FBConnector class
     */
    public DBEventModifier() {
        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (DatabaseReference) connector.getConnectionObject();
    }

    /**
     * This method is used to update the database to add a existing user to an existing event
     * The method takes the parameters event and user, if either of the id's fields is emtpy an exception is thrown
     * @param eventID not null
     * @param uid not null
     */
    @Override
    public void addAttendingUser(String eventID, String uid) {
        if(eventID.isEmpty()){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " tried adding user to eventID null");
        }else if(uid.isEmpty()){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " tried adding attending user with uid null to an event");
        }
        DatabaseReference ref = firebase.child("Event").child(eventID).child("Attending").child(uid);
        DatabaseReference userRef = firebase.child("User").child(uid).child("Attending").child(eventID);

        ref.setValue("Attending");
        userRef.setValue("Attending");
    }

    /**
     * This method is used to see or an specific user is attending a specific workshop
     * it takes an instance workshop which is the workshop it has to check the users for
     * and an uid which specifies the user it has to check thes tatus for
     * @param ws not null
     * @param uid not null
     */
    @Override
    public void checkAttending(final Workshop ws, final String uid) {
        if(ws == null || ws.getId().isEmpty()){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " tried checking out user for workshop null");
        }else if(uid.isEmpty()){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " tried checking uid null out as an attencence for a workshop");
        }
        DatabaseReference ref = firebase.child("Event/" + ws.getId() + "/Attending");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.getKey().equals(uid)) {
                        ws.setAttending("true");
                        break;
                    }
                }
                Utility.unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                        " " + de.getMessage()); 
            }
        });

        Utility.lockFXThread();
    }

    /**
     * This method is used to remove an attending user from a workshop, it takes the argumetns
     * event and user which specify which event and which user. the id's in these instances
     * are not able to null or an exception will be thrown
     * @param event not null
     * @param user not null
     */
    @Override
    public void removeAttendingUser(String eventID, String uid) {
        if(eventID.isEmpty()){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " tried removing user from eventID null");
        }else if(uid.isEmpty()){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " tried removing attending user with uid null from an event");
        }
        DatabaseReference ref = firebase.child("Event").child(eventID).child("Attending").child(uid);
        DatabaseReference userRef = firebase.child("User").child(uid).child("Attending").child(eventID);
        ref.removeValue();
        userRef.removeValue();
    }

    /**
     * This method is used to to add a new event to the Firebase database.
     * As parameters it takes the Event object that it has to add, it uses the putEventValues method
     * to add the values of the Event to a Map<String, String> which it can push to the Firebase database
     * @param event not null
     */
    @Override
    public void insertEvent(Event event) {
        if(event == null){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " tried adding event null to firebase");
        }
        Map<String, String> data = new HashMap();
        data.put("EventName", event.getEventName());
        data.put("StartTime", event.getStartTime());
        data.put("EndTime", event.getEndTime());
        data.put("Date", event.getDate());
        data.put("ImageURL", event.getImageURL());
        data.put("LocationName", event.getLocationName());
        data.put("Description", event.getDescription());
        data = putEventTypeValues(event, data);

        DatabaseReference ref = firebase.child("Event").push();
        ref.setValue(data);
    }

    /**
     * this method is used to remove an existing day from the firebase. As argument it takes an EventDay
     * which speciefies what event has to be deleted. An IllegalArgumentException will be thrown if the ID equals null
     * @param event not null
     */
    @Override
    public void removeEvent(Event event) {
        if(event == null || "".equals(event.getId())){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " the event instance's ID was null");
        }
        
        DatabaseReference ref = firebase.child("Event").child(event.getId());
        ref.removeValue();
    }

    /**
     * This method is used to fetch all the existing childs from the event branch from the firebase.
     * the data is loaded in an ArrayList<Event> events and returned with the method dsToEvent. 
     * to stop the FXThread until the method is finished loading loading the method uses 
     * the lockFXThread and unlockFXThread methods
     * @return events
     */
    @Override
    public ArrayList<Event> getEvents() {
        System.out.println("in getEvents");
        final ArrayList<Event> events = new ArrayList();
        DatabaseReference ref = firebase.child("Event");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    events.add(dsToEvent(ds));
                }
                Utility.unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                        " " + de.getMessage()); 
            }
        });

        Utility.lockFXThread();
        return events;
    }

    /**
     * this method is used to convert a DataSnapshot to a EventDay instance
     * Because event has mutltiple childs the specifyEvent method is used to load the childs
     * correcly
     * @param ds not null
     * @return day
     */
    private Event dsToEvent(DataSnapshot ds) {
        if(ds == null){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " the ds that had to be converted equaled null");
        }
        String id = ds.getKey();
        String startTime = (String) ds.child("StartTime").getValue();
        String endTime = (String) ds.child("EndTime").getValue();
        String date = (String) ds.child("Date").getValue();
        String imageURL = (String) ds.child("ImageURL").getValue();
        String locationName = (String) ds.child("LocationName").getValue();
        String description = (String) ds.child("Description").getValue();
        System.out.println("Start SpecifyEvent");
        Event event = specifyEvent(ds);
        System.out.println("Event specified");
        event.setId(id);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setDate(date);
        event.setImageURL(imageURL);
        event.setLocationName(locationName);
        event.setDescription(description);
        System.out.println("end of dsToEvent");
        return event;
    }

    /**
     * This method is used to fetch all childs from the Users.'thisuser'.Attending
     * with this it then fetches the according events from the events branch
     * the data is loaded in an ArrayList<Event> events and returned with the method dsToEvent. 
     * to stop the FXThread until the method is finished loading loading the method uses 
     * the lockFXThread and unlockFXThread methods
     * @param uid not null
     * @return events
     */
    @Override
    public ArrayList<Event> getEvents(String uid) {
        if(uid.isEmpty()){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " tried fetching events for user with the uid null");
        }
        final ArrayList<Event> events = new ArrayList();
        final ArrayList<String> eventIDs = new ArrayList();
        System.out.println("in getEventes(String uid)");
        DatabaseReference userRef = firebase.child("User").child(uid).child("Attending");
        System.out.println("userRef created");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("in userRef DataChange");
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String eventUID = ds.getKey();
                    eventIDs.add(eventUID);
                }
                Utility.unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                        " " + de.getMessage()); 
            }
        });
        Utility.lockFXThread();

        DatabaseReference eventRef = firebase.child("Event");
        eventRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("in eventRef DataChange");
                for (DataSnapshot dsEvents : snapshot.getChildren()) {
                    System.out.println("Retreiving eventkey");
                    String id = dsEvents.getKey();
                    for (String eventID : eventIDs) {
                        System.out.println("looping eventIDs with id: " + eventID);
                        if (id.equals(eventID)) {
                            System.out.println("in equals");
                            events.add(dsToEvent(dsEvents));
                        }
                    }

                }
                Utility.unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError fe) {
                throw new UnsupportedOperationException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                        " " + fe.getMessage()); 
            }
        });
        Utility.lockFXThread();
        System.out.println("Na lock, returning events");
        return events;
    }

    /**
     * This method is used to check the attendent users for specific event
     * it takes the paramenter eventID which specifies the event
     * @param eventID not null
     * @return attendency
     */
    @Override
    public String[] checkAttendancy(String eventID) {
        if(eventID.isEmpty()){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " tried fetching attendency for event with eventID null");
        }
        final String[] attendancy = new String[2];
        DatabaseReference ref = firebase.child("Event/" + eventID);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                attendancy[1] = String.valueOf(snapshot.child("MaxUsers").getValue());
                snapshot = snapshot.child("Attending");
                if (snapshot != null) {
                    attendancy[0] = String.valueOf(snapshot.getChildrenCount());
                }

                Utility.unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError fe) {
                throw new UnsupportedOperationException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                        " " + fe.getMessage()); 
            }
        });

        Utility.lockFXThread();
        return attendancy;
    }

    /**
     * this method is used to correcly load all data from a possible child. It uses
     * the child EventType from the events branch in the firebase to identify the child
     * then it loads the child specific fields
     * @param ds
     * @return event
     */
    private Event specifyEvent(DataSnapshot ds) {
        try {
            System.out.println(ds.getRef().toString());
            String eventType = (String) ds.child("EventType").getValue();
            System.out.println("eventtype = " + eventType);
            if (eventType.equals("Workshop")) {
                System.out.println("in workshop");
                Workshop event = new Workshop((String) ds.child("EventName").getValue());
                String presenter = (String) ds.child("Presenter").getValue();
                int maxUsers = Integer.valueOf((String) ds.child("MaxUsers").getValue());
                ArrayList<User> users = attendantsToUsers(ds.child("Attending"));

                event.setPresenter(presenter);
                event.setMaxUsers(maxUsers);
                event.setUsers(users);

                return event;
            } else if (eventType.equals("Lecture")) {
                System.out.println("in Lecture");

                Lecture event = new Lecture((String) ds.child("EventName").getValue());
                String presenter = (String) ds.child("Presenter").getValue();
                event.setPresenter(presenter);

                return event;
            } else {
                System.out.println("in else -->  performance");

                Performance event = new Performance((String) ds.child("EventName").getValue());

                return event;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("exception catch");
            return null;
        }
    }

    /**
     * This method is used to put the differnt values from a Event instance to a Map<String, String>
     * this is important because with the new format we can push the data to the firebase
     * The method is used by insertEvent and updateEvent. as arguments it takes a Map<String, String> which is
     * the object the data has to be added too. and a Event which contains the values that have to be added
     * because an Event object can be any of the child it uses the putEventTypeValues method to put the specific
     * child values to the Map<String, String>
     * @param data not null
     * @param day not null
     */
    private Map<String, String> putEventTypeValues(Event event, Map<String, String> data) {
        if(data == null){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " the data instance was null");
        }else if(event == null){
            throw new IllegalArgumentException(getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + 
                    " the event instance was null");
        }
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

    /**
     * This method is used to convert a Datasnapshot into a ArrayList<User>
     * @param snapshot
     * @return users
     */
    private ArrayList<User> attendantsToUsers(DataSnapshot snapshot) {
        ArrayList<User> users = new ArrayList();
        for (DataSnapshot ds : snapshot.getChildren()) {
            String uid = ds.getKey();
            users.add(new User(uid));
        }
        return users;
    }
}
