public class Account{
    private String accountID;
    private String name;
    private String password;
    private String accountType;
    private String address;
    private String phoneNumber;


    public Account(String accountID, String name, String password, String accountType,
    String address, String phoneNumber){
        this.accountID = accountID;
        this.name = name;
        this.password = password;
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