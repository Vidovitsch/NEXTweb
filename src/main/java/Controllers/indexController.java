/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.LoginModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This Controller is used to load the index screen
 * @author Michiel van Eijkeren
 */
@Controller
public class indexController
{
    /**
     * This method is used to load the standard view of the index screen
     * @return new ModelAndView("index")
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView LoginRequest()
    {
        return new ModelAndView("index");
    }
}
