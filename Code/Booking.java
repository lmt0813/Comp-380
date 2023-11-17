import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;


public class Booking {
    protected Account account;
    protected Scanner sc;
    protected String bookingID;
    protected double price;
    protected int hotelID;
    protected String checkInDate;
    protected String checkOutDate;
    protected String paymentInformation;
    protected int roomID;

    public Booking(){}

    public Booking(Account account, String bookingID, double price, int hotelID, String checkInDate, String checkOutDate, String paymentInformation, int roomID) {
        this.account = account;
        this.bookingID = bookingID;
        this.price = price;
        this.hotelID = hotelID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.paymentInformation = paymentInformation;
        this.roomID = roomID;
    }


    public LinkedList<Booking> getUserBookings(String username){
        LinkedList<Booking> result = new LinkedList<>();
        try{
        sc = new Scanner(new File("bookings.txt"));
        
        while(sc.hasNextLine()){
            String[] readLine = sc.nextLine().split(",");
            if(readLine[0] == username){
                
                //when txt file is formated, replace variables with readLine[index]
                //result.add(new Booking(readLine[0], readLine[1], readLine[2], ...));
            }
            
        }
        sc.close();
        }catch(FileNotFoundException e){}
        

        return result;
    }



    public Account getAccount() {
        //account.
        return null;
    }
    
    public String getBookingID() {
        return bookingID;
    }

    public double getPrice() {
        return price;
    }

    public int getHotelID() {
        return hotelID;
    }

    public String getCheckoutInDate() {
        return checkInDate;
    }

    public String getCheckoutDate() {
        return checkOutDate;
    }

    public String getPaymentInformation() {
        return paymentInformation;
    }

    public int getRoomID() {
        return getRoomID();
    }

    public static void main(String[] args) {
        
    }
}