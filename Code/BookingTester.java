import static org.junit.Assert.assertEquals;
import java.util.*;
import java.io.*;
import org.junit.Test;

/** GUI for user login
 * @author Joey Kaz
 * @version 11/20/2023
 */

public class BookingTester {
    
    /**Testing method that checks if someone requesting to cancel their booking changes the available rooms 
    */
    @Test
    public void cancelBookingTest(){
        Account user = new Account("lance", "password2", "lance", "lancetrinidad0813@gmail.com", "customer", "123 Sesame St.", "123-456-7891");
        Booking booked = new Booking(user, "1-115", 69.0, 5, "1/6/2023", "1/12/2023", 115);
        
        List<String> compareFile = new ArrayList<>();
        List<String> controlFile = new ArrayList<>();
        booked.cancelBooking("testBooking.txt");
        try{
        Scanner sc = new Scanner(new File("testBooking.txt"));

        while(sc.hasNextLine()){
            compareFile = Arrays.asList(sc.nextLine().split(","));
        }
        sc.close();

        sc = new Scanner(new File("controlBookings.txt"));

        while(sc.hasNextLine()){
            controlFile = Arrays.asList(sc.nextLine().split(","));
        }

        sc.close();
        }catch(Exception e){}
        assertEquals(compareFile.toString(),controlFile.toString());
    }

    @Test
    /**Testing method that checks if the method that creates a room does it properly
    */
    public void getRoomTest(){
        Account existingAccount = new Account("jsmith1", "Password2" , "JohnSmith" ,"jsmith1@gmail.com" ,"user" ,"234fakestreet" ,"818-888-8888");
        Booking existingBooking = new Booking(existingAccount, "4-212", 62.0,4, "2/13/2023", "2/25/2023",212);

        Room actualRoom = new Room(4, "4-212", 1, false, 2, 212, "Standard", 62.0);
        Room result = existingBooking.getRoom();
        assertEquals(result.toString(), actualRoom.toString());
    }


}