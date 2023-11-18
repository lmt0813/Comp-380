import java.util.*;
import java.io.*;
import java.time.*;

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
   
    /*
     goals for this class:
     1) Create an additional method specifically for scanning from the text file. The searchResults method below should be a main method
     that calls other methods and returns the list called from the HotelGUI class
     2) Create a method that filters results based on the search criteria. We will most likely have to put two lines per hotel on the text file: one
     for the attributes, and a second for criteria.
     3) Could either create a initial list to scan everything and second list to return based on the filtered criteria, or load everything in to one list.,
     check the entire list, and remove every room that doesn't fit the criteria
     4) Most likely scan hotel text file, check for attributes per hotel, then scan the room list based on hotel ID
     */

     //returns ArrayList<Integer> of the Hotel IDs that match the searchCriteria
     //look to hotels.txt for format of hotel info/criteria. It is pretty sensitive

    

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

    public LocalDate convertDate(String ld) {
        String[] dateComponents = ld.split("/");
        LocalDate tmp = LocalDate.of(Integer.parseInt(dateComponents[2]), Integer.parseInt(dateComponents[0]), Integer.parseInt(dateComponents[1]));
        return tmp;
    }

    private void writeTempFile(Room current, PrintWriter pw) {
        pw.append(current.hotelID + "," + current.roomID + ",");
        pw.append(Integer.toString(current.numberBed) +"," + Boolean.toString(current.avaialbility) + ",");
        pw.append(Integer.toString(current.floorNumber) + "," + Integer.toString(current.roomNumber) + ",");
        pw.append(current.roomType + "," + Double.toString(current.price) + "\n");
    }

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
                System.out.println(Arrays.toString(readLine));
                int index = 0;
                int count = 0;
                while(index < searchCriteria.size()) {
                    if(Arrays.asList(readLine).contains(searchCriteria.get(index).replace('_',' ')) && !(hotelResults.contains(currentHotel))) {
                        count++;
                    }
                    if(count == searchCriteria.size()){
                        hotelResults.add(currentHotel);
                        count = 0;
                    }
                    index++;
                }
            }
            scanner.close();
        } catch(FileNotFoundException e) {}
        return hotelResults;
     }


    //returns ArrayList<Integer> of hotel IDs whose names match searchBar
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

    //returns an LinkedList<Room> of rooms that matched the searchCriteria.
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

    public long compareDates(LocalDate previousCheckOut, LocalDate checkout) {
        Duration duration = Duration.between(previousCheckOut.atStartOfDay(), checkout.atStartOfDay());
        long diff = duration.toDays();
        return diff;
        
    }

    public int checkBetween(BookingDate bd, LocalDate checkIn, LocalDate checkOut) {
        // date can be either a checkin or checkout date
        if(compareDates(bd.checkIn, checkIn) >= 0 && compareDates(bd.checkOut, checkIn) < 0) {return 1;} // compare check in date
        if(compareDates(bd.checkIn, checkOut) > 0 && compareDates(checkOut, bd.checkOut) > 0) {return 1;} // compare check out date
        return 0;
    }

    public int checkOverlaps(ArrayList<BookingDate> list, LocalDate checkIn, LocalDate checkOut) {
        BookingDate bd;
        if(list.size() == 0) {return 0;} // might not need this line to check
        else if(list.size() == 1) {
            bd = list.get(0);
            if(compareDates(bd.checkOut, checkIn) >= 0 || compareDates(checkOut, bd.checkIn) >= 0) {
                return 0;
            }
        } // end else-if
        else {
            for(BookingDate bookingDate: list) {
                if(checkBetween(bookingDate, checkIn, checkOut) == 1) {
                    list.remove(bookingDate);

                }
            }
        }
        return 1;
    }

    private void allRooms() {
        try{
            scanner = new Scanner(new File("Room.txt"));
            while(scanner.hasNext()) {
                attributes = scanner.next().split(",");
                addRoom();
            }
            scanner.close();
        } catch(FileNotFoundException e) {}
    }


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

    // method that adds Room Objects to the results list
    public void addRoom() {
        assignAttributes();
        results.add(new Room(hotelID, roomID, numberBed, avaialbility, floorNumber, roomNumber, roomType, price));
    }


    // method that assigns sent attributes to the data types defined in the class header
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
