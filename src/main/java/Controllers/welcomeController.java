package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author David
 */
@Controller
public class welcomeController {
    
    @RequestMapping("/welcome")
    public ModelAndView setupScreen() {
        ModelAndView mav = new ModelAndView("welcome");
        mav.addObject("name", new Name());
        return mav;
    }
}
