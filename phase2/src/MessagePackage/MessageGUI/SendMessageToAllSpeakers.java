package MessagePackage.MessageGUI;

import GUI.Presenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendMessageToAllSpeakers extends JFrame{
    private JTextArea messageArea;
    private JButton sendMessagesButton;
    private JPanel mainPanel;
    private JButton backButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SendMessageToAllSpeakers(Presenter presenter){
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        int userID = presenter.getUserController().getCurrentUserId();

        sendMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageArea.getText();
                messageArea.setText(presenter.sendBroadcastToSpeakers(userID, message));
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrganizerMessageMenu organizerMessageMenu = new OrganizerMessageMenu(presenter);
                organizerMessageMenu.setVisible(true);
                setVisible(false);
            }
        });
    }
}
