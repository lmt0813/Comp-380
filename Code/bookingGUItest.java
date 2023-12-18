import static org.junit.Assert.assertEquals;
import java.util.*;

import javax.naming.directory.SearchControls;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.io.*;
import java.time.LocalDate;

import org.junit.Test;

/** Test class for the booking GUI page
 * @author Joey Kaz
 * @version 12/13/2023
 */

public class bookingGUItest {

    Account a = new Account("username", "password", "name", "email", "user", "Address", "phone");
    bookingGUI b = new bookingGUI(new Booking(a, "roomID", 10.0, 1, "t", "t", 1), new JFrame(), null);

    /**Testing method that checks if the credit card expiration date is entered correctly
    */
    @Test
    public void testValidateExp() {
        JTextField expField = new JTextField("01/27");
        int check = b.validateExp(expField.getText());
        assertEquals(0, check);
    }

    /**Testing method that checks if the credit card number is the correct format
    */
    @Test
    public void validateCard() {
        JTextField card = new JTextField("1234123412341234");
        int check = b.validateCard(card.getText());
        assertEquals(0, check);
    }

    /**Testing method that checks if the credit card security code is exactly 3 digits
    */
    @Test
    public void validateCVV() {
        JTextField cvv = new JTextField("1234");
        int check = b.validateCVV(cvv.getText());
        assertEquals(1, check);
    }

    /**Testing method that checks if the user has selected a valid payment option
    */
    @Test
    public void validatePaymentOption() {
        JComboBox cb = new JComboBox<>();
        cb.addItem("Item1");
        cb.addItem("Item2");
        cb.setSelectedIndex(0);
        int check = b.validateCBox(cb.getSelectedIndex());
        assertEquals(1, check);
    }

    /**Testing method that checks if all fields have been entered and selected correctly
    */
    @Test
    public void validateAllFields() {
        JTextField nameField = new JTextField("name");
        JTextField cardField = new JTextField("card");
        JTextField expField = new JTextField("exp");
        JTextField securityField = new JTextField("");
        JTextField[] textFieldsArray = new JTextField[]{nameField, cardField, expField, securityField};
        int check = b.checkFields(textFieldsArray);
        assertEquals(1, check);
    }


}
