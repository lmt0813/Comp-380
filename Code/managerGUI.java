import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

/**GUI interface for hotel manager and print report
 * @author Victor Ruiz 
 * @author Joseph Kaz
 * @version 11/28/2023
 */

public class managerGUI extends JFrame implements ActionListener, ItemListener{
    Booking booking;
    JFrame manFrame;
    JLabel hotelName, managerReport;  
    JComboBox <String> reportStart , reportEnd;
    JPanel dayChooserIn, dayChooserOut;
    ButtonGroup bgIn, bgOut;
    JRadioButton[] dateRadioButtonsIn, dateRadioButtonsOut;
    LinkedList<JButton> buttonResults, bookingResults;
    JButton checkInDateButton, checkOutDateButton;
    LocalDate checkIn, checkOut;
    int checkInButtonIndex, checkOutButtonIndex, checkInMonthIndex, checkOutMonthIndex;
    String checkInString, checkOutString;

    managerGUI(){}

    managerGUI(Booking booking){
        this.booking= booking;
        manFrame = new JFrame();
        hotelName = new JLabel("Hotel Name");
        managerReport = new JLabel("Manager report");
        String[] dates = {"January" , "February" , "March" , "April", "May" , "June" , "July" , "August" , "September", "October" , "November" , "December"};
        reportStart = new JComboBox<String>(dates);
        reportEnd = new JComboBox<String>(dates);

        //x= left right/ y: up down/ w: left to right size/ h: up down size
        hotelName.setBounds(25, 1, 150, 150);
        hotelName.setFont(new Font("Serif", Font.PLAIN, 30));
        manFrame.add(hotelName);
        managerReport.setBounds(25,25,150, 150);
        manFrame.add(managerReport);
        reportStart.setBounds(50, 150, 150, 20);
        manFrame.add(reportStart);
        reportEnd.setBounds(490,150,150, 20 );
        manFrame.add(reportEnd);


        dayChooserIn = new JPanel();
        dayChooserIn.setLayout(new GridLayout(5,7));
        dayChooserOut = new JPanel();
        dayChooserOut.setLayout(new GridLayout(5,7));
        dayChooserIn.setBounds(50,200, 270,90);
        dayChooserOut.setBounds(650, 200, 270, 90);
        dayChooserIn.setVisible(false);
        dayChooserOut.setVisible(false);
      
        reportStart.addItemListener(this);
        reportEnd.addItemListener(this);
        
       

        checkInDateButton = new JButton("Report start Date");
        checkInDateButton.setBounds(210, 150, 135,20);
        checkInDateButton.addActionListener(this);
        checkOutDateButton = new JButton("Report end date");
        checkOutDateButton.setBounds(650, 150, 145, 20);
        checkOutDateButton.addActionListener(this);
        manFrame.add(checkInDateButton);
        manFrame.add(checkOutDateButton);

        manFrame.add(dayChooserIn);
        manFrame.add(dayChooserOut);
        manFrame.add(reportStart);
        manFrame.add(reportEnd);
        

        //set button groups for date choosers
        dateRadioButtonsIn = new JRadioButton[31];
        dateRadioButtonsOut = new JRadioButton[31];
        bgIn = new ButtonGroup();
        bgOut = new ButtonGroup();
        setDays();

        buttonResults = new LinkedList<JButton>();
        bookingResults = new LinkedList<JButton>();


        manFrame.setSize(800, 800);
        manFrame.setLayout(null);
        manFrame.setTitle("Manager interface");
        manFrame.setVisible(true);
        manFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == checkInDateButton) {
            changeCheckInVisibility();
            return;
        }

        if(source == checkOutDateButton) {
            changeCheckOutVisibility();
            return;
        }
    }

    /*public int setDates() {
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
    }*/


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


    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == reportStart) {
            checkInMonthIndex =  reportStart.getSelectedIndex();
       }
       if(e.getSource() == reportEnd) {
            checkOutMonthIndex = reportEnd.getSelectedIndex();
       }
    } // end item state changed method to handle date selection

    public static void main(String[] args) {
        managerGUI test =  new managerGUI(new Booking());
    }
    }

