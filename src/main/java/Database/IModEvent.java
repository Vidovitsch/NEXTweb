/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Event;
import Models.User;
import Models.Workshop;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public interface IModEvent {
    
    /**
     * Inserts a new event to firebase
     * 
     * @param event 
     */
    void insertEvent(Event event);
    
    /**
     * Removes an existing event from firebase
     * 
     * @param event 
     */
    void removeEvent(Event event);

    /**
     * Get all events from Firebase
     * 
     * @return All events
     */
    ArrayList<Event> getEvents();
    
    /**
     * Adds an attendant to a event
     * 
     * @param eventID
     * @param uid
     */
    void addAttendingUser(String eventID, String uid);
    
    /**
     * Removes an attendant from a event
     * 
     * @param eventID
     * @param uid 
     */
    void removeAttendingUser(String eventID, String uid);
    
    String[] checkAttendancy(String eventID);
    
    void checkAttending(Workshop ws, String uid);

    List getEvents(String uid);
}
