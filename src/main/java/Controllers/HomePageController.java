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
public class HomePageController {

    private IModAnnouncement modAnnouncement = new DBAnnouncementModifier();
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView LoginRequest() {
        ArrayList<Announcement> announcements = modAnnouncement.fetchAnnouncement();
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("announcements", announcements);
        
        return new ModelAndView("home");
    }
}
