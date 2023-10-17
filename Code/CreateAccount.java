import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;

//Problems  spamming create Account button on GUI will create that account that many times, so
//          the newUsers.txt and usernames.txt will have that many duplicates
//Fix:      have the GUI window close after you create an account,
//          you probably want to run Login right after you click create account button anyways
//
//Problem:  need to add email and user type as fields
//          will also need to add those fields in append method in getAttributes() to add to text file
//          (if it's too messy in getAttributes() we can put PrintStreams into methods and call the method
//          in getAttributes())
//Fix:      easy fix, just do it
//You can delete these when the problems are fixed ^^ I leave it to you Joey

public class CreateAccount extends JFrame implements ActionListener, ItemListener {
    
    JFrame frame;
    String userName, name, password, accountType, address, phoneNumber, type, email;
    char passwordResult[];
    JLabel userNameLabel, nameLabel, passwordLabel, addressLabel, phoneNumberLabel, subPhoneLabel, typeLabel, emailLabel;
    JPasswordField passwordField;
    String fields[];
    JTextField userNameField, nameField, addressField, phoneNumberField, emailField;
    JButton createButton;
    BufferedWriter bw;
    File userFile;
    JComboBox<String> cb;
    static ArrayList<String> usernameList;


    CreateAccount() {
        usernameList = new ArrayList<String>();
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
        typeLabel = new JLabel("Enter user type: ");
        cb = new JComboBox<String>();
        cb.addItemListener(this);
        cb.addItem("User");
        cb.addItem("Manager");
        typeLabel.setBounds(25,15,100,20);
        cb.setBounds(250,15,200,20);
        frame.add(typeLabel);
        frame.add(cb);

        emailLabel = new JLabel("Enter Email");
        emailField = new JTextField();
        emailLabel.setBounds(25,65,100,20);
        emailField.setBounds(250,65,200,20);
        frame.add(emailLabel);
        frame.add(emailField);

        nameLabel = new JLabel("Enter your name:");
        nameLabel.setBounds(25,115,100,20);
        nameField.setBounds(250, 115, 200, 20);
        frame.add(nameLabel);
        frame.add(nameField);        

        userNameLabel = new JLabel("Enter Username:");
        userNameLabel.setBounds(25,165,100,20);
        userNameField.setBounds(250,165,200,20);
        frame.add(userNameLabel);
        frame.add(userNameField);

        passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setBounds(25, 215, 100, 20);
        passwordField.setBounds(250, 215, 200, 20);
        frame.add(passwordLabel);
        frame.add(passwordField);
        
        addressLabel = new JLabel("Enter Address:");
        addressLabel.setBounds(25, 265, 100, 20);
        addressField.setBounds(250, 265, 200, 20);
        frame.add(addressLabel);
        frame.add(addressField);

        phoneNumberLabel = new JLabel("Enter Phone Number:");
        phoneNumberLabel.setBounds(25, 315, 150, 20);
        phoneNumberField.setBounds(250, 315, 200, 20);
        subPhoneLabel = new JLabel("Format must be: ###-###-####");
        subPhoneLabel.setBounds(25,335, 175, 20);
        frame.add(phoneNumberLabel);
        frame.add(phoneNumberField);
        frame.add(subPhoneLabel);

        createButton = new JButton("Create Account");
        createButton.setBounds(75,450,300,20);
        createButton.addActionListener(this);
        frame.add(createButton);

        frame.setTitle("Create Account");
        frame.setSize(500,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  

    public static void main(String[] args) {
        setUsernameList();
        CreateAccount newAccount = new CreateAccount();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createButton) {
            getAttributes();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        if(ie.getSource() == cb) {
            type = cb.getSelectedItem().toString();
        }
    }

    public static void setUsernameList() {
        try {
            Scanner sc = new Scanner(new File("usernames.txt"));
            while(sc.hasNext()) {
                usernameList.add(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e){}
    }

    public void getAttributes() {
        userName = userNameField.getText();
        if(usernameList.contains(userName)) {
            JOptionPane.showMessageDialog(null, "username already in use");
            return;
        }
        type = cb.getSelectedItem().toString();
        System.out.println(type);
        email = emailField.getText();
        name = nameField.getText();
        passwordResult = passwordField.getPassword();
        convertPassword();
        address = addressField.getText();
        phoneNumber = phoneNumberField.getText();
        fields = new String[] {type, email,name, userName, password, address, phoneNumber};
        if(checkNullFields() == false) {
            return;
        }
        if(checkPhoneNumber() == false) return;

        //writing usernames into usernames.txt
        try{ PrintStream ps = new PrintStream(new FileOutputStream("usernames.txt", true));
            ps.append(userName + "\n");
            ps.close();
        }catch(FileNotFoundException e) {}
        
        //writing accountInfo into newUsers.txt
        //will need to add email and user type to append after added to GUI
        try { PrintStream ps = new PrintStream(new FileOutputStream("newUsers.txt", true));
            ps.append(userName + ", " + String.valueOf(passwordResult) + ", " + name + ", " + address + ", " + phoneNumber + "\n");
            ps.close();
        } catch (FileNotFoundException e) {}
        
        createAccount();
        
    }

    public boolean checkNullFields() {
        System.out.println(userName);
        for(int i = 0; i < fields.length; i++) {
            if(fields[i].compareTo("") == 0) {
                String emptyField = getEmptyField(i);
                System.out.println(emptyField);
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
                return "Type";
            case 1:
                return "Email";
            case 2:
                return "Name";
            case 3:
                return "Username";
            case 4:
                return "Password";
            case 5:
                return "Address";
            case 6:
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

