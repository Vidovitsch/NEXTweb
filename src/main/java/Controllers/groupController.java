/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Enums.UserRole;
import Enums.UserStatus;
import Models.Group;
import Models.User;
import java.util.ArrayList;
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
    private Group group;
    
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public ModelAndView initGroupScreen()
    {
        ModelAndView modelView = new ModelAndView("group");
        insertDummyUsers();
        modelView.addObject("group", group);
        modelView.addObject("users", group.getUsers());
        return modelView;
    }
    
    private void insertDummyUsers()
    {
        group = new Group(1);
        group.setGroupName("TestGroup");
        
        User u = new User("i329413");
        u.setEmail("michael.janssen@student.fontys.nl");
        u.setImage("http://www.apicius.es/wp-content/uploads/2012/07/IMG-20120714-009211.jpg");
        u.setName("Michael Janssen");
        u.setUserRole(UserRole.Student);
        u.setUserStatus(UserStatus.Attending);
        
        User u2 = new User("i291337");
        u2.setEmail("leet.mofo@student.fontys.nl");
        u2.setImage("http://www.apicius.es/wp-content/uploads/2012/07/IMG-20120714-009211.jpg");
        u2.setName("Leet Mofo");
        u2.setUserRole(UserRole.Student);
        u2.setUserStatus(UserStatus.Attending);
        
        ArrayList<User> users = new ArrayList();
        users.add(u);
        users.add(u2);
        
        group.setUsers(users);
    }
}
