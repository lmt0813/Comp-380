import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Confirmation {

    Hotel hotel;
    Account user;

    Confirmation(){}

    Confirmation(Hotel hotel, Account user) {this.hotel = hotel; this.user = user;}
    public static void main(String[] args) throws InterruptedException{
		String email, subject = "Confirmation", body = "Thank you for booking your room at Hotel Transylvania!\nPlease note that this is a test email and Hotel Transylvania is " +
        " is a joke if you are reading this, Geoffrey."; 
		
        String[] command = {"python3", "Email.py" , "joseph.kaz.911@my.csun.edu", subject, body};
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.directory(new File(System.getProperty("user.dir")));
		
		try {
			Process process = processBuilder.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
			//int exitCode = process.waitFor();
			//System.out.println("\nExited with error code: " + exitCode);
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}
}

