
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientRater implements Rater{
    private String myID;
    private Map<String,Rating> myMap;

    public EfficientRater(String id) {
        myID = id; //rater id
        myMap = new HashMap(); //use hashMap to store raing with movie ID
    }

    public void addRating(String item, double rating) {
        myMap.put(item,new Rating(item,rating));
    }

    //method to check if this rater has rated movie with movie id (String item)
    public boolean hasRating(String item) {
        return myMap.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    //for movie item, return the rating from this rater
    public double getRating(String item) {
        return myMap.get(item).getValue();
    }

    public int numRatings() {
        return myMap.size();
    }

    //for this rater, get movie ids that he/she rated
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String s : myMap.keySet()){
            list.add(s);
        }
        return list;
    }
}

