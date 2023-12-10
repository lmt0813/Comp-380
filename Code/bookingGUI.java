import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

/** GUI for booking customer info 
 * @author Victor Ruiz 
 * @author Joseph Kaz
 * @author Geoffrey Anselmo
 * @version 11/21/2023
 */

public class bookingGUI extends JFrame implements ActionListener{
    JLabel hotelLabel, checkinLabel, checkoutLabel, totalLabel, nameLabel ,lastNameLabel, cardLabel, expLabel, securityLabel;
    JLabel hotelNameLabel, checkinDateLabel, checkoutDateLabel, totalFillLabel;
    JButton submitButton;
    JComboBox <String> selectPay;
    JTextField  firstNameField, lastNameField, cardField, expField, securityField;
    JTextField []textFieldsArray;
    JFrame frame, hotelGUIFrame;
    Booking booking;
    Confirmation c;
    Scanner scanner;
    Account user;
    searchControl sc;
    double totalCost;
        /**Default constructor for bookingGui 
         */
        bookingGUI(){}

        /**Constructor with parameters for bookingGui
         * @param booking the booking that the user wants 
         */
        bookingGUI(Booking booking, JFrame hotelGUIFrame, String hotelName){
            this.booking = booking;
            this.hotelGUIFrame = hotelGUIFrame;
            this.user = booking.getAccount();
            sc = new searchControl();
            frame = new JFrame();
            hotelLabel = new JLabel("Hotel :");
            hotelNameLabel = new JLabel(hotelName);
            checkinLabel = new JLabel("Checkin Date :");
            checkinDateLabel = new JLabel(booking.checkInDate);
            checkoutLabel = new JLabel("Checkout Date :");
            checkoutDateLabel = new JLabel(booking.checkOutDate);
            totalLabel = new JLabel("Total: ");
            totalFillLabel = new JLabel(Double.toString(booking.price));
            String[] payMethods = {"select payment option", "pay online", "pay In-person"};
            selectPay = new JComboBox <String>(payMethods);
            nameLabel = new JLabel("First name:");
            firstNameField = new JTextField();
            lastNameLabel = new JLabel("Last Name:");
            lastNameField = new JTextField();
            cardLabel = new JLabel("Card number: ");
            cardField = new JTextField();
            expLabel = new JLabel("Expiration date (MM/YY): ");
            expField = new JTextField();
            securityLabel = new JLabel("CVV :");
            securityField = new JTextField();
            submitButton = new JButton("Submit Payment ");
            
            textFieldsArray = new JTextField[]{firstNameField, lastNameField, cardField, expField, securityField};
            

            hotelLabel.setBounds(50, 25, 150, 50);
            frame.add(hotelLabel);
            hotelNameLabel.setBounds(150, 25, 150, 50);
            frame.add(hotelNameLabel);
            checkinLabel.setBounds(50, 75, 150, 50);
            frame.add(checkinLabel);
            checkinDateLabel.setBounds(150, 75, 150, 50);
            frame.add(checkinDateLabel);
            checkoutLabel.setBounds(50,125, 150, 50);
            frame.add(checkoutLabel);
            checkoutDateLabel.setBounds(150, 125, 150, 50);
            frame.add(checkoutDateLabel);
            totalLabel.setBounds(50, 175, 150, 50);
            totalCost = sc.compareDates(sc.convertDate(booking.checkInDate), sc.convertDate(booking.checkOutDate)) * booking.getPrice();
            totalLabel.setText("Total Price: $          " +Double.toString(totalCost));
            frame.add(totalLabel);
            selectPay.setBounds(50, 225, 165, 25);
            frame.add(selectPay);
            //selectPay.addItem("Select payment option");
            //selectPay.addItem("Pay Online");
            //selectPay.addItem("Pay In-person");
            nameLabel.setBounds(50 , 275, 150, 25);
            frame.add(nameLabel);
            firstNameField.setBounds(200, 275 ,175, 25);
            frame.add(firstNameField);
            lastNameLabel.setBounds(50, 310,150 ,25);
            frame.add(lastNameLabel);
            lastNameField.setBounds(200, 310, 175, 25);
            frame.add(lastNameField);
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
            submitButton.addActionListener(this);
            frame.add(submitButton);
            
            frame.setSize(600, 600);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);
            frame.setTitle("Booking Confirmation");
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           
        }
        
        //can reformat to make more readable, ask Geoffrey if confused on what this does
        /**rewrites txt file after a room is reserved to update availability values 
         * @param file the text file that is going to be rewritten
         * @param ID Index of the hotel ID
         * @param instruction 
         */
        public void rewrite(File file, int ID, char instruction) {
            //setup for what to do depending on txt file
            int index; //index of availability value
            int indexOfID; //index of ID
            int increment; //increment value in for loop
            if(file.equals(new File("hotels.txt"))) {
                index = 4;
                indexOfID = 2;
                increment = 2;
            } else if(file.equals(new File("Room.txt"))) {
                index = 3;
                indexOfID = 5;
                increment = 1;
            } else return;

            try{
                Scanner sc = new Scanner(file);
                //initialize string[][] to be big enough
                String[][] content = new String[100][10];
                for(int i = 0; sc.hasNextLine(); i+=increment) {
                    String[] readLine = sc.next().split(",");
                    if(Integer.parseInt(readLine[indexOfID]) == ID) {
                        if(file.equals(new File("hotels.txt"))) {
                            int changeValue = Integer.parseInt(readLine[index]); //number of available rooms
                            if(instruction == '-') { //subtract available rooms by 1
                                changeValue = changeValue - 1; 
                            } else changeValue = changeValue + 1; //add available rooms by 1
                            String temp = String.valueOf(changeValue);
                            readLine[index] = temp;
                        }
                        if(file.equals(new File("Room.txt"))) {
                            if(instruction == '-') { //change room availability to false
                                readLine[index] = String.valueOf(false);
                            } else readLine[index] = String.valueOf(true); //change room availability to true
                        }
                    }
                    content[i] = readLine;
                    //only if the file is hotels.txt, then read next line to iterate through criteria
                    if(file.equals(new File("hotels.txt"))) {
                        readLine = sc.next().split(",");
                        content[i+1] = readLine;
                    }
                }
                sc.close();
                PrintWriter pw = new PrintWriter(file);
                for(int i = 0; i < content.length; i++) {
                    for(int j = 0; j < content[i].length; j++) {
                        pw.append(content[i][j]);
                        if(j+1 < content[i].length) { //appends "," in between values and not after last value
                            pw.append(",");
                        }
                    }
                    if((content[i+1][0]) == null) {  //stops if next array row is has a null value
                            pw.close();
                            return;
                        }
                    pw.append("\n");
                }
                pw.close();
            } catch (FileNotFoundException e) {}
        }

        /**Writes the bookings object into bookings.txt
         */
        public void writeBookings() {
            try {
                PrintWriter pw = new PrintWriter(new FileOutputStream(new File("bookings.txt"), true));
                Account account = booking.getAccount();
                pw.append(account.getUsername() + "," + account.getPassword() + ",");
                pw.append(account.getName() + "," + account.getEmail() + ",");
                pw.append(account.getAccountType() + "," + account.getAddress() + ",");
                pw.append(account.getPhoneNumber() + "\n");
                pw.append(booking.getRoomID() + ",");
                pw.append(booking.getPrice() + "," + booking.getHotelID() + ",");
                pw.append(booking.getCheckInDate() + "," + booking.getCheckoutDate() + ",");
                pw.append(booking.getRoomNumber() + "\n");
                pw.close();
            } catch(FileNotFoundException e) {}
        }

        /**Writes the reserved room into ReservedRooms.txt 
         */
        public void writeReserved() {
            try{
                PrintWriter pw = new PrintWriter(new FileOutputStream(new File("ReservedRooms.txt"), true));
                Account account = booking.getAccount();
                pw.append(account.getUsername() + ",");
                pw.append(booking.getCheckInDate() + "," + booking.getCheckoutDate() + ",");
                pw.append(booking.getHotelID() + ",");
                pw.append(booking.getRoomID() + "\n");
                pw.close();
                } catch(FileNotFoundException e) {}
        }

        
        /**Checks which action was performed
         * @param ae which action was performed
         */
        public void actionPerformed(ActionEvent ae){
            Object o = ae.getSource();
            
            
            
            if (o == submitButton){ 
               
                if (checkFields(textFieldsArray)==1)
                return;
                if(validateFields() == 1 )
                return;
                writeReserved();
                writeBookings();
                rewrite(new File("hotels.txt"), booking.hotelID, '-');
                rewrite(new File("Room.txt"), booking.roomNumber, '-');
                c = new Confirmation(user, booking, retrieveHotel(booking.getHotelID()));
                c.confirm();
                hotelGUIFrame.dispose();
                frame.dispose();
                new HotelGUI(booking.getAccount());
            }

            
        }

        public Hotel retrieveHotel(int hotelID) {
            Hotel tmp = new Hotel();
            String[] attributes;
            try {
                scanner = new Scanner(new File("hotels.txt"));
                while(scanner.hasNext()) {
                    attributes = scanner.next().split(",");
                    if(attributes[2].compareTo(String.valueOf(hotelID)) == 0) {
                       tmp = createHotel(attributes); 
                       break;
                    }
                    attributes = scanner.next().split(",");
                }
            }

            catch(FileNotFoundException e) {

            }
            finally {
                scanner.close();
            }
            return tmp;
        }

        public Hotel createHotel(String[] attributes) {
            return new Hotel(attributes[0], attributes[1], attributes[2], Integer.parseInt(attributes[3]),Integer.parseInt(attributes[4]));
        }
        
        /**Validates the text fields relating to entering Card information
         * @return int signifies how the method exitted
         */
        int validateFields(){
            if(validateCBox(selectPay.getSelectedIndex()) == 1)
            return 1;
            if(validateName() == 1)
            return 1;
            if(validateLastName() == 1)
            return 1;
            if(validateCard(cardField.getText()) == 1)
            return 1;
            if (validateExp(expField.getText())== 1)
            return 1;
            if(validateCVV(securityField.getText()) == 1)
            return 1;
            return 0;
        }

        /*validates combo box to ensure a choice is made for payment methods */
        int validateCBox(int index){
            if(index == 0){
                JOptionPane.showMessageDialog(null,"must select payment option");
                return 1;
            }
            return 0;   
        }

        /*name validation method checks name field from user input for character limit and  
         * and checks name is consisting of letters
         */
        int validateName(){
            String nameLength = firstNameField.getText();
            if (nameLength.length()> 20) {
                JOptionPane.showMessageDialog(null, "");
            }
            for(int i = 0; i < nameLength.length(); i++){
                char nameChar = nameLength.charAt(i);
                if(!Character.isAlphabetic(nameChar)){
                    JOptionPane.showMessageDialog(null,"First name must consist of only letters");
                    return 1;
                }
            }
            return 0;
        }

         /*last name validation method checks name field from user input for character limit and  
         * and checks name is consisting of letters
         */
        int validateLastName(){
            String lastNameLength = lastNameField.getText();
            if(lastNameLength.length() >20){
                JOptionPane.showMessageDialog(null, "");
            }
            for(int i = 0; i < lastNameLength.length(); i++){
                char nameChar = lastNameLength.charAt(i);
                if(!Character.isAlphabetic(nameChar)){
                    JOptionPane.showMessageDialog(null,"Last name must consist of only letters");
                    return 1;
                }

             }
            return 0;
        }



        /**Checks if the card number entered is of the correct format
         * @return int which signifies how the method exitted
         */
        int validateCard(String cardLength){
            if (cardLength.length()!= 16){
                JOptionPane.showMessageDialog(null,"Card length must be 16 digits");
                return 1;
            }
            for(int i = 0; i < cardLength.length(); i++){
                char currentChar = cardLength.charAt(i);
                if(!Character.isDigit(currentChar)){
                    JOptionPane.showMessageDialog(null, "Card number must consist of all numbers");
                    return 1;
                }
            }
            return 0; 
        } 

        
        /**Checks if the experiation date of the card is of the correct format
         * @return int which signifies how the method exitted
         */
        int validateExp(String ExpDate){
            if (ExpDate.length() != 5){
                JOptionPane.showMessageDialog(null, "Must be proper date format (MM/YY)");
                return 1;
            }
            if(!Character.isDigit((ExpDate.charAt(0)))||!Character.isDigit(ExpDate.charAt(1))){
                JOptionPane.showMessageDialog(null,"Must be proper date format (MM/YY)");
                return 1;
            }
            if(ExpDate.charAt(2) != '/'){
                JOptionPane.showMessageDialog(null, "Must be proper date format (MM/YY)");
                return 1;
            }
            if(!Character.isDigit(ExpDate.charAt(3))||!Character.isDigit(ExpDate.charAt(4))){
                JOptionPane.showMessageDialog(null, "Must be proper date format (MM/YY)");
                return 1;
            }
            
            return 0;
        }

        
        /**Checks if the CVV of the card is of the correct format
         * @return int which signifies how the method exitted
         */
        int validateCVV(String CardCVV){
                if (CardCVV.length() != 3){
                JOptionPane.showMessageDialog(null, "CVV must be 3 numbers");
                return 1;
                }
                if(!Character.isDigit(CardCVV.charAt(0))||!Character.isDigit(CardCVV.charAt(1))||!Character.isDigit(CardCVV.charAt(2))){
                JOptionPane.showMessageDialog(null, "CVV must contain 3 numbers");
                return 1;
                }
                return 0;
        }


        
        /**Checks if any of the text fields are empty
         * @return int which signifies how the method exitted
         */
        int checkFields(JTextField[] fields){
            for(int i=0; i < fields.length; i++){
                if(fields[i].getText().compareTo("")==0){
                    JOptionPane.showMessageDialog(null,getEmptyField(i)+" cannot be empty");
                    return 1;
                }
            }
            return 0; 
        }

        
        /**Finds which text field is empty
         * @param i signifies which text field to check
         * @return String containing the name of the empty text field
         */
        String getEmptyField(int i){
            switch (i) {
                case 0: return "First Name";
                    
                case 1: return "Last Name";

                case 2: return "Card Number";

                case 3: return "Expiration Date";

                case 4: return "CVV";

                default:
                    break;
            }
            return "";
        }

        public void disposeFrame() {
            frame.dispose();
        }
        
        public static void main(String[] args) {
            //bookingGUI x = new bookingGUI(new Booking());
            
        }
}