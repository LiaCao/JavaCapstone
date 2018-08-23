
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    //another constructor
    public ThirdRatings(String ratingsfile) {
        FirstRatings firRate = new FirstRatings();
        myRaters = firRate.loadRaters("data/" + ratingsfile);
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    //the average movie rating for this ID if there are at least minimalRaters ratings
    private double getAverageByID(String id, int minimalRaters){
        ArrayList<Rater> raterList = new ArrayList();
        for(Rater rt: myRaters){
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
}

