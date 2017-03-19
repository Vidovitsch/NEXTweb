/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeltests;

import Enums.EventType;
import Models.Performance;
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
public class PerformanceTest {
    private Performance testPerformance;
    
    public PerformanceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
            testPerformance = new Performance("test performance");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor(){
        assertEquals("The constructor did not assign the propper value to EventName", "test performance", testPerformance.getEventName());
    }
    
    @Test
    public void testConstructorTwo(){
        testPerformance = new Performance("second test");
        assertEquals("The constructor did not assign the propper value to EventName", "second test", testPerformance.getEventName());
    }
    
    @Test
    public void testGetEventType(){
        testPerformance.setEventType(EventType.Performance);
        assertEquals("The eventType was not propperly returened", testPerformance.getEventType(), EventType.Performance);
    }
}
