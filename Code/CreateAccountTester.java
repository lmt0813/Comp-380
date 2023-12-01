import static org.junit.Assert.assertEquals;
import java.util.*;

import javax.naming.directory.SearchControls;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.io.*;
import java.time.LocalDate;

import org.junit.Test;


public class CreateAccountTester {
    CreateAccount c = new CreateAccount();


    @Test
    public void validateAccountType(){ 
        String[] accTypes = {"select account type", "User", "Manager"};
        JComboBox cb = new JComboBox<>(accTypes);
        cb.setSelectedIndex(0);
        int check = c.validateAccountType(cb.getSelectedIndex());
        assertEquals(1, check);
        
    }

    @Test
    public void validateEmail(){
        JTextField email = new JTextField("sample@gmail.com");
        int check = c.validateEmail(email.getText());
        assertEquals(1, check);
        }

    @Test 
    public void validatePassword(){
        JTextField pass = new JTextField("Sample1$");
        String a = pass.getText();
        char[] test = a.toCharArray();
        int check = c.validatePassword(test);
        assertEquals(1, check);

    }


}
