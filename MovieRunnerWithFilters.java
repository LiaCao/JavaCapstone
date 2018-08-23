
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {

    //Given minimal rates required, print out ratings in ascending order, without filters
    public void printAverageRatings(){
        //String moviename = "ratedmovies_short.csv";
        String moviename = "ratedmoviesfull.csv";
        //String ratingname = "ratings_short.csv";
        String ratingname = "ratings.csv";
        int minimalRaters = 35;
        
        ThirdRatings thirdRate = new ThirdRatings(ratingname);
        System.out.println("The number of raters: " + thirdRate.getRaterSize());
        //set up movie database
        MovieDatabase.initialize(moviename);
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        ArrayList<Rating> orderRateList = thirdRate.getAverageRatings(minimalRaters);
        System.out.println("There are " + orderRateList.size() + " number of movies that have " + minimalRaters + " or more ratings");
        Collections.sort(orderRateList);//ArrayList to store ratings from smallest to largest
        
        //print rating list in order
        for(int k = 0; k < orderRateList.size(); k++){
            System.out.println(String.format("%.2f",orderRateList.get(k).getValue()) + " " + MovieDatabase.getTitle(orderRateList.get(k).getItem()));
        }
    }
    
    //with afteryear filter
    public void printAverageRatingsByYearAfter(){
        //String moviename = "ratedmovies_short.csv";
        String moviename = "ratedmoviesfull.csv";
        //String ratingname = "ratings_short.csv";
        String ratingname = "ratings.csv";
        int minimalRaters = 20;
        int year = 2000;
        
        ThirdRatings thirdRate = new ThirdRatings(ratingname);
        System.out.println("The number of raters: " + thirdRate.getRaterSize());
        //set up movie database
        MovieDatabase.initialize(moviename);
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        Filter f = new YearAfterFilter(year);
        ArrayList<Rating> orderRateList = thirdRate.getAverageRatingsByFilter(minimalRaters,f);
        System.out.println("There are " + orderRateList.size() + " number of movies that have " + minimalRaters + " or more ratings, and is after year " + year);
        Collections.sort(orderRateList);//ArrayList to store ratings from smallest to largest
        
        //print rating list in order
        for(int k = 0; k < orderRateList.size(); k++){
            System.out.println(String.format("%.2f",orderRateList.get(k).getValue()) + " " + MovieDatabase.getYear(orderRateList.get(k).getItem())  + " " + MovieDatabase.getTitle(orderRateList.get(k).getItem()));
        }
    }
    
    //with genre filter
    public void printAverageRatingsByGenre(){
        //String moviename = "ratedmovies_short.csv";
        String moviename = "ratedmoviesfull.csv";
        //String ratingname = "ratings_short.csv";
        String ratingname = "ratings.csv";
        int minimalRaters = 20;
        String genre = "Comedy";
        
        ThirdRatings thirdRate = new ThirdRatings(ratingname);
        System.out.println("The number of raters: " + thirdRate.getRaterSize());
        //set up movie database
        MovieDatabase.initialize(moviename);
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        Filter f = new GenreFilter(genre);
        ArrayList<Rating> orderRateList = thirdRate.getAverageRatingsByFilter(minimalRaters,f);
        System.out.println("There are " + orderRateList.size() + " number of movies that have " + minimalRaters + " or more ratings, and has the genre of " + genre);
        Collections.sort(orderRateList);//ArrayList to store ratings from smallest to largest
        
        //print rating list in order
        for(int k = 0; k < orderRateList.size(); k++){
            System.out.println(String.format("%.2f",orderRateList.get(k).getValue()) + " " + MovieDatabase.getTitle(orderRateList.get(k).getItem()));
            System.out.println("    " + MovieDatabase.getGenres(orderRateList.get(k).getItem()));
        }
    }
    
    //filter by minutes
    public void printAverageRatingsByMinutes(){
        //String moviename = "ratedmovies_short.csv";
        String moviename = "ratedmoviesfull.csv";
        //String ratingname = "ratings_short.csv";
        String ratingname = "ratings.csv";
        int minimalRaters = 5;
        int minMinutes = 105;
        int maxMinutes = 135;
        
        ThirdRatings thirdRate = new ThirdRatings(ratingname);
        System.out.println("The number of raters: " + thirdRate.getRaterSize());
        //set up movie database
        MovieDatabase.initialize(moviename);
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        Filter f = new MinutesFilter(minMinutes, maxMinutes);
        ArrayList<Rating> orderRateList = thirdRate.getAverageRatingsByFilter(minimalRaters,f);
        System.out.println("There are " + orderRateList.size() + " number of movies that have " + minimalRaters + " or more ratings, and has lenth of " + minMinutes + " to " + maxMinutes);
        Collections.sort(orderRateList);//ArrayList to store ratings from smallest to largest
        
        //print rating list in order
        for(int k = 0; k < orderRateList.size(); k++){
            System.out.println(String.format("%.2f",orderRateList.get(k).getValue())  +" Time: " + MovieDatabase.getMinutes(orderRateList.get(k).getItem()) + " " + MovieDatabase.getTitle(orderRateList.get(k).getItem()));
        }
    }
    
    //filter by directors
    public void printAverageRatingsByDirectors(){
        //String moviename = "ratedmovies_short.csv";
        String moviename = "ratedmoviesfull.csv";
        //String ratingname = "ratings_short.csv";
        String ratingname = "ratings.csv";
        int minimalRaters = 4;
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        
        ThirdRatings thirdRate = new ThirdRatings(ratingname);
        System.out.println("The number of raters: " + thirdRate.getRaterSize());
        //set up movie database
        MovieDatabase.initialize(moviename);
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        Filter f = new DirectorsFilter(directors);
        ArrayList<Rating> orderRateList = thirdRate.getAverageRatingsByFilter(minimalRaters,f);
        System.out.println("There are " + orderRateList.size() + " number of movies that have " + minimalRaters + " or more ratings, and has one of the following directors: " + directors);
        Collections.sort(orderRateList);//ArrayList to store ratings from smallest to largest
        
        //print rating list in order
        for(int k = 0; k < orderRateList.size(); k++){
            System.out.println(String.format("%.2f",orderRateList.get(k).getValue())  + " " + MovieDatabase.getTitle(orderRateList.get(k).getItem()));
            System.out.println("    " + MovieDatabase.getDirector(orderRateList.get(k).getItem()));
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
        
        ThirdRatings thirdRate = new ThirdRatings(ratingname);
        System.out.println("The number of raters: " + thirdRate.getRaterSize());
        //set up movie database
        MovieDatabase.initialize(moviename);
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(year));
        f.addFilter(new GenreFilter(genre));
        ArrayList<Rating> orderRateList = thirdRate.getAverageRatingsByFilter(minimalRaters,f);
        System.out.println(orderRateList.size() + " movies matched");
        Collections.sort(orderRateList);//ArrayList to store ratings from smallest to largest
        
        //print rating list in order
        for(int k = 0; k < orderRateList.size(); k++){
            System.out.println(String.format("%.2f",orderRateList.get(k).getValue())  + " " + MovieDatabase.getYear(orderRateList.get(k).getItem()) + " " + MovieDatabase.getTitle(orderRateList.get(k).getItem()));
            System.out.println("    " + MovieDatabase.getGenres(orderRateList.get(k).getItem()));
        }
    }
    
    //filter by allFilters (Directors and minutes)
    public void printAverageRatingsByDirectorsAndMinutes() {
        //String moviename = "ratedmovies_short.csv";
        String moviename = "ratedmoviesfull.csv";
        //String ratingname = "ratings_short.csv";
        String ratingname = "ratings.csv";
        int minimalRaters = 3;
        int minMinutes = 90;
        int maxMinutes = 180;
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        
        ThirdRatings thirdRate = new ThirdRatings(ratingname);
        System.out.println("The number of raters: " + thirdRate.getRaterSize());
        //set up movie database
        MovieDatabase.initialize(moviename);
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        AllFilters f = new AllFilters();
        f.addFilter(new MinutesFilter(minMinutes,maxMinutes));
        f.addFilter(new DirectorsFilter(directors));
        ArrayList<Rating> orderRateList = thirdRate.getAverageRatingsByFilter(minimalRaters,f);
        System.out.println(orderRateList.size() + " movies matched");
        Collections.sort(orderRateList);//ArrayList to store ratings from smallest to largest
        
        //print rating list in order
        for(int k = 0; k < orderRateList.size(); k++){
            System.out.println(String.format("%.2f",orderRateList.get(k).getValue())  +" Time: " + MovieDatabase.getMinutes(orderRateList.get(k).getItem()) + " " + MovieDatabase.getTitle(orderRateList.get(k).getItem()));
            System.out.println("    " + MovieDatabase.getDirector(orderRateList.get(k).getItem()));
        }
    }
}
