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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Bert
 */
@Controller
public class messageboardController {
    
    private IModGroup groupDB = new DBGroupModifier();
    private Group group;
    
    @RequestMapping(value = "/messageboard", method = RequestMethod.GET)
    public ModelAndView initWorkshopmessageboardScreen(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("messageboard");
        
        System.out.println(request.getParameter("userpcn"));
        group = groupDB.getGroup(request.getParameter("userpcn"));
        model.addObject("group", group.getGroupName());
        model.addObject("messages", getMessages());
        model.addObject("user", "Gerard");
        return model;
    } 
    
    public List<Message> getMessages()
    {
        return group.getMessages();
    }
}
