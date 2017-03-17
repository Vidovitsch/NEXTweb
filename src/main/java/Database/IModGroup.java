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
    
    /**
     * Adds a member to a group
     * 
     * @param group
     * @param user 
     */
    void addUser(Group group, User user);
    
    /**
     * Removes a member from a group
     * 
     * @param group
     * @param user 
     */
    void removeUser(Group group, User user);
            
    /**
     * Creates a new group in Firbase
     * 
     * @param group 
     */
    void insertGroup(Group group);
    
    /**
     * Gets the group of a member's pcn
     * 
     * @param pcn
     * @return The member's group
     */
    Group getGroup(String pcn);
    
    /**
     * Adds a message to a group
     * 
     * @param message 
     */
    void addMessage(Message message);
    
    /**
     * Fetch all groups from firebase
     * 
     * @return All groups
     */
    ArrayList<Group> getGroups();
    
    /**
     * Getting max group number
     * 
     * @return Max group number
     */
    int getMaxGroupNumber();
}
