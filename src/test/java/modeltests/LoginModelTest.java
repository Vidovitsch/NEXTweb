/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeltests;

import Models.LoginModel;
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
public class LoginModelTest {
    private LoginModel testLoginModel;
    
    public LoginModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testLoginModel = new LoginModel();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testEmail(){
        testLoginModel.setCurrentemail("first test");
        assertEquals("The setEmail method did not assign the propper value to Email", "first test", testLoginModel.getCurrentemail());
    }
    
    @Test
    public void testEmailTwo(){
        testLoginModel.setCurrentemail("secondtest");
        assertEquals("The setEmail method did not assign the propper value to Email", "secondtest", testLoginModel.getCurrentemail());
    }
}
