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

    public Booking(){}

    public Booking(Account account, String roomID, double price, int hotelID, String checkInDate, String checkOutDate, int roomNumber) {
        this.account = account;
        this.roomID = roomID;
        this.price = price;
        this.hotelID = hotelID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomNumber = roomNumber;
    }

    public Account getAccount() {
        return account;
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