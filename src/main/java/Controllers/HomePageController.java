package Controllers;

import Database.DBAnnouncementModifier;
import Database.DBGroupModifier;
import Database.DBPollModifier;
import Database.IModAnnouncement;
import Database.IModGroup;
import Database.IModPoll;
import Models.Announcement;
import Models.Poll;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author David
 */
@Controller
public class HomePageController {

    private IModAnnouncement modAnnouncement = new DBAnnouncementModifier();
    private IModPoll modPoll = new DBPollModifier();
    private IModGroup groupDB = new DBGroupModifier();
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView initHomePageGET(HttpServletRequest request) {
        return newPageInstance(request);
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView initHomePagePOST(HttpServletRequest request) {
        return newPageInstance(request);
    }
    
    private ModelAndView newPageInstance(HttpServletRequest request) {
        TreeSet<Announcement> announcements = modAnnouncement.fetchAnnouncements();
        String uid = getCurrentUID(request);
        Poll poll = modPoll.fetchPoll();
        boolean submitted = modPoll.submitted(uid);
                
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("poll", poll);
        mav.addObject("announcements", announcements);
        mav.addObject("uid", uid);
        mav.addObject("submitted", submitted);
        
        return mav;
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
