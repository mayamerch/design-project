package MessagePackage.MessageGUI;

import GUI.Presenter;
import MessagePackage.ChatroomController;
import UserPackage.UserType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckMessages extends JFrame{
    private JTextArea messageArea;
    private JButton showAllMessages;
    private JPanel mainPanel;
    private JTextField enterID;
    private JButton showMessagesFromUserButton;
    private JButton backButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public CheckMessages(Presenter presenter) {
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        ChatroomController chatroomController = presenter.getChatroomController();
        int userID = presenter.getUserController().getCurrentUserId();

        showAllMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageArea.setText(presenter.displayMessages(userID));
            }
        });

        showMessagesFromUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int senderID = Integer.parseInt(enterID.getText());
                messageArea.setText(presenter.displayMessagesFromUser(userID, senderID));
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(presenter.getUserController().getUserType() == UserType.ATTENDEE){
                    AttendeeMessageMenu attendeeMessageMenu = new AttendeeMessageMenu(presenter);
                    attendeeMessageMenu.setVisible(true);
                    setVisible(false);
                }
                else if(presenter.getUserController().getUserType() == UserType.SPEAKER){
                    SpeakerMessageMenu speakerMessageMenu = new SpeakerMessageMenu(presenter);
                    speakerMessageMenu.setVisible(true);
                    setVisible(false);
                }
                else if(presenter.getUserController().getUserType() == UserType.ORGANIZER){
                    OrganizerMessageMenu organizerMessageMenu = new OrganizerMessageMenu(presenter);
                    organizerMessageMenu.setVisible(true);
                    setVisible(false);
                }
            }
        });

    }

}
