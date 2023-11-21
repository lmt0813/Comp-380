import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;


public class Booking {
    protected Account account;
    protected Scanner sc;
    protected double price;
    protected int hotelID;
    protected String checkInDate;
    protected String checkOutDate;
    protected String roomID;
    protected int roomNumber;

    /**Default constructor for Booking object 
     */
    public Booking(){}

    /**Constructor with parameters for Booking objects
     * @param account The account of the user trying to create the booking
     * @param roomID ID of the room reserved
     * @param price price of the room being reserved
     * @param hotelID ID of the hotel that the room belongs to
     * @param checkInDate The date the user will check into the hotel
     * @param checkoutDate The date the user will check out of the hotel
     * @param roomNumber Room number 
     */
    public Booking(Account account, String roomID, double price, int hotelID, String checkInDate, String checkOutDate, int roomNumber) {
        this.account = account;
        this.roomID = roomID;
        this.price = price;
        this.hotelID = hotelID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomNumber = roomNumber;
    }

    
    /**Retruns the Account associted with the booking 
     * @return Account object associted with the booking
     */
    public Account getAccount() {
        return account;
    }

    
    /**Retruns the Room ID associted with the booking 
     * @return A String with the ID of the room that was booked
     */
    public String getRoomID() {
        return roomID;

    }

    
    /**Retruns the price of the room that was booked
     * @return a double which refers to the price of the room per night 
     */
    public double getPrice() {
        return price;
    }

    
    /**Retruns the Hotel ID associted with the booking  
     * @return an int that is the hotel ID associated with the booked room
     */
    public int getHotelID() {
        return hotelID;
    }

    
    /**Retruns the date in which the user will Check into the room
     * @return A String containing the check in date of the user 
     */
    public String getCheckInDate() {
        return checkInDate;
    }

    
    /**Retruns the date in which the user will Check out of the room 
     * @return A String containing the checkout date of the user 
     */
    public String getCheckoutDate() {
        return checkOutDate;
    }

    
    /**Returns the room number of the booked room 
     * @return an int which contains the room number of the booked room
     */
    public int getRoomNumber() {
        return roomNumber;
    }


    

    public static void main(String[] args) {
        
    }
}