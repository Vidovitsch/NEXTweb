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
import Enums.Day;
import Models.EventViewModel;
import Models.LoginModel;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


/**
 *
 * @author David
 */
@Controller
public class eventsController {

    private String uid;
    
    private final static int ROWNUMBER = 3;
    private IModEvent dbEvent = new DBEventModifier();
    private IModGroup groupDB = new DBGroupModifier();
            
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ModelAndView initWorkshopScreen(HttpServletRequest request) {
        uid = getCurrentUID(request);
        
        ModelAndView modelView = new ModelAndView("events");
        modelView.addObject("events", initEvents(dbEvent.getEvents()));
        modelView.addObject(new EventViewModel());
        modelView.addObject("message", "null");
        
        return modelView;
    }
    
    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public ModelAndView updateEvent(@ModelAttribute("SpringWeb") EventViewModel eventViewModel,
            ModelMap model, HttpServletRequest request) {
        uid = getCurrentUID(request);
        String eventID = eventViewModel.getEventID();
        String mode = eventViewModel.getMode();
        ModelAndView messageView = new ModelAndView("events");
        messageView.addObject("eventID", eventID);
        messageView.addObject("uid", uid);
        messageView.addObject(eventViewModel);
        
        String[] a = dbEvent.checkAttendancy(eventID);

        if (mode.equals("unattend")) {
            System.out.println("unattend: " + uid);
            dbEvent.removeAttendingUser(eventID, uid);
            messageView.addObject("message", "Un-Attended");
        } else {
            if (Integer.valueOf(a[1]) != Integer.valueOf(a[0])) {
                System.out.println(uid);
                dbEvent.addAttendingUser(eventID, uid);
                messageView.addObject("message", "Attendance succesful!");
            } else {
                messageView.addObject("message", "This workshop is full!");
            }
        }
        messageView.addObject("events", initEvents(dbEvent.getEvents()));
        
        return messageView;
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
    
    private ArrayList<ArrayList<Event>> filterOnDay(Day day, ArrayList<Event> events) {
        ArrayList<Event> filtered = new ArrayList();
        for (Event e : events) {
            if (day.equals(day.dateToDate(e.getDate()))) {
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
    
    private void setAttending(Workshop ws) {
        
    }
}
