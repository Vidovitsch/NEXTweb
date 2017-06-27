/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;


/**
 * This is a model used to pass information between the GUI and business layer
 * @author David
 */
public class EventViewModel {

    private String eventID;
    private String mode;
    
    /**
     * Get the value of mode
     *
     * @return the value of mode
     */
    public String getMode() {
        return this.mode;
    }
    
    /**
     * Set the value of mode
     *
     * @param mode new value of mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }
    
    /**
     * Get the value of eventID
     *
     * @return the value of eventID
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Set the value of eventID
     *
     * @param eventID new value of eventID
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
}
