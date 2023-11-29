import javax.swing.*;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;

public class MyAccountGUI extends JFrame implements ActionListener{
    private Account user;
    private JFrame mainFrame, confirmationFrame, hotelGUIFrame;
    private JLabel username, password, name, email, address, phoneNumber;
    private JLabel oldPassword, newPassword;
    private JTextField oldPasswordField, newPasswordField;
    private JButton changeUsername, changePassword, changeEmail, doneButton;

    public MyAccountGUI(Account user ,JFrame hotelGUIFrame) {
        this.user = user;
        this.hotelGUIFrame = hotelGUIFrame;

        mainFrame = new JFrame();

        //initialize JLabels
        username = new JLabel("Username: " + user.getUsername());
        password = new JLabel("Password: ********");
        name = new JLabel("Name: " + user.getName());
        email = new JLabel("Email: " + user.getEmail());
        address = new JLabel("Address: " + user.getAddress());
        phoneNumber = new JLabel("Phone Number: " + user.getPhoneNumber());

        username.setBounds(50, 25, 150, 25);
        mainFrame.add(username);
        password.setBounds(50, 75, 150, 25);
        mainFrame.add(password);
        name.setBounds(50, 125, 150, 25);
        mainFrame.add(name);
        email.setBounds(50, 175, 150, 25);
        mainFrame.add(email);
        address.setBounds(50, 225, 150, 25);
        mainFrame.add(address);
        phoneNumber.setBounds(50, 275, 200, 25);
        mainFrame.add(phoneNumber);

        changeUsername = new JButton("Change Username");
        changePassword = new JButton("Change Password");
        changeEmail = new JButton("Change Email");
        
        changeUsername.setBounds(200, 25, 150, 25);
        changeUsername.addActionListener(this);
        mainFrame.add(changeUsername);
        changePassword.setBounds(200, 75, 150, 25);
        changePassword.addActionListener(this);
        mainFrame.add(changePassword);
        changeEmail.setBounds(200, 175, 150, 25);
        changeEmail.addActionListener(this);
        mainFrame.add(changeEmail);

        //setup for mainFrame
        mainFrame.setSize(475, 475);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(null);
        mainFrame.setTitle("Account Settings");
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == changeUsername || source == changePassword || source == changeEmail) {
            confirmationFrameSetup(source);
        }

        if(source == doneButton) {
            if(oldPasswordField.getText() == user.getPassword()) {
                user.changePassword(newPasswordField.getText());
            }
        }

    }

    public void confirmationFrameSetup(Object source) {
        confirmationFrame = new JFrame();

        oldPassword = new JLabel("Type Old Password: ");
        newPassword = new JLabel("Type New Password: ");
        oldPasswordField = new JTextField();
        newPasswordField = new JTextField();
        doneButton = new JButton("Done");

        doneButton.setBounds(100, 150, 75, 25);
        confirmationFrame.add(doneButton);

        oldPassword.setBounds(25, 50, 125, 25);
        confirmationFrame.add(oldPassword);
        newPassword.setBounds(25, 100, 125, 25);
        confirmationFrame.add(newPassword);

        oldPasswordField.setBounds(150, 50, 100, 25);
        confirmationFrame.add(oldPasswordField);
        newPasswordField.setBounds(150, 100, 100, 25);
        confirmationFrame.add(newPasswordField);    


        confirmationFrame.setSize(300, 275);
        confirmationFrame.setLocationRelativeTo(null);
        confirmationFrame.setLayout(null);
        confirmationFrame.setTitle("Account Settings");
        confirmationFrame.setVisible(true);
        confirmationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        Account a = new Account("geoff", "password", "Geoffrey Anselmo", "geoffrey.anselmo.807@my.csun.edu", "user", "123 Address St", "818-380-1233");
        JFrame f = null;
        new MyAccountGUI(a, f);
    }

}