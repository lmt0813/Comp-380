/**Entity Class for Hotel Object
 * @author Joey Kaz
 * @author Lance Trinidad
 * @version 11/21/2023
 */
public class Hotel {
    protected int roomCapacity;
    protected int roomsAvailable;
    protected String location;
    protected String hotelName;
    protected String hotelID;
    protected String[] staff;
    protected String[] criteria;
    protected Room[] roomList;

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
    public Hotel(String hotelName, String location, int roomCapacity, String[] staff, String[] critera, Room[] roomList){
        this.hotelName = hotelName;
        this.staff = staff;
        this.criteria = critera;
        this.location = location;
        this.roomCapacity = roomCapacity;
        this.roomList = roomList;
    }

    
    /**Changes the number of rooms available
     * @param rooms number of rooms available
     */
    public void changeVacancy(int rooms){
        this.roomsAvailable = rooms;
    }

    
    /**Returns the criteria of the hotel 
     * @return String[] of the criteria assocaited with the hotel 
     */
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
