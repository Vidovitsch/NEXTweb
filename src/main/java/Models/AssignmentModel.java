/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 * This model is used to pass data between the gui and business layer
 * @author Youri van der Ceelen
 */
public class AssignmentModel {

    private String uid;
    private String name;
    private String assignmentlink;
    private String email;

    /**
     * Get the value of uid
     *
     * @return the value of uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * Set the value of uid
     *
     * @param uid new value of uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Get the value of assignmentlink
     *
     * @return the value of assignmentlink
     */
    public String getAssignmentlink() {
        return assignmentlink;
    }

    /**
     * Set the value of assignmentlink
     *
     * @param assignmentlink new value of assignmentlink
     */
    public void setAssignmentlink(String assignmentlink) {
        this.assignmentlink = assignmentlink;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
