package MessagePackage.MessageGUI;

import GUI.Presenter;
import UserPackage.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AttendeeCheckMessages extends JFrame{
    private JTextArea messageArea;
    private JButton showMessages;
    private JPanel mainPanel;
    private JTextField enterID;
    private JButton viewFriends;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public AttendeeCheckMessages(UserController loggedInUserContoller) {
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        //TODO: make loggedInUser

        viewFriends.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: pass in user to viewFriendsForm
                ViewFriendsForm viewFriendsForm = new ViewFriendsForm();
                viewFriendsForm.setContentPane(viewFriendsForm.getMainPanel());
                viewFriendsForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                viewFriendsForm.pack();
                viewFriendsForm.setVisible(true);
                setVisible(true);
            }
        });

        showMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = enterID.getText();
                //TODO: get messages from userID and current user and display in textarea
            }
        });


    }

}
