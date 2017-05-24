/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.DBUserModifier;
import Database.IModUser;
import Models.LoginModel;
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
public class loginController
{

    private IModUser dbUser = new DBUserModifier();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView LoginRequest()
    {
        return new ModelAndView("login", "command", new LoginModel());
    }

    @RequestMapping(value = "/requestlogin", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute("SpringWeb") LoginModel loginModel,
            ModelMap model)
    {
        model.addAttribute("currentemail", loginModel.getCurrentemail());
        return "loginresult";
    }

    @RequestMapping(value = "/requestregistration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("SpringWeb") LoginModel loginModel,
            ModelMap model)
    {
        model.addAttribute("currentemail", loginModel.getCurrentemail());
        return "registeruser";
    }

    @RequestMapping(value = "/loggedin", method = RequestMethod.POST)
    public String loggedIn(@ModelAttribute("SpringWeb") LoginModel loginModel,
            ModelMap model)
    {
        model.addAttribute("currentemail", loginModel.getCurrentemail());
        return "index";
    }
}
