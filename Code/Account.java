public class Account{
    private String username;
    private String name;
    private String password;
    private String accountType;
    private String address;
    private String phoneNumber;


    public Account(String username, String password, String name, String accountType, String address, String phoneNumber){
        this.username = username;
        this.password = password;
        this.name = name;
        this.accountType = accountType;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    private void makeReview(){


    }

    //maybe make param as current password as security? or email with verification?
    private void changePassword(){


    }

    private void changeSettings(){


    }

}