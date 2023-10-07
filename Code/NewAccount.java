import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NewAccount extends JFrame implements ActionListener {
    
    JFrame frame;
    String userName, name, password, accountType, address, phoneNumber;
    String fields[];
    JTextField userNameField, nameField, passwordField, addressField, phoneNumberField;
    JButton createButton;
    BufferedWriter bw;
    File userFile;


    NewAccount() {
        frame = new JFrame();
        userNameField = new JTextField();
        nameField = new JTextField();
        passwordField = new JTextField();
        addressField = new JTextField();
        phoneNumberField = new JTextField();
        createButton = new JButton("Create Account");
        createButton.addActionListener(this);
        
        fields = new String[] {userName, name, password, address, phoneNumber};

        setTitle("Create Account");
        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

  

    public static void main(String[] args) {
        NewAccount newAccount = new NewAccount();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createButton) {
            getAttributes();
        }
    }

    public void getAttributes() {
        userName = userNameField.getText();
        name = nameField.getText();
        password = passwordField.getText();
        address = addressField.getText();
        phoneNumber = phoneNumberField.getText();
        if(checkNullFields() == false) return;
        createAccount();
    }

    public boolean checkNullFields() {
        for(int i = 0; i < fields.length; i++) {
            if(fields[i].equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, fields[i] + "cannot be empty!");
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
}
