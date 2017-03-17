/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.DBGroupModifier;
import Database.DBUserModifier;
import Database.IModGroup;
import Database.IModUser;
import Enums.UserRole;
import Enums.UserStatus;
import Models.Group;
import Models.LoginRequest;
import Models.Message;
import Models.User;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author David
 */
@Controller
public class loginController {
    private IModUser dbUser = new DBUserModifier();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView LoginRequest() {
        return new ModelAndView("login", "command", new LoginRequest());
    }

    @RequestMapping(value = "/requestlogin", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute("SpringWeb") LoginRequest loginRequest,
            ModelMap model) {
        String result = dbUser.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        model.addAttribute("result", result);
        return "loginresult";
    }
}
