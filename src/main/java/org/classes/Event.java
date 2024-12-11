package org.classes;

/**
 * Blueprint for creating Event objects
 */
public class Event {
    //Attributes of  an Event object
    String eventName;
    String eventLocation;
    String eventDate;
    String eventTime;

    /**
     * Event Constructor
     *
     * @param eventName The name of the event
     * @param eventLocation The location of the event
     * @param eventDate The event date
     * @param eventTime The time when the event happens.
     */
    public Event(String eventName, String eventLocation, String eventDate, String eventTime) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    //Getters and Setters
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        eventLocation = eventLocation;
    }


    /**
     * @return a String containing event details
     */
    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", eventLocation='" + eventLocation + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", eventTime='" + eventTime + '\'' +
                '}';
    }
}
