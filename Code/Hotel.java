public class Hotel {
    protected int roomCapacity;
    protected int roomsAvailable;
    protected String location;
    protected String hotelName;
    protected String[] staff;
    protected String[] criteria;

    public Hotel(){}

    public Hotel(String hotelName, String location, int roomCapacity, String[] staff, String[] critera){
        this.hotelName = hotelName;
        this.staff = staff;
        this.criteria = critera;
        this.location = location;
        this.roomCapacity = roomCapacity;
    }

    public void changeVacancy(int rooms){
        this.roomsAvailable = rooms;
    }

    protected void hireStaff(){

    }

    protected void takesPayment(){


    }

    protected void providesService(){


    }

    
}
