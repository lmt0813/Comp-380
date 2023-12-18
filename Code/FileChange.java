import java.util.*;
import java.io.*;

/** File Change class that controls operations for handling the text files that are used in the software
 * @author Geoffrey Anselmo
 * @version 12/13/2023
 */

public class FileChange {
    private Scanner sc;
    private PrintWriter pw;
    private File usersFile;
    private String[][] userData;
    private int indexOfUsername;
    private int indexOfPassword;
    private int indexOfEmail;
    private Account user;

    /**Constructor with parameters for hotelGUI
     * @param user the Account that is using the application
     */

    public FileChange(Account user) {
        this.user = user;
        usersFile = new File("users.txt");
        indexOfUsername = 0;
        indexOfPassword = 1;
        indexOfEmail = 3;
        userData = new String[7][100];
    }

    /**Changes the password for the user's account settings
     * @param newPassword the new password that the user would like to change their account settings to
     */
    public void changePassword(String newPassword) {
        try {
            sc = new Scanner(usersFile);
            for(int i = 0; sc.hasNextLine(); i++) {
                userData[i] = sc.nextLine().split(",");
                String username = userData[i][indexOfUsername];
                String password = userData[i][indexOfPassword];
                if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    userData[i][indexOfPassword] = newPassword;
                } //end if
            } //end for
            sc.close();
            pw = new PrintWriter(usersFile);
            for(int i = 0; i < userData.length; i++) {
                for(int j = 0; j < userData[i].length; j++) {
                    pw.append(userData[i][j]);
                    if(j+1 == userData[i].length) {
                        pw.append("\n");
                    } else pw.append(",");
                } //end inner for
                if(userData[i+1][0] == null) {
                    pw.close();
                    return;
                }
            } //end outer for
            pw.close();
        } catch(FileNotFoundException e) {}
        
    }

    /**Changes the email for the user's account settings
     * @param newEmail the new email address that the user would like to change their account settings to
     */
    public void changeEmail(String newEmail) {
        try {
            sc = new Scanner(usersFile);
            for(int i = 0; sc.hasNextLine(); i++) {
                userData[i] = sc.nextLine().split(",");
                String username = userData[i][indexOfUsername];
                String email = userData[i][indexOfEmail];
                if(username.equals(user.getUsername()) && email.equals(user.getEmail())) {
                    userData[i][indexOfEmail] = newEmail;
                } //end if
            } //end for
            sc.close();
            pw = new PrintWriter(usersFile);
            for(int i = 0; i < userData.length; i++) {
                for(int j = 0; j < userData[i].length; j++) {
                    pw.append(userData[i][j]);
                    if(j+1 == userData[i].length) {
                        pw.append("\n");
                    } else pw.append(",");
                }
                if(userData[i+1][0] == null) {
                    pw.close();
                    return;
                }
            }
            pw.close();
        } catch(FileNotFoundException e) {}
    }
}
