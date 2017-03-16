/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Models.Group;
import Models.Message;
import Models.User;
import com.firebase.client.Firebase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author David
 */
public class DBGroupModifier implements IModGroup {
    
    private Firebase firebase;
    private Object lock;
    private boolean done = false;
    
    public DBGroupModifier() {
        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (Firebase) connector.getConnectionObject();
    }

    @Override
    public void insertGroup(Group group) {
        //Adding a group to firebase
        Map<String, String> data = new HashMap();
        data.put("Name", group.getGroupName());
        Firebase ref = firebase.child("Group").child(String.valueOf(group.getGroupNumber()));
        ref.setValue(data);
        
        //Adding users to the group
        Firebase fb;
        for (User user : group.getUsers()) {
            fb = ref.child("Members").child(user.getPcn());
            fb.setValue("Attending");
        }
        
        //Adding messages to the group
        Firebase fb2;
        Map<String, String> msgData = new HashMap();
        for (Message message : group.getMessages()) {
            msgData.put("pcn", message.getPcn());
            msgData.put("group", String.valueOf(message.getGroupNumber()));
            msgData.put("content", message.getContent());
            fb2 = ref.child("Messages").child(String.valueOf(message.getDate()));
            fb2.setValue(msgData);
        }
    }

    @Override
    public void addUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Group getGroup(String pcn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertMessage(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Message> getMessages(Group group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
