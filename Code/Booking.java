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

    /**Default constructor for Booking object */
    public Booking(){}

<<<<<<< HEAD
    /**Constructor for Booking object
     * @param price price of the room being reserved
     * @param hotelID ID of the hotel that the room belongs to
     * @param checkInDate The date the user will check into the hotel
     * @param checkOutDate The date the user will check out of the hotel
     * @param paymentInformation Payment information that the user used to reserve the room
     * @param username the username of the customer who reserved the room
     * @param roomID ID of the room reserved 
     */
    public Booking(double price, int hotelID, String checkInDate, String checkOutDate, String paymentInformation, String username, int roomID) {
        this.bookingID = "aaaa";
=======
    public Booking(Account account, String roomID, double price, int hotelID, String checkInDate, String checkOutDate, int roomNumber) {
        this.account = account;
        this.roomID = roomID;
>>>>>>> 49bea316d7045dad73cf17f748a4c0b1bdedd6e7
        this.price = price;
        this.hotelID = hotelID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomNumber = roomNumber;
    }

<<<<<<< HEAD
    /**Reads the bookings.txt file and returns a linked list of the User's current bookings
     * @param username: The username of the user
     * @return A LinkedList<Booking> of Booking objects that belong to the username given
     */
    public LinkedList<Booking> getUserBookings(String username){
        LinkedList<Booking> result = new LinkedList<>();
        try{
        sc = new Scanner(new File("bookings.txt"));
        
        while(sc.hasNextLine()){
            String[] readLine = sc.nextLine().split(",");
            if(readLine[1] == username){
                //when txt file is formated, replace variables with readLine[index]
                //result.add(new Booking(readLine[0], readLine[1], readLine[2], ...));
            }
            
        }
        sc.close();
        }catch(FileNotFoundException e){}
        

        return result;
=======
    public Account getAccount() {
        return account;
>>>>>>> 49bea316d7045dad73cf17f748a4c0b1bdedd6e7
    }

    public String getRoomID() {
        return roomID;

    }

    public double getPrice() {
        return price;
    }

    public int getHotelID() {
        return hotelID;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckoutDate() {
        return checkOutDate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }


    

    public static void main(String[] args) {
        
    }
}