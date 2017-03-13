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
    
    void insertUser(User user, Workshop workshop);
   
    ArrayList<Event> getEvents();
}
