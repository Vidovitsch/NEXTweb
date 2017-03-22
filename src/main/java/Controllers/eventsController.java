/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Database.DBEventModifier;
import Database.DBUserModifier;
import Database.IModUser;
import Enums.UserRole;
import Enums.UserStatus;
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
import Database.IModEvent;
import Enums.Day;


/**
 *
 * @author David
 */
@Controller
public class eventsController {

    private final static int ROWNUMBER = 3;
    private IModEvent dbEvent = new DBEventModifier();
    private IModUser dbUser = new DBUserModifier();
            
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ModelAndView initWorkshopScreen() {
        
        //insertDummyWorkshops();
        
        ModelAndView modelView = new ModelAndView("events");
        modelView.addObject("events", initEvents(dbEvent.getEvents()));
        
        return modelView;
    }
    
    @RequestMapping(value = "/events/ma", method = RequestMethod.GET)
    public ModelAndView mondayEvents() {
        ModelAndView modelView = new ModelAndView("events");
        modelView.addObject("events", filterOnDay(Day.Ma, dbEvent.getEvents()));
        
        return modelView;
    }
    
    @RequestMapping(value = "/events/di", method = RequestMethod.GET)
    public ModelAndView tuesdayEvents() {
        ModelAndView modelView = new ModelAndView("events");
        modelView.addObject("events", filterOnDay(Day.Di, dbEvent.getEvents()));
        
        return modelView;
    }
    
    @RequestMapping(value = "/events/wo", method = RequestMethod.GET)
    public ModelAndView wednessdayEvents() {
        ModelAndView modelView = new ModelAndView("events");
        modelView.addObject("events", filterOnDay(Day.Wo, dbEvent.getEvents()));
        
        return modelView;
    }
    
    @RequestMapping(value = "/events/do", method = RequestMethod.GET)
    public ModelAndView thursdayEvents() {
        ModelAndView modelView = new ModelAndView("events");
        modelView.addObject("events", filterOnDay(Day.Do, dbEvent.getEvents()));
        
        return modelView;
    }
    
    @RequestMapping(value = "/events/vr", method = RequestMethod.GET)
    public ModelAndView fridayEvents() {
        ModelAndView modelView = new ModelAndView("events");
        modelView.addObject("events", filterOnDay(Day.Vr, dbEvent.getEvents()));
        
        return modelView;
    }
    
    //Test method
    private void insertDummyWorkshops() {
        Workshop ws = new Workshop("TestWorkshop");
        ws.setStartTime("14:30");
        ws.setEndTime("15:00");
        ws.setDate("12-11-2017");
        ws.setLocationName("Hier");
        Lecture ws1 = new Lecture("TestLecture");
        ws1.setStartTime("13:10");
        ws1.setEndTime("15:15");
        ws1.setDate("13-11-2017");
        ws1.setLocationName("Hier");
        Performance ws2 = new Performance("TestPerformance");
        ws2.setStartTime("18:00");
        ws2.setEndTime("19:00");
        ws2.setDate("14-11-2017");
        ws2.setLocationName("Hier");
        
        dbEvent.insertEvent(ws);
        dbEvent.insertEvent(ws1);
        dbEvent.insertEvent(ws2);
    }
    
    /**
     * Fetching workshops from the database into a list.
     * Each index of the list represents a row of workshops.
     * This amount of workshops is defined in the variable ROWNUMBER.
     * @return list of rows containing workshops
     */
    private ArrayList<Event[]> initWorkshops(ArrayList<Event> events) {
        ArrayList<Event[]> eventsDivided = new ArrayList();
        Event[] row = new Event[ROWNUMBER];
        int wsCounter = 0;
        for (int i = 0; i < events.size(); i++) {
            row[wsCounter] = events.get(i);
            wsCounter++;
            if (wsCounter == ROWNUMBER || events.size() - 1 == i) {
                eventsDivided.add(row);
                row = new Event[ROWNUMBER];
                wsCounter = 0;
            }
        }
        
        return eventsDivided;
    }
    
    private ArrayList<ArrayList<Event>> initEvents(ArrayList<Event> events) {
        ArrayList<ArrayList<Event>> orderedEvents = new ArrayList();
        int counter = 0;
        ArrayList<Event> row = new ArrayList();
        System.out.println("List size: " + events.size());
        for (Event event : events) {
            row.add(event);
            counter++;
            if (counter == ROWNUMBER) {
                orderedEvents.add(row);
                row = new ArrayList();
                counter = 0;
            }
        }
        orderedEvents.add(row);
        
        return orderedEvents;
    }
    
    private ArrayList<ArrayList<Event>> filterOnDay(Day day, ArrayList<Event> events) {
        ArrayList<Event> filtered = new ArrayList();
        for (Event e : events) {
            if (day.equals(day.dateToDate(e.getDate()))) {
                filtered.add(e);
            }
        }
        return initEvents(filtered);
    }
}
