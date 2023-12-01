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
        JComboBox cb = new JComboBox<>()
        String[] accTypes = {"select account type", "User", "Manager"};
        cb.setSelectedIndex(0);
        int check = c.validateAccountType(cb.getSelectedIndex());
        
    }

    @Test
    public void validateEmail(){
        JTextField email = new JTextField("sample@gmail.com");

    }


}
