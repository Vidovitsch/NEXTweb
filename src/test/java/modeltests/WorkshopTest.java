/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeltests;

import Enums.EventType;
import Models.User;
import Models.Workshop;
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
public class WorkshopTest {
    private Workshop testWorkshop;
    
    public WorkshopTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testWorkshop = new Workshop("testWorkshop");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor(){
        assertEquals("The constructor did not assign the propper value to EventName", "testWorkshop", testWorkshop.getEventName());
    }
    
    @Test
    public void testConstructorTwo(){
        testWorkshop = new Workshop("second test");
        assertEquals("The constructor did not assign the propper value to EventName", "second test", testWorkshop.getEventName());
    }
    
    @Test
    public void testPresenter(){
        testWorkshop.setPresenter("first test");
        assertEquals("The setPresenter method Presenter not assign the propper value to Presenter", "first test", testWorkshop.getPresenter());
    }
    
    @Test
    public void testPresenterTwo(){
        testWorkshop.setPresenter("secondtest");
        assertEquals("The setPresenter method Presenter not assign the propper value to Presenter", "secondtest", testWorkshop.getPresenter());
    }
    
    @Test
    public void testGetEventType(){
        testWorkshop.setEventType(EventType.Workshop);
        assertEquals("The eventType was not propperly returened", testWorkshop.getEventType(), EventType.Workshop);
    }
    
    @Test
    public void testMaxUsers(){
        testWorkshop.setMaxUsers(1);
        assertEquals("The setMaxUsers method did not assign the propper value to maxUsers", 1, testWorkshop.getMaxUsers());
    }
    
    @Test
    public void testMaxUsersTwo(){
        testWorkshop.setMaxUsers(4);
        assertEquals("The setMaxUsers method did not assign the propper value to maxUsers", 4, testWorkshop.getMaxUsers());
    }
    
    @Test
    public void testUSers(){
        ArrayList<User> testList = new ArrayList<User>();
        testList.add(new User("testUser1"));
        testWorkshop.setUsers(testList);
        assertEquals("The setUsers method did not assign the propper value to users", testList, testWorkshop.getUsers());
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
        testWorkshop.setUsers(testList);
        assertEquals("The setUsers method did not assign the propper value to users", testList, testWorkshop.getUsers());
    }
}
