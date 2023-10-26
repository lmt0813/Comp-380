import javax.swing.*;
import java.awt.*;

public class bookingGUI extends JFrame{
    JLabel hotelLabel, checkinLabel, checkoutLabel, totalLabel, nameLabel, cardLabel, expLabel, securityLabel;
    //NOT FILLED 
    JLabel hotelNameLabel, checkinDateLabel, checkoutDateLabel, totalFillLabel;
    JButton submitButton;
    JComboBox <String> selectPay;
    JTextField  nameField, cardField, expField, securityField;
    JFrame frame;

        bookingGUI(){}

        bookingGUI(Booking booking){
            frame = new JFrame();
            hotelLabel = new JLabel("Hotel :");
            //hotelNameLabel = new JLabel(booking.hotel.hotelName);
            checkinLabel = new JLabel("Checkin Data :");
            //checkinDateLabel = new JLabel(booking.checkInDate);
            checkoutLabel = new JLabel("Checkout Date :");
            //checkoutDateLabel = new JLabel(booking.checkOutDate);
            totalLabel = new JLabel("Total: ");
            //TotalFillLabel = new JLabel(Double.toString(booking.price));
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


            hotelLabel.setBounds(50, 50, 150, 50);
            frame.add(hotelLabel);
            //hotelNameLabel.setBounds(150, 50, 150, 50);
            //frame.add(hotelNameLabel);
            checkinLabel.setBounds(50, 100, 150, 50);
            frame.add(checkinLabel);
            //checkinDateLabel.setBounds(150, 125, 150, 50);
            //frame.add(checkinDateLabel);
            checkoutLabel.setBounds(50,150, 150, 50);
            frame.add(checkoutLabel);
            //checkoutDateLabel.setBounds(150, 135, 150, 50);
            //frame.add(checkoutDateLabel);
            totalLabel.setBounds(50, 200, 150, 50);
            frame.add(totalLabel);
            selectPay.setBounds(50, 250, 165, 25);
            frame.add(selectPay);
            selectPay.addItem("Select payment option");
            selectPay.addItem("Pay Online");
            selectPay.addItem("Pay In-person");
            nameLabel.setBounds(50 , 300, 150, 25);
            frame.add(nameLabel);
            nameField.setBounds(200, 300 ,175, 25);
            frame.add(nameField);
            cardLabel.setBounds(50, 350, 150, 25);
            frame.add(cardLabel);
            cardField.setBounds(200, 350, 175, 25);
            frame.add(cardField);
            expLabel.setBounds(50, 400, 150, 25);
            frame.add(expLabel);
            expField.setBounds(200, 400, 175, 25);
            frame.add(expField);
            securityLabel.setBounds(50, 450, 150, 25);
            frame.add(securityLabel);
            securityField.setBounds(200,450, 175, 25);
            frame.add(securityField);
            submitButton.setBounds(100, 500, 150, 25);
            frame.add(submitButton);
            


            frame.setSize(600, 600);
            frame.setLayout(null);
            frame.setTitle("Booking Confirmation");
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           


        }
        public static void main(String[] args) {
            bookingGUI x = new bookingGUI(new Booking());
        }
}