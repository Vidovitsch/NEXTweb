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
 *
 * @author Youri van der Ceelen
 */
@Controller
public class contactController {
        @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView LoginRequest()
    {
        return new ModelAndView("contact");
    }
}
