/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Enums.EventType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Arno Dekkers Los
 */
public abstract class EventDate {
    private String eventName;
    private String startTime;
    private String endTime;
    private String date;
    private String day;
    private String locationName;
    
    public EventDate(String eventName){
        this.eventName = eventName;
    }
    
    /**
     * Get the value of locationName
     * @return the value of locationName
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Set the value of locationName
     * @param locationName new value of locationName
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    
    /**
     * Get the value of date
     * @return the value of date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the value of date
     * @param date new value of date
     */
    public void setDate(String date) {
        this.date = date;
        setDay();
    }

    /**
     * Get the value of endTime
     * @return the value of endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Set the value of endTime
     * @param endTime new value of endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Get the value of startTime
     * @return the value of startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Set the value of startTime
     * @param startTime new value of startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Get the value of eventName
     * @return the value of eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Set the value of eventName
     * @param eventName new value of eventName
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    
    /**
     * set's the day, this method is called after the date is changed
     */
    public void setDay(){
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
            Date dateEvent = new SimpleDateFormat("dd-MM-yyyy").parse(date);
            day = simpleDateFormat.format(dateEvent);
        } catch (ParseException ex){
            System.out.println("something went wrong whilst trying to set the day of the event");
        }
    }
    
    public String getDay(){
        return day;
    }
    
    public abstract EventType getEventType();
}
