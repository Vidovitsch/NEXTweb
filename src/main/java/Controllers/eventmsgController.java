/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.DBEventModifier;
import Database.DBGroupModifier;
import Database.IModEvent;
import Database.IModGroup;
import Models.EventViewModel;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author David
 */
@Controller
public class eventmsgController  {
    private IModGroup groupDB = new DBGroupModifier();
    private IModEvent eventDB = new DBEventModifier();
    
    @RequestMapping(value = "/eventmsg", method = RequestMethod.POST)
    public ModelAndView initMsgScreen(@ModelAttribute("SpringWeb") EventViewModel eventViewModel,
            ModelMap model, HttpServletRequest request) {
        String uid = getCurrentUID(request);
        String eventID = eventViewModel.getEventID();
        ModelAndView loginView = new ModelAndView("login");
        ModelAndView messageView = new ModelAndView("eventmsg");
        messageView.addObject("eventID", eventID);
        messageView.addObject("uid", uid);
        
        //Redirect user to the login screen if he/she isn't signed up yet
        if (uid == null) {
            return loginView;
        } else {
            eventDB.addAttendingUser(eventID, uid);
            return messageView;
        }
    }
    
    /**
     * Get the UID of the user currently signed in
     * @param request
     * @return uid of the current signed in user
     */
    private String getCurrentUID(HttpServletRequest request) {
        String uid = null;
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
