package Controllers;

import Database.DBAnnouncementModifier;
import Database.DBGroupModifier;
import Database.DBPollModifier;
import Database.IModAnnouncement;
import Database.IModGroup;
import Database.IModPoll;
import Models.Announcement;
import Models.Poll;
import java.util.TreeSet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class is used for loading the home screen
 * @author David
 */
@Controller
public class indexController {
    /**
     * This method returs the standard ModelAndView for the homescreen
     * @return new ModelAnDView("home")
     */

    private IModAnnouncement modAnnouncement = new DBAnnouncementModifier();
    private IModPoll modPoll = new DBPollModifier();
    private IModGroup groupDB = new DBGroupModifier();
    

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView initHomePageGET(HttpServletRequest request) {
        System.out.println("Im here!!!!!!!!!!!!!!!!! btw uid is: GET");
        return newPageInstance(request);
    }
    
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView initHomePagePOST(HttpServletRequest request) {
        System.out.println("Im here!!!!!!!!!!!!!!!!! btw uid is: POST");
        return newPageInstance(request);
    }
    
    private ModelAndView newPageInstance(HttpServletRequest request) {
        TreeSet<Announcement> announcements = modAnnouncement.fetchAnnouncements();
        String uid = getCurrentUID(request);
        Poll poll = modPoll.fetchPoll();
        boolean submitted = modPoll.submitted(uid);
                
        ModelAndView mav = new ModelAndView("index");
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
