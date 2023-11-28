import java.util.*;
import java.io.*;
import java.time.*;

/**searchControl determines what rooms should be returned based off search criteria 
 * @author Geoffrey Anselmo
 * @author Joey Kaz 
 * @author Lance Trinidad
 * @version 11/19/2023
 */
public class searchControl {

    private Scanner scanner;
    protected LinkedList<String> searchCriteria;
    LinkedList<Room> results = new LinkedList<Room>();
    String attributes[];
    String roomID, roomType;
    int hotelID, numberBed, floorNumber, roomNumber;
    boolean avaialbility;
    double price;
    File roomFile, bookingFile, tmpFile;
    String searchbar;
    LocalDate checkIn, checkOut, compare, today, dateAttributes;
    HashMap<String,ArrayList<BookingDate>> reservedRooms;
    PrintWriter pw; 
    BookingDate bd;

    /**Constructor for searchControl
     * @param checkIn Chosen check in date
     * @param checkOut Chosen checkout date
    */
    searchControl(LocalDate checkIn, LocalDate checkOut) {
        roomFile = new File("./Room.txt");
        bookingFile = new File("./ReservedRooms.txt");
        tmpFile = new File("./tmp.txt");
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        dateAttributes = LocalDate.now();
        today = LocalDate.of(dateAttributes.getYear(), dateAttributes.getMonthValue(), dateAttributes.getDayOfMonth());
        reservedRooms = new HashMap<String,ArrayList<BookingDate>>();
    }

     //returns ArrayList<Integer> of the Hotel IDs that match the searchCriteria
     //look to hotels.txt for format of hotel info/criteria. It is pretty sensitive

    
    
    /**Searches the bookingFile to note which rooms are booked
      */
    private void readReservedRooms() {
        try {
            scanner = new Scanner(bookingFile);
            while(scanner.hasNext()) {
                attributes = scanner.next().split(",");
                if(!reservedRooms.containsKey(attributes[4])) {
                    ArrayList <BookingDate> tmp = new ArrayList<BookingDate>();
                    tmp.add(new BookingDate(convertDate(attributes[1]), convertDate(attributes[2])));
                    reservedRooms.put(attributes[4],tmp);
                }
                else {
                    reservedRooms.get(attributes[4]).add(new BookingDate(convertDate(attributes[1]), convertDate(attributes[2])));
                }
                
            } // end while
        } // end try

        catch(IOException e){e.printStackTrace();}

        finally {
            scanner.close();
        } // end finally
    } // end read rooms method

    
    /**Sorts a LinkedList of BookingDate objects so that the associated rooms are in order 
     * @param list An arraylist containings the Booking Dates of reserved rooms 
     */
    public void sortReservedRooms(ArrayList<BookingDate> list) {
        for(int i = 0; i < list.size() - 1; i++) {
            int minIndex = i;
            for(int j = i + 1; j < list.size(); j++) {
                if(compareDates(list.get(minIndex).checkIn, list.get(j).checkIn) < 0) {
                    minIndex = j;
                }
            }
            if(minIndex != i) {
                BookingDate tmp = list.get(i);
                list.set(i, list.get(minIndex));
                list.set(minIndex, tmp);
            }
        }

    }

    
    /**Converts the date into a LocalDate object
     * @param ld the date which is going to be convereted into a LocalDate object
     * @return LocalDate object after converting the given date
     */
    public LocalDate convertDate(String ld) {
        String[] dateComponents = ld.split("/");
        LocalDate tmp = LocalDate.of(Integer.parseInt(dateComponents[2]), Integer.parseInt(dateComponents[0]), Integer.parseInt(dateComponents[1]));
        return tmp;
    }

    
    /**Writes the room being reserved into ReservedRooms.txt
     * @param current the Room being booked
     * @param pw the PrintWriter object being used to write into a .txt file
     */
    private void writeTempFile(Room current, PrintWriter pw) {
        pw.append(current.hotelID + "," + current.roomID + ",");
        pw.append(Integer.toString(current.numberBed) +"," + Boolean.toString(current.avaialbility) + ",");
        pw.append(Integer.toString(current.floorNumber) + "," + Integer.toString(current.roomNumber) + ",");
        pw.append(current.roomType + "," + Double.toString(current.price) + "\n");
    }

    
    /**Searchs hotel.txt for hotels with criteria matching the provided list of criteria
     * @return returns ArrayList<Integer> of hotel IDs whose names match searchBar
     */
    private ArrayList<Integer> filterSearchCriteria() {
        ArrayList<Integer> hotelResults = new ArrayList<>(); 
        if(searchCriteria.isEmpty()) {
            return hotelResults;
        }
        try{
            scanner = new Scanner(new File("hotels.txt"));
            while(scanner.hasNextLine()) {
                String[] readLine = scanner.next().split(",");
                int currentHotel = Integer.parseInt(readLine[2]);
                readLine = scanner.next().replace("_"," ").split(",");
                int count = 0;
                for(int i = 0; i < searchCriteria.size(); i++) {
                    if(Arrays.asList(readLine).contains(searchCriteria.get(i).replace('_',' ')) && !(hotelResults.contains(currentHotel))) {
                        count++;
                    }
                    if(count == searchCriteria.size()){
                        hotelResults.add(currentHotel);
                        count = 0;
                    }
                }
            }
            scanner.close();
        } catch(FileNotFoundException e) {}
        return hotelResults;
     }

    
    /**Searchs hotels.txt for hotels that match the
     * @return returns ArrayList<Integer> of hotel IDs whose names match searchBar
     */
    private ArrayList<Integer> searchBarFilter() {
        ArrayList<Integer> nameMatch = new ArrayList<>();
        if(searchbar.compareTo("") == 0) {
            return nameMatch;
        }
        try {
            scanner = new Scanner(new File("hotels.txt"));
            while(scanner.hasNextLine()) {
                String[] readLine = scanner.next().split(",");
                if(readLine[0].toLowerCase().replace('_',' ').contains(searchbar.toLowerCase())) {
                    nameMatch.add(Integer.parseInt(readLine[2]));
                }
                readLine = scanner.next().split(",");
            }
            scanner.close();
        } catch(FileNotFoundException e) {}
        return nameMatch;
    }

    //this is for if searchbar is nonsense and criteria has at least one

    /**Modifies the global linked list, results, to be intialized with Room objects that match the criteria and String provided in the search
     */
    private void filterRoomSearch(){
        ArrayList<Integer> nameMatchIDs = searchBarFilter();
        ArrayList<Integer> criteriaMatchIDs = filterSearchCriteria();
        ArrayList<Integer> hotelIDs = new ArrayList<>();
        if(nameMatchIDs.isEmpty()) {
            hotelIDs = criteriaMatchIDs;
        } else if(criteriaMatchIDs.isEmpty()) {
            hotelIDs = nameMatchIDs;
        } else {
            //combines matching elements from nameMatchIDs and criteriaMatchIDs into hotelIDs
            for(int i = 0; i < criteriaMatchIDs.size(); i++) {
                if(nameMatchIDs.contains(criteriaMatchIDs.get(i))) {
                    hotelIDs.add(criteriaMatchIDs.get(i));
                }
            }
        }
        try{
            scanner = new Scanner(new File("Room.txt"));
                while(scanner.hasNextLine()){
                    attributes = scanner.next().split(",");
                    if(hotelIDs.contains(Integer.parseInt(attributes[0])) && Boolean.parseBoolean(attributes[3]) != false){
                        addRoom();
                    }
                    // new code //
                    else if(hotelIDs.contains(Integer.parseInt(attributes[0])) && Boolean.parseBoolean(attributes[3]) == false) {
                        switch(checkOverlaps(reservedRooms.get(attributes[1]), this.checkIn, this.checkOut)) { // check overlaps with requested checkin/checkout date
                            case 0:
                                addRoom();
                                break;
                            default:
                                break;
                        }
                    }
                } // end while
            scanner.close();
        } catch(FileNotFoundException e){}
    } 

    
    /**Compares two LocalDate objects 
     * @param firstDate The date that was selected
     * @param secondDate The date that is being compared to first date
     * @return long
     */
    public long compareDates(LocalDate firstDate, LocalDate secondDate) {
        Duration duration = Duration.between(firstDate.atStartOfDay(), secondDate.atStartOfDay());
        long diff = duration.toDays();
        return diff;
    }

    
    /**Checks the dates inbetween the check in date and check out date of a booking object
     * @param bd The date which is booked
     * @param checkIn The check in date
     * @param checkOut The check out date
     * @return an int signifying whether or not there is a difference
     */
    public int checkBetween(BookingDate bd, LocalDate checkIn, LocalDate checkOut) {
        if(compareDates(bd.checkIn, checkIn) >= 0 && compareDates(bd.checkOut, checkIn) < 0) {return 1;} // compare check in date
        if(compareDates(bd.checkIn, checkOut) > 0 && compareDates(checkOut, bd.checkOut) > 0) {return 1;} // compare check out date
        return 0;
    }

    
    /**Checks the overlapping Check in and Check Out dates in reservedRooms.txt 
     * @param list a list of BookingDates refering to the rooms that have been reserved
     * @param checkIn The check in date that we want to compare
     * @param checkOut The check out date that we want to compare
     * @return an int signifiying if there were any overlapped dates
     */
    public int checkOverlaps(ArrayList<BookingDate> list, LocalDate checkIn, LocalDate checkOut) {
        BookingDate bd;
        if(list == null) {
            return 0;
        }
        if(list.size() == 0) {return 0;} // might not need this line to check
        else if(list.size() == 1) {
            bd = list.get(0);
            if(compareDates(bd.checkOut, checkIn) >= 0 || compareDates(checkOut, bd.checkIn) >= 0) {
                return 0;
            }
        } // end else-if
        // else statement will check to see if a date falls between any booking. If it doesn't, it will then check to see if the booking check in
        // and check out dates fall between the list's current and next bookings
        else {
            bd = list.get(0);
            if(compareDates(bd.checkOut, checkIn) >= 0 || compareDates(checkOut, bd.checkIn) >= 0) {
                return 0;
            }
            for(int i = 0; i < list.size() - 1; i++) {
                BookingDate tmp = list.get(i);
                BookingDate tmp2 = list.get(i+1);
                if(checkBetween(tmp, checkIn, checkOut) == 1) {
                    return 1;
                }
                if(compareDates(tmp.checkOut,checkIn) >= 0 && compareDates(checkOut,tmp2.checkIn) >= 0) {
                    return 0;
                }
            } // end for loop
            if(compareDates(list.get(list.size() - 1).checkOut, checkIn) >= 0) {return 0;}
        }
        return 1;
    }

    /**Modifies the global linked list, results, to be intialized with all the available rooms in Room.txt
     */
    private void allRooms() {
        try{
            scanner = new Scanner(new File("Room.txt"));
            while(scanner.hasNext()) {
                attributes = scanner.next().split(",");
                switch(checkOverlaps(reservedRooms.get(attributes[1]), this.checkIn, this.checkOut)) { // check overlaps with requested checkin/checkout date
                    case 0:
                        addRoom();
                        break;
                    default:
                        break;
                }
            }
            scanner.close();
        } catch(FileNotFoundException e) {}
    }

    /**Returns a linked list of Room objects which fit the criteria and hotel name provided by the user
     * @param criteria: List of criteria the user provides
     * @param searchbar: Hotel name that the user wants to search for
     * @return LinkedList<Room> of rooms which fit the provided criteria
     */
    public LinkedList<Room> searchResults(LinkedList<String> criteria, String searchbar) {
        readReservedRooms();
        this.searchbar = searchbar;
        searchCriteria = criteria;
        //checks if searchBar and searchCriteria are both empty
        if(this.searchbar.compareTo("") == 0 && searchCriteria.isEmpty()) {
            allRooms();
            return results;
        }
        filterRoomSearch();
        return results;
    }

    /**Creates room objects and adds them to the global linked list, results
     */
    public void addRoom() {
        assignAttributes();
        results.add(new Room(hotelID, roomID, numberBed, avaialbility, floorNumber, roomNumber, roomType, price));
    }
    
    /**Assigns attributes to the data types defined in the class header
     */
    public void assignAttributes() {
        hotelID = Integer.parseInt(attributes[0]);
        roomID = attributes[1];
        numberBed = Integer.parseInt(attributes[2]);
        avaialbility = Boolean.parseBoolean(attributes[3]);
        floorNumber = Integer.parseInt(attributes[4]);
        roomNumber = Integer.parseInt(attributes[5]);
        roomType = attributes[6];
        price = Double.parseDouble(attributes[7]);
    }

}
