/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.DBDayModifier;
import Database.DBEventModifier;
import Enums.EventType;
import Models.Event;
import Models.EventDate;
import Models.ScheduleableItemModel;
import Models.Workshop;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Youri van der Ceelen
 */
@Controller
public class pieController {

    private DBEventModifier eventModifier;
    private DBDayModifier dayModifier;

    @RequestMapping(value = "/partnerpage", method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView pieRequest() {

        return createModelAndView();
    }

    private ModelAndView createModelAndView() {
        eventModifier = new DBEventModifier();
        dayModifier = new DBDayModifier();
        ModelAndView modelView = new ModelAndView("partnerpage");
        modelView.addObject("types", getEventTypes());
        modelView.addObject("fields", getPossibleFields());
        return modelView;
    }

    private List<String> getEventTypes() {
        List<String> retV = new ArrayList<String>();
        for (EventType eT : EventType.values()) {
            retV.add(eT.toString());
        }
        return retV;
    }

    private List<String> getPossibleFields() {
        List<String> retV = new ArrayList<String>();
        retV.add("eventName");
        retV.add("startTime");
        retV.add("endTime");
        retV.add("date");
        retV.add("locationName");
        retV.add("imageURL");
        retV.add("presenter");
        retV.add("maxUsers");
        return retV;
    }

    @RequestMapping(value = "/createWorkshop", method = RequestMethod.POST)
    public ModelAndView createWorkshop(@ModelAttribute("SpringWeb") ScheduleableItemModel scheduleableItemModel,
            ModelMap model) {
        Event newWorkshop = new Workshop(scheduleableItemModel.getEventName());
        newWorkshop = addEventFieldValues(scheduleableItemModel, newWorkshop);
        ((Workshop) newWorkshop).setPresenter(scheduleableItemModel.getPresenter());
        ((Workshop) newWorkshop).setMaxUsers(Integer.parseInt(scheduleableItemModel.getMaxUsers()));
        eventModifier.insertEvent(newWorkshop);
        return createModelAndView();
    }

    private Event addEventFieldValues(ScheduleableItemModel item, Event targetEvent) {
        targetEvent = (Event) addEventDateValues(item, (EventDate) targetEvent);
        targetEvent.setImageURL(item.getImageURL());
        return targetEvent;
    }

    private EventDate addEventDateValues(ScheduleableItemModel item, EventDate targetEventDate) {
        targetEventDate.setDate(item.getDate());
        targetEventDate.setDescription(item.getDescription());
        targetEventDate.setEndTime(item.getEndTime());
        targetEventDate.setStartTime(item.getStartTime());
        targetEventDate.setLocationName(item.getLocationName());
        return targetEventDate;
    }
}