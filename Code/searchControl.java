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
     
    public LinkedList<Room> searchResults(LinkedList<String> search){
        return results;
     }

     public LinkedList<Room> searchResults() {
        try {
            scanner = new Scanner(roomFile);
            while(scanner.hasNextLine()) {
                attributes = scanner.next().split(",");
                System.out.println("Scanned");
                addRoom(attributes);
            }
        }

        catch(FileNotFoundException e){e.printStackTrace();}

        catch(NoSuchElementException e){e.printStackTrace();}

        finally {scanner.close();}

        return results;
    }

    public void addRoom(String[] attStrings) {
        assignAttributes();
        results.add(new Room(hotelID, roomID, numberBed, avaialbility, floorNumber, roomNumber, roomType, price));
        System.out.println("room added");
    }

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
    
}
