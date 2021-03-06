package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VIPMenuView extends JFrame {
    private JTextField textField1;
    private JButton removeVIPButton;
    private JButton makeVIPButton;
    private JLabel usernameIDLabel;
    private JLabel statusLabel;
    private JButton backButton;
    private JPanel vipPanel;

    private Presenter presenter;

    /**
     * GUI Menu Make a user VIP or not VIP if you are an organiser
     * @param presenter Presenter to be used in this view
     */
    public VIPMenuView(Presenter presenter) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(vipPanel);
        this.presenter = presenter;
        this.pack();

        makeVIPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = textField1.getText();
                if (makeVIP(userInput)){
                    statusLabel.setText("User has been made VIP");
                }
                else{statusLabel.setText("User is already VIP");}
            }
        });
        removeVIPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = textField1.getText();
                if (removeVIP(userInput)){
                    statusLabel.setText("User has had VIP status removed");
                }
                else{statusLabel.setText("User is not VIP. No change needed");}
            }
            });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                UserMenuView userMenuView = new UserMenuView(presenter);
                userMenuView.setVisible(true);
            }
        });
    }
    private boolean makeVIP(String userInput){
        return presenter.changeVIP(userInput, true);
        }
    private boolean removeVIP(String userInput){
        return presenter.changeVIP(userInput, false);
    }
}
