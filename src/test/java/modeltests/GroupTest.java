/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeltests;

import Models.Group;
import Models.Message;
import Models.User;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Arno Dekkers Los
 */
public class GroupTest {
    private Group testGroup;
    
    public GroupTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testGroup = new Group(1);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGroupConstructor(){
        assertEquals("the group number was not propperly assined", testGroup.getGroupNumber(), 1);
    }
    
    @Test
    public void testGroupConstructorTwo(){
        testGroup = new Group(99);
        assertEquals("the group number was not propperly assined", testGroup.getGroupNumber(), 99);
    }
    
    @Test
    public void testGroupName(){
        testGroup.setGroupName("first test");
        assertEquals("The setGroupName method did not assign the propper value to GroupName", "first test", testGroup.getGroupName());
    }
    
    @Test
    public void testGroupNameTwo(){
        testGroup.setGroupName("secondtest");
        assertEquals("The setGroupName method did not assign the propper value to GroupName", "secondtest", testGroup.getGroupName());
    }
    
    @Test
    public void testGroupNumber(){
        testGroup.setGroupNumber(4);
        assertEquals("The setGroupNumber method did not assign the propper value to GroupNumber", 4, testGroup.getGroupNumber());
    }
    
    @Test
    public void testGroupNumberTwo(){
        testGroup.setGroupNumber(1);
        assertEquals("The setGroupNumber method did not assign the propper value to GroupNumber", 1, testGroup.getGroupNumber());
    }
    
    @Test
    public void testUSers(){
        ArrayList<User> testList = new ArrayList<User>();
        testList.add(new User("testUser1"));
        testGroup.setUsers(testList);
        assertEquals("The setUsers method did not assign the propper value to users", testList, testGroup.getUsers());
    }
    
    @Test
    public void testGroupUsersTwo(){
        ArrayList<User> testList = new ArrayList<User>();
        testList.add(new User("testUser1"));
        testList.add(new User("henry"));
        testList.add(new User("bart"));
        testList.add(new User("hans wijnen"));
        testList.add(new User("Moniek Moriarty"));
        testList.add(new User("een hele lange naam"));
        testGroup.setUsers(testList);
        assertEquals("The setUsers method did not assign the propper value to users", testList, testGroup.getUsers());
    }  
    
    @Test
    public void testMessage(){
        ArrayList<Message> testList = new ArrayList<Message>();
        testList.add(new Message("testPCN", "naam", 1, "test message", "1-12-2017"));
        testGroup.setMessages(testList);
        assertEquals("The setMessage method did not assign the propper value to Message", testList, testGroup.getMessages());
    }
    
    @Test
    public void testGroupMessageTwo(){
        ArrayList<Message> testList = new ArrayList<Message>();
        testList.add(new Message("testPCN", "naam", 1, "test message", "1-12-2017"));
        testList.add(new Message("testPCN2", "naam", 1, "test message with content", "1-12-2017"));
        testList.add(new Message("testPCN", "naam", 1, "test message with some more content", "1-12-2017"));
        testList.add(new Message("testPCN3", "naam", 1, "test message", "1-12-2017"));
        testGroup.setMessages(testList);
        assertEquals("The setMessage method did not assign the propper value to Message", testList, testGroup.getMessages());
    }   
}
