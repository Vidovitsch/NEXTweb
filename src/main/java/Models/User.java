/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

import Enums.UserRole;
import Enums.UserStatus;


/**
 *
 * @author David
 */
public class User {

    private String pcn;
    private String name;
    private String email;
    private String image;
    private UserRole userRole;
    private UserStatus userStatus;

    public User(String pcn) {
        this.pcn = pcn;
    }
        
    /**
     * Get the value of pcn
     *
     * @return the value of pcn
     */
    public String getPcn() {
        return pcn;
    }

    /**
     * Set the value of pcn
     *
     * @param pcn new value of pcn
     */
    public void setPcn(String pcn) {
        this.pcn = pcn;
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
    
    /**
     * Get the value of image
     *
     * @return the value of image
     */
    public String getImage() {
        return image;
    }

    /**
     * Set the value of image
     *
     * @param image new value of image
     */
    public void setImage(String image) {
        this.image = image;
    }
    
    /**
     * Get the value of userRole
     *
     * @return the value of userRole
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Set the value of userRole
     *
     * @param userRole new value of userRole
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    
    /**
     * Get the value of userStatus
     *
     * @return the value of userStatus
     */
    public UserStatus getUserStatus() {
        return userStatus;
    }

    /**
     * Set the value of userStatus
     *
     * @param userStatus new value of userStatus
     */
    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
