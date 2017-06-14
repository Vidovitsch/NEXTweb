/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This model is used to load the map screen
 * @author David
 */
@Controller
public class mapController {
    /**
     * this method is used to load the default map screen
     * @return new ModelAndView("map")
     */
    @RequestMapping("/map")
    public ModelAndView initMapScreen() {
        return new ModelAndView("map");
    }
}
