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

    /**Default constructor for Booking object 
     */
    Confirmation(){}

    /**Constructor with parameters for Booking objects
     * @param hotel the hotel whose attributes will be referenced in the confirmation email
     */
    Confirmation(Hotel hotel, Account user) {this.hotel = hotel; this.user = user;}
    
    /**Confirm method that processes the constructors arguments and creates a process to call the python script to send the email
     */

	public void confirm() {
		String email, subject = "Confirmation", body = "Thank you for booking your room at " + hotel.hotelName +"\nPlease note that this is a test email and Hotel Transylvania is " +
        " is a joke if you are reading this, Geoffrey."; 
		
        String[] command = {"python3", "Email.py" , user.getEmail(), subject, body};
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.directory(new File(System.getProperty("user.dir")));
		
		try {
			Process process = processBuilder.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}
}

