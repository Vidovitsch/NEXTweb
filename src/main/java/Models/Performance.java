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
public class Performance extends Event {

    private String eventType = "Performance";
    
    public Performance(String eventName) {
        super(eventName);
    }
    
    /**
     * Get the value of eventType
     *
     * @return the value of eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Set the value of eventType
     *
     * @param eventType new value of eventType
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
