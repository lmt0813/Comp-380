import javax.swing.*;
import java.awt.*;

public class bookingGUI extends JFrame{
    JLabel hotelLabel, checkinLabel, checkoutLabel, TotalLabel, nameLabel, cardLabel, expLabel, securityLabel;
    //NOT FILLED 
    JLabel hotelNameLabel, checkinDateLabel, checkoutDateLabel, TotalFillLabel;
    JButton submitButton;
    JComboBox <String> selectPay;
    JTextField  nameField, cardField, expField, securityField;
    JFrame frame;

        bookingGUI(){}

        bookingGUI(Booking booking){
            frame = new JFrame();
            hotelLabel = new JLabel("hotel :");
            hotelNameLabel = new JLabel(booking.hotel.hotelName);
            checkinLabel = new JLabel("checkin Data :");
            checkinDateLabel = new JLabel(booking.checkInDate);
            checkoutLabel = new JLabel("checkout Date :");
            checkoutDateLabel = new JLabel(booking.checkOutDate);
            TotalLabel = new JLabel("Total: ");
            TotalFillLabel = new JLabel(Double.toString(booking.price));
            selectPay = new JComboBox<String>();
            nameLabel = new JLabel("Name on card :");
            nameField = new JTextField();
            cardLabel = new JLabel("Card number: ");
            cardField = new JTextField();
            expLabel = new JLabel("Expiration date: ");
            expField = new JTextField();
            securityLabel = new JLabel("CCV :");
            securityField = new JTextField();
            submitButton = new JButton("Submit Payment ");


            hotelLabel.setBounds(50, 150, 150, 50);
            frame.add(hotelLabel);
            




            
            frame.setSize(500, 500);
            frame.setLayout(null);
            frame.setTitle("Booking Confirmation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);




        }
        public static void main(String[] args) {
            bookingGUI x = new bookingGUI();
        }
}