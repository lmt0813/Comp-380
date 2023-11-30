import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/** GUI that creates the process to call the python script that will run the code, emailing the user their confirmation email
 * @author Joey Kaz
 * @version 11/11/2023
 */

public class Confirmation {

    Hotel hotel;
    Account user;
	Booking booking;

    /**Default constructor for Booking object 
     */
    Confirmation(){}

    /**Constructor with parameters for Booking objects
     * @param hotel the hotel whose attributes will be referenced in the confirmation email
     */
    Confirmation(Account user, Booking booking, Hotel hotel) {this.user = user; this.booking = booking; this.hotel = hotel;}
    
    /**Confirm method that processes the constructors arguments and creates a process to call the python script to send the email
     */

	public void confirm() {
		String email = user.getEmail(), subject = "Confirmation", body = constructMessage(); 
		
        String[] command = {"python3", "Email.py" , email, subject, body};
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.directory(new File(System.getProperty("user.dir")));
		
		try {
			Process process = processBuilder.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println("Finished sending");
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

	public String constructMessage() {
		return new String("Hi " + user.getName() +",\n\nThank you for choosing " + hotel.getHotelName() + ". Your booking has been confirmed\n\n" +
		"Confirmation Summary: \nHotel Name: " + hotel.getHotelName() + "\n" + 
		"Check In Date: " + booking.getCheckInDate() + "\nCheck Out Date: " + booking.getCheckoutDate() + 
		"Location: " + hotel.getAddress() + "\n\nWe look forward to seeing you soon.\n\nSincerely, XP Booking Services");
	}
}

