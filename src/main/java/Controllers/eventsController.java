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
            
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ModelAndView initWorkshopScreen() {
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
    
    /**
     * Fetching workshops from the database into a list.
     * Each index of the list represents a row of workshops.
     * This amount of workshops is defined in the variable ROWNUMBER.
     * @return list of rows containing workshops
     */
    private ArrayList<ArrayList<Event>> initEvents(ArrayList<Event> events) {
        ArrayList<ArrayList<Event>> orderedEvents = new ArrayList();
        int counter = 0;
        ArrayList<Event> row = new ArrayList();
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
