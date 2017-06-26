/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

import Enums.EventType;


/**
 * This class is used for the Lecture's that can be scheduled/viewed in the application
 * @author David
 */
public class Lecture extends Event {
    
    private String presenter;
    private EventType eventType = EventType.Lecture;

    //For visual aspects
    private String hexColor = "#F6B067";
    
    //For expansion reasons
    private String attending = "false";
    
    /**
     * constructure of the Lecture class, it takes a String eventName that is passed
     * on to the parent class
     * @param eventName 
     */
    public Lecture(String eventName) {
        super(eventName);
    }
     
    /**
     * Get the value of attending
     *
     * @return the value of attending
     */
    public String getAttending() {
        return this.attending;
    }
    
    /**
     * Set the value of attending
     *
     * @param attending new value of attending
     */
    public void setAttending(String attending) {
        this.attending = attending;
    }
    
    /**
     * Get the value of eventType
     *
     * @return the value of eventType
     */
    @Override
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
    
    /**
     * Get the value of presenter
     *
     * @return the value of presenter
     */
    public String getPresenter() {
        return presenter;
    }

    /**
     * Set the value of presenter
     *
     * @param presenter new value of presenter
     */
    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }
    
    /**
     * Get the value of hexColor
     *
     * @return the value of hexColor
     */
    @Override
    public String getHexColor() {
        return hexColor;
    }

    /**
     * Set the value of hexColor
     *
     * @param hexColor new value of hexColor
     */
    @Override
    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }
}
