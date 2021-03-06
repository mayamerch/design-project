package EventPackage.EventGUI.EventAttending;

import EventPackage.EventOuterLayer.EventController;
import EventPackage.EventOuterLayer.EventMessagePresenter;
import EventPackage.EventOuterLayer.EventPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelAttendView extends JFrame {
    private JPanel mainPanel;
    private JTextField eventId;
    private JButton okButton;
    private JLabel title;
    private JTable eventTable;
    private JScrollPane scrollPane;
    private JLabel info;
    private EventPresenter eventPresenter;
    private final String[] header = {"Event Id", "Event Name", "Event Type", "Event Room",
            "Event Date", "Event Time", "Event Duration", "Event Capacity", "Available Spaces", "VIP Event", "Event Speaker Ids"};
    private String[][] eventsInfo;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * GUI responsible for giving an Attendee the ability to stop attending events
     * @param eventPresenter1 EventPresenter to be used in this view
     */
    public CancelAttendView(EventPresenter eventPresenter1) {
        eventPresenter = eventPresenter1;

        okButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = eventId.getText();
                int status = eventPresenter.cancelAttend(choice);

                EventMessagePresenter eventMessagePresenter = new EventMessagePresenter();
                eventMessagePresenter.CancelAttendEventMessage(status);
            }
        });
    }

    private void createUIComponents() {
        eventsInfo = eventPresenter.getEventsAttending();
        eventTable = new JTable(eventsInfo, header);
        scrollPane = new JScrollPane(eventTable);
        title = new JLabel();
        title.setText("Events you are Currently Attending");
    }
}