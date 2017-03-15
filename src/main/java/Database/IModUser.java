/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Models.User;

/**
 *
 * @author David
 */
public interface IModUser {
    
    void insertUser(User user);
    
    void removeUser(User user);
    
    User getUser(String pcn);
}
