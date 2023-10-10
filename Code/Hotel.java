public class Hotel {
    protected int roomCapacity;
    protected String location;
    protected String hotelName;
    protected String[] staff = new String[];
    protected String[] criteria = new Stringp[];

    public Hotel(){

    }

    public Hotel(String hotelName, String location, String[] staff, String[] critera){
        this.hotelName = hotelName;
        this.staff = staff;
        this.criteria = critera;
        this.location = location;
    }

    protected void hireStaff(){

    }

    protected void takesPayment(){


    }

    protected void providesService(){


    }

    
}
