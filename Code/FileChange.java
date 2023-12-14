import java.util.*;
import java.io.*;

public class FileChange {
    private Scanner sc;
    private PrintWriter pw;
    private File usersFile;
    private String[][] userData;
    private int indexOfUsername;
    private int indexOfPassword;
    private int indexOfEmail;
    private Account user;

    public FileChange(Account user) {
        this.user = user;
        usersFile = new File("users.txt");
        indexOfUsername = 0;
        indexOfPassword = 1;
        indexOfEmail = 3;
        userData = new String[7][100];
    }

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
