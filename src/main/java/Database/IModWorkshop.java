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

/**
 *
 * @author David
 */
public interface IModWorkshop {
    
    void insertEvent(Event event);
    
    void removeEvent(Event event);

    ArrayList<Event> getEvents();
    
    void addAttendingUser(Workshop event, User user);
    
    void removeAttendingUser(Workshop event, User user);
}
