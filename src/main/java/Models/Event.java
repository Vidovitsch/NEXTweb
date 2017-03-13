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
public abstract class Event {
    
    private String eventName;
    private String startTime;
    private String endTime;
    private String date;
    private String imageURL = "/images/next_logo.png";
    private String locationName;
    private String id;

    public Event(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Get the value of locationName
     *
     * @return the value of locationName
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Set the value of locationName
     *
     * @param locationName new value of locationName
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
 
    /**
     * Get the value of imageURL
     *
     * @return the value of imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Set the value of imageURL
     *
     * @param imageURL new value of imageURL
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Get the value of date
     *
     * @return the value of date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the value of date
     *
     * @param date new value of date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get the value of endTime
     *
     * @return the value of endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Set the value of endTime
     *
     * @param endTime new value of endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Get the value of startTime
     *
     * @return the value of startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Set the value of startTime
     *
     * @param startTime new value of startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Get the value of eventName
     *
     * @return the value of eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Set the value of eventName
     *
     * @param eventName new value of eventName
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    
    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(String id) {
        this.id = id;
    }
}
