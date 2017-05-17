/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.User;

/**
 *
 * @author Youri van der Ceelen
 */
public interface IModAssignment {

    /**
     * Adds a new assignment submission into Firebase
     *
     * @param user
     */
    void insertSubmission(String uid, String link, String name);
}
