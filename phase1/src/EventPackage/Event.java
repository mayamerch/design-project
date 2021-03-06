package EventPackage;


import java.util.ArrayList;
import java.util.Date;

public class Event{
    private int eventId;
    private String eventName;
    private Date eventDate;
    private int eventRoom;
    private int eventSpeaker;
    private int eventDuration;
    private ArrayList<Integer> eventAttendees;


    /**
     * Constructs a new EventPackage.Event object
     * @param eventDate A Date object
     * @param eventRoom Number of Room the event is in.
     * @param eventName Name of event, is a String
     * @param eventSpeaker The ID of a speaker at an event
     * @param eventId The unique ID of an event
     * @param eventDuration The duration in hours of the event
     */
    public Event(int eventId, String eventName, int eventSpeaker, Date eventDate, int eventRoom, int eventDuration) // event duration is for phase 2
        {
            this.eventName = eventName;
            this.eventSpeaker = eventSpeaker;
            this.eventDate = eventDate;
            this.eventRoom = eventRoom;
            this.eventAttendees = new ArrayList<>();
            this.eventId = eventId;
            this.eventDuration = eventDuration;
        }

    /**
     * Returns the ID of the current event
     * @return      The ID of this Event
     */
    public int getEventId() {
        return eventId;
    }


    /**
     * Returns the name of the current event
     * @return      The name of this Event
     */
    public String getEventName() {
        return eventName;
    }


    /**
     * Returns the date of the current event
     * @return      The date of this Event
     */
    public Date getEventDate() {
        return eventDate;
    }


    /**
     * Returns the number of the room the current event is held
     * @return      The number of the room the event is held in
     */
    public int getEventRoom() {
        return eventRoom;
    }


    /**
     * Returns the duration of this event
     * @return      The duration of this event
     */
    public int getEventDuration() {return eventDuration;}


    /**
     * Returns the List of the IDs of the Attendees attending the event
     * @return      List of IDs of Attendees attending
     */
    public ArrayList<Integer> getEventAttendees() {
        return eventAttendees;
    }


    /**
     * Returns the ID of the Speaker at this event
     * @return      The ID of the Speaker at this event
     */
    public int getEventSpeaker() {
        return eventSpeaker;
    }


    /**
     * Set the date the event is going to occur at
     * @param eventDate The new date the event is occurring at
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * Set the room number where the event is happening
     * @param eventRoom The room number
     */
    public void setEventRoom(Integer eventRoom) {
        this.eventRoom = eventRoom;
    }


    /**
     * Set the ID of the new speaker for the event
     * @param eventSpeaker The ID of the speaker for this event
     */
    public void setEventSpeaker(int eventSpeaker) {
        this.eventSpeaker = eventSpeaker;
    }


    /**
     * Change the duration of an event
     * @param eventDuration The new duration of the event
     */
    public void setEventDuration(int eventDuration) {
        this.eventDuration = eventDuration;
    }


    /**
     * Adds an Attendee to an event if he isn't already attending
     * @param AttendeeID ID of Attendee we need to add.
     */
    public void addAttendee(Integer AttendeeID) {
        if (!eventAttendees.contains(AttendeeID))
            eventAttendees.add(AttendeeID);
    }

    /**
     * Remove an Attendee with ID AttendeeID from list of Attendees attending the event if hes attending.
     * @param AttendeeID The ID of the Attendee we need to remove
     */
    public void removeAttendee(Integer AttendeeID) {
        int index = eventAttendees.indexOf(AttendeeID);
        if (index != -1) {
            eventAttendees.remove(index);
        }
    }
    /**
     * Returns true if userID is enrolled in Event, otherwise false
     * @param userID id of a user
     * @return True if usuer is enrolled, false otherwise.
     */
    public boolean enrolled(int userID) {
        return eventAttendees.contains(userID);
    }


    /**
     * Prints Event in string format
     * @return      event in string format
     */
    @Override
    public String toString() {
        return (eventId + "," +
                eventName + "," +
                eventSpeaker + "," +
                eventDate + "," +
                eventRoom + "," +
                eventDuration) + "," +
                eventAttendees;
    }


    /**
     * Returns a list of all participants (attendees + speaker)
     * @return list of participants
     */
    public ArrayList<Integer> getParticipants() {
        ArrayList<Integer> participants = new ArrayList<>();
        participants.add(eventSpeaker);
        participants.addAll(eventAttendees);

        return participants;
    }

}
