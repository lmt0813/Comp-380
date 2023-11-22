import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

/**GUI interface for hotel manager and print report
 * @author Victor Ruiz 
 * @version 11/21/2023
 */

public class managerGUI extends JFrame implements ActionListener{
    Booking booking;
    JFrame manFrame;
    JLabel hotelName, managerReport;  
    JComboBox <String> reportStart , reportEnd;
    JPanel dayChooserIn, dayChooserOut;


    managerGUI(){}

    managerGUI(Booking booking){
        this.booking= booking;
        manFrame = new JFrame();
        hotelName = new JLabel("Hotel Name");
        managerReport = new JLabel("Manager report");
        String[] dates = {"January" , "February" , "March" , "April", "May" , "June" , "July" , "August" , "September", "October" , "November" , "December"};
        reportStart = new JComboBox<String>(dates);
        reportEnd = new JComboBox<String>(dates);


        hotelName.setBounds(25, 25, 150, 150);
        manFrame.add(hotelName);
        managerReport.setBounds(25,25,150, 150);
        manFrame.add(managerReport);
        reportStart.setBounds(50, 150, 150, 20);
        manFrame.add(reportStart);


        

    
    


        manFrame.setSize(600, 600);
        manFrame.setLayout(null);
        manFrame.setTitle("Manager interface");
        manFrame.setVisible(true);
        manFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


   
    
    public void actionPerformed(ActionEvent e) {
        
    }

    public static void main(String[] args) {
        managerGUI test =  new managerGUI(new Booking());
    }
}
