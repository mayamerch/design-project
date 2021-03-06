package EventPackage.EventUseCases;

import EventPackage.EventEntities.*;
import UserPackage.UserManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class EventProgramSorter {
    private final EventManager eventManager;
    private final UserManager userManager;
    private SimpleDateFormat timeFormatter;

    /**
     * create an instance of EventProgramSorter which compiles event information for the html export in string format
     * @param eventManager EventManager which has information on all events
     * @param userManager UserManager which has information on all users
     */
    public EventProgramSorter(EventManager eventManager, UserManager userManager){
        this.eventManager = eventManager;
        this.userManager = userManager;

        // ex: 10:00am - 10:30am
        timeFormatter = new SimpleDateFormat("hh:mma");

    }

    /**
     * returns a HashMap of all data to export to the Conference Program for all Events in the conference.
     * @return HashMap where key is Date and value is ArrayList of String[] which represents data for a single event
     */
    public HashMap<Date, ArrayList<String[]>> getAllEventsForProgram() {
        ArrayList<Event> events = eventManager.getEventList();
        return this.generateEventInfo(events);
    }

    /**
     * returns a HashMap of all data to export to the Conference Program for events attended by user
     * @param myUserID userID for the current user logged in
     * @return HashMap where key is Date and value is ArrayList of String[] which represents data for a single event
     */
    public HashMap<Date, ArrayList<String[]>> getEventsUserAttendingForProgram(int myUserID) {
        ArrayList<Event> events = eventManager.myEvents(myUserID);
        return this.generateEventInfo(events);
    }


    /**
     * returns a HashMap of all data to export to the Conference Program for all events of one type.
     * @param type the EventType to export all events for
     * @return HashMap where key is Date and value is ArrayList of String[] which represents data for a single event
     */
    public HashMap<Date, ArrayList<String[]>> getEventsByTypeForProgram(EventType type){
        ArrayList<Event> events = null;
        if (type == EventType.PARTY){
            events = new ArrayList<>(eventManager.getPartyList());
        } else if (type == EventType.MULTISPEAKER){
            events = new ArrayList<>(eventManager.getMultiSpeakerList());
        } else if (type == EventType.SINGLESPEAKER){
            events = new ArrayList<>(eventManager.getSingleSpeakerList());
        }
        return this.generateEventInfo(events);
    }

    /**
     * returns a HashMap of all data to export to the Conference Program for all events with a specific speaker
     * @param speakerID the speaker for current speaker we are looking for
     * @return HashMap where key is Date and value is ArrayList of String[] which represents data
     */
    public HashMap<Date, ArrayList<String[]>> getEventsBySpeakerForProgram(int speakerID){
        ArrayList<Event> events = eventManager.speakingAt(speakerID);
        return this.generateEventInfo(events);
    }

    /**
     *
     * @param events an ArrayList of the Events to export to the conference program
     * @return HashMap where key is Date and value is ArrayList of String[] which represents data for a single event
     */
    private HashMap<Date, ArrayList<String[]>> generateEventInfo(ArrayList<Event> events){
        //copy constructor for shallow copy
        ArrayList<Event> sortedEvents = new ArrayList<>(events);
        Collections.sort(sortedEvents);

        HashMap<Date, ArrayList<Event>> eventsByDayHashmap = groupEventsByDay(sortedEvents);

        ArrayList<Date> eventDaysInOrder = getEventDatesInOrder(eventsByDayHashmap);

        HashMap<Date, ArrayList<String[]>> eventInfoHashmap = new HashMap<>();

        for (Date date : eventDaysInOrder){
            ArrayList<Event> eventsOnDay = eventsByDayHashmap.get(date);
            eventInfoHashmap.put(date, generateEventInfoForDay(eventsOnDay));
        }
        return eventInfoHashmap;
    }

    /**
     *
     * @param events ArrayList of Events on one day in the conference
     * @return ArrayList of String[] that represents data for events the parameter event
     */
    private ArrayList<String[]> generateEventInfoForDay(ArrayList<Event> events){
        ArrayList<String[]> info = new ArrayList<>();
        for (Event event : events){
            info.add(generateEventInfoForEvent(event));
        }
        return info;
    }

    /**
     * return an array of String that represents all information for the event for export to html program
     * @param event an event to extract information from for export
     * @return String[] an array of strings for event information
     */
    private String[] generateEventInfoForEvent(Event event){
        Date eventDate = event.getEventDate();
        int duration = event.getEventDuration();
        //TODO: check if this works
        Date endDate = new Date(eventDate.getTime() + TimeUnit.HOURS.toMillis(duration));
        String startTime = timeFormatter.format(eventDate);
        String endTime = timeFormatter.format(endDate);
        String eventName = event.getEventName();
        String speakerName = getSpeakerString(event);
        String eventRoom = Integer.toString(event.getEventRoom());
        return new String[]{startTime, endTime, eventName, speakerName, eventRoom};
    }

    /**
     * Get string representation of the speaker names for the event.
     * @param event an event to extract information from for export
     * @return String of speaker names for the event. If there are no speakers, returns empty string.
     */
    private String getSpeakerString(Event event){
        int eventID = event.getEventId();
        String speakerName;
        if (eventManager.isMultiSpeakerEvent(eventID)){
            ArrayList<Integer> speakers = ((MultiSpeakerEvent)event).getEventSpeakers();
            StringBuilder namesBuilder = new StringBuilder();
            for (int speakerId : speakers){
                String name = userManager.getUserByID(speakerId).getUsername();
                namesBuilder.append(name);
                namesBuilder.append(", ");
            }
            speakerName = namesBuilder.substring(0, namesBuilder.length()-2);
        } else if (eventManager.isSingleSpeakerEvent(eventID)) {
            int speakerId = ((SingleSpeakerEvent)event).getEventSpeaker();
            speakerName = userManager.getUserByID(speakerId).getUsername();
        } else { // it's a party
            speakerName = "";
        }
        return speakerName;
    }

    /**
     * Group events into Hashmap by Date.
     * @param events ArrayList of events to export to our program
     * @return Hashmap where key is a Date during the conference and value is ArrayList of Events on that Date
     */
    private HashMap<Date, ArrayList<Event>> groupEventsByDay(ArrayList<Event> events){
        HashMap<Date, ArrayList<Event>> eventsByDay = new HashMap<>();
        for(Event event : events){
            Date date = event.getEventDate();
            if(eventsByDay.containsKey(date)){
                ArrayList<Event> existingEvents = eventsByDay.get(date);
                existingEvents.add(event);
                eventsByDay.put(date, existingEvents);
            }else{
                ArrayList<Event> newEventsForDay = new ArrayList<>();
                newEventsForDay.add(event);
                eventsByDay.put(date, newEventsForDay);
            }
        }
        return eventsByDay;
    }


    /**
     * 
     * @param eventsByDay Hashmap where key is a Date and value is ArrayList of events for that Date
     * @return ordered ArrayList of the Dates for the conference
     */
    private ArrayList<Date> getEventDatesInOrder(HashMap<Date, ArrayList<Event>> eventsByDay){
        ArrayList<Date> eventDatesInOrder = new ArrayList<>(eventsByDay.keySet());
        Collections.sort(eventDatesInOrder);
        return eventDatesInOrder;
    }
}
