/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeltests;

import Enums.EventType;
import Models.EventDay;
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
public class EventDayTest {
    private EventDay testDay;
    
    public EventDayTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testDay = new EventDay("test Day");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testConstructor(){
        assertEquals("The constructor did not assign the propper value to EventName", "test Day", testDay.getEventName());
    }
    
    @Test
    public void testConstructorTwo(){
        testDay = new EventDay("second test");
        assertEquals("The constructor did not assign the propper value to EventName", "second test", testDay.getEventName());
    }

    @Test
    public void testDescription(){
        testDay.setDescription("first test");
        assertEquals("The setDescription method did not assign the propper value to Description", "first test", testDay.getDescription());
    }
    
    @Test
    public void testDescriptionTwo(){
        testDay.setDescription("secondtest");
        assertEquals("The setDescription method did not assign the propper value to Description", "secondtest", testDay.getDescription());
    }
    
    @Test
    public void testGetEventType(){
        assertEquals("The eventType was not propperly returened", testDay.getEventType(), EventType.None);
    }
}
