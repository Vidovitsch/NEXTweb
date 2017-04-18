/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.DBGroupModifier;
import Database.IModGroup;
import Enums.Course;
import Enums.UserRole;
import Enums.UserStatus;
import Models.Group;
import Models.User;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author mmjan
 */
@Controller
public class groupController
{
    private final static int MAXGROUPSIZE = 10;
    private IModGroup groupDB = new DBGroupModifier();
    private Group group;
    
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public ModelAndView initGroupScreen(HttpServletRequest request)
    {
        ModelAndView modelView = new ModelAndView("group");
        
        try
        {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username"))
                    {
                        String uid = groupDB.getUid(cookie.getValue());
                        modelView.addObject("userUID", uid);
                        group = groupDB.getGroup(uid);
                        modelView.addObject("group", group);
                    }
                }
            }       
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        } 
        return modelView;
    }

}
