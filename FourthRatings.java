
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {

    //the average movie rating for this ID if there are at least minimalRaters ratings
    private double getAverageByID(String id, int minimalRaters){
        ArrayList<Rater> raterList = new ArrayList();
        for(Rater rt: RaterDatabase.getRaters()){
            if(rt.hasRating(id)){
                raterList.add(rt);
            }
        }
        if(raterList.size() >= minimalRaters){
            double sumRating = 0;
            for(Rater currRater: raterList){
                sumRating += currRater.getRating(id);
            }
            return sumRating/raterList.size();
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        return avgRating(movies, minimalRaters);
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        return avgRating(movies, minimalRaters);
    }
    
    //Given the movies that satisfies filter criteria, return list of Rating
    private ArrayList<Rating> avgRating(ArrayList<String> movies, int minimalRaters){
        ArrayList<Rating> ratingList = new ArrayList();
        for(String id : movies) {
            double avg = getAverageByID(id, minimalRaters);
            if(avg > 0.0){
                Rating currRating = new Rating(id,avg);
                ratingList.add(currRating);
            }
        }
        return ratingList;
    }
    
    private double dotProduct(Rater me,Rater r){
        double sum = 0.0;
        for(String s: me.getItemsRated()){
            if(r.hasRating(s)){
                double myRating = me.getRating(s);
                double rRating = r.getRating(s);
                sum += (myRating - 5.0) * (rRating - 5.0);
            }
        }
        return sum;
    }
    
    //Parameter is Rater ID, return ArrayList (Item Rater ID, value-dot product,positive only).
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> similar = new ArrayList();
        for(Rater rt : RaterDatabase.getRaters()){
            if(!rt.getID().equals(id)){//Make sure rt is not the parameter itself
                double dp = dotProduct(RaterDatabase.getRater(id),rt);
                if(dp > 0.0) {
                    similar.add(new Rating(rt.getID(),dp));
                }
            }
        }
        Collections.sort(similar);
        Collections.reverse(similar);
        return similar;
    }
    
    //Parameter is Rater id. Return ArrayList(Item-Movie ID, value -weighted average ratings using only the top numSimilarRaters 
    //with positive ratings and including only those movies that have at least minimalRaters ratings
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> similar = getSimilarities(id);
        ArrayList<Rating> topSimilar = new ArrayList();
        ArrayList<String> tgMovies = new ArrayList();
        ArrayList<Rating> recMovies = new ArrayList();
        
        for(int i = 0; i < numSimilarRaters && i < similar.size(); i++){
            topSimilar.add(similar.get(i));
            String rID = similar.get(i).getItem();
            Rater oRater = RaterDatabase.getRater(rID);
            for(String m : oRater.getItemsRated()){
                if(!tgMovies.contains(m))  {
                    tgMovies.add(m);
                }
            }
        }
        
        for(String m : tgMovies){
            double weighted = getWeigthtedAverageByID(m,topSimilar,minimalRaters);
            if(weighted > 0.0) {
                recMovies.add(new Rating(m,weighted));
            }
        }
        
        Collections.sort(recMovies);
        Collections.reverse(recMovies);
        return recMovies;
    }
    
    private double getWeigthtedAverageByID(String movieID, ArrayList<Rating> topSimilar, int minimalRaters){
        ArrayList<String> raterIDList = new ArrayList();
        for(Rating rtg: topSimilar){
            String raterID = rtg.getItem();
            Rater topRater = RaterDatabase.getRater(raterID);
            if(topRater.hasRating(movieID)){
                raterIDList.add(raterID);
            }
        }
        
        if(raterIDList.size() >= minimalRaters){
            //Product of weight and rating
            double sumProduct = 0.0;
            
            //use for loop to go through all qualified raters
            for(String currRaterID: raterIDList){
                //find the similar rating for this rater ID
                double weight=0.0;
                for(Rating rtg : topSimilar){
                    if(rtg.getItem().equals(currRaterID)){
                        weight = rtg.getValue();
                    }
                }
                //for the Rater, find the rating for movie in parameter
                double thisRating = RaterDatabase.getRater(currRaterID).getRating(movieID);
              
                sumProduct += weight*thisRating;
            }
            return sumProduct/raterIDList.size();
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> similar = getSimilarities(id);
        ArrayList<Rating> topSimilar = new ArrayList();
        ArrayList<String> tgMovies = new ArrayList();
        ArrayList<Rating> recMovies = new ArrayList();
        
        for(int i = 0; i < numSimilarRaters && i < similar.size(); i++){
            topSimilar.add(similar.get(i));
            String rID = similar.get(i).getItem();
            Rater oRater = RaterDatabase.getRater(rID);
            for(String m : oRater.getItemsRated()){
                if(!tgMovies.contains(m) && filterCriteria.satisfies(m))  {
                    tgMovies.add(m);
                }
            }
        }
        
        for(String m : tgMovies){
            double weighted = getWeigthtedAverageByID(m,topSimilar,minimalRaters);
            if(weighted > 0.0) {
                recMovies.add(new Rating(m,weighted));
            }
        }
        
        Collections.sort(recMovies);
        Collections.reverse(recMovies);
        return recMovies;
    }
}
