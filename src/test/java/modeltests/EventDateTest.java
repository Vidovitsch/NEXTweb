/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeltests;

import Models.EventDate;
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
public class EventDateTest {
    private EventDate testDate;
    
    public EventDateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testDate = new EventDay("testDate");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor(){
        assertEquals("The constructor did not assign the propper value to EventName", "testDate", testDate.getEventName());
    }
    
    @Test
    public void testConstructorTwo(){
        testDate = new EventDay("second test");
        assertEquals("The constructor did not assign the propper value to EventName", "second test", testDate.getEventName());
    }
    
    @Test
    public void testLocationName(){
        testDate.setLocationName("first test");
        assertEquals("The setLocationName method did not assign the propper value to locationName", "first test", testDate.getLocationName());
    }
    
    @Test
    public void testLocationNameTwo(){
        testDate.setLocationName("secondtest");
        assertEquals("The setLocationName method did not assign the propper value to locationName", "secondtest", testDate.getLocationName());
    }
    
    @Test
    public void testStartTime(){
        testDate.setStartTime("8:23");
        assertEquals("The setStartTime method did not assign the propper value to StartTime", "8:23", testDate.getStartTime());
    }
    
    @Test
    public void testStartTimeTwo(){
        testDate.setStartTime("20:30");
        assertEquals("The setStartTime method did not assign the propper value to StartTime", "20:30", testDate.getStartTime());
    }
	
    @Test(expected = IllegalArgumentException.class)
    public void testStartTimeThree(){
        testDate.setStartTime("string");
        fail("an exception should have been trown with an invalid time");
    }
	
    @Test(expected = AssertionError.class)
    public void testStartTimeFour(){
        testDate.setStartTime("26:30");
        fail("an exception should have been trown with an invalid time");
    }
    
    @Test
    public void testEndTime(){
        testDate.setEndTime("8:23");
        assertEquals("The setEndTime method did not assign the propper value to EndTime", "8:23", testDate.getEndTime());
    }
    
    @Test
    public void testEndTimeTwo(){
        testDate.setEndTime("20:30");
        assertEquals("The setEndTime method did not assign the propper value to EndTime", "20:30", testDate.getEndTime());
    }
	
    @Test(expected = IllegalArgumentException.class)
    public void testEndTimeThree(){
        testDate.setEndTime("string");
        fail("an exception should have been trown with an invalid time");
    }
	
    @Test(expected = AssertionError.class)
    public void testEndTimeFour(){
        testDate.setEndTime("26:30");
        fail("an exception should have been trown with an invalid time");
    }
    
    @Test
    public void testEventName(){
        testDate.setEventName("first test");
        assertEquals("The setEventName method did not assign the propper value to EventName", "first test", testDate.getEventName());
    }
    
    @Test
    public void testEventNameTwo(){
        testDate.setEventName("secondtest");
        assertEquals("The setEventName method did not assign the propper value to EventName", "secondtest", testDate.getEventName());
    }
    
    @Test
    public void testDate(){
        testDate.setDate("1-1-2018");
        assertEquals("The setDate method did not assign the propper value to Date", "1-1-2018", testDate.getDate());
    }

    @Test
    public void testDateTwo(){
        testDate.setDate("30-12-2018");
        assertEquals("The setDate method did not assign the propper value to Date", "30-12-2018", testDate.getDate());
    }
	
    @Test(expected = AssertionError.class)
    public void testDateThree(){
        testDate.setDate("50-12-2018");
	fail("an invallid argument exception should have been trown for having a invalid day");
    }
	
    @Test(expected = AssertionError.class)
    public void testDateFour(){
        testDate.setDate("30-13-2018");
	fail("an invallid argument exception should have been trown for having a invalid month");
    }
	
    @Test(expected = AssertionError.class)
    public void testDateFive(){
        testDate.setDate("30-12-20185");
	fail("an invallid argument exception should have been trown for having a invalid year");
    }
        
    @Test
    public void testDay(){
        testDate.setDate("31-12-2018");
        assertEquals("The Day was not properly calculated", "Monday", testDate.getDay());
    }

    @Test
    public void testDayTwo(){
        testDate.setDate("30-12-2018");
        assertEquals("The Day was not properly calculated", "Sunday", testDate.getDay());
    }
    
    @Test
    public void testDayThree(){
        testDate.setDate("29-12-2018");
        assertEquals("The Day was not properly calculated", "Saturday", testDate.getDay());
    }
    
    @Test
    public void testDayFour(){
        testDate.setDate("28-12-2018");
        assertEquals("The Day was not properly calculated", "Friday", testDate.getDay());
    }
    
    @Test
    public void testDayFive(){
        testDate.setDate("27-12-2018");
        assertEquals("The Day was not properly calculated", "Thursday", testDate.getDay());
    }
    
    @Test
    public void testDaySix(){
        testDate.setDate("26-12-2018");
        assertEquals("The Day was not properly calculated", "Wednesday", testDate.getDay());
    }
    
    @Test
    public void testDaySeven(){
        testDate.setDate("25-12-2018");
        assertEquals("The Day was not properly calculated", "Tuesday", testDate.getDay());
    }
    
    @Test
    public void testDescription(){
        testDate.setDescription("first test");
        assertEquals("The setDescription method did not assign the propper value to Description", "first test", testDay.getDescription());
    }
    
    @Test
    public void testDescriptionTwo(){
        testDate.setDescription("secondtest");
        assertEquals("The setDescription method did not assign the propper value to Description", "secondtest", testDay.getDescription());
    }
}
