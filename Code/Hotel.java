public class Hotel {
    protected int roomCapacity;
    protected int roomsAvailable;
    protected String location;
    protected String hotelName;
    protected String hotelID;
    protected String[] staff;
    protected String[] criteria;
    protected Room[] roomList;

    public Hotel(){}

    public Hotel(String hotelName, String location, int roomCapacity, String[] staff, String[] critera, Room[] roomList){
        this.hotelName = hotelName;
        this.staff = staff;
        this.criteria = critera;
        this.location = location;
        this.roomCapacity = roomCapacity;
        this.roomList = roomList;
    }

    public void changeVacancy(int rooms){
        this.roomsAvailable = rooms;
    }

    public String[] getCriteria(){
        return this.criteria;
    }

    protected void hireStaff(){

    }

    protected void takesPayment(){


    }

    protected void providesService(){


    }

    
}
