package com.nextweek.nextweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author David
 */
@Controller
@RequestMapping("/welcome")
public class welcomeController {

    public String printWelcome(ModelMap model) {
        System.out.println("out");
        return "welcome";
    }
}
