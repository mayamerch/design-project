package EventPackage;

import java.util.ArrayList;

public class EventTestOld {
    public static void main(String[] args) {
        EventController ec = new EventController();

        ArrayList<Integer> SpeakerIds = new ArrayList<>(); //Contains sample Id of speakers

        // More Statement adding Ids of speakers acn be added
        SpeakerIds.add(1);
        SpeakerIds.add(2);

        ec.run(0, 'O', SpeakerIds); // User Id (first number) has no impact separately, its what shows up when you attend an event (your UserId)
                                                // Second parameter is permissions, -1 for speaker, 1 for attendee, 0 for organizer
    }
}