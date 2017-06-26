/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class is used for loading the home screen
 * @author David
 */
@Controller
public class HomePageController {
    /**
     * This method returs the standard ModelAndView for the homescreen
     * @return new ModelAnDView("home")
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView LoginRequest() {
        return new ModelAndView("home");
    }
}
