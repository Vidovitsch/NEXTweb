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
 * This controller is used by the login screen
 * @author David
 */
@Controller
public class loginController {

    private IModUser dbUser = new DBUserModifier();

    /**
     * This method is used to load the default login screen
     * it passes an empty LoginModel which the user can use to sent
     * information to the controller
     * @return new ModelAndView("login", "command", new LoginModel())
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView LoginRequest() {
        return new ModelAndView("login", "command", new LoginModel());
    }

    /**
     * This method is used when a user tries to sign in. It has a LoginModel
     * which contains the data the user enters.
     * It also has a model in which it can enter the users curent email
     * @param loginModel
     * @param model
     * @return "loginresult"
     */
    @RequestMapping(value = "/requestlogin", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute("SpringWeb") LoginModel loginModel,
            ModelMap model) {
        model.addAttribute("currentemail", loginModel.getCurrentemail());
        return "loginresult";
    }

    /**
     * This method is called when a user registers for the application.
     * it takes a LoginModel containing the information that the user entered
     * in the login screen and a ModelMap to which it adds the entered email
     * @param loginModel
     * @param model
     * @return "registeruser"
     */
    @RequestMapping(value = "/requestregistration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("SpringWeb") LoginModel loginModel,
            ModelMap model) {
        model.addAttribute("currentemail", loginModel.getCurrentemail());
        return "registeruser";
    }

    /**
     * upon succesfully signing in in the login screen this method is called
     * it takes a LoginModel containing the entered data in the GUI and a 
     * ModelMap to which it adds the entered email.
     * @param loginModel
     * @param model
     * @return "home"
     */
    @RequestMapping(value = "/loggedin", method = RequestMethod.POST)
    public String loggedIn(@ModelAttribute("SpringWeb") LoginModel loginModel,
            ModelMap model) {
        model.addAttribute("currentemail", loginModel.getCurrentemail());
        return "home";
    }
}
