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

public class bookingGUItest {

    Account a = new Account("username", "password", "name", "email", "user", "Address", "phone");
    bookingGUI b = new bookingGUI(new Booking(a, "roomID", 10.0, 1, "t", "t", 1), new JFrame());

    @Test
    public void testValidateExp() {
        JTextField expField = new JTextField("01/27");
        int check = b.validateExp(expField.getText());
        assertEquals(0, check);
    }

    @Test
    public void validateCard() {
        JTextField card = new JTextField("1234123412341234");
        int check = b.validateCard(card.getText());
        assertEquals(0, check);
    }

    @Test
    public void validateCVV() {
        JTextField cvv = new JTextField("1234");
        int check = b.validateCVV(cvv.getText());
        assertEquals(1, check);
    }

    @Test
    public void validatePaymentOption() {
        JComboBox cb = new JComboBox<>();
        cb.addItem("Item1");
        cb.addItem("Item2");
        cb.setSelectedIndex(0);
        int check = b.validateCBox(cb.getSelectedIndex());
        assertEquals(1, check);
    }

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
