package Controllers;

import Database.DBAnnouncementModifier;
import Database.IModAnnouncement;
import Models.Announcement;
import java.util.ArrayList;
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
    
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView LoginRequest() {
        ArrayList<Announcement> announcements = modAnnouncement.fetchAnnouncements();
        announcements.addAll(modAnnouncement.fetchAnnouncements());
        announcements.addAll(modAnnouncement.fetchAnnouncements());
        announcements.addAll(modAnnouncement.fetchAnnouncements());
        announcements.addAll(modAnnouncement.fetchAnnouncements());
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("announcements", announcements);
        
        return mav;
    }
}
