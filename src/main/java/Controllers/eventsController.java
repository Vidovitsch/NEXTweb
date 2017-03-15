/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Database.DBWorkshopModifier;
import Database.IModWorkshop;
import Models.Event;
import Models.Lecture;
import Models.Performance;
import Models.User;
import Models.Workshop;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author David
 */
@Controller
public class eventsController {

    private final static int ROWNUMBER = 3;
    private IModWorkshop dbMod = new DBWorkshopModifier();
            
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ModelAndView initWorkshopScreen() {
        //insertDummyWorkshops();
                
        ModelAndView modelView = new ModelAndView("events");
        modelView.addObject("events", getWorkshops());
        
        return modelView;
    }
    
    //Test method
    private void insertDummyWorkshops() {
        Workshop ws = new Workshop("TestWorkshop");
        ws.setStartTime("12:00");
        ws.setEndTime("13:00");
        ws.setDate("11-11-2017");
        ws.setLocationName("Hier");
        Lecture ws1 = new Lecture("TestLecture");
        ws1.setStartTime("12:00");
        ws1.setEndTime("13:00");
        ws1.setDate("11-11-2017");
        ws1.setLocationName("Hier");
        Performance ws2 = new Performance("TestPerformance");
        ws2.setStartTime("12:00");
        ws2.setEndTime("13:00");
        ws2.setDate("11-11-2017");
        ws2.setLocationName("Hier");
        
        dbMod.insertEvent(ws);
        dbMod.insertEvent(ws1);
        dbMod.insertEvent(ws2);
    }
    
    /**
     * Fetching workshops from the database into a list.
     * Each index of the list represents a row of workshops.
     * This amount of workshops is defined in the variable ROWNUMBER.
     * @return list of rows containing workshops
     */
    private ArrayList<Event[]> getWorkshops() {
        ArrayList<Event[]> eventsDivided = new ArrayList();
        ArrayList<Event> events = dbMod.getEvents();
        
        dbMod.addAttendingUser((Workshop) events.get(0), new User("342925"));
        
        Event[] row = new Event[ROWNUMBER];
        int wsCounter = 0;
        for (int i = 0; i < events.size(); i++) {
            row[wsCounter] = events.get(i);
            wsCounter++;
            if (wsCounter == ROWNUMBER || events.size() - 1 == i) {
                eventsDivided.add(row);
                row = new Event[calcRowLength(i + 1, events.size())];
                wsCounter = 0;
            }
        }
        
        return eventsDivided;
    }
    
    /**
     * Calculates the length of a row.
     * Example: ROWNUMBER = 3. There are 7 workshops.
     * This means there are 2 rows of 3 workshops and 1 row of 1 workshop.
     * This method calculates the indexes of each row.
     * @param current
     * @param max
     * @return the current row length.
     */
    private int calcRowLength(int current, int max) {
        if (max - current > ROWNUMBER) {
            return ROWNUMBER;
        } else {
            return max - current;
        }
    }
}
