import javax.swing.*;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;

public class MyAccountGUI extends JFrame implements ActionListener{
    private Account user;
    private JFrame mainFrame, confirmationFrame, hotelGUIFrame;
    private JLabel username, password, name, email, address, phoneNumber;
    private JLabel oldLabel, newLabel;
    private JTextField oldField, newField;
    private JButton changePassword, changeEmail, doneButton;

    public MyAccountGUI() {}

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

        changePassword = new JButton("Change Password");
        changeEmail = new JButton("Change Email");
        
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

        if(source == changePassword || source == changeEmail) {
            if(confirmationFrame != null) {
                confirmationFrame.dispose();
            }
            confirmationFrameSetup(source);
        }

        if(source == doneButton) {
            validateEmailFields();
            validatePasswordFields();
            
        }

    }

    public void validateEmailFields() {
        if(oldLabel.getText().equals("Type Old Email: ")) {
            if(oldField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please input your old email");
                return;
            }
            if(newField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Input a New Email");
                return;
            }
            if(oldField.getText().equals(user.getEmail())) {
                JOptionPane.showMessageDialog(null, "Email Changed");
                user.changeEmail(newField.getText());
                confirmationFrame.dispose();
            } else JOptionPane.showMessageDialog(null, "Incorrect email. Please Reenter");
        }
    }

    public void validatePasswordFields() {
        if(oldLabel.getText().equals("Type Old Password: ")) {
            if(oldField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please input your old password");
                return;
            }
            if(newField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Input a New Password");
                return;
            }
            if(oldField.getText().equals(user.getPassword())) {
                JOptionPane.showMessageDialog(null, "Password Changed");
                user.changePassword(newField.getText());
                confirmationFrame.dispose();
            } else JOptionPane.showMessageDialog(null, "Incorrect password. Please reenter");
        }
    }

    public void confirmationFrameSetup(Object source) {
        confirmationFrame = new JFrame();
        
        if(source == changePassword) {
            oldLabel = new JLabel("Type Old Password: ");
            newLabel = new JLabel("Type New Password: ");
        } else {
            oldLabel = new JLabel("Type Old Email: ");
            newLabel = new JLabel("Type New Email: ");
        }
        
        oldField = new JTextField();
        newField = new JTextField();
        doneButton = new JButton("Done");

        doneButton.setBounds(100, 150, 75, 25);
        doneButton.addActionListener(this);
        confirmationFrame.add(doneButton);

        oldLabel.setBounds(25, 50, 125, 25);
        confirmationFrame.add(oldLabel);
        newLabel.setBounds(25, 100, 125, 25);
        confirmationFrame.add(newLabel);

        oldField.setBounds(150, 50, 100, 25);
        confirmationFrame.add(oldField);
        newField.setBounds(150, 100, 100, 25);
        confirmationFrame.add(newField);    


        confirmationFrame.setSize(300, 250);
        confirmationFrame.setLocationRelativeTo(null);
        confirmationFrame.setLayout(null);
        confirmationFrame.setTitle("Account Settings");
        confirmationFrame.setVisible(true);
        confirmationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    

    public void disposeFrame() {
        mainFrame.dispose();
        if(confirmationFrame != null) {
            confirmationFrame.dispose();
        }
    }

    public static void main(String[] args) {
        Account a = new Account("geoff", "password", "Geoffrey Anselmo", "geoffrey.anselmo.807@my.csun.edu", "user", "123 Address St", "818-380-1233");
        JFrame f = null;
        new MyAccountGUI(a, f);
    }

}