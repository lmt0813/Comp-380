import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.time.*;

/** Entity Class for Account Object
 * @author Geoffrey Anselmo
 * @author Lance Trinidad
 * @version 12/9/2023
 */

public class Account{
    private Scanner sc;
    private String username;
    private String name;
    private String password;
    private String email;
    private String accountType;
    private String address;
    private String phoneNumber;
    private FileChange fc;


    /**Constructor for Account object
     * @param username: the username associated with the account
     * @param password: the password associated with the account
     * @param name: user's name
     * @param mail: email associated with the account
     * @param accountType: type of account (i.e. if its a customer or staff account)
     * @param address: user's address
     * @param phoneNumber: user's phone number
     */
    public Account(String username, String password, String name,String email, String accountType, String address, String phoneNumber){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.accountType = accountType;
        this.address = address;
        this.phoneNumber = phoneNumber;
        fc = new FileChange(this);
    }


    
    /**Returns a LinkedList of the bookings of the user
     * @return LinkedList<Booking> of Booking objects that are bookings associated with the user's account
     */
    public LinkedList<Booking> getUserBookings(){
        LinkedList<Booking> result = new LinkedList<>();
        try{
        sc = new Scanner(new File("bookings.txt"));
        while(sc.hasNextLine()){
            String[] readLine = sc.nextLine().split(",");
            if(readLine[0].equals(username)){
                readLine = sc.nextLine().split(",");
                Account account = this;
                Booking booking = new Booking(account, readLine[0], Double.parseDouble(readLine[1]), Integer.parseInt(readLine[2]), readLine[3], readLine[4], Integer.parseInt(readLine[5]));
                result.add(booking);
            } else readLine = sc.nextLine().split(",");
        }
        sc.close();
        }catch(FileNotFoundException e){}
        return result;
    }

    /**Returns a LinkedList of the current bookings of the user
     * @return LinkedList<Booking> of Booking objects that are only the current bookings associated with the user's account
     */
    public LinkedList<Booking> getCurrentUserBookings() {
        LinkedList<Booking> result = new LinkedList<>();
        try {
            sc = new Scanner(new File("bookings.txt"));
            while(sc.hasNextLine()) {
                String[] readLine = sc.nextLine().split(",");
                
                if(readLine[0].equals(username)) {
                    readLine = sc.nextLine().split(",");

                    LocalDate today = LocalDate.now();
                    searchControl searchcontrol = new searchControl();
                    LocalDate checkindate = searchcontrol.convertDate(readLine[3]);
                    LocalDate checkoutdate = searchcontrol.convertDate(readLine[4]);

                    if(checkindate.isAfter(today) || checkoutdate.isAfter(today) || checkindate.isEqual(today) || checkoutdate.isEqual(today)) {
                        Account account = this;
                        Booking booking = new Booking(account, readLine[0], Double.parseDouble(readLine[1]), Integer.parseInt(readLine[2]), readLine[3], readLine[4], Integer.parseInt(readLine[5]));
                        result.add(booking);
                    } 
                }else readLine = sc.nextLine().split(",");
            }
            sc.close();
        } catch(FileNotFoundException e) {}
        return result;
    }

    /**Change the email associated with the account
     * @param newEmail: the new email that the user wants the account to be assocaited with
     */
    public void changeEmail(String newEmail) {
        fc.changeEmail(newEmail);
        email = newEmail;
    }

    /**Change the password associated with the account
     * @param newPassword: the new password that the user wants the account to be assocaited with
     */
    public void changePassword(String newPassword){
        fc.changePassword(newPassword);
        password = newPassword;
    }

    /**Returns the account's username
     * @return username assocaited with the account
     */
    public String getUsername(){
        return this.username;
    }


    /**Returns the account's password
    * @return password asocaited with the account
    */
    public String getPassword(){
        return this.password;
    }

    
    /**Returns the user's name
     * @return a String of the name asocaited with the account
     */
    public String getName(){
        return this.name;
    }

    
    /**Returns the account's email
     * @return A String of the email asocaited with the account
     */
    public String getEmail(){
        return this.email;
    }

    
    /**Returns the type of the account
     * @return a String of the the Account's type
     */
    public String getAccountType(){
        return this.accountType;
    }

    
    /**Returns the address assocaited with the account
     * @return a String of the address asocaited with the account
     */
    public String getAddress(){
        return this.address;
    }

    
    /**Returns the phone nuumber associated with the account
     * @return a String of the phone number asocaited with the account
     */
    public String getPhoneNumber(){
        return this.phoneNumber;
    }


}