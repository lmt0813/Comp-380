import java.util.*;
import java.io.*;

public class searchControl {

    private Scanner scanner;
    protected LinkedList<String> searchCriteria;
    LinkedList<Room> results = new LinkedList<Room>();
    String attributes[];
    String roomID, roomType;
    int hotelID, numberBed, floorNumber, roomNumber;
    boolean avaialbility;
    double price;
    File roomFile;
    String searchbar;

    //searchControl()
    //Constructor for searchControl
    //Input: none
    //Output: none
    searchControl() {
        roomFile = new File("./Room.txt");
    }

     //filterSearchCriteria()
     //Searchs hotel.txt for hotels with criteria matching the provided list of criteria
     //Input: none
     //Output: ArrayList<Integer>: A list of hotel IDs which refer to hotels that match the criteria that the user provided
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

    //searchBarFilter()
    //Searchs hotels.txt for hotels that match the String provided by the user
    //Input: none
    //Output: ArrayList<Integer>: A list of hotel IDs which refer to hotels that match the String that the user provided
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

    //filterRoomSearch()
    //Modifies the global linked list, results, to be intialized with Room objects that match the criteria and String provided in the search
    //Input: none
    //Output: void
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
                }
            scanner.close();
        } catch(FileNotFoundException e){}
    } 

    //allRooms()
    //Modifies the global linked list, results, to be intialized with all the available rooms in Room.txt
    //Input: none
    //Output: void
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

    //searchResults(LinkedList<String> criteria, String searchbar)
    //Returns a linked list of Room objects which fit the criteria and hotel name provided by the user
    //Input: LinkedList<String> criteria: List of criteria the user provides
    //       String searchbar: Hotel name that the user wants to search for
    //Output: LinkedList<Room>: List of rooms which fit the provided criteria
    public LinkedList<Room> searchResults(LinkedList<String> criteria, String searchbar) {
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

    //addRoom()
    //Creates room objects and adds them to the global linked list, results
    //Input: none
    //Output: void
    public void addRoom() {
        assignAttributes();
        results.add(new Room(hotelID, roomID, numberBed, avaialbility, floorNumber, roomNumber, roomType, price));
    }


    //assignAttributes()
    //Assigns attributes to the data types defined in the class header
    //Input: none
    //Output: void
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
