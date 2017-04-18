/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Database.DBGroupModifier;
import Database.IModGroup;
import Models.Group;
import Models.Message;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bert
 */
@Controller
public class messageboardController{
    
    private IModGroup groupDB = new DBGroupModifier();
    private Group group;
    
    @RequestMapping(value = "/messageboard", method = RequestMethod.GET)
    public ModelAndView initWorkshopmessageboardScreen(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("messageboard");
        try
        {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username"))
                    {
                        String uid = groupDB.getUid(cookie.getValue());
                        model.addObject("userUID", uid);
                        group = groupDB.getGroup(uid);
                        model.addObject("group", group.getGroupName());
                    }
                }
            }       
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }        
        return model;
    }
}
