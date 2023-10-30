import java.awt.*;
import java.awt.event.*;

import javax.naming.directory.SearchControls;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class HotelGUI extends JFrame implements ActionListener, ItemListener {
   
    Account user; // logged in as a user
    JLabel userNameLabel, criteriaLabel;
    JFrame mainFrame;
    JMenuBar menuBar;
    JMenu myBookings, myAccount, criteria;
    JTextField search;
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
    LinkedList<JButton> buttonResults;
    LocalDate checkIn, checkOut;
    int checkInButtonIndex, checkOutButtonIndex, checkInMonthIndex, checkOutMonthIndex;

    HotelGUI(){}

    HotelGUI(Account user) {
        // instantiate UI components
        searchCriteria = new String[] {"Pool", "Pet Friendly", "Free Breakfast", "Free Parking", "Free Wi-Fi"};
        textFileCriteria = new String[] {"Pool", "Pet Friendly" , "Free_Breakfast", "Free_Parking", "Free_Wi-Fi"};
        criteriaCheckBoxes = new JCheckBox[searchCriteria.length];
        dates = new String[] {"January" , "February" , "March" , "April", "May" , "June" , "July" , "August" , "September", "October" , "November" , "December"};
        mainFrame = new JFrame();
        mainFrame.getContentPane();
        menuBar = new JMenuBar();
        myBookings = new JMenu("My Bookings");
        myAccount = new JMenu("My Account");
        criteria = new JMenu("Criteria");
        setCriteria();                                  
        search = new JTextField();
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
        search.setBounds(50,100,700,25);
        top.add(search);
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
        

    } // end constructor

    public void searchHotels(LinkedList<String> criteriaList) {
        System.out.println(criteriaList);
    } // end search hotels method

    public void setCriteria() {
        for(int i = 0; i < searchCriteria.length; i++) {
            criteriaCheckBoxes[i] = new JCheckBox(searchCriteria[i]);
            criteria.add(criteriaCheckBoxes[i]);
        }
    } // end set criteria method

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
            if(getDates() != 0) return;
            sp.setVisible(false);
            getResults();
            sp.setVisible(true);
            
            
        }

        if(source == checkInDateButton) {
            changeCheckInVisibility();
        }

        if(source == checkOutDateButton) {
            changeCheckOutVisibility();
        }
    } // end actionperformed method to handle button presses

    void changeCheckInVisibility() {
        if(dayChooserIn.isVisible()) {
            dayChooserIn.setVisible(false);
        }
        else {
            dayChooserIn.setVisible(true);
        }
    }

    void changeCheckOutVisibility() {
        if(dayChooserOut.isVisible()) {
            dayChooserOut.setVisible(false);
        }
        else {
            dayChooserOut.setVisible(true);
        }
    }

    public void getCriteria(LinkedList<String> selectedCriteria) {
        for(int i = 0; i < criteriaCheckBoxes.length; i ++) {
            if(criteriaCheckBoxes[i].isSelected()) {
                selectedCriteria.add(textFileCriteria[i]);
            }
        }
    }

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
    }

    public void setDates() {
        checkIn = LocalDate.of(2023, checkInMonthIndex + 1, checkInButtonIndex + 1);
        checkOut = LocalDate.of(2023, checkOutMonthIndex + 1, checkOutButtonIndex + 1);
    }

    public void getResults() {
        criteriaResults = new LinkedList<String>();
        getCriteria(criteriaResults);
        searchControl sc = new searchControl();
        roomResults = sc.searchResults(criteriaResults);
        if(roomResults.size() == 0) {
            JOptionPane.showMessageDialog(null, "No results returned for your search!");
            return;
        }
        loadButtonResults();
    }

    public void loadButtonResults() {
        buttonResults = new LinkedList<JButton>();
        for(int i = 0; i < roomResults.size(); i++) {
            buttonResults.add(new JButton("Reserve"));
            buttonResults.get(i).addActionListener(this);
        }
        displayResults();
    }

    public void displayResults() {
        for(int i = 0; i < roomResults.size(); i++) {
            bottom.add(new JLabel(roomResults.get(i).roomID +": " + roomResults.get(i).price));
            bottom.add(buttonResults.get(i));
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
       if(e.getSource() == checkInMonths) {
            checkInMonthIndex =  checkInMonths.getSelectedIndex();
       }
       if(e.getSource() == checkOutMonths) {
            checkOutMonthIndex = checkOutMonths.getSelectedIndex();
       }
    } // end item state changed method to handle date selection

    public void parseDates() {

    } // end parse dates method

    public static void main(String[] args) throws ParseException{
        HotelGUI g = new HotelGUI();
        LocalDate localdate = LocalDate.of(2023, 10, 3);
        LocalDate localDate2 = LocalDate.of(2023, 10, 17);
        Duration diff = Duration.between(localdate.atStartOfDay(), localDate2.atStartOfDay());
        System.out.println(diff.toDays());
        System.out.println(localdate.getDayOfMonth() +"/"+localdate.getMonthValue()+"/"+localdate.getYear());

    } 
} // end class HotelGUI