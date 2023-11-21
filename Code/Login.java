import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;


//Login is a class which handles the GUI elements for the Login screen and allows the user access to the main application
public class Login extends JFrame implements ActionListener, ItemListener{
    private JFrame mainFrame;
    private JPasswordField passwordField;
    private JLabel label, usernameLabel, passwordLabel;
    private JComboBox<String> comboBox;
    private File loginInfo;
    private String[] options, files;
    private JTextField usernameText;
    private JButton loginButton, newUserButton;
    private Scanner scanner;
    private String attributes[];
    private String username, password, name, email, accountType, address, phoneNumber;


    /**Constructor for Login classs 
     */
    Login() {
        // instantiate GUI Components

        options = new String[] {"User" , "Manager"};
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

    /**Performs the actions of the buttons that are pressed
     *ex: if "Create New Account" button is pressed, it will open a new window with the createAccount GUI
     * @param e refers to the action that was performed on certain GUI components 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton) {
            String username = usernameText.getText();
            String password = new String(passwordField.getPassword());
            if(username.compareTo("") == 0 || password.compareTo("") == 0) { // check for either field being null
                JOptionPane.showMessageDialog(null, "Username and password both cannot be null!");
                return;
            }
            switch(readInfo(username, password, loginInfo)) {
                case 0:
                    loginUser(username, password, name, email, accountType, address, phoneNumber);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null,"Password Incorrect. Please re enter.");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null,"Username not found.");
                    break;
                default:
                    break;

            }
        } // end login button

        if(e.getSource() == newUserButton) {
            CreateAccount c = new CreateAccount();
            close();
            return;
        }

    } // end actionperformed method

    /**Checks GUI componenets that are selecteable
     * @param e: refers to the GUI component which has a changable state
     */
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == comboBox) {
            int selectedItem = comboBox.getSelectedIndex();
            
            assignFile(selectedItem);
        }
    } // end itemstatechanged method

    /**Based off the which login option the user selected, the method will assign which file should be read from to check Login Information 
     * @param selectedItem: refers which option the user selected from the "I am Logging in as" dropdown menu
     */
    public void assignFile(int selectedItem) {
        if(selectedItem == 0) {
            loginInfo = new File(files[0]);
        }
        else if(selectedItem == 1) {
            loginInfo = new File(files[1]);
        }
    } // end assign file method

    /**Reads the infromation that the user inputs in the username and password text fields on the GUI
     * @param usernameString: refers to the String the user inputs into the username text field
     * @param passwordString: refers to the string the user inputs into the password text field
     * @param loginFile: refers to the file which assignFile chose, meaning the file which to read from for checking login information
     * @return an int which signifies how the method was ressolved
     */
    public int readInfo(String usernameString, String passwordString, File loginFile) {
        try {
            scanner = new Scanner(loginFile);
            attributes = scanner.next().split(",");
            while(scanner.hasNextLine()) {
                setAttriubtes(attributes);
                if(username.equals(usernameString) && password.equals(passwordString)) {
                    if(accountType.equalsIgnoreCase("User")) {
                        return 0;
                    }
                }
                else if(username.equals(usernameString) && !password.equals(passwordString)) {
                    return 1;
                }
                
                else if(!username.equals(usernameString)) {
                	attributes = scanner.next().split(",");
                }
            } 
        }

        catch (FileNotFoundException fne){ 
            fne.printStackTrace();
        } //end catch

        catch(NoSuchElementException e) {} // reach end of the file

        finally {
           scanner.close();
        } // end finally
        return 2;
    }

    /**Logs the user into the appication as a customer/user and stores their information temporaily into an Account object
     * @param username: A string that refers to the username attached to the user's account
     * @param password:A string that refers to the password attached to the user's account
     * @param name: A string that refers to the name attached to the user's account
     * @param email: A string that refers to the email attached to the user's account
     * @param accountType: A string that refers to the user's account type
     * @param address: A string that refers to the address attached to the user's account
     * @param phoneNumber: A string that refers to the phone number attached to the user's account
     */
    public void loginUser(String username, String password, String name, String email, String accountType, String address, String phoneNumber) {
        JOptionPane.showMessageDialog(null, "Welcome: " + username + "!");
        Account constructor = new Account(username, password, name, email, accountType, address, phoneNumber);
        HotelGUI hotelGUI = new HotelGUI(constructor);
        close();
    }


    /**Allows the user access to the application and the manager features of the application
     */
    public void loginManager() {

    }


    /**Intializes the attributes of the user's account
     * @param attributeStrings: An array of strings that holds the attributes for the user's account (i.e. username, password, ect.)
     */
    public void setAttriubtes(String[] attributeStrings) {
        username = attributeStrings[0];
        password = attributeStrings[1];
        name = attributeStrings[2];
        email = attributeStrings[3];
        accountType = attributeStrings[4];
        address = attributeStrings[5];
        phoneNumber = attributeStrings[6];
    }

    /**Closes the application and all the GUI components
     */
    public void close() {
        mainFrame.dispose();
    }
}
