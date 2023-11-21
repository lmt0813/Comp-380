import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class managerGUI extends JFrame implements ActionListener{
    Booking booking;
    JFrame manFrame;
    JMenuBar menuBar;
    JMenu managerReport , other; 


    managerGUI(){}

    managerGUI(Booking booking){
        this.booking= booking;
        manFrame = new JFrame();
        
        //menu bar if needed 
        menuBar = new JMenuBar();
        managerReport = new JMenu("Managers report");
        other = new JMenu("other manager task");
        manFrame.setJMenuBar(menuBar);
        menuBar.add(managerReport);
        menuBar.add(other);

        

    
    


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
