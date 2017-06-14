/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.DBGroupModifier;
import Database.IModGroup;
import Models.Group;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This is the controller associated with the group screen 
 * @author mmjan
 */
@Controller
public class groupController
{
    private final static int MAXGROUPSIZE = 10;
    private IModGroup groupDB = new DBGroupModifier();
    private Group group;
    
    /**
     * This method is used to load the default view of the group screen
     * it takes the current user from a cookie that is in the HtppServletRequest
     * it uses this to user to determine for which user it has to retrieve the group
     * data.
     * In the case that the user is not found or part of a group the index screen is
     * loaded instead of the group screen
     * @param request
     * @return modelAndView
     */
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
        if (group == null)
        {
            return new ModelAndView("index");
        }
        else
        {
            return modelView;
        }
    }

}
