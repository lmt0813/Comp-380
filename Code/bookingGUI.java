import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class bookingGUI extends JFrame implements ActionListener{
    JLabel hotelLabel, checkinLabel, checkoutLabel, totalLabel, nameLabel, cardLabel, expLabel, securityLabel;
    JLabel hotelNameLabel, checkinDateLabel, checkoutDateLabel, totalFillLabel;
    JButton submitButton;
    JComboBox <String> selectPay;
    JTextField  nameField, cardField, expField, securityField;
    JTextField []textFieldsArray;
    JFrame frame;
    Booking booking;
        bookingGUI(){}

        bookingGUI(Booking booking){
            this.booking = booking;
            frame = new JFrame();
            hotelLabel = new JLabel("Hotel :");
            hotelNameLabel = new JLabel(Integer.toString(booking.hotelID));
            checkinLabel = new JLabel("Checkin Date :");
            checkinDateLabel = new JLabel(booking.checkInDate);
            checkoutLabel = new JLabel("Checkout Date :");
            checkoutDateLabel = new JLabel(booking.checkOutDate);
            totalLabel = new JLabel("Total: ");
            totalFillLabel = new JLabel(Double.toString(booking.price));
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
            
            textFieldsArray = new JTextField[]{nameField, cardField, expField, securityField};
            

            hotelLabel.setBounds(50, 50, 150, 50);
            frame.add(hotelLabel);
            hotelNameLabel.setBounds(150, 50, 150, 50);
            frame.add(hotelNameLabel);
            checkinLabel.setBounds(50, 100, 150, 50);
            frame.add(checkinLabel);
            checkinDateLabel.setBounds(150, 100, 150, 50);
            frame.add(checkinDateLabel);
            checkoutLabel.setBounds(50,150, 150, 50);
            frame.add(checkoutLabel);
            checkoutDateLabel.setBounds(150, 150, 150, 50);
            frame.add(checkoutDateLabel);
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
            submitButton.addActionListener(this);
            frame.add(submitButton);
            


            frame.setSize(600, 600);
            frame.setLayout(null);
            frame.setTitle("Booking Confirmation");
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           

        }
        
        
        /** 
         * @param file
         * @param ID
         * @param instruction
         */
        //can reformat to make more readable, ask Geoffrey if confused on what this does
        //rewrites txt file after a room is reserved to update availability values 
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

        public void actionPerformed(ActionEvent ae){
            Object o = ae.getSource();
            if (o == submitButton){
                if (checkFields()==1)
                return;
                if(validateFields() == 1 )
                return;
                writeReserved();
                writeBookings();
                rewrite(new File("hotels.txt"), booking.hotelID, '-');
                rewrite(new File("Room.txt"), booking.roomNumber, '-');
            }
        }
        int validateFields(){
            if(validateCard() == 1 )
            return 1;
            if (validateExp()== 1)
            return 1;
            if(validateCVV() == 1)
            return 1;
            return 0;
        }

        /*vallidates card
        checks  for 16 digit card number 
        */ 
        int validateCard(){
            String cardLength = cardField.getText();
            if (cardLength.length()!= 16){
                JOptionPane.showMessageDialog(null,"Card length must be 16 digits");
            }
            for(int i = 0; i < cardLength.length(); i++){
                char currentChar = cardLength.charAt(i);
                if(!Character.isDigit(currentChar)){
                    JOptionPane.showMessageDialog(null, "card number must consist of all numbers");
                    return 1;
                }
            }
            return 0; 
        } 

        int validateExp(){
            String ExpDate = expField.getText();
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

        int validateCVV(){
                String CardCVV = securityField.getText();
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


        int checkFields(){
            for(int i=0; i < textFieldsArray.length; i++){
                if(textFieldsArray[i].getText().compareTo("")==0){
                    JOptionPane.showMessageDialog(null,getEmptyField(i)+" cannot be empty");
                    return 1;
                }
            }
            return 0; 
        }

        String getEmptyField(int i){
            switch (i) {
                case 0: return "Name";
                    
                case 1: return "Card Number";

                case 2: return "Expiration Date";

                case 3: return "CCV";

                default:
                    break;
            }
            return "";
        }
        public static void main(String[] args) {
            //bookingGUI x = new bookingGUI(new Booking());
        }
}