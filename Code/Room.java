public class Room {
    protected int numberBed;
    protected boolean avaialbility;
    protected String roomID;
    protected int floorNumber;
    protected int roomNumber;
    protected String roomType;
    protected int hotelID;
    protected double price;

    /**Constructor for Room objects
     * @param hotelID The ID number for the hotel that this room is from
     * @param roomID The ID for the room
     * @param numberBed Number of beds in the room
     * @param availability signifies if the room is available (true) or not (false)
     * @param floorNumber The floor the room is located
     * @param roomNumber The room number
     * @param roomType The type of room (i.e suite, penthouse, ect.)
     * @param price The price for each night spent in the room
     */
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

    /**Makes the Room object into a readable string
     * @return A string of the attributes of the Room object, seperated by commas 
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(hotelID + ", ");
        sb.append(roomID + ", ");
        sb.append(numberBed + ", ");
        sb.append(avaialbility + ", ");
        sb.append(floorNumber + ", ");
        sb.append(roomNumber + ", ");
        sb.append(roomType + ", ");
        sb.append(price);

        return sb.toString();
    }
}