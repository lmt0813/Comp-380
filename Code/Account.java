public class Account{
    private String username;
    private String name;
    private String password;
    private String email;
    private String accountType;
    private String address;
    private String phoneNumber;


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
    }

    private void makeReview(Hotel hotel){
        //will call another class to make review
    }

    /**Change the email associated with the account
     * @param newEmail: the new email that the user wants the account to be assocaited with
     */
    private void changeEmail(String newEmail) {
        email = newEmail;
    }

    /**Change the password associated with the account
     * @param newPassword: the new password that the user wants the account to be assocaited with
     */
    private void changePassword(String newPassword){
        password = newPassword;
    }

    private void changeSettings(){


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

    //getName()
    //Returns the user's name
    //Input: none
    //Output: String: name asocaited with the account
    public String getName(){
        return this.name;
    }

    //getEmail()
    //Returns the account's email
    //Input: none
    //Output: String: email asocaited with the account
    public String getEmail(){
        return this.email;
    }

    //getPassword()
    //Returns the type of the account
    //Input: none
    //Output: String: the Account's type
    public String getAccountType(){
        return this.accountType;
    }

    //getAddress()
    //Returns the address assocaited with the account
    //Input: none
    //Output: String: address asocaited with the account
    public String getAddress(){
        return this.address;
    }

    //getPhoneNumber()
    //Returns the phone nuumber associated with the account
    //Input: none
    //Output: String: phone number asocaited with the account
    public String getPhoneNumber(){
        return this.phoneNumber;
    }


}