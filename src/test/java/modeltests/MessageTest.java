/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeltests;

import Models.Message;
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
public class MessageTest {
    private Message testMessage;
    
    public MessageTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testMessage = new Message("testPCN", 1, "test message", "1-12-2017");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor(){
        assertEquals("the constructor did not assign the propper value to pcn", "testPCN", testMessage.getPcn());
        assertEquals("the constructor did not assign the propper value to groupNumber", 1, testMessage.getGroupNumber());
        assertEquals("the constructor did not assign the propper value to content", "test message", testMessage.getContent());
        assertEquals("the constructor did not assign the propper value to date", "1-12-2017", testMessage.getDate());
    }
    
    @Test
    public void testConstructorTwo(){
        testMessage = new Message("test PCN", 3, "test message for the second test", "30-10-2017");
        assertEquals("the constructor did not assign the propper value to pcn", "test PCN", testMessage.getPcn());
        assertEquals("the constructor did not assign the propper value to groupNumber", 3, testMessage.getGroupNumber());
        assertEquals("the constructor did not assign the propper value to content", "test message for the second test", testMessage.getContent());
        assertEquals("the constructor did not assign the propper value to date", "30-10-2017", testMessage.getDate());
    }
    
    @Test
    public void testGroupNumber(){
        testMessage.setGroupNumber(1);
        assertEquals("The setGroupNumber method did not assign the propper value to GroupNumber", 1, testMessage.getGroupNumber());
    }
    
    @Test
    public void testGroupNumberTwo(){
        testMessage.setGroupNumber(4);
        assertEquals("The setGroupNumber method did not assign the propper value to GroupNumber", 4, testMessage.getGroupNumber());
    }
    
    @Test
    public void testPCN(){
        testMessage.setPcn("first test");
        assertEquals("The setPCN method did not assign the propper value to PCN", "first test", testMessage.getPcn());
    }
    
    @Test
    public void testPCNTwo(){
        testMessage.setPcn("secondtest");
        assertEquals("The setPCN method did not assign the propper value to PCN", "secondtest", testMessage.getPcn());
    }
    
    @Test
    public void testContent(){
        testMessage.setContent("first test");
        assertEquals("The setContent method did not assign the propper value to Content", "first test", testMessage.getContent());
    }
    
    @Test
    public void testContentTwo(){
        testMessage.setContent("secondtest");
        assertEquals("The setContent method did not assign the propper value to Content", "secondtest", testMessage.getContent());
    }
    
    @Test
    public void testDate(){
        testMessage.setDate("1-1-2018");
        assertEquals("The setDate method did not assign the propper value to Date", "1-1-2018", testMessage.getDate());
    }

    @Test
    public void testDateTwo(){
        testMessage.setDate("30-12-2018");
        assertEquals("The setDate method did not assign the propper value to Date", "30-12-2018", testMessage.getDate());
    }
	
    @Test(expected = IllegalArgumentException.class)
    public void testDateThree(){
        testMessage.setDate("50-12-2018");
	fail("an invallid argument exception should have been trown for having a invalid day");
    }
	
    @Test(expected = IllegalArgumentException.class)
    public void testDateFour(){
        testMessage.setDate("30-13-2018");
	fail("an invallid argument exception should have been trown for having a invalid month");
    }
	
    @Test(expected = IllegalArgumentException.class)
    public void testDateFive(){
        testMessage.setDate("30-12-20185");
	fail("an invallid argument exception should have been trown for having a invalid year");
    }
    
    
}
