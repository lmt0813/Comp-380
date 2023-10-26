import java.util.*;
import java.io.*;

public class searchControl {

    private Scanner scanner;
    protected LinkedList<String> searchCriteria;
    LinkedList<Room> results = new LinkedList<Room>();
    String attributes[];
    String hotelID, roomID, roomType;
    int numberBed, floorNumber, roomNumber;
    boolean avaialbility;
    double price;
    File roomFile;


    searchControl() {roomFile = new File("./Room.txt");}
   
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





     //returns ArrayList<String> of names of Hotels
     //look to hotels.txt for format of hotel info/criteria. It is pretty sensitive
     //will have to change testSearchCriteria to searchCriteria after its initialization
     public ArrayList<String> filterSearchCriteria() {
        //dummy code for testing since searchCriteria is not initialized
        LinkedList<String> testSearchCriteria = new LinkedList<>();
        testSearchCriteria.add("free_WI-FI");
        testSearchCriteria.add("pool");
        //start of method
        ArrayList<String> hotelResults = new ArrayList<>(); 
        try{
            scanner = new Scanner(new File("hotels.txt"));
            while(scanner.hasNextLine()) {
                //probably a more efficient way to do this and might change later
                String[] nLine = scanner.next().split(",");
                String currentHotelName = nLine[0];
                nLine = scanner.next().split(",");
                int index = 0;
                while(index < testSearchCriteria.size()) {
                    if(Arrays.asList(nLine).contains(testSearchCriteria.get(index)) && !hotelResults.contains(currentHotelName)) {
                        hotelResults.add(currentHotelName);
                    }
                    index++;
                }
            }
            scanner.close();
        } catch(FileNotFoundException e) {}

        return hotelResults;
     }

     public LinkedList<Room> searchResults(LinkedList<String> search) {
        try {
            scanner = new Scanner(roomFile);
            while(scanner.hasNextLine()) {
                attributes = scanner.next().split(",");
                System.out.println("Scanned");
                addRoom(attributes);
            }
            System.out.println("Finished scanning");
        }

        catch(FileNotFoundException e){e.printStackTrace();}

        catch(NoSuchElementException e){e.printStackTrace();}

        finally {scanner.close();}

        return results;
    }

    // method that adds rooms to the return list

    public void addRoom(String[] attStrings) {
        assignAttributes();
        results.add(new Room(hotelID, roomID, numberBed, avaialbility, floorNumber, roomNumber, roomType, price));
        System.out.println("room added");
    }

    // method that assigns sent attributes to the data types defined in the class header
    public void assignAttributes() {
        hotelID = attributes[0];
        roomID = attributes[1];
        numberBed = Integer.parseInt(attributes[2]);
        avaialbility = Boolean.parseBoolean(attributes[3]);
        floorNumber = Integer.parseInt(attributes[4]);
        roomNumber = Integer.parseInt(attributes[5]);
        roomType = attributes[6];
        price = Double.parseDouble(attributes[7]);
    }
    

    public static void main(String[] args) {
        
    }

}
