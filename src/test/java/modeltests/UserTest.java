/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeltests;

import Enums.Course;
import Enums.UserRole;
import Enums.UserStatus;
import Models.User;
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
public class UserTest {
    private User testUser;
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testUser = new User("test PCN");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor(){
        assertEquals("The constructor did not assign the propper value to pcn", "test PCN", testUser.getUid());
    }
    
    @Test
    public void testConstructorTwo(){
        testUser = new User("second test");
        assertEquals("The constructor did not assign the propper value to pcn", "second test", testUser.getUid());
    }
    
    @Test
    public void testUid(){
        testUser.setUid("first test");
        assertEquals("The setUid method did not assign the propper value to Uid", "first test", testUser.getUid());
    }
    
    @Test
    public void testUidTwo(){
        testUser.setUid("secondtest");
        assertEquals("The setUid method did not assign the propper value to Uid", "secondtest", testUser.getUid());
    }
    
    @Test
    public void testName(){
        testUser.setName("first test");
        assertEquals("The setName method did not assign the propper value to Name", "first test", testUser.getName());
    }
    
    @Test
    public void testNameTwo(){
        testUser.setName("secondtest");
        assertEquals("The setName method did not assign the propper value to Name", "secondtest", testUser.getName());
    }
    
    @Test
    public void testemail(){
        testUser.setEmail("first test");
        assertEquals("The setemail method did not assign the propper value to email", "first test", testUser.getEmail());
    }
    
    @Test
    public void testemailTwo(){
        testUser.setEmail("secondtest");
        assertEquals("The setemail method did not assign the propper value to email", "secondtest", testUser.getEmail());
    }
    
    @Test
    public void testUserRole(){
        testUser.setUserRole(UserRole.Student);
        assertEquals("The setUserRole method did not assign the propper value to UserRole", UserRole.Student, testUser.getUserRole());
    }
    
    @Test
    public void testUserRoleTwo(){
        testUser.setUserRole(UserRole.Teacher);
        assertEquals("The setUserRole method did not assign the propper value to UserRole", UserRole.Teacher, testUser.getUserRole());
    }
    
    @Test
    public void testUserRoleThree(){
        testUser.setUserRole(UserRole.Other);
        assertEquals("The setUserRole method did not assign the propper value to UserRole", UserRole.Other, testUser.getUserRole());
    }
    
    @Test
    public void testUserStatus(){
        testUser.setUserStatus(UserStatus.Attending);
        assertEquals("The setUserRole method did not assign the propper value to UserStatus", UserStatus.Attending, testUser.getUserStatus());
    }
    
    @Test
    public void testUserStatusTwo(){
        testUser.setUserStatus(UserStatus.Canceled);
        assertEquals("The setUserRole method did not assign the propper value to UserStatus", UserStatus.Canceled, testUser.getUserStatus());
    }
    
    @Test
    public void testCourse(){
        testUser.setCourse(Course.Business);
        assertEquals("The setCourse method did not assign the propper value to course", Course.Business, testUser.getCourse());
    }
    
    @Test
    public void testCourseTwo(){
        testUser.setCourse(Course.Media_Design);
        assertEquals("The setCourse method did not assign the propper value to course", Course.Media_Design, testUser.getCourse());
    }
    
    @Test
    public void testCourseThree(){
        testUser.setCourse(Course.Software_Engineering);
        assertEquals("The setCourse method did not assign the propper value to course", Course.Software_Engineering, testUser.getCourse());
    }
    
    @Test
    public void testCourseFour(){
        testUser.setCourse(Course.Techniek);
        assertEquals("The setCourse method did not assign the propper value to course", Course.Techniek, testUser.getCourse());
    }
    
    @Test
    public void testSemester(){
        testUser.setSemester(1);
        assertEquals("The setSemester method did not assign the propper value to Semester", 1, testUser.getSemester());
    }
    
    @Test
    public void testSemesterTwo(){
        testUser.setSemester(4);
        assertEquals("The setSemester method did not assign the propper value to Semester", 4, testUser.getSemester());
    }
    
    /*@Test(expected = IllegalArgumentException.class)
    public void testSemesterThree(){
        testUser.setSemester(9);
        fail("There is no 9th Semester");
    }*/
}
