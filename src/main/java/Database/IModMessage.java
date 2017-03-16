/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import Models.Group;
import Models.Message;



/**
 *
 * @author David
 */
public interface IModMessage {
    
    void insertMessage(Message message);
    
    void getMessages(Group group);
}
