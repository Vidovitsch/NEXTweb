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
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
                        String email = cookie.getValue();
                        model.addObject("userUID", groupDB.getUid(email));
                        group = groupDB.getGroup(groupDB.getUid(email));
                        model.addObject("group", group.getGroupName());
                        List<Message> messages = group.getMessages();
                        messages = groupDB.addNamesToMessages(messages);
                        model.addObject("messages", messages);                     
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
    
    
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public void postMessage(@RequestParam String message) {
        System.out.println("owiej");
        //groupDB.addMessage(new Message("","", 1, message, ""));
    }
    
    
}
