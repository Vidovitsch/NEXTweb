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
public class loginController
{

    private IModUser dbUser = new DBUserModifier();
    
    //Test
    private IModGroup dbGroup = new DBGroupModifier();
    User u1;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView LoginRequest()
    {
        dbGroup.insertGroup(createDummyGroup());
        return new ModelAndView("login", "command", new LoginRequest());
    }

    @RequestMapping(value = "/requestlogin", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute("SpringWeb") LoginRequest loginRequest,
            ModelMap model)
    {
        String result = dbUser.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        model.addAttribute("result", result);
        return "loginresult";
    }
    
    //Test method
    private Group createDummyGroup() {
        u1 = new User("234223");
        u1.setEmail("iets@iets.nl");
        u1.setName("Frank");
        u1.setUserRole(UserRole.Student);
        u1.setUserStatus(UserStatus.Attending);
        User u2 = new User("334223");
        u2.setEmail("niets@iets.nl");
        u2.setName("Henk");
        u2.setUserRole(UserRole.Student);
        u2.setUserStatus(UserStatus.Attending);
        
        Message m1 = new Message(u1.getPcn(), 1, "Hoi", "12-12-12:12:12");
        Message m2 = new Message(u2.getPcn(), 1, "Hoi", "12-12-12:12:13");
        
        ArrayList<User> users = new ArrayList();
        ArrayList<Message> messages = new ArrayList();
        users.add(u1);
        users.add(u2);
        messages.add(m1);
        messages.add(m2);
        
        Group group = new Group(1);
        group.setGroupName("AH appeltaart");
        group.setMessages(messages);
        group.setUsers(users);
        
        return group;
    }
}
