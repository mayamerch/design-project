package EventPackage.EventGUI.Reschedule;

import EventPackage.EventGUI.EventsView;
import EventPackage.EventGUI.RoomView;
import EventPackage.EventOuterLayer.EventController;
import EventPackage.EventOuterLayer.EventMessagePresenter;
import EventPackage.EventOuterLayer.EventPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EditSingleSpeaker extends JFrame {
    private JPanel mainPanel;
    private JLabel name;
    private JLabel title;
    private JLabel capacity;
    private JLabel date;
    private JLabel room;
    private JLabel duration;
    private JButton seeRooms;
    private JLabel vipStatus;
    private JButton seeEvents;
    private JTextField nameInput;
    private JTextField capacityInput;
    private JTextField roomInput;
    private JTextField durationInput;
    private JComboBox booleanSelector;
    private JFormattedTextField dateInput;
    private JButton createButton;
    private JLabel info1;
    private JLabel info2;
    private JLabel info3;
    private JLabel info4;
    private JLabel info5;
    private JLabel currName;
    private JLabel currCapacity;
    private JLabel currDate;
    private JLabel currRoom;
    private JLabel currDuration;
    private JLabel currVIP;
    private JLabel info6;
    private JLabel currID;
    private JLabel speaker;
    private JTextField speakerInput;
    private JLabel info7;
    private JLabel currSpeaker;
    private JLabel label1;
    private JLabel currTime;
    private JFormattedTextField timeInput;
    private JLabel speakerInfo;
    private JLabel existingIds;
    private EventPresenter eventPresenter;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Create a GUI responsible for editing a Single Speaker Event.
     * @param eventPresenter1 The EventPresenter used in this view
     * @param currEventId the id of the event to be edited
     */
    public EditSingleSpeaker(EventPresenter eventPresenter1, String currEventId) {
        eventPresenter = eventPresenter1;
        String[] eventInfo = eventPresenter.getSingleSpeakerInfo(currEventId);

        currID.setText(currEventId);
        currName.setText(eventInfo[0]);
        currCapacity.setText(eventInfo[1]);
        currDate.setText(eventInfo[2].substring(0, 10));
        currTime.setText(eventInfo[2].substring(11, 16));
        currRoom.setText(eventInfo[3]);
        currDuration.setText(eventInfo[4]);
        currVIP.setText(eventInfo[5]);
        currSpeaker.setText(eventInfo[6]);
        existingIds.setText(eventPresenter.getSpeakerIds());

        seeRooms.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomView roomView = new RoomView(eventPresenter.getRooms());
                roomView.setContentPane(roomView.getMainPanel());
                roomView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                roomView.pack();
                roomView.setVisible(true);
            }
        });


        seeEvents.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                EventsView eventsView = new EventsView(eventPresenter.getAllEvents(), "All the Events");
                eventsView.setContentPane(eventsView.getMainPanel());
                eventsView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                eventsView.pack();
                eventsView.setVisible(true);
            }
        });


        createButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = nameInput.getText();
                String eventCapacity = capacityInput.getText();
                String eventDate = dateInput.getText();
                String eventTime = timeInput.getText();
                String eventRoom = roomInput.getText();
                String eventDuration = durationInput.getText();
                String eventVIP = (String) booleanSelector.getSelectedItem();
                String eventSpeaker = speakerInput.getText();

                int status = eventPresenter.editSingleSpeaker(currEventId, eventName, eventCapacity, eventDate,
                        eventTime, eventRoom, eventDuration, eventVIP, eventSpeaker);

                EventMessagePresenter eventMessagePresenter = new EventMessagePresenter();
                eventMessagePresenter.editSingleMessage(status);


                String[] eventInfo = eventPresenter.getSingleSpeakerInfo(currEventId);

                currName.setText(eventInfo[0]);
                currCapacity.setText(eventInfo[1]);
                currDate.setText(eventInfo[2].substring(0, 10));
                currTime.setText(eventInfo[2].substring(11, 16));
                currRoom.setText(eventInfo[3]);
                currDuration.setText(eventInfo[4]);
                currVIP.setText(eventInfo[5]);
                currSpeaker.setText(eventInfo[6]);
            }
        });

    }

    private void createUIComponents() {

        String[] choices = {"true", "false"};
        booleanSelector = new JComboBox(choices);
        booleanSelector.setSelectedIndex(1);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateInput = new JFormattedTextField(dateFormat);

        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeInput = new JFormattedTextField(timeFormat);

        currID = new JLabel();

        currName = new JLabel();
        currCapacity = new JLabel();
        currDate = new JLabel();
        currTime = new JLabel();
        currRoom = new JLabel();
        currDuration = new JLabel();
        currVIP = new JLabel();
        currSpeaker = new JLabel();
    }
}