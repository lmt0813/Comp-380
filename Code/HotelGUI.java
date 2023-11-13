import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.time.*;


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
    JMenuItem[] daysIn, daysOut;
    JRadioButton[] dateRadioButtonsIn, dateRadioButtonsOut;
    ButtonGroup bgIn, bgOut;
    JScrollPane sp;
    LinkedList<String> criteriaResults;
    LinkedList<Room> roomResults;
    LinkedList<JButton> buttonResults, bookingResults;
    LocalDate checkIn, checkOut;
    int checkInButtonIndex, checkOutButtonIndex, checkInMonthIndex, checkOutMonthIndex;

    HotelGUI(){}

    HotelGUI(Account user) {
        // instantiate UI components
        this.user = user;
        searchCriteria = new String[] {"Pool", "Pet Friendly", "Free Breakfast", "Free Parking", "Free Wi-Fi", "Cable"};
        textFileCriteria = new String[] {"Pool","Pet_Friendly" , "Free_Breakfast", "Free_Parking", "Free_Wi-Fi", "Cable"};
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
        dayChooserIn.setBounds(50,200, 270,90);
        dayChooserOut.setBounds(490, 200, 270, 90);
        checkInMonths.setBounds(50, 150, 150, 20);
        checkOutMonths.setBounds(490,150, 150, 20);
        dayChooserIn.setVisible(false);
        dayChooserOut.setVisible(false);

        checkInDateButton = new JButton("Check In Selector");
        checkInDateButton.setBounds(210, 150, 135,20);
        checkInDateButton.addActionListener(this);
        checkOutDateButton = new JButton("Check Out Selector");
        checkOutDateButton.setBounds(650, 150, 145, 20);
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
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(true);
        mainFrame.setVisible(true);

        // set for condition check on method
        checkInButtonIndex = -1;
        checkOutButtonIndex = -1;
        checkInMonthIndex = 0;
        checkOutMonthIndex = 0;
        
        displayBookings();

    } // end constructor

    // method that sets the criteria for the menu list
    public void setCriteria() {
        for(int i = 0; i < searchCriteria.length; i++) {
            criteriaCheckBoxes[i] = new JCheckBox(searchCriteria[i]);
            criteria.add(criteriaCheckBoxes[i]);
        }
    } // end set criteria method

    // loads the radio buttons for the checkin and checkout calendars
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
        /*
        for(int i = 0; i < buttonResults.size(); i++) {
            if(source == buttonResults.get(i)) {
                Room r = roomResults.get(i);
                bookingGUI b = new bookingGUI(new Booking(r.price, r.hotelID, checkIn.toString(), checkOut.toString(), "x", "geoff", 1));
            }
        }
        */
    } // end actionperformed method to handle button presses

    // method that aids the searching process by getting and setting the dates, then calling the methods
    // that load the result component
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

    // changes whether the check in calendar may be seen or not
    public void changeCheckInVisibility() {
        if(dayChooserIn.isVisible()) {
            dayChooserIn.setVisible(false);
        }
        else {
            dayChooserIn.setVisible(true);
        }
    } // end changeCheckInVisibility() method

    // changes whether the check out calendar may be seen or not
    public void changeCheckOutVisibility() {
        if(dayChooserOut.isVisible()) {
            dayChooserOut.setVisible(false);
        }
        else {
            dayChooserOut.setVisible(true);
        }
    } // end changeCheckOutVisibility() method

    // adds the selected criteria to a linkedlist
    public void getCriteria(LinkedList<String> selectedCriteria) {
        selectedCriteria.clear();
        for(int i = 0; i < criteriaCheckBoxes.length; i ++) {
            if(criteriaCheckBoxes[i].isSelected()) {
                selectedCriteria.add(textFileCriteria[i]);
            }
        }
    } // end getCriteria() method

    // method that retrieves the dates from the selector components
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

    //method that sets the dates for the bookings, and will check if they're invalid

    public int setDates() {
        try {
            checkIn = LocalDate.of(2023, checkInMonthIndex + 1, checkInButtonIndex + 1);
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
        }

        catch(DateTimeException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
            return 1;
        }
        return 0;
    } // end int setDates() method

    // method calls on search control class to return results for rooms, then will create the button components that 
    // create the bookings
    public void getResults() {
        criteriaResults = new LinkedList<String>();
        getCriteria(criteriaResults);
        searchControl sc = new searchControl();
        roomResults = sc.searchResults(criteriaResults, searchTextField.getText());
        if(roomResults.size() == 0) {
            JOptionPane.showMessageDialog(null, "No results returned for your search!");
            return;
        }
        loadButtonResults();
    }

    //adds the results as clickable components to the a linked list
    public void loadButtonResults() {
        buttonResults.clear();
        for(int i = 0; i < roomResults.size(); i++) {
            buttonResults.add(new JButton("Reserve"));
            buttonResults.get(i).addActionListener(this);
        }
        displayResults();
    } // end loadButtonResults() method

    //method adds checkout components to the GUI
    public void displayResults() {
        for(int i = 0; i < roomResults.size(); i++) {
            bottom.add(new JLabel(roomResults.get(i).roomID +": $" + roomResults.get(i).price + "/night"));
            bottom.add(buttonResults.get(i));
        }
    } // end displayResults() method

    @Override
    public void itemStateChanged(ItemEvent e) {
       if(e.getSource() == checkInMonths) {
            checkInMonthIndex =  checkInMonths.getSelectedIndex();
       }
       if(e.getSource() == checkOutMonths) {
            checkOutMonthIndex = checkOutMonths.getSelectedIndex();
       }
    } // end item state changed method to handle date selection

    public void displayBookings(){
        LinkedList<Booking> userBooking = new LinkedList<>();
        bookingResults.clear();
        Booking b = new Booking();

        userBooking = b.getUserBookings(user.getUsername());

        if(userBooking.size() == 0){
            myBookings.add(new JTextField("No Current Bookings"));
            return;
        }

        for(int i = 0; i < userBooking.size(); i++){
            bookingResults.add(new JButton(userBooking.get(i).bookingID + " " + userBooking.get(i).hotelID));
            bookingResults.get(i).addActionListener(this);
            myBookings.add(bookingResults.get(i));
        }
    }

} // end class HotelGUI