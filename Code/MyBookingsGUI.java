import javax.swing.*;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;

/**A GUI component that displays the selected Booking
 * @author Lance Trinidad
 * @author Geoffrey Anselmo
 * @version 11/21/2023
 */
public class MyBookingsGUI extends JFrame implements ActionListener{
    JLabel hotelNameLabel, roomNumberLabel, checkinDateLabel, checkoutDateLabel, totalDueLabel, confirmationLabel1, confirmationLabel2;
    JFrame mainFrame, confirmationFrame;
    JButton cancelButton, confirmButton, denyButton;
    Booking booking;

    /**Default consturctor for MyBookingsGUI
     */
    MyBookingsGUI(){}


    /**Constructor with parameters for MyBookingsGUI
     * @param booking The booking that the user wants to inspect
     */
    MyBookingsGUI(Booking booking){
        this.booking = booking;
        mainFrame = new JFrame();
        hotelNameLabel = new JLabel("Hotel: " + booking.getHotelID());
        roomNumberLabel = new JLabel("Room Number: " + booking.getRoomNumber());
        checkinDateLabel = new JLabel("Check-in Date: " + booking.getCheckInDate());
        checkoutDateLabel = new JLabel("Checkout Date: " + booking.getCheckoutDate());
        totalDueLabel = new JLabel("Total Due: " + booking.getPrice() + " Per Night");

        cancelButton = new JButton("Cancel Booking");

        hotelNameLabel.setBounds(50, 25, 150, 50);
        mainFrame.add(hotelNameLabel);
        roomNumberLabel.setBounds(50, 75, 150, 50);
        mainFrame.add(roomNumberLabel);
        checkinDateLabel.setBounds(50, 125, 150, 50);
        mainFrame.add(checkinDateLabel);
        checkoutDateLabel.setBounds(50, 175, 150, 50);
        mainFrame.add(checkoutDateLabel);
        totalDueLabel.setBounds(50, 225, 150, 50);
        mainFrame.add(totalDueLabel);
        cancelButton.setBounds(100, 300, 150, 25);
        cancelButton.addActionListener(this);
        mainFrame.add(cancelButton);

        mainFrame.setSize(400, 400);
        mainFrame.setLayout(null);
        mainFrame.setTitle("My Booking");
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    


    /**Checks which action was performed
     * @param e which action was performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o == cancelButton){
            cancelConfirmation();
        } else if(o == confirmButton){
            JOptionPane.showMessageDialog(null,"Booking Canceled");
            booking.cancelBooking();
            new HotelGUI(booking.getAccount());
            confirmationFrame.dispose();
            mainFrame.dispose();
            //add a method call that cancels the booking and rewrite ReservedRooms.txt and bookings.txt
        } else if(o == denyButton){
            confirmationFrame.dispose();
        }
    }

    /**Displays a confirmation window for the user when cancelling their booking 
     */
    public void cancelConfirmation(){
        confirmationFrame = new JFrame();
        confirmButton = new JButton("Yes");
        denyButton = new JButton("No");
        confirmationLabel1 = new JLabel("WARNING! You are about to cancel your booking");
        confirmationLabel2 = new JLabel("Are you sure you want to do this?");


        confirmationLabel1.setBounds(25, 0, 500, 100);
        confirmationFrame.add(confirmationLabel1);
        confirmationLabel2.setBounds(65, 15, 500, 100);
        confirmationFrame.add(confirmationLabel2);

        confirmButton.setBounds(75, 100, 75, 25);
        confirmButton.addActionListener(this);
        confirmationFrame.add(confirmButton);
        denyButton.setBounds(175, 100, 75, 25);
        denyButton.addActionListener(this);
        confirmationFrame.add(denyButton);

        confirmationFrame.setSize(350, 175);
        confirmationFrame.setLayout(null);
        confirmationFrame.setVisible(true);
        confirmationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Booking b = new Booking();
        new MyBookingsGUI(b);
    }
}
