/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Enums.Course;
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
    
    //Moet eigenlijk via de database
    private void insertDummyUsers()
    {
        group = new Group(0);
        group.setGroupName("TestGroup");
        
        User u2 = new User("JuHFVbEaTjYuHXpROGc71lSFsg23");
        u2.setEmail("leet.mofo@student.fontys.nl");
        u2.setName("Leet Mofo");
        u2.setUserRole(UserRole.Student);
        u2.setUserStatus(UserStatus.Attending);
        u2.setCourse(Course.Software_Engineering);
        u2.setSemester(4);
        
        ArrayList<User> users = new ArrayList();
        users.add(u2);
        
        group.setUsers(users);
    }
}
