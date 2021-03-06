package EventPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class EventRoomGateway {
    private File fileDataEvent;
    private File fileDataRoom;
    private ArrayList<StringBuilder> eventData;
    private ArrayList<StringBuilder> roomData;


    /**
     * Creates a new EventRoomGateway
     */
    public EventRoomGateway() {
        try {
            //One of the two works according to how you run Intellij

           // this.fileDataEvent = new File("src/EventPackage/eventData.txt");
            this.fileDataEvent = new File("eventData.txt");
            if (this.fileDataEvent.createNewFile()) {
                this.eventData = new ArrayList<>();
            } else {
                this.eventData = read(fileDataEvent);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            //One of the two works according to how you run Intellij

            //this.fileDataRoom = new File("src/EventPackage/roomData.txt");
            this.fileDataRoom = new File("phase1/src/EventPackage/roomData.txt");

            if (this.fileDataRoom.createNewFile()) {
                this.roomData = new ArrayList<>();
            } else {
                this.roomData = read(fileDataRoom);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    /**
     * Tries to read and return data from a text file.
     * Prints to console on exception
     * @param fileData Data of file to be read
     * @return Returns a the data in String form
     */
    public ArrayList<StringBuilder> read(File fileData) {
        ArrayList<StringBuilder> data = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(fileData);
            while (fileReader.hasNextLine()) {
                StringBuilder e = new StringBuilder(fileReader.nextLine());
                data.add(e);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }


    /**
     * Tries to write a list of events and rooms to file specified in constructor.
     * Prints to console on exception
     * @param eventList A list of Event objects
     * @param roomList A list of Room objects
     */
    public void write(ArrayList<Event> eventList, ArrayList<Room> roomList) {
        try {
            FileWriter eventWriter = new FileWriter(this.fileDataEvent);
            for (Event e : eventList) {
                eventWriter.write(e.toString() + System.lineSeparator());
            }
            eventWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter roomWriter = new FileWriter(this.fileDataRoom);
            for (Room r : roomList) {
                roomWriter.write(r.toString() + System.lineSeparator());
            }
            roomWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



    /**
     * Returns an arraylist of Event objects by translating data in eventData
     * @return Returns an arraylist of Event objects
     */
    public ArrayList<Event> parseEvent() {
        ArrayList <Event> objectData = new ArrayList<>();
        for (StringBuilder stringEvent : this.eventData) {
            String[] fieldArr = stringEvent.toString().split(",", 7);
            try {
                Date newDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(fieldArr[3]);
                Event newEvent = new Event(Integer.parseInt(fieldArr[0]),
                        fieldArr[1],
                        Integer.parseInt(fieldArr[2]),
                        newDate,
                        Integer.parseInt(fieldArr[4]),
                        Integer.parseInt(fieldArr[5]));
                if (!fieldArr[6].equals("[]")) {
                    String[] attendeesID = fieldArr[6].substring(1, fieldArr[6].length() - 1).split(", ");
                    for (String s : attendeesID) {
                        newEvent.addAttendee(Integer.parseInt(s));
                    }
                }
                objectData.add(newEvent);
            } catch (ParseException e) {
                continue;
            }
        }
        return objectData;
    }



    /**
     * Returns an arraylist of Room objects by translating data in roomData
     * @return Returns an arraylist of Room objects
     */
    public ArrayList<Room> parseRoom() {
        ArrayList <Room> objectData = new ArrayList<>();
        for (StringBuilder stringEvent : this.roomData) {
            String[] fieldArr = stringEvent.toString().split(",");
            Room newRoom = new Room(Integer.parseInt(fieldArr[0]),
                    Integer.parseInt(fieldArr[1]));
            objectData.add(newRoom);
        }
        return objectData;
    }

}