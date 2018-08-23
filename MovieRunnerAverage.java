
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {

    //Given minimal rates required, print out ratings in ascending order
    public void printAverageRatings(){
        //String moviename = "ratedmovies_short.csv";
        String moviename = "ratedmoviesfull.csv";
        //String ratingname = "ratings_short.csv";
        String ratingname = "ratings.csv";
        int minimalRaters = 12;
        
        SecondRatings secRate = new SecondRatings(moviename, ratingname);
        System.out.println("The number of movies: " + secRate.getMovieSize());
        System.out.println("The number of raters: " + secRate.getRaterSize());
        
        ArrayList<Rating> orderRateList = secRate.getAverageRatings(minimalRaters);
        System.out.println("There are " + orderRateList.size() + " number of movies that have " + minimalRaters + " or more ratings");
        Collections.sort(orderRateList);//ArrayList to store ratings from smallest to largest
        
        //print rating list in order
        for(int k = 0; k < orderRateList.size(); k++){
            System.out.println(String.format("%.2f",orderRateList.get(k).getValue()) + " " + secRate.getTitle(orderRateList.get(k).getItem()));
        }
        
    }
    
    //Given a movie title, print the average rating
    public void getAverageRatingOneMovie(){
        //String moviename = "ratedmovies_short.csv";
        String moviename = "ratedmoviesfull.csv";
        //String ratingname = "ratings_short.csv";
        String ratingname = "ratings.csv";
        //String title = "The Godfather";
        //String title = "The Maze Runner";
        //String title = "Moneyball";
        String title = "Vacation";
        
        SecondRatings secRate = new SecondRatings(moviename, ratingname);
        String movieID = secRate.getID(title);
        ArrayList<Rating> ratingList = secRate.getAverageRatings(1);
        
        for(Rating rt: ratingList){
            if(rt.getItem().equals(movieID)){
                double avgRating = rt.getValue();
                System.out.println("The average rating for the movie \"" + title + "\" would be " + avgRating);
                break;
            }
        }
    }
}
