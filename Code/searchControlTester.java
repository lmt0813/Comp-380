import static org.junit.Assert.assertEquals;
import java.util.*;

import javax.naming.directory.SearchControls;

import java.io.*;
import java.time.LocalDate;

import org.junit.Test;


    public class searchControlTester {

        LocalDate checkin = LocalDate.of(2023, 2, 1);
        LocalDate checkout = LocalDate.of(2023, 2, 14);

        @Test
        public void emptySearchbarAndEmptyCriteria() {
            LinkedList<Room> rooms = new LinkedList<>();
            try {Scanner sc = new Scanner(new File("Room.txt"));
                while(sc.hasNextLine()) {
                    String[] attributes = sc.next().split(",");
                    Room room = new Room(Integer.parseInt(attributes[0]), attributes[1], Integer.parseInt(attributes[2]),
                    Boolean.parseBoolean(attributes[3]), Integer.parseInt(attributes[4]),
                    Integer.parseInt(attributes[5]), attributes[6], Double.parseDouble(attributes[7]));
                    rooms.add(room);
                }
                rooms.remove(0);
                rooms.remove(2);
                rooms.remove(3);
            }
            
            catch(FileNotFoundException e) {}
            searchControl sc = new searchControl(checkin, checkout);
            LinkedList<String> criteria = new LinkedList<>();
            String searchbar = "";
            LinkedList<Room> result = sc.searchResults(criteria,searchbar);
            assertEquals(rooms.toString(), result.toString());
        }

        
        
        @Test
        public void emptyCriteria() {
            //1,1-13,2,true,1,13,King,278.00
            //1,1-14,1,true,1,14,Twin,333.00
            LinkedList<Room> rooms = new LinkedList<>();
            //rooms.add(new Room(1, "1-13", 2, true, 1, 13, "King", 278.00));
            //rooms.add(new Room(1,"1-14",1,true,1,14,"Twin",333.00));
            searchControl sc = new searchControl(checkin,checkout);
            LinkedList<String> criteria = new LinkedList<>();
            String searchbar = "The Hilton Inn";
            LinkedList<Room> result = sc.searchResults(criteria,searchbar);
            assertEquals(rooms.toString(), result.toString());
        }

        @Test
        public void emptySearchbar() {
            LinkedList<Room> rooms = new LinkedList<>();
            //rooms.add(new Room(1, "1-13", 2, true, 1, 13, "King", 278.00));
            rooms.add(new Room(5,"5-111",1,true,1,111,"Standard",47.00));
            //rooms.add(new Room(1, "1-14", 1, true, 1, 14, "Twin", 333.00));
            searchControl sc = new searchControl(checkin, checkout);
            LinkedList<String> criteria = new LinkedList<>();
            criteria.add("Pool");
            String searchbar = "";
            LinkedList<Room> result = sc.searchResults(criteria,searchbar);
            assertEquals(rooms.toString(), result.toString());
        }

        @Test
        public void searchBarAndCriteriaNotEmpty() {
            LinkedList<Room> rooms = new LinkedList<>();
          
            rooms.add(new Room(2, "2-465", 2, true, 4, 465, "Queen", 127.00));
            searchControl sc = new searchControl(checkin, checkout);
            LinkedList<String> criteria = new LinkedList<>();
            criteria.add("Pet Friendly");
            String searchbar = "Ramada Inn";
            LinkedList<Room> result = sc.searchResults(criteria,searchbar);
            assertEquals(rooms.toString(), result.toString());
        }
        //2,2-465,2,true,4,465,Queen,127.00
        //3,3-237,2,true,2,237,Standard,79.00
        //2,2-84,1,true,1,84,Twin,81.00
        @Test
        public void searchbarIncorrectAndCriteriaNotEmpty() {
            LinkedList<Room> rooms = new LinkedList<>();
            rooms.add(new Room(2, "2-465", 2, true, 4, 465, "Queen", 127.00));
            rooms.add(new Room(3, "3-237", 2, true, 2, 237, "Standard", 79.00));
           //dunno what expected value should be
           //should it return all Rooms under Pet Friendly or nothing at all since it doesn't match searchbar?
            searchControl sc = new searchControl(checkin, checkout);
            LinkedList<String> criteria = new LinkedList<>();
            criteria.add("Pet Friendly");
            String searchbar = "dsadhfjldshfj";
            LinkedList<Room> result = sc.searchResults(criteria,searchbar);
            assertEquals(rooms.toString(), result.toString());
        }

        @Test 
        public void testDateBetweenTrue() {
            LocalDate one = LocalDate.of(2023, 11, 5);
            LocalDate two = LocalDate.of(2023, 11,17);
            LocalDate checkIn = LocalDate.of(2023,11,7);
            LocalDate checkOut = LocalDate.of(2023, 11, 14);
            BookingDate bd = new BookingDate(one,two);
            searchControl sc = new searchControl(checkIn, checkOut);
            int test = sc.checkBetween(bd, checkIn, checkOut);
            assertEquals(test, 1);
        }

        @Test 
        public void testDateBetweenFalse() {
            LocalDate one = LocalDate.of(2023, 11, 5);
            LocalDate two = LocalDate.of(2023, 11,17);
            LocalDate checkIn = LocalDate.of(2023,11,17);
            LocalDate checkOut = LocalDate.of(2023, 11, 25);
            BookingDate bd = new BookingDate(one,two);
            searchControl sc = new searchControl(checkIn, checkOut);
            int test = sc.checkBetween(bd, checkIn, checkOut);
            assertEquals(test, 0);
        }

        
    }