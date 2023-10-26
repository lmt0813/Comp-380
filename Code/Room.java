public class Room {
    protected int numberBed;
    protected boolean avaialbility;
    protected String roomID;
    protected int floorNumber;
    protected int roomNumber;
    protected String roomType;
    protected int hotelID;
    protected double price;

    public Room(int hotelID, String roomID, int numberBed, boolean availability,
                int floorNumber, int roomNumber, String roomType, double price) {

        this.hotelID = hotelID;
        this.roomID = roomID;
        this.numberBed = numberBed;
        this.avaialbility = availability;
        this.floorNumber = floorNumber;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
    }

    protected void assignKeyCard() {
        
    }
}