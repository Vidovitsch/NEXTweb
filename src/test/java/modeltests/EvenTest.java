/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeltests;

import Models.Event;
import Models.Workshop;
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
public class EvenTest {
    private Event testEvent;
    
    public EvenTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testEvent = new Workshop("Test Workshop");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetImageURL(){
        assertEquals("The standard url was not correct", testEvent.getImageURL(), "/images/default_workshop.jpg");
    }
    
    @Test
    public void testGetImageURLTwo(){
        testEvent.setImageURL("testURLOne");
        assertEquals("The SetImageURL method did not assign the propper value", testEvent.getImageURL(), "testURLOne");
    }
    
    @Test
    public void testGetImageURLThree(){
        testEvent.setImageURL("test URL two");
        assertEquals("The SetImageURL method did not assign the propper value", testEvent.getImageURL(), "test URL two");
    }
    
    @Test
    public void testId(){
        testEvent.setId("first test");
        assertEquals("The setId method dId not assign the propper value to Id", "first test", testEvent.getId());
    }
    
    @Test
    public void testIdTwo(){
        testEvent.setId("secondtest");
        assertEquals("The setId method dId not assign the propper value to Id", "secondtest", testEvent.getId());
    }
}
