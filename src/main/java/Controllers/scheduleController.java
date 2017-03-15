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
 * @author Arno Dekkers Los
 */
@Controller
public class scheduleController {
    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public ModelAndView initWorkshopmessageboardScreen() {
        return new ModelAndView("schedule");
    } 
}
