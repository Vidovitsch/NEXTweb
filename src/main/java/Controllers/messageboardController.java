/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Models.Message;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

/**
 *
 * @author Bert
 */
@Controller
public class messageboardController {
    
    @RequestMapping(value = "/messageboard", method = RequestMethod.GET)
    public ModelAndView initWorkshopmessageboardScreen() {
        ModelAndView model = new ModelAndView("messageboard");
        
        model.addObject("group", "BananenBoot (1)");
        model.addObject("messages", getMessages());
        model.addObject("user", "Gerard");
        return model;
    } 
    
    public List<Message> getMessages()
    {
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message("Gerard", "wyd Gertie?", Timestamp.valueOf("2017-03-13 14:10:01")));
        messages.add(new Message("Gertie", "yes", Timestamp.valueOf("2017-03-13 14:50:38")));
        return messages;
    }
}
