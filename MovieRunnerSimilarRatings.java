
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {
    //Given minimal rates required, print out ratings in ascending order, without filters
    public void printAverageRatings(){
        //String moviename = "ratedmovies_short.csv";
        String moviename = "ratedmoviesfull.csv";
        //String ratingname = "ratings_short.csv";
        String ratingname = "ratings.csv";
        int minimalRaters = 35;
        
        FourthRatings fourRate = new FourthRatings();
        MovieDatabase.initialize(moviename);
        RaterDatabase.initialize(ratingname);
        
        System.out.println("The number of raters: " + RaterDatabase.size());
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        ArrayList<Rating> orderRateList = fourRate.getAverageRatings(minimalRaters);
        System.out.println("There are " + orderRateList.size() + " number of movies that have " + minimalRaters + " or more ratings");
        Collections.sort(orderRateList);//ArrayList to store ratings from smallest to largest
        
        //print rating list in order
        for(int k = 0; k < orderRateList.size(); k++){
            System.out.println(String.format("%.2f",orderRateList.get(k).getValue()) + " " + MovieDatabase.getTitle(orderRateList.get(k).getItem()));
        }
    }
    
    //filter by allFilters (YearAfterFilter and GenreFilter)
    public void printAverageRatingsByYearAfterAndGenre(){
        //String moviename = "ratedmovies_short.csv";
        String moviename = "ratedmoviesfull.csv";
        //String ratingname = "ratings_short.csv";
        String ratingname = "ratings.csv";
        int minimalRaters = 8;
        int year = 1990;
        String genre = "Drama";
        
        FourthRatings fourRate = new FourthRatings();
        MovieDatabase.initialize(moviename);
        RaterDatabase.initialize(ratingname);
        
        System.out.println("The number of raters: " + RaterDatabase.size());
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(year));
        f.addFilter(new GenreFilter(genre));
        ArrayList<Rating> orderRateList = fourRate.getAverageRatingsByFilter(minimalRaters,f);
        System.out.println(orderRateList.size() + " movies matched");
        Collections.sort(orderRateList);//ArrayList to store ratings from smallest to largest
        
        //print rating list in order
        for(int k = 0; k < orderRateList.size(); k++){
            System.out.println(String.format("%.2f",orderRateList.get(k).getValue())  + " " + MovieDatabase.getYear(orderRateList.get(k).getItem()) + " " + MovieDatabase.getTitle(orderRateList.get(k).getItem()));
            System.out.println("    " + MovieDatabase.getGenres(orderRateList.get(k).getItem()));
        }
    }
    
    public void printSimilarRatings(){
        //String moviename = "ratedmovies_short.csv";
        String moviename = "ratedmoviesfull.csv";
        //String ratingname = "ratings_short.csv";
        String ratingname = "ratings.csv";
        int minimalRaters = 5;
        String raterID = "71";//to get similar ratings for this Rater with ID input here.
        int numSimilarRaters = 20;
        
        FourthRatings fourRate = new FourthRatings();
        MovieDatabase.initialize(moviename);
        RaterDatabase.initialize(ratingname);
        
        System.out.println("The number of raters: " + RaterDatabase.size());
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        ArrayList<Rating> orderRateList = fourRate.getSimilarRatings(raterID,numSimilarRaters,minimalRaters);
        System.out.println("found " + orderRateList.size() + " movies");
        
        //print rating list in order
        for(int k = 0; k < orderRateList.size(); k++){
            System.out.println(String.format("%.2f",orderRateList.get(k).getValue()) + " " + MovieDatabase.getTitle(orderRateList.get(k).getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre(){
        //String moviename = "ratedmovies_short.csv";
        String moviename = "ratedmoviesfull.csv";
        //String ratingname = "ratings_short.csv";
        String ratingname = "ratings.csv";
        int minimalRaters = 5;
        String raterID = "964";//to get similar ratings for this Rater with ID input here.
        int numSimilarRaters = 20;
        String genre = "Mystery";
        
        FourthRatings fourRate = new FourthRatings();
        MovieDatabase.initialize(moviename);
        RaterDatabase.initialize(ratingname);
        
        System.out.println("The number of raters: " + RaterDatabase.size());
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        Filter f = new GenreFilter(genre);
        ArrayList<Rating> orderRateList = fourRate.getSimilarRatingsByFilter(raterID,numSimilarRaters,minimalRaters,f);
        System.out.println("found " + orderRateList.size() + " movies");
        
        //print rating list in order
        for(int k = 0; k < orderRateList.size(); k++){
            System.out.println(String.format("%.2f",orderRateList.get(k).getValue()) + " " + MovieDatabase.getTitle(orderRateList.get(k).getItem()));
            System.out.println("    " + MovieDatabase.getGenres(orderRateList.get(k).getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector(){
        //String moviename = "ratedmovies_short.csv";
        String moviename = "ratedmoviesfull.csv";
        //String ratingname = "ratings_short.csv";
        String ratingname = "ratings.csv";
        int minimalRaters = 2;
        String raterID = "120";//to get similar ratings for this Rater with ID input here.
        int numSimilarRaters = 10;
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        
        FourthRatings fourRate = new FourthRatings();
        MovieDatabase.initialize(moviename);
        RaterDatabase.initialize(ratingname);
        
        System.out.println("The number of raters: " + RaterDatabase.size());
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        Filter f = new DirectorsFilter(directors);
        ArrayList<Rating> orderRateList = fourRate.getSimilarRatingsByFilter(raterID,numSimilarRaters,minimalRaters,f);
        System.out.println("found " + orderRateList.size() + " movies");
        
        //print rating list in order
        for(int k = 0; k < orderRateList.size(); k++){
            System.out.println(String.format("%.2f",orderRateList.get(k).getValue()) + " " + MovieDatabase.getTitle(orderRateList.get(k).getItem()));
            System.out.println("    " + MovieDatabase.getDirector(orderRateList.get(k).getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        //String moviename = "ratedmovies_short.csv";
        String moviename = "ratedmoviesfull.csv";
        //String ratingname = "ratings_short.csv";
        String ratingname = "ratings.csv";
        int minimalRaters = 3;
        String raterID = "168";//to get similar ratings for this Rater with ID input here.
        int numSimilarRaters = 10;
        int minMinutes = 80;
        int maxMinutes = 160;
        String genre = "Drama";
        
        FourthRatings fourRate = new FourthRatings();
        MovieDatabase.initialize(moviename);
        RaterDatabase.initialize(ratingname);
        
        System.out.println("The number of raters: " + RaterDatabase.size());
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        AllFilters f = new AllFilters();
        f.addFilter(new GenreFilter(genre));
        f.addFilter(new MinutesFilter(minMinutes,maxMinutes));
        ArrayList<Rating> orderRateList = fourRate.getSimilarRatingsByFilter(raterID,numSimilarRaters,minimalRaters,f);
        System.out.println("found " + orderRateList.size() + " movies");
        
        //print rating list in order
        for(int k = 0; k < orderRateList.size(); k++){
            System.out.println(String.format("%.2f",orderRateList.get(k).getValue())  +" Time: " + MovieDatabase.getMinutes(orderRateList.get(k).getItem()) + " " + MovieDatabase.getTitle(orderRateList.get(k).getItem()));
            System.out.println("    " + MovieDatabase.getGenres(orderRateList.get(k).getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        //String moviename = "ratedmovies_short.csv";
        String moviename = "ratedmoviesfull.csv";
        //String ratingname = "ratings_short.csv";
        String ratingname = "ratings.csv";
        int minimalRaters = 5;
        String raterID = "314";//to get similar ratings for this Rater with ID input here.
        int numSimilarRaters = 10;
        int year = 1975;
        int minMinutes = 70;
        int maxMinutes = 200;
        
        FourthRatings fourRate = new FourthRatings();
        MovieDatabase.initialize(moviename);
        RaterDatabase.initialize(ratingname);
        
        System.out.println("The number of raters: " + RaterDatabase.size());
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        AllFilters f = new AllFilters();
        f.addFilter(new MinutesFilter(minMinutes,maxMinutes));
        f.addFilter(new YearAfterFilter(year));
        ArrayList<Rating> orderRateList = fourRate.getSimilarRatingsByFilter(raterID,numSimilarRaters,minimalRaters,f);
        System.out.println("found " + orderRateList.size() + " movies");
        
        //print rating list in order
        for(int k = 0; k < orderRateList.size(); k++){
            System.out.println(String.format("%.2f",orderRateList.get(k).getValue()) + " " + MovieDatabase.getYear(orderRateList.get(k).getItem()) 
                                             +" Time: " + MovieDatabase.getMinutes(orderRateList.get(k).getItem()) + " " + MovieDatabase.getTitle(orderRateList.get(k).getItem()));
            
        }
    }
    
}
