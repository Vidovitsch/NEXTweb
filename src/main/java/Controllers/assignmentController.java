/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.DBAssignmentModifier;
import Database.DBGroupModifier;
import Database.IModAssignment;
import Database.IModGroup;
import Models.AssignmentModel;
import Models.LoginModel;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This is the controller used for the assignment screen
 * @author Youri van der Ceelen
 */
@Controller
public class assignmentController {

    private IModGroup groupDB = new DBGroupModifier();
    private IModAssignment dbAssignment = new DBAssignmentModifier();

    /**
     * This method is used to load the standard assignment screen
     * @param request
     * @return modelAndView
     */
    @RequestMapping(value = "/assignments", method = RequestMethod.GET)
    public ModelAndView AssignmentRequest(HttpServletRequest request) {
        ModelAndView modelView = new ModelAndView("assignments");

        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username")) {
                        String uid = groupDB.getUid(cookie.getValue());
                        modelView.addObject("userUID", uid);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return modelView;
    }

    /**
     * Once a user tries to submit an assignment this method is called.
     * the AssignmentModel that is passed to this method contains the information
     * that the user has entered and is used to call the database method for storing
     * the submission
     * @param request
     * @param model
     * @return new ModelAndView("assigments")
     */
    @RequestMapping(value = "/submitassignment", method = RequestMethod.POST)
    public ModelAndView SubmitAssignment(HttpServletRequest request, AssignmentModel model) {
        ModelAndView modelView = new ModelAndView("assignments");
        String uid = model.getUid();
        String link = model.getAssignmentlink();
        String name = model.getName();
        String email = model.getEmail();
        dbAssignment.insertSubmission(uid, link, name, email);
        modelView.addObject("message", "Submission succesful!");
        return modelView;
    }
}
