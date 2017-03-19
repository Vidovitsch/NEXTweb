/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeltests;

import Enums.EventType;
import Models.Lecture;
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
public class LectureTest {
    private Lecture testLecture;
    
    public LectureTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testLecture = new Lecture("test Lecture");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testConstructor(){
        assertEquals("The constructor did not assign the propper value to EventName", "test Lecture", testLecture.getEventName());
    }
    
    @Test
    public void testConstructorTwo(){
        testLecture = new Lecture("second test");
        assertEquals("The constructor did not assign the propper value to EventName", "second test", testLecture.getEventName());
    }

    @Test
    public void testPresenter(){
        testLecture.setPresenter("first test");
        assertEquals("The setPresenter method did not assign the propper value to Presenter", "first test", testLecture.getPresenter());
    }
    
    @Test
    public void testPresenterTwo(){
        testLecture.setPresenter("secondtest");
        assertEquals("The setPresenter method did not assign the propper value to Presenter", "secondtest", testLecture.getPresenter());
    }
    
    @Test
    public void testGetEventType(){
        testLecture.setEventType(EventType.Lecture);
        assertEquals("The eventType was not propperly returened", testLecture.getEventType(), EventType.Lecture);
    }
}
