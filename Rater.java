
/**
 * Write a description of Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public interface Rater {
    public void addRating(String item, double rating);
    
    
    //method to check if this rater has rated movie with movie id (String item)
    public boolean hasRating(String item);
    
    public String getID();

    //for movie item, return the rating from this rater
    public double getRating(String item);

    public int numRatings();

    //for this rater, get movie ids that he/she rated
    public ArrayList<String> getItemsRated();
}
