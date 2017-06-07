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
import Models.EventDate;
import Models.EventDay;
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
 *
 * @author Arno Dekkers Los
 */
@Controller
public class scheduleController
{

    private String uid;
    
    private IModEvent dbEvent = new DBEventModifier();
    private IModDay dbDay = new DBDayModifier();
    private IModGroup groupDB = new DBGroupModifier();
    
    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public ModelAndView initWorkshopmessageboardScreen(HttpServletRequest request)
    {
        //insertDummySchedule();
        uid = getCurrentUID(request);
        ModelAndView modelView = new ModelAndView("schedule");
        for (Map.Entry<String, List<Event>> e : getSchedule(uid).entrySet())
        {
            modelView.addObject(e.getKey(), e.getValue());
        }
        return modelView;
    }

//    //Ik het de setDescription verplaatst naar de klasse 'Event', omdat die
//    //zo beter bereikbaar is voor jsp en firebase. Ook past het meer bij
//    //het event zelf dat bij een event dag.
//    private void insertDummySchedule()
//    {
//        EventDay day1 = new EventDay("dinsdag NextSpy");
//        day1.setStartTime("09:00");
//        day1.setEndTime("17:00");
//        day1.setDate("07-11-2017");
//        day1.setLocationName("Hier");
//        day1.setDescription("hier komt dan die shit voor dinsdag nextspy");
//        //day1.setDescription("De geplande dinsdag van de NextWeek");
//        EventDay day2 = new EventDay("donderdag NextSpy");
//        day2.setStartTime("10:50");
//        day2.setEndTime("17:00");
//        day2.setDate("09-11-2017");
//        day2.setLocationName("Hier");
//        day2.setDescription("hier komt dan die shit voor donderdag nextspy");
//        //day2.setDescription("De geplande donderdag van de NextWeek");
//        EventDay day3 = new EventDay("vrijdag NextSpy");
//        day3.setStartTime("10:00");
//        day3.setEndTime("17:20");
//        day3.setDate("10-11-2017");
//        day3.setLocationName("Hier");
//        day3.setDescription("hier komt dan die shit voor vrijdag nextspy");
//        //day3.setDescription("De geplande vrijdag van de NextWeek");
//
//        dbDay.insertDay(day1);
//        dbDay.insertDay(day2);
//        dbDay.insertDay(day3);
//    }

    public Map<String, List<Event>> getSchedule(String uid)
    {
        Map schedule = new HashMap<String, List<Event>>();
        addEvents(schedule, dbEvent.getEvents(uid));
        return schedule;
    }

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
