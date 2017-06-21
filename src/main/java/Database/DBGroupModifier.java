/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Enums.Course;
import Models.Group;
import Models.Message;
import Models.User;
import Models.Utility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author David
 */
public class DBGroupModifier implements IModGroup {
    
    private DatabaseReference firebase;
    private Object lock;
    private boolean done = false;
    private Group group = null;
    private String uid = "";
    private String groupNumber = "";
    
    public DBGroupModifier() {
        FBConnector connector = FBConnector.getInstance();
        connector.connect();
        firebase = (DatabaseReference) connector.getConnectionObject();
    }

    @Override
    public void insertGroup(Group group) {
        //Adding a group to firebase
        Map<String, String> data = new HashMap();
        data.put("Name", group.getGroupName());
        data.put("Location", String.valueOf(group.getLocation()));
        DatabaseReference ref = firebase.child("Group").child(String.valueOf(group.getGroupNumber()));
        ref.setValue(data);
        
        //Adding users to the group
        DatabaseReference fb;
        for (User user : group.getUsers()) {
            fb = ref.child("Members").child(user.getUid());
            fb.setValue("NS");
        }
        
        //Adding messages to the group
        DatabaseReference fb2;
        Map<String, String> msgData = new HashMap();
        for (Message message : group.getMessages()) {
            msgData.put("uid", message.getUid());
            msgData.put("group", String.valueOf(message.getGroupNumber()));
            msgData.put("content", message.getContent());
            fb2 = ref.child("Messages").child(String.valueOf(message.getDate()));
            fb2.setValue(msgData);
        }
    }

    @Override
    public void addUser(Group group, User user) {
        DatabaseReference ref = firebase.child("Group").child(String.valueOf(group.getGroupNumber()))
                .child("Members").child(user.getUid());
        ref.setValue("NS");
    }

    @Override
    public void removeUser(Group group, User user) {
        DatabaseReference ref = firebase.child("Group").child(String.valueOf(group.getGroupNumber()))
                .child("Members").child(user.getUid());
        ref.removeValue();
    }
	

    @Override
    public Group getGroup(final String uid) {
        DatabaseReference refUser = firebase.child("User");
        refUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                groupNumber = String.valueOf(snapshot.child(uid).child("GroupID").getValue());
                Utility.unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError fe) {
                System.out.println(fe.toException().toString());
            }
        });
        
        Utility.lockFXThread();
        
        final List<String> uidLst = new ArrayList<String>();
        final ArrayList<User> members = new ArrayList<User>();
        if (!groupNumber.equals(""))
        {
            DatabaseReference refGroup = firebase.child("Group");
            refGroup.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    DataSnapshot ds = snapshot.child(groupNumber);
                    ArrayList<Message> messages = new ArrayList();

                    //Fetching other data
                    String groupNumber = (String) ds.getKey();
                    if (groupNumber.equals("-1"))
                    {
                        Utility.unlockFXThread();
                        return;
                    }
                    int location=  Integer.valueOf(String.valueOf(ds.child("Location").getValue()));
                    String groupName = (String) ds.child("Name").getValue();
                    
                    for (DataSnapshot ds2 : ds.child("Members").getChildren()) {
                        String uid = ds2.getKey();
                        uidLst.add(uid);
                    }

                    //Fetching messages
                    Message msg;
                    for (DataSnapshot ds2 : ds.child("Messages").getChildren()) {
                        String date = ds2.getKey();
                        String content = String.valueOf(ds2.child("Content").getValue());
                        String uid = String.valueOf(ds2.child("UID").getValue());
                        msg = new Message(uid, 0, content, date);
                        // TODO: change to get name instead of double uid
                        messages.add(msg);
                    }
                    group = new Group(groupNumber);
                    group.setGroupName(groupName);
                    group.setMessages(messages);
                    group.setLocation(location);
                    Utility.unlockFXThread();
                }

                @Override
                public void onCancelled(DatabaseError fe) {
                    System.out.println(fe.toException().toString());
                }
            });
        }
        Utility.lockFXThread();
        if (groupNumber.equals("-1"))
        {
            return null;
        }
        
        refUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (String uid : uidLst)
                {
                    DataSnapshot ss = snapshot.child(uid);
                    User u = new User(uid);
                    u.setEmail(String.valueOf(ss.child("Mail").getValue()));
                    u.setName(String.valueOf(ss.child("Name").getValue()) + " " + String.valueOf(ss.child("Lastname").getValue()));
                    u.setCourse(Course.getCourse((String.valueOf(ss.child("Course").getValue())).charAt(0)));
                    u.setSemester(Integer.valueOf(String.valueOf(ss.child("Semester").getValue())));
                    members.add(u);
                }
                Utility.unlockFXThread();
            }

            @Override
            public void onCancelled(DatabaseError fe) {
                System.out.println(fe.toException().toString());
            }
        });
        Utility.lockFXThread();
        
        group.setUsers(members);
        return group;
    }
    
    @Override
    public String getUid(final String email)
    {
        DatabaseReference ref = firebase.child("User");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    //Fetching members
                    String emailDB = (String) ds.child("Mail").getValue();
                    if (emailDB.equals(email)) {
                        uid = ds.getKey();
                    }
                }
                Utility.unlockFXThread();
            }
            
            @Override
            public void onCancelled(DatabaseError fe) {
                System.out.println(fe.toException().toString());
            }
        });
        Utility.lockFXThread();
        return uid;
    }
    
    @Override
    public List<Message> addNamesToMessages(final List<Message> messages) {
        DatabaseReference ref = firebase.child("User");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (Message msg : messages) 
                {
                    msg.setUserName(String.valueOf(snapshot.child(msg.getUid()).child("Name").getValue()));
                }
                Utility.unlockFXThread();
            }
            
            @Override
            public void onCancelled(DatabaseError fe) {
                System.out.println(fe.toException().toString());
            }
        });
        Utility.lockFXThread();
        return messages;
    }

    @Override
    public void addMessage(Message message) {
        DatabaseReference ref = firebase.child("Group").child(String.valueOf(message.getGroupNumber())).
                child("Messages").child(String.valueOf(message.getDate()));
        Map<String, String> msgData = new HashMap();
        msgData.put("uid", message.getUid());
        msgData.put("group", String.valueOf(message.getGroupNumber()));
        msgData.put("content", message.getContent());
        ref.setValue(msgData);
    }
    
    @Override
    public ArrayList<Group> getGroups() {
        final ArrayList<Group> groups = new ArrayList();
        DatabaseReference ref = firebase.child("Group");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Group g = null;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ArrayList<Message> messages = new ArrayList();
                    ArrayList<User> members = new ArrayList();
                    
                    //Fetching members
                    User u;
                    for (DataSnapshot ds3 : ds.child("Members").getChildren()) {
                        String uid = ds3.getKey();
                        u = new User(uid);
                        members.add(u);
                    }
                    
                    //Fetching other data
                    String groupNumber = (String) ds.getKey();
                    String groupName = (String) ds.child("Name").getValue();

                    //Fetching messages
                    Message msg;
                    for (DataSnapshot ds2 : ds.child("Messages").getChildren()) {
                        String date = ds2.getKey();
                        String content = (String) ds2.child("content").getValue();
                        int group = Integer.valueOf((String) ds2.child("group").getValue());
                        String uid = (String) ds2.child("UID").getValue();
                        // TODO: change to get name instead of double uid
                        msg = new Message(uid, uid, group, content, date);
                        messages.add(msg);
                    }

                    g = new Group(groupNumber);
                    g.setGroupName(groupName);
                    g.setMessages(messages);
                    g.setUsers(members);
                    
                    groups.add(g);
                }
                Utility.unlockFXThread();
            }
            
            @Override
            public void onCancelled(DatabaseError fe) {
                System.out.println(fe.toException().toString());
            }
        });
        
        Utility.lockFXThread();
        return groups;
    }
    
    @Override
    public int getMaxGroupNumber() {
        final Set<Integer> groupNumbers = new TreeSet();
        DatabaseReference ref = firebase.child("Group");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    int groupNumber = Integer.valueOf((String) ds.getKey());
                    groupNumbers.add(groupNumber);
                }
                Utility.unlockFXThread();
            }
            
            @Override
            public void onCancelled(DatabaseError fe) {
                System.out.println(fe.toException().toString());
            }
        });
        List<Integer> numbers = new ArrayList(groupNumbers);
        return numbers.get(numbers.size() - 1);
    }
}
