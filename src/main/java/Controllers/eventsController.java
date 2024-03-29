/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Database.DBEventModifier;
import Database.DBGroupModifier;
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
import Database.IModGroup;
import Models.EventViewModel;
import Models.LoginModel;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * This Controller is used by the events screen 
 * @author David
 */
@Controller
public class eventsController {

    private String uid;
    
    private final static int ROWNUMBER = 3;
    private IModEvent dbEvent = new DBEventModifier();
    private IModGroup groupDB = new DBGroupModifier();
           
    /**
     * This method is used to load the default events screen
     * @param request
     * @return modelView
     */
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ModelAndView initWorkshopScreen(HttpServletRequest request) {
        uid = getCurrentUID(request);
        
        ModelAndView modelView = new ModelAndView("events");
        modelView.addObject("events", initEvents(dbEvent.getEvents()));
        modelView.addObject(new EventViewModel());
        modelView.addObject("message", "null");
        
        return modelView;
    }
    
    /**
     * upon creating a new event this method is used. it takes EventViewModel
     * which is to pass the information that the user entered in to GUI to the 
     * business layer. the information from the eventViewModel is sent to the
     * appropriate database method
     * The parameter request is used to get the uid from the current user
     * @param eventViewModel
     * @param model
     * @param request
     * @return 
     */
    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public ModelAndView updateEvent(@ModelAttribute("SpringWeb") EventViewModel eventViewModel,
            ModelMap model, HttpServletRequest request) {
        uid = getCurrentUID(request);
        String eventID = eventViewModel.getEventID();
        String mode = eventViewModel.getMode();
        ModelAndView messageView = new ModelAndView("events");
        messageView.addObject("eventID", eventID);
        messageView.addObject(eventViewModel);
        
        String[] a = dbEvent.checkAttendancy(eventID);

        if (mode.equals("unattend")) {
            dbEvent.removeAttendingUser(eventID, uid);
            messageView.addObject("message", "Un-Attended");
        } else {
            //Check if max users is smaller or equal to the attending users
            System.out.println(a[1] + " >= " + a[0]);
            if (Integer.valueOf(a[1]) >= Integer.valueOf(a[0])) {
                dbEvent.addAttendingUser(eventID, uid);
                messageView.addObject("message", "Attendance succesful!");
            } else {
                messageView.addObject("message", "This workshop is full!");
            }
        }
        messageView.addObject("events", initEvents(dbEvent.getEvents()));
        
        return messageView;
    }
    
    /**
     * This method is used to load a filtered events screen containing only
     * the events that take place on Monday
     * @return modelView
     */
    @RequestMapping(value = "/events/ma", method = RequestMethod.GET)
    public ModelAndView mondayEvents() {
        ModelAndView modelView = new ModelAndView("events");
        modelView.addObject("events", filterOnDay("Monday", dbEvent.getEvents()));
        modelView.addObject(new EventViewModel());
        modelView.addObject("message", "null");
        
        return modelView;
    }
    
    /**
     * This method is used to load a filtered events screen containing only
     * the events that take place on Tuesday
     * @return modelView
     */
    @RequestMapping(value = "/events/di", method = RequestMethod.GET)
    public ModelAndView tuesdayEvents() {
        ModelAndView modelView = new ModelAndView("events");
        modelView.addObject("events", filterOnDay("Tuesday", dbEvent.getEvents()));
        modelView.addObject(new EventViewModel());
        modelView.addObject("message", "null");
        
        return modelView;
    }
    
    /**
     * This method is used to load a filtered events screen containing only
     * the events that take place on Wednesday
     * @return modelView
     */
    @RequestMapping(value = "/events/wo", method = RequestMethod.GET)
    public ModelAndView wednessdayEvents() {
        ModelAndView modelView = new ModelAndView("events");
        modelView.addObject("events", filterOnDay("Wednesday", dbEvent.getEvents()));
        modelView.addObject(new EventViewModel());
        modelView.addObject("message", "null");
        
        return modelView;
    }
    
    /**
     * This method is used to load a filtered events screen containing only
     * the events that take place on Thursday
     * @return modelView
     */
    @RequestMapping(value = "/events/do", method = RequestMethod.GET)
    public ModelAndView thursdayEvents() {
        ModelAndView modelView = new ModelAndView("events");
        modelView.addObject("events", filterOnDay("Thursday", dbEvent.getEvents()));
        modelView.addObject(new EventViewModel());
        modelView.addObject("message", "null");
        
        return modelView;
    }
    
    /**
     * This method is used to load a filtered events screen containing only
     * the events that take place on Friday
     * @return modelView
     */
    @RequestMapping(value = "/events/vr", method = RequestMethod.GET)
    public ModelAndView fridayEvents() {
        ModelAndView modelView = new ModelAndView("events");
        modelView.addObject("events", filterOnDay("Friday", dbEvent.getEvents()));
        modelView.addObject(new EventViewModel());
        modelView.addObject("message", "null");
        
        return modelView;
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
            if (event instanceof Workshop) {
                Workshop ws = (Workshop) event;
                dbEvent.checkAttending(ws, uid);
            }
        }
        orderedEvents.add(row);
        
        return orderedEvents;
    }
    
    /**
     * This method is used to filter the list events to only contain events from a certaint day
     * it takes an argument Day which specifies the day it has to filter and an ArrayList<Events>
     * which contains all the events not yet filtered for days
     * @param day
     * @param events
     * @return filtered
     */
    public ArrayList<ArrayList<Event>> filterOnDay(String day, ArrayList<Event> events) {
        ArrayList<Event> filtered = new ArrayList();
        for (Event e : events) {
            if (day.equals(e.getDay())) {
                filtered.add(e);
            }
        }
        return initEvents(filtered);
    }
    
    /**
     * Get the UID of the user currently signed in
     * @param request
     * @return uid of the current signed in user
     */
    private String getCurrentUID(HttpServletRequest request) {
        uid = null;
        Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username")) {
                        uid = groupDB.getUid(cookie.getValue());
                    }
                }
            }
        return uid;
    }
}
