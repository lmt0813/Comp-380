import javax.swing.*;
import java.io.*;

import java.awt.event.*;
import java.util.*;

public class Login extends JFrame implements ActionListener, ItemListener{
    JFrame mainFrame;
    JPasswordField passwordField;
    JLabel label, usernameLabel, passwordLabel;
    JComboBox<String> comboBox;
    File loginInfo;
    String[] options, files;
    JTextField usernameText;
    JButton loginButton, newUserButton;
    Scanner scanner;

    Login() {
        // instantiate GUI Components

        options = new String[] {"User" , "Manager", "New User"};
        files = new String[]{"./users.txt", "./managers.txt"};
        comboBox = new JComboBox<String>(options);
        passwordField = new JPasswordField();
        passwordField.setEchoChar('*'); // passwordfield will show asterisks instead of text
        label = new JLabel("I am logging in as a: ");
        loginInfo = new File("./users.txt");
        usernameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");
        usernameText = new JTextField();
        loginButton = new JButton("Login");
        mainFrame = new JFrame("Login Page");

        // add components to Frame
        //add label
        label.setBounds(50,100,150,50);
        mainFrame.add(label);
        // add combo box
        comboBox.setBounds(175, 115, 175, 23);
        comboBox.addItemListener(this);
        comboBox.setSelectedIndex(0);
        comboBox.setFocusable(false);
        
        mainFrame.add(comboBox);

        // add username and password components
        usernameLabel.setBounds(50, 150, 150, 50);
        mainFrame.add(usernameLabel);
        usernameText.setBounds(175, 167, 175, 20);
        mainFrame.add(usernameText);
        passwordLabel.setBounds(50, 200, 150, 50);
        mainFrame.add(passwordLabel);
        passwordField.setBounds(175, 218, 175, 20);
        mainFrame.add(passwordField);
        //login button
        loginButton.setBounds(210,270,100,25);
        loginButton.addActionListener(this);
        //newUser button
        newUserButton = new JButton("Create Account");
        newUserButton.setBounds(150, 325, 215, 25);
        newUserButton.addActionListener(this);
        mainFrame.add(newUserButton);

        mainFrame.add(loginButton);
    
        // set final aspects
        mainFrame.setTitle("Login Homepage");
        mainFrame.setLayout(null);
        mainFrame.setSize(500,500);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Login login = new Login();
       
    }

    // action performed method as a part of the actionlistener interface to help with GUI buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton) {
            String username = usernameText.getText();
            String password = new String(passwordField.getPassword());
            if(username.equals("") || password.equals("")) { // check for either field being null
                JOptionPane.showMessageDialog(null, "Username and password both cannot be null!");
                return;
            }
            readInfo(username,password, loginInfo);
        }
    } // end actionperformed method

    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == comboBox) {
            int selectedItem = comboBox.getSelectedIndex();
            
            assignFile(selectedItem);
        }
    } // end itemstatechanged method

    public void assignFile(int selectedItem) {
        if(selectedItem == 0) {
            loginInfo = new File(files[0]);
        }
        else if(selectedItem == 1) {
            loginInfo = new File(files[1]);
        }
    } // end assign file method

    public void readInfo(String usernameString, String passwordString, File loginFile) {
        try {
            scanner = new Scanner(loginFile);
            String username, name, password, accountType, address, phoneNumber, email;
            while(scanner.hasNextLine()) {
                username = scanner.next();
                password = scanner.next();
                name = scanner.next();
                email = scanner.next();
                accountType = scanner.next();
                address = scanner.next();
                phoneNumber = scanner.next();
                if(username.equals(usernameString) && password.equals(passwordString)) {
                    if(accountType.equalsIgnoreCase("User")) {
                        loginUser(username, password, name, email, accountType, address, phoneNumber);
                    }
                }
                else if(username.equals(usernameString) && !password.equals(passwordString)) {
                    JOptionPane.showMessageDialog(null, "Password Incorrect. Please re-enter");
                }

                else {
                    JOptionPane.showMessageDialog(null,"Username not found");
                }
            } // end while loop
               
                    
        }

        catch (FileNotFoundException fne){

        } //end catch

        finally {
           scanner.close();
        } // end finally
    }

    public void loginUser(String username, String password, String name, String email, String accountType, String address, String phoneNumber) {
        JOptionPane.showMessageDialog(null, "Welcome: " + username + "!");
        Account constructor = new Account(username, password, name, email, accountType, address, phoneNumber);
        HotelGUI hotelGUI = new HotelGUI(constructor);
        close();
        return;
    }

    public void loginManager() {

    }

    public void close() {
        mainFrame.dispose();
    }
}
