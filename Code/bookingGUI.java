import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bookingGUI extends JFrame implements ActionListener{
    JLabel hotelLabel, checkinLabel, checkoutLabel, totalLabel, nameLabel, cardLabel, expLabel, securityLabel;
    JLabel hotelNameLabel, checkinDateLabel, checkoutDateLabel, totalFillLabel;
    JButton submitButton;
    JComboBox <String> selectPay;
    JTextField  nameField, cardField, expField, securityField;
    JTextField []textFieldsArray;
    JFrame frame;

        bookingGUI(){}

        bookingGUI(Booking booking){
            frame = new JFrame();
            hotelLabel = new JLabel("Hotel :");
            hotelNameLabel = new JLabel(booking.hotel.hotelName);
            checkinLabel = new JLabel("Checkin Data :");
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
            checkinDateLabel.setBounds(150, 125, 150, 50);
            frame.add(checkinDateLabel);
            checkoutLabel.setBounds(50,150, 150, 50);
            frame.add(checkoutLabel);
            checkoutDateLabel.setBounds(150, 135, 150, 50);
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

        public void actionPerformed(ActionEvent ae){
            Object o = ae.getSource();
            if (o == submitButton){
                if (checkFields()==1)
                return;
                if(validateFields() == 1 )
                return;
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
                if(!Character.isDigit(CardCVV.charAt(0))||!Character.isDigit(CardCVV.charAt(2))||!Character.isDigit(CardCVV.charAt(3))){
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
            bookingGUI x = new bookingGUI(new Booking());
        }
}