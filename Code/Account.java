public class Account{
    private String username;
    private String name;
    private String password;
    private String email;
    private String accountType;
    private String address;
    private String phoneNumber;


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

    private void changeEmail(String newEmail) {
        email = newEmail;
    }

    
    private void changePassword(String newPassword){
        password = newPassword;
    }

    private void changeSettings(){


    }

    public String getUsername(){
        return this.username;
    }

}