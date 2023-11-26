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
            rooms.add(new Room(1,"1-13",2,false,1,13,"King",278.00));
            rooms.add(new Room(2,"2-465",2,true,4,465,"Queen",127.00));
            rooms.add(new Room(3,"3-237",2,true,2,237,"Standard",79.00));
            rooms.add(new Room(4,"4-212",1,false,2,212,"Standard",62.00));
            rooms.add(new Room(5,"5-111",1,true,1,111,"Standard",47.00));
            //rooms.add(new Room(1,"1-14",1,false,1,14,"Twin",333.00));
            searchControl sc = new searchControl(checkin, checkout);
            LinkedList<String> criteria = new LinkedList<>();
            String searchbar = "";
            LinkedList<Room> result = sc.searchResults(criteria,searchbar);
            assertEquals(rooms.toString(), result.toString());
        }

        
        
        @Test
        public void emptyCriteria() {
            LinkedList<Room> rooms = new LinkedList<>();
            rooms.add(new Room(1,"1-13",2,false,1,13,"King",278.00));
            //rooms.add(new Room(2,"2-465",2,true,4,465,"Queen",127.00));
            //rooms.add(new Room(3,"3-237",2,true,2,237,"Standard",79.00));
            //rooms.add(new Room(4,"4-212",1,false,2,212,"Standard",62.00));
            //rooms.add(new Room(5,"5-111",1,true,1,111,"Standard",47.00));
            //rooms.add(new Room(1,"1-14",1,false,1,14,"Twin",333.00));
            searchControl sc = new searchControl(checkin,checkout);
            LinkedList<String> criteria = new LinkedList<>();
            String searchbar = "The Hilton Inn";
            LinkedList<Room> result = sc.searchResults(criteria,searchbar);
            assertEquals(rooms.toString(), result.toString());
        }

        @Test
        public void emptySearchbar() {
            LinkedList<Room> rooms = new LinkedList<>();
            rooms.add(new Room(1,"1-13",2,false,1,13,"King",278.00));
            //rooms.add(new Room(2,"2-465",2,true,4,465,"Queen",127.00));
            //rooms.add(new Room(3,"3-237",2,true,2,237,"Standard",79.00));
            rooms.add(new Room(4,"4-212",1,false,2,212,"Standard",62.00));
            rooms.add(new Room(5,"5-111",1,true,1,111,"Standard",47.00));
            //rooms.add(new Room(1,"1-14",1,false,1,14,"Twin",333.00));
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
            //rooms.add(new Room(1,"1-13",2,false,1,13,"King",278.00));
            rooms.add(new Room(2,"2-465",2,true,4,465,"Queen",127.00));
            //rooms.add(new Room(3,"3-237",2,true,2,237,"Standard",79.00));
            //rooms.add(new Room(4,"4-212",1,true,2,212,"Standard",62.00));
            //rooms.add(new Room(5,"5-111",1,false,1,111,"Standard",47.00));
            //rooms.add(new Room(1,"1-14",1,true,1,14,"Twin",333.00));
            searchControl sc = new searchControl(checkin, checkout);
            LinkedList<String> criteria = new LinkedList<>();
            criteria.add("Pet Friendly");
            String searchbar = "Ramada Inn";
            LinkedList<Room> result = sc.searchResults(criteria,searchbar);
            assertEquals(rooms.toString(), result.toString());
        }

        @Test
        public void searchbarIncorrectAndCriteriaNotEmpty() {
            LinkedList<Room> rooms = new LinkedList<>();
            //rooms.add(new Room(1,"1-13",2,true,1,13,"King",278.00));
            rooms.add(new Room(2,"2-465",2,true,4,465,"Queen",127.00));
            rooms.add(new Room(3,"3-237",2,true,2,237,"Standard",79.00));
            //rooms.add(new Room(4,"4-212",1,true,2,212,"Standard",62.00));
            //rooms.add(new Room(5,"5-111",1,true,1,111,"Standard",47.00));
            //rooms.add(new Room(1,"1-14",1,true,1,14,"Twin",333.00));
            searchControl sc = new searchControl(checkin, checkout);
            LinkedList<String> criteria = new LinkedList<>();
            criteria.add("Pet Friendly");
            String searchbar = "dsadhfjldshfs";
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