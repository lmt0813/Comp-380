public class Account{
    private String username;
    private String name;
    private String password;
    private String email;
    private String accountType;
    private String address;
    private String phoneNumber;


    //Account(String username, String password, String name,String email, String accountType, String address, String phoneNumber)
    //Constructor for Account object
    //Input: String username: the username associated with the account
    //       String password: the password associated with the account
    //       String name: user's name
    //       String email: email associated with the account
    //       String accountType: type of account (i.e. if its a customer or staff account)
    //       String address: user's address
    //       String phoneNumber: user's phone number
    //Output: none
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
        // will call another class to make review
    }

    //changeEmail(String newEmail)
    //Change the email associated with the account
    //Input: String newEmail: the new email that the user wants the account to be assocaited with
    //Output: void
    private void changeEmail(String newEmail) {
        email = newEmail;
    }

    //changePassword(String newPassword)
    //Change the password associated with the account
    //Input: String newPassword: the new password that the user wants the account to be assocaited with
    //Output: void
    private void changePassword(String newPassword){
        password = newPassword;
    }

    private void changeSettings(){


    }

    //getUsername()
    //Returns the account's username
    //Input: none
    //Output:String: username assocaited with the account
    public String getUsername(){
        return this.username;
    }


    //getPassword()
    //Returns the account's password
    //Input: none
    //Output:String: password asocaited with the account
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