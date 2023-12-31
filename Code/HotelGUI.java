import java.awt.*;
import java.awt.event.*;
import java.beans.EventHandler;

import javax.swing.*;
import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

/** GUI class that allows users to interact with the system. It allows users to enter their criteria for searching available rooms and displays booking results
 * @author Joey Kaz
 * @author Victor Ruiz
 * @author Lance Trinidad
 * @version 12/9/2023
 */

public class HotelGUI extends JFrame implements ActionListener, ItemListener {
   
    Account user; // logged in as a user
    JLabel userNameLabel, criteriaLabel;
    JFrame mainFrame;
    JMenuBar menuBar;
    JMenu myBookings, myAccount, criteria;
    JTextField searchTextField;
    File hotelFile;
    JButton searchButton, checkInDateButton, checkOutDateButton;
    String[] searchCriteria, textFileCriteria;
    JCheckBox[] criteriaCheckBoxes;
    JComboBox<String> checkInMonths, checkOutMonths;
    String[] dates;
    JPanel dayChooserIn, dayChooserOut, resultsPanel, top, bottom;
    JMenuItem settingsMenuItem, logOutMenuItem;
    JMenuItem[] daysIn, daysOut;
    JRadioButton[] dateRadioButtonsIn, dateRadioButtonsOut;
    ButtonGroup bgIn, bgOut;
    JScrollPane sp;
    LinkedList<String> criteriaResults;
    LinkedList<Room> roomResults;
    LinkedList<JButton> buttonResults, bookingResults;
    LocalDate checkIn, checkOut;
    String checkInString, checkOutString;
    SimpleDateFormat sm;
    int checkInButtonIndex, checkOutButtonIndex, checkInMonthIndex, checkOutMonthIndex;
    bookingGUI bookinggui;
    MyAccountGUI myaccountgui;
    MyBookingsGUI mybookingsgui;
    String[][] hotelInfo = new String[100][100];

    /**Defualt constructor for hotelGUI 
     */
    HotelGUI(){}

    /**Constructor with parameters for hotelGUI
     * @param user the Account that is using the application
     */
    HotelGUI(Account user) {
        // instantiate UI components
        this.user = user;
        searchCriteria = new String[] {"Pool", "Pet Friendly", "Free Breakfast", "Free Parking", "Free Wi-Fi", "Cable", "Gym"};
        textFileCriteria = new String[] {"Pool","Pet_Friendly" , "Free_Breakfast", "Free_Parking", "Free_Wi-Fi", "Cable", "Gym"};
        criteriaCheckBoxes = new JCheckBox[searchCriteria.length];
        dates = new String[] {"January" , "February" , "March" , "April", "May" , "June" , "July" , "August" , "September", "October" , "November" , "December"};
        mainFrame = new JFrame();
        mainFrame.getContentPane();
        menuBar = new JMenuBar();
        myBookings = new JMenu("My Bookings");
        myAccount = new JMenu("My Account");
        criteria = new JMenu("Criteria");
        setCriteria();                                  
        searchTextField = new JTextField();
        hotelFile = new File("./hotels.txt");
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        
        top = new JPanel();
        top.setLayout(null);
        bottom = new JPanel(new GridLayout(100,2));
        sp = new JScrollPane(bottom, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
        //construct UI
        criteriaLabel = new JLabel("Criteria Selected: ");
        userNameLabel = new JLabel();
        myAccount.add(userNameLabel);

        menuBar.add(myAccount);

        menuBar.add(myBookings);
        menuBar.add(criteria);
        mainFrame.setJMenuBar(menuBar);
        searchTextField.setBounds(50,100,700,25);
        top.add(searchTextField);
        searchButton.setBounds(770, 100, 100, 25);
        top.add(searchButton);
        
        // for check in and out date chooser
        dayChooserIn = new JPanel();
        dayChooserIn.setLayout(new GridLayout(5,7));
        dayChooserOut = new JPanel();
        dayChooserOut.setLayout(new GridLayout(5,7));

        //add select date features to main Frame
        checkInMonths = new JComboBox<String>(dates);
        checkOutMonths = new JComboBox<String>(dates);
        checkInMonths.addItemListener(this);
        checkOutMonths.addItemListener(this);
        dayChooserIn.setBounds(50,200, 350,90);
        dayChooserOut.setBounds(490, 200, 350, 90);
        checkInMonths.setBounds(50, 150, 150, 20);
        checkOutMonths.setBounds(490,150, 150, 20);
        dayChooserIn.setVisible(false);
        dayChooserOut.setVisible(false);

        checkInDateButton = new JButton("Check In Selector");
        checkInDateButton.setBounds(240, 150, 150,20);
        checkInDateButton.addActionListener(this);
        checkOutDateButton = new JButton("Check Out Selector");
        checkOutDateButton.setBounds(670, 150, 150, 20);
        checkOutDateButton.addActionListener(this);
        top.add(checkInDateButton);
        top.add(checkOutDateButton);

        top.add(dayChooserIn);
        top.add(dayChooserOut);
        top.add(checkInMonths);
        top.add(checkOutMonths);
        
        //set button groups for date choosers
        dateRadioButtonsIn = new JRadioButton[31];
        dateRadioButtonsOut = new JRadioButton[31];
        bgIn = new ButtonGroup();
        bgOut = new ButtonGroup();
        setDays();

        buttonResults = new LinkedList<JButton>();
        bookingResults = new LinkedList<JButton>();

        // final UI configuration
        mainFrame.setLayout(null);
        top.setBounds(0,0,1000,300);
        sp.setBounds(5,350, 1000, 250);
        mainFrame.add(top);
        mainFrame.add(sp);
        mainFrame.setSize(1025,750);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(true);
        mainFrame.setVisible(true);

        // set for condition check on method
        checkInButtonIndex = -1;
        checkOutButtonIndex = -1;
        checkInMonthIndex = 0;
        checkOutMonthIndex = 0;
        
        // display myAccount menu items
        //displayAccountMenuItems();
        
        settingsMenuItem = new JMenuItem("Settings");
        settingsMenuItem.addActionListener(this);
        logOutMenuItem = new JMenuItem("Log Out");
        logOutMenuItem.addActionListener(this);

        myAccount.add(settingsMenuItem);
        myAccount.add(logOutMenuItem);

        displayBookings();
        setInitialDates();

    } // end constructor

    /**Sets the criteria for the menu list
     */
    public void setCriteria() {
        for(int i = 0; i < searchCriteria.length; i++) {
            criteriaCheckBoxes[i] = new JCheckBox(searchCriteria[i]);
            criteria.add(criteriaCheckBoxes[i]);
        }
    } // end set criteria method

    /*loads the radio buttons for the checkin and checkout calendars
     */
    public void setDays() {
        for(int i = 1; i <= 31; i++) {
            dateRadioButtonsIn[i-1] = new JRadioButton(""+i);
            dateRadioButtonsOut[i-1] = new JRadioButton(""+i);
            dayChooserIn.add(dateRadioButtonsIn[i-1]);
            bgIn.add(dateRadioButtonsIn[i-1]);
            dayChooserOut.add(dateRadioButtonsOut[i-1]);
            bgOut.add(dateRadioButtonsOut[i-1]);
        }
    } // end setDays method

    
    /**Checks whitch action was performed and calls the appropriate methods
     * @param ae represents where the action was performed
     */
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source == searchButton) {
            processInformation();
            return;
        }

        if(source == checkInDateButton) {
            changeCheckInVisibility();
            return;
        }

        if(source == checkOutDateButton) {
            changeCheckOutVisibility();
            return;
        }

        if(source == settingsMenuItem) {
            if(myaccountgui != null) {
                myaccountgui.disposeFrame();
            }
            myaccountgui = new MyAccountGUI(user, mainFrame);
        }

        if(source == logOutMenuItem) {
            disposeAll();
            new Login();
        }
        
        for(int i = 0; i < bookingResults.size(); i++){
            LinkedList<Booking> userBookings = user.getCurrentUserBookings();
            if(source == bookingResults.get(i)){ 
                Booking current = userBookings.get(i);
                if(mybookingsgui != null) {
                    mybookingsgui.disposeFrame();
                }
                mybookingsgui = new MyBookingsGUI(current, mainFrame);
            }
        }
        

        for(int i = 0; i < buttonResults.size(); i++) {
            if(source == buttonResults.get(i)) {
                Room r = roomResults.get(i);
                if(bookinggui != null) {
                    bookinggui.disposeFrame();
                }
                bookinggui = new bookingGUI(new Booking(user,  r.roomID ,r.price, r.hotelID, checkInString, checkOutString, r.roomNumber), mainFrame, hotelInfo[0][0]);
            }
        }
        
    } // end actionperformed method to handle button presses

    /**method that aids the searching process by getting and setting the dates, then calling the methods that load the result component
     */
    public void processInformation() {
        String searchBar = searchTextField.getText();
        if(getDates() == 1) {return;}
        if(setDates() == 1) {return;}
        if(searchBar.compareTo("") == 0) {
            int input = JOptionPane.showConfirmDialog(null, "Your search bar is empty. Would you like to continue with this being a generic search?");
            if(input == 1 || input == 2){return;}
        } // end if statement
        sp.setVisible(false);
        bottom.removeAll();
        getResults();
        sp.setVisible(true);
    } // end process information method

    /**changes whether the check in calendar may be seen or not
     */
    public void changeCheckInVisibility() {
        if(dayChooserIn.isVisible()) {
            dayChooserIn.setVisible(false);
        }
        else {
            dayChooserIn.setVisible(true);
        }
    } // end changeCheckInVisibility() method

    /*changes whether the check out calendar may be seen or not
     */
    public void changeCheckOutVisibility() {
        if(dayChooserOut.isVisible()) {
            dayChooserOut.setVisible(false);
        }
        else {
            dayChooserOut.setVisible(true);
        }
    } // end changeCheckOutVisibility() method

    
    /**Gets the criteria that the user selecetd 
     * @param selectedCriteria a linkedlist containting the selected filter criteria
     */
    public void getCriteria(LinkedList<String> selectedCriteria) {
        selectedCriteria.clear();
        for(int i = 0; i < criteriaCheckBoxes.length; i ++) {
            if(criteriaCheckBoxes[i].isSelected()) {
                selectedCriteria.add(textFileCriteria[i]);
            }
        }
    } // end getCriteria() method

    
    /**retrieves the dates from the selector components
     * @return int signifies where the method exitted
     */
    public int getDates() {
        for(int i = 0; i < dateRadioButtonsIn.length; i++) {
            if(dateRadioButtonsIn[i].isSelected()) {
                checkInButtonIndex = i;
                break;
            }
        }

        if(checkInButtonIndex == -1) {
            JOptionPane.showMessageDialog(null,"You must select a check in date before continuing!");
            return 1;
        }

        for(int j = 0; j < dateRadioButtonsOut.length; j++) {
            if(dateRadioButtonsOut[j].isSelected()) {
                checkOutButtonIndex = j;
                break;
            }
        }
        if(checkOutButtonIndex == -1) {
            JOptionPane.showMessageDialog(null,"You must select a check out date before continuing!");
            return 1;
        }
        return 0;
    } // end getDates() method

    
    /**that sets the dates for the bookings, and will check if they're invalid
     * @return int signifies where the method exitted
     */
    public int setDates() {
        try {
            checkIn = LocalDate.of(2023, checkInMonthIndex+1,checkInButtonIndex+1);
            checkOut = LocalDate.of(2023, checkOutMonthIndex + 1, checkOutButtonIndex + 1);
            Duration diff = Duration.between(checkIn.atStartOfDay(),checkOut.atStartOfDay());
            long length = diff.toDays();
            if(length  > 14) {
                JOptionPane.showMessageDialog(null, "The Maximum Duration is 14 Days or Less");
                return 1;
            }
            if(length < 1) {
                JOptionPane.showMessageDialog(null, "Duration Of Stay Must Be Greater Than or Equal to 1 Day");
                return 1;
            }
            checkInString = new String(checkIn.getMonthValue() + "/" + checkIn.getDayOfMonth() + "/" + checkIn.getYear());
            checkOutString = new String(checkOut.getMonthValue() + "/" + checkOut.getDayOfMonth()+ "/" +checkOut.getYear());

        }

        catch(DateTimeException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
            return 1;
        }
        return 0;
    } // end int setDates() method

    /**Calls on search control class to return results for rooms, then will create the button components that create the bookings
     */
    public void getResults() {
        criteriaResults = new LinkedList<String>();
        getCriteria(criteriaResults);
        searchControl sc = new searchControl(checkIn, checkOut);
        roomResults = sc.searchResults(criteriaResults, searchTextField.getText());
        if(roomResults.size() == 0) {
            JOptionPane.showMessageDialog(null, "No results returned for your search!");
            return;
        }
        loadButtonResults();
    }

    /**Adds the results as clickable components to the a linked list
     */
    public void loadButtonResults() {
        buttonResults.clear();
        for(int i = 0; i < roomResults.size(); i++) {
            buttonResults.add(new JButton("Reserve"));
            buttonResults.get(i).addActionListener(this);
        }
        displayResults();
    } // end loadButtonResults() method

    /**Adds checkout components to the GUI
     */
    public void displayResults() {
        for(int i = 0; i < roomResults.size(); i++) {
            Room room = roomResults.get(i);
            hotelInfo = room.getHotelInfo();
            bottom.add(new JLabel(hotelInfo[0][0] + " " + roomResults.get(i).roomID +": $" + roomResults.get(i).price + "/night " + 
            Arrays.toString(hotelInfo[1])));
            bottom.add(buttonResults.get(i));
        }
    } // end displayResults() method


    /**Sets the initial combo boxes and radiobuttons to current date
     */
    public void setInitialDates() {
        LocalDate tmp = LocalDate.now();
        LocalDate today = LocalDate.of(tmp.getYear(),tmp.getMonthValue() , tmp.getDayOfMonth());
        for(int i = 0; i < dateRadioButtonsIn.length; i++) {
            if(today.getDayOfMonth() == i + 1) {
                dateRadioButtonsIn[i].setSelected(true);
                dateRadioButtonsOut[i+1].setSelected(true); 
                break;
            }
        }
        for(int i = 0; i < dates.length; i++) {
            if(today.getMonthValue() == i+1) {
                checkInMonths.setSelectedIndex(i); 
                checkOutMonths.setSelectedIndex(i);
                break;
            }
        }
    }

     /**Changes the checkInMonth GUI and checkOutMonth GUI components
     * @param e the GUI component that was selected
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
       if(e.getSource() == checkInMonths) {
            checkInMonthIndex =  checkInMonths.getSelectedIndex();
       }
       if(e.getSource() == checkOutMonths) {
            checkOutMonthIndex = checkOutMonths.getSelectedIndex();
       }
    } // end item state changed method to handle date selection

    /**Displays the bookings that are associated with the user's account 
     */
    public void displayBookings(){
        LinkedList<Booking> userBooking = new LinkedList<>();
        bookingResults.clear();
        userBooking = user.getUserBookings();

        if(userBooking.size() == 0){
            myBookings.add(new JTextField("No Current Bookings"));
            return;
        }
        int count = 0;
        for(int i = 0; i < userBooking.size(); i++){
            Booking booking = userBooking.get(i);
            Room room = booking.getRoom();
            String[][] hotelInfo = room.getHotelInfo();
            LocalDate today = LocalDate.now();
            searchControl sc = new searchControl(checkIn, checkOut);
            LocalDate checkindate = sc.convertDate(booking.getCheckInDate());
            LocalDate checkoutdate = sc.convertDate(booking.getCheckoutDate());
            if(checkindate.isAfter(today) || checkoutdate.isAfter(today) || checkindate.isEqual(today) || checkoutdate.isEqual(today)) {
                bookingResults.add(new JButton(booking.getRoomID() + ": " + hotelInfo[0][0]));
                bookingResults.get(count).addActionListener(this);
                myBookings.add(bookingResults.get(count));
                count++;
            }
            
        }
        if(count == 0) {
            myBookings.add(new JTextField("No Current Bookings"));
            return;
        }
    }
    /**Disposes all frames for the HoteGUI page
     */

    public void disposeAll() {
        if(bookinggui != null) {
            bookinggui.disposeFrame();
        }
        if(myaccountgui != null) {
            myaccountgui.disposeFrame();
        }
        if(mybookingsgui != null) {
            mybookingsgui.disposeFrame();
        }
        mainFrame.dispose();
    }


} // end class HotelGUI