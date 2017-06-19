package Controllers;

import Database.DBAnnouncementModifier;
import Database.DBPollModifier;
import Database.IModAnnouncement;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author David
 */
@Controller
public class homePageController {

    private IModAnnouncement modAnnouncement = new DBAnnouncementModifier();
    private IModPoll modPoll = new DBPollModifier();
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView initHomePageGET() {
        return newPageInstance();
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView initHomePagePOST() {
        return newPageInstance();
    }
    
    private ModelAndView newPageInstance() {
        TreeSet<Announcement> announcements = modAnnouncement.fetchAnnouncements();
        Poll poll = modPoll.fetchPoll();
        
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("poll", poll);
        mav.addObject("announcements", announcements);
        
        return mav;
    }
}
