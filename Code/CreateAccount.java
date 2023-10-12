import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CreateAccount extends JFrame implements ActionListener {
    
    JFrame frame;
    String userName, name, password, accountType, address, phoneNumber;
    char passwordResult[];
    JLabel userNameLabel, nameLabel, passwordLabel, addressLabel, phoneNumberLabel, subPhoneLabel;
    JPasswordField passwordField;
    String fields[];
    JTextField userNameField, nameField, addressField, phoneNumberField;
    JButton createButton;
    BufferedWriter bw;
    File userFile;
    ArrayList<String> usernameList = new ArrayList<String>();


    CreateAccount() {
        frame = new JFrame();
        userNameField = new JTextField();
        nameField = new JTextField();
        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        addressField = new JTextField();
        phoneNumberField = new JTextField();
        createButton = new JButton("Create Account");
        createButton.addActionListener(this);

        userName = "";
        name = "";
        password = "";
        address = "";
        phoneNumber = "";

        // add data entry fields
        nameLabel = new JLabel("Enter your name:");
        nameLabel.setBounds(25,15,100,20);
        nameField.setBounds(250, 15, 200, 20);
        frame.add(nameLabel);
        frame.add(nameField);        

        userNameLabel = new JLabel("Enter Username:");
        userNameLabel.setBounds(25,65,100,20);
        userNameField.setBounds(250,65,200,20);
        frame.add(userNameLabel);
        frame.add(userNameField);

        passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setBounds(25, 115, 100, 20);
        passwordField.setBounds(250, 115, 200, 20);
        frame.add(passwordLabel);
        frame.add(passwordField);
        
        addressLabel = new JLabel("Enter Address:");
        addressLabel.setBounds(25, 165, 100, 20);
        addressField.setBounds(250, 165, 200, 20);
        frame.add(addressLabel);
        frame.add(addressField);

        phoneNumberLabel = new JLabel("Enter Phone Number:");
        phoneNumberLabel.setBounds(25, 215, 150, 20);
        phoneNumberField.setBounds(250, 215, 200, 20);
        subPhoneLabel = new JLabel("Format must be: ###-###-####");
        subPhoneLabel.setBounds(25,240, 175, 20);
        frame.add(phoneNumberLabel);
        frame.add(phoneNumberField);
        frame.add(subPhoneLabel);

        createButton = new JButton("Create Account");
        createButton.setBounds(75,315,300,20);
        createButton.addActionListener(this);
        frame.add(createButton);

        frame.setTitle("Create Account");
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  

    public static void main(String[] args) {
        CreateAccount newAccount = new CreateAccount();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createButton) {
            getAttributes();
        }
    }

    public void getAttributes() {
        usernameList.add("bob");
        userName = userNameField.getText();
        if(usernameList.contains(userName)) {
            JOptionPane.showMessageDialog(null, "username already in use");
            return;
        }
        usernameList.add(userName);
        name = nameField.getText();
        passwordResult = passwordField.getPassword();
        convertPassword();
        address = addressField.getText();
        phoneNumber = phoneNumberField.getText();
        fields = new String[] {name, userName, password, address, phoneNumber};
        if(checkNullFields() == false) {
            return;
        }
        if(checkPhoneNumber() == false) return;
        System.out.println("The method worked and I can go watch football!");
        createAccount();
    }

    public boolean checkNullFields() {
        for(int i = 0; i < fields.length; i++) {
            if(fields[i].isEmpty()) {
                String emptyField = getEmptyField(i);
                JOptionPane.showMessageDialog(null, emptyField + " cannot be empty!");
                return false;
            }
        }
        return true;
    }

    public void createAccount() {
        try {
            userFile = new File("./users.txt");
            bw = new BufferedWriter(new FileWriter(userFile, true));
        }

        catch(FileNotFoundException e) {
            e.printStackTrace();
        } 
        
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertPassword() {
        password = "";
        for(int i = 0; i < passwordResult.length; i++) {
            password += passwordResult[i];
        }
    }

    public String getEmptyField(int i) {
        switch(i) {
            case 0:
                return "Name";
            case 1:
                return "Username";
            case 2:
                return "Password";
            case 3:
                return "Address";
            case 4:
                return "Phone Number";
            default:
                break;
        }
        return "";
    } // end getEmptyField Method

    public boolean checkPhoneNumber() {
        if(phoneNumber.length() != 12) {
            JOptionPane.showMessageDialog(null, "Phone Number must be of correct format: ###-###-####");
            return false;
        }

        if(Character.compare(phoneNumber.charAt(3), '-') != 0 || Character.compare(phoneNumber.charAt(7), '-') != 0) {
            JOptionPane.showMessageDialog(null, "Please put dashes");
            return false;
        }

        if(!Character.isDigit(phoneNumber.charAt(0)) || !Character.isDigit(phoneNumber.charAt(1)) || !Character.isDigit(phoneNumber.charAt(2))) {
            JOptionPane.showMessageDialog(null, "Phone Number must be of correct format: ###-###-####");
            return false;
        }

        if(!Character.isDigit(phoneNumber.charAt(4)) || !Character.isDigit(phoneNumber.charAt(5)) || !Character.isDigit(phoneNumber.charAt(6))) {
            JOptionPane.showMessageDialog(null, "Phone Number must be of correct format: ###-###-####");
            return false;
        }

        if(!Character.isDigit(phoneNumber.charAt(8)) || !Character.isDigit(phoneNumber.charAt(9)) || !Character.isDigit(phoneNumber.charAt(10))) {
            JOptionPane.showMessageDialog(null, "Phone Number must be of correct format: ###-###-####");
            return false;
        }

        if(!Character.isDigit(phoneNumber.charAt(11))) {
            JOptionPane.showMessageDialog(null, "Phone Number must be of correct format: ###-###-####");
            return false;
        }

        return true;
        }
    } // end NewAccount.java class

