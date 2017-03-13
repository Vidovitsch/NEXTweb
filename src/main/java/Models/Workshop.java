/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;


/**
 *
 * @author David
 */
public class Workshop extends Event {

    private int maxUsers = 0;
    
    public Workshop(String eventName) {
        super(eventName);
    }

    /**
     * Get the value of maxUsers
     *
     * @return the value of maxUsers
     */
    public int getMaxUsers() {
        return maxUsers;
    }

    /**
     * Set the value of maxUsers
     *
     * @param maxUsers new value of maxUsers
     */
    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }
}
