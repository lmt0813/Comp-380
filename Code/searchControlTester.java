import static org.junit.Assert.assertEquals;
import java.util.*;
import java.io.*;

import org.junit.Test;


    public class searchControlTester {
        

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
            }
            catch(FileNotFoundException e) {}
            searchControl sc = new searchControl();
            LinkedList<String> criteria = new LinkedList<>();
            String searchbar = "";
            LinkedList<Room> result = sc.searchResults(criteria,searchbar);
            assertEquals(rooms, result);
        }

        
        
        @Test
        public void emptyCriteria() {
            LinkedList<Room> rooms = new LinkedList<>();
            try {Scanner sc = new Scanner(new File("Room.txt"));
                for(int i = 0; i < 6; i++)  {
                    String[] attributes = sc.next().split(",");
                    if(i== 0 || i==5) {
                        
                        Room room = new Room(Integer.parseInt(attributes[0]), attributes[1], Integer.parseInt(attributes[2]),
                        Boolean.parseBoolean(attributes[3]), Integer.parseInt(attributes[4]),
                        Integer.parseInt(attributes[5]), attributes[6], Double.parseDouble(attributes[7]));
                        rooms.add(room);
                    }
                }
            }
            catch(FileNotFoundException e) {}
            searchControl sc = new searchControl();
            LinkedList<String> criteria = new LinkedList<>();
            String searchbar = "The_Hilton_Inn";
            LinkedList<Room> result = sc.searchResults(criteria,searchbar);
            assertEquals(rooms, result);
        }

        @Test
        public void emptySearchbar() {
            LinkedList<Room> rooms = new LinkedList<>();
            rooms.add(new Room(1, "1-13", 2, true, 1, 13, "King", 278.00));
            rooms.add(new Room(1, "1-14", 1, true, 1, 14, "Twin", 333.00));
            
            searchControl sc = new searchControl();
            LinkedList<String> criteria = new LinkedList<>();
            criteria.add("Pool");
            String searchbar = "";
            LinkedList<Room> result = sc.searchResults(criteria,searchbar);
            assertEquals(rooms, result);
        }

        @Test
        public void searchBarAndCriteriaNotEmpty() {
            LinkedList<Room> rooms = new LinkedList<>();
          
            rooms.add(new Room(2, "2-465", 2, true, 4, 465, "Queen", 127.00));
            rooms.add(new Room(2, "2-84", 1, true, 1, 84, "Twin", 81.00));
            searchControl sc = new searchControl();
            LinkedList<String> criteria = new LinkedList<>();
            criteria.add("Pet_Friendly");
            String searchbar = "Ramada_Inn";
            LinkedList<Room> result = sc.searchResults(criteria,searchbar);
            assertEquals(rooms, result);
        }

        @Test
        public void searchbarIncorrectAndCriteriaNotEmpty() {
            LinkedList<Room> rooms = new LinkedList<>();
           
            searchControl sc = new searchControl();
            LinkedList<String> criteria = new LinkedList<>();
            criteria.add("Pet_Friendly");
            String searchbar = "doo";
            LinkedList<Room> result = sc.searchResults(criteria,searchbar);
            assertEquals(rooms, result);
        }
    }