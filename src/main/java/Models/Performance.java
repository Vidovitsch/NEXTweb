/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

import Enums.EventType;


/**
 *
 * @author David
 */
public class Performance extends Event {

    private EventType eventType = EventType.Performance;
    
    public Performance(String eventName) {
        super(eventName);
    }
    
    /**
     * Get the value of eventType
     *
     * @return the value of eventType
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Set the value of eventType
     *
     * @param eventType new value of eventType
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
