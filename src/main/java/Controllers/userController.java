/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Models.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author David
 */
@Controller
public class userController {

    private List<User> users;
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView userPage() {
        ModelAndView mv = new ModelAndView("user");
        //mv.addObject("users", getUsers());
        
        return mv;
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView subminUser(@ModelAttribute User user) {
        users.add(user);
        
        ModelAndView mv = new ModelAndView("user");
        mv.addObject("users", users);
        
        return mv;
    }
   
}
