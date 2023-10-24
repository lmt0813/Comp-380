import javax.swing.*;
import java.awt.*;

public class bookingGUI {
    JLabel hotelLabel, checkinLabel, checkoutLabel, TotalLabel, nameLabel, cardLabel, expLabel, securityLabel;
    //NOT FILLED 
    JLabel hotelNameLabel, checkinDateLabel, checkoutDateLabel, TotalFillLabel;
    JButton submitButton;
    JComboBox <String> selectPay;
    JTextField  nameField, cardField, expField, securityField;

        bookingGUI(){}

        bookingGUI(Booking booking){
            hotelLabel = new JLabel("hotel :");
            hotelNameLabel = new JLabel(booking.hotel.hotelName);
            checkinLabel = new JLabel("checkin Data :");
            checkinDateLabel = new JLabel(booking.checkInDate);
            checkoutLabel = new JLabel("checkout Date :");
            checkoutDateLabel = new JLabel(booking.checkOutDate);



        }

}
