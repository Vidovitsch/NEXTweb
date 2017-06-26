/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.DBDayModifier;
import Database.DBEventModifier;
import Database.DBGroupModifier;
import Database.IModDay;
import Database.IModEvent;
import Database.IModGroup;
import Models.Event;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This Controller is used to load the different pages for the Schedule screen
 * @author Arno Dekkers Los
 */
@Controller
public class scheduleController
{
    private String uid;
    
    private IModEvent dbEvent = new DBEventModifier();
    private IModDay dbDay = new DBDayModifier();
    private IModGroup groupDB = new DBGroupModifier();
    
    /**
     * This method is used to load the default event screen
     * it takes a parameter of the type HttpServletRequest from which it takes
     * the uid from the signed in user. with this uid it calls the getSchedule
     * method to get a list of all Events for the signed user. this list is
     * added to the modelView
     * @param request
     * @return modelView
     */
    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public ModelAndView initWorkshopmessageboardScreen(HttpServletRequest request)
    {
        uid = getCurrentUID(request);
        ModelAndView modelView = new ModelAndView("schedule");
        for (Map.Entry<String, List<Event>> e : getSchedule(uid).entrySet())
        {
            modelView.addObject(e.getKey(), e.getValue());
        }
        return modelView;
    }

    /**
     * This method is used to get the schedule for a specific user
     * it takes a parameter uid which specifies the user
     * the objects retrieved from the firebase are added to a
     * list Map<String, List<Event>> by using the addEvents method
     * @param uid
     * @return 
     */
    public Map<String, List<Event>> getSchedule(String uid)
    {
        Map schedule = new HashMap<String, List<Event>>();
        addEvents(schedule, dbEvent.getEvents(uid));
        return schedule;
    }

    /**
     * This method is used to fill Map<String, List<Event>> with a List
     * it takes these 2 objects as parameters. 
     * It loops through all tie items in the scheduleItems list and
     * adds these to the Map schedule. the days are added to the Map using
     * the day they take place on as key
     * @param schedule
     * @param scheduleItems 
     */
    private void addEvents(Map<String, List<Event>> schedule, List scheduleItems)
    {
        List<Event> temp;
        for (Object eD : scheduleItems)
        {
            Event current = (Event) eD;
            if (!schedule.containsKey(current.getDay()))
            {
                temp = new ArrayList<Event>();
            } else
            {
                temp = (ArrayList<Event>) schedule.get(current.getDay());
            }
            temp.add(current);
            schedule.put(current.getDay(), temp);
        }
    }
    
    /**
     * this method is used to retrieve the current uid from a HttpServletRequest instance
     * @param request
     * @return uid
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
