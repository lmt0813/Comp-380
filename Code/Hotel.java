/**Entity Class for Hotel Object
 * @author Joey Kaz
 * @author Lance Trinidad
 * @version 11/28/2023
 */
public class Hotel {
    protected int roomCapacity;
    protected int roomsAvailable;
    protected String location;
    protected String hotelName;
    protected String hotelID;

    /**Default constructor for Hotel objects
    */
    public Hotel() {}

    /**Constructor with parameters for Hotel objects
     * @param hotelName Name of the hotel
     * @param location Where the hotel is located
     * @param roomCapacity The total number of rooms the hotel has
     * @param staff A list of the staff employed at the hotel
     * @param criteria A list of criteria tags that the hotel has
     * @param roomList A list of the rooms in the hotel
     */
    public Hotel(String hotelName, String location, String hotelID, int roomCapacity, int roomsAvailable){
        this.hotelName = hotelName;
        this.location = location;
        this.roomCapacity = roomCapacity;
        this.hotelID = hotelID;
        this.roomCapacity = roomCapacity;
    }

    
    /**Changes the number of rooms available
     * @param rooms number of rooms available
     */
    public void changeVacancy(int rooms){
        this.roomsAvailable = rooms;
    }

    public String getHotelName() {
        return this.hotelName;
    }

    public String getAddress() {
        return this.location;
    }

    
}
