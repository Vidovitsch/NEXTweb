/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Group;
import Models.Message;
import Models.User;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public interface IModGroup {
    
    void addUser(Group group, User user);
    
    void removeUser(Group group, User user);
            
    void insertGroup(Group group);
    
    Group getGroup(String pcn);
    
    void addMessage(Message message);
    
    ArrayList<Message> getMessages(Group group);
}
