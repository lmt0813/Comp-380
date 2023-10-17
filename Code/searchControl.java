import java.util.*;

public class searchControl {
     protected LinkedList<String> searchCriteria;
     
     public LinkedList<Hotel> searchResults(LinkedList<String> search){
        ArrayList<Hotel> results = new ArrayList<Hotel>();

        ArrayList<Hotel> dummyList;

        //assuming that an array of hotels exist and a different class has this
        // method to pass said list on

        //also need to make getCriteria method in hotel.java

        //dummyList = getHotelList();  

        for(int i = 0; i < dummmyList.length(); i++){
            for(int j = 0; j < dummyList.get(i).getCriteria.length(); j++){
                if(dummyList.get(i).getCriteria[j].compareTo(search[i]) == 0){
                    results.add(dummyList.get(i));
                }
            }

        }

        return results;
     }
    
}
