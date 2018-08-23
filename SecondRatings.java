
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    //another constructor
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firRate = new FirstRatings();
        myMovies = firRate.loadMovies("data/" + moviefile);
        myRaters = firRate.loadRaters("data/" + ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
        ArrayList<Rating> ratingList = new ArrayList();
        for(Movie m: myMovies){
            String mId = m.getID();
            double avg = getAverageByID(mId, minimalRaters);
            if(avg > 0.0){
                Rating currRating = new Rating(mId,avg);
                ratingList.add(currRating);
            }
        }
        return ratingList;
    }
    
    //return movie title
    public String getTitle(String id){
        for(Movie m: myMovies){
            if(m.getID().equals(id)){
                return m.getTitle();
            }
        }
        return "NO SUCH MOVIE ID";
    }
    
    //return movie ID
    public String getID(String title){
        for(Movie m: myMovies){
            if(m.getTitle().equals(title)){
                return m.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
}

