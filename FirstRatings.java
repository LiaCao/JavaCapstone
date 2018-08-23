
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    //method to load movies
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> listMovie = new ArrayList();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record: parser) {
            Movie thisMovie = new Movie(record.get("id"),record.get("title"),record.get("year"),record.get("genre"),
                                        record.get("director"),record.get("country"),record.get("poster"),Integer.parseInt(record.get("minutes").trim()));
            listMovie.add(thisMovie);
        }
        return listMovie;
    }
    
    //method to test loadMovies method
    public void testLoadMovies(){
        //String filename = "ratedmovies_short.csv";
        String filename = "ratedmoviesfull.csv";
        ArrayList<Movie> movieList = loadMovies("data/" + filename);
        //print out how many movies in file
        System.out.println("Number of movies in " + filename + " is: " + movieList.size());
        //print out all movies in file
        System.out.println(movieList);
        
        //Determine how many movies include the Comedy(target) genre
        String target = "Comedy";
        ArrayList<Movie> genreList = new ArrayList();
        for(Movie m : movieList){
            if(m.getGenres().indexOf(target) != -1) {
                genreList.add(m);
            }
        }
        System.out.println("Number of movies include the " + target + " genre is: " + genreList.size());
        
        //Determine how many movies are greater than 150 minutes in length
        int minutes = 150;
        ArrayList<Movie> timeList = new ArrayList();
        for(Movie m : movieList){
            if(m.getMinutes() > minutes) {
                timeList.add(m);
            }
        }
        System.out.println("Number of movies include are greater than " + minutes + " minutes in length is: " + timeList.size());
        
        //Determine the maximum number of movies by any director
        //Use hashmap to store director(String dr) and their movies(ArrayList of movie)
        HashMap<String,ArrayList<Movie>> dMap = new HashMap();
        for(Movie m : movieList){
            String[] director = m.getDirector().replaceAll(",\\s+",",").split(",");
            for(String dr: director){
                if(dMap.containsKey(dr)){
                    ArrayList<Movie> dMovie = dMap.get(dr);
                    dMovie.add(m);
                }else {
                    ArrayList<Movie> dMovie = new ArrayList();
                    dMovie.add(m);
                    dMap.put(dr,dMovie);
                }
            }
        }
        
        //go through dMap to find the maximum number of movies one director directed
        int maxMovie = 0;
        for(String dr: dMap.keySet()){
            if(dMap.get(dr).size() > maxMovie){
                maxMovie = dMap.get(dr).size();
            }
        }
        System.out.println("The maximum number of movies one director directed is: " + maxMovie);
        
        //go through dMap again to find directors that directed the maximum number of movies
        ArrayList<String> maxDirector = new ArrayList();
        for(String dr: dMap.keySet()){
            if(dMap.get(dr).size() == maxMovie){
                maxDirector.add(dr);
            }
        }
        System.out.println("Directors that directed the maximum number of movies: " + maxDirector);
    }
    
    //method to load raters
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> listRater = new ArrayList();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record: parser) {
            String currRaterId = record.get("rater_id");
            int check = 0;
            for(Rater rt: listRater){
                if(rt.getID().equals(currRaterId)) {
                    rt.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                    check = 1;
                    break;
                }
            }
            if(check == 0) {
                Rater currRater = new EfficientRater(currRaterId); //Choose to use Plain rater or efficient rater
                currRater.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                listRater.add(currRater);
            }
        }
        return listRater;
    }
    
    public void testLoadRaters(){
        //String filename = "ratings_short.csv";
        String filename = "ratings.csv";
        ArrayList<Rater> raterList = loadRaters("data/" + filename);
        //print out how many raters in file
        System.out.println("Number of raters in " + filename + " is: " + raterList.size());
        //print out all raters with number of movies they raterd
        //for(Rater rt: raterList){
        //    System.out.println("Rater ID: " + rt.getID() + "    Number of movies rated: " + rt.numRatings());
        //}
        
        //Find the number of ratings for a particular rater
        String tagRater = "193";
        for(Rater rt: raterList){
            if(rt.getID().equals(tagRater)){
                System.out.println("Rater ID " + tagRater + "    Number of ratings: " + rt.numRatings());
            }
        }
        
        //Determine maximum number of ratings by any rater
        int maxRating = 0;
        for(Rater rt: raterList){
            if(rt.numRatings() > maxRating) {
                maxRating = rt.numRatings();
            }
        }
        System.out.println("The maximum number of ratings by any rater is: " + maxRating);
        
        //Determine how many raters have this maximum number of ratings and who those raters are
        ArrayList<String> maxRater = new ArrayList();
        for(Rater rt: raterList){
            if(rt.numRatings() == maxRating) {
                maxRater.add(rt.getID());
            }
        }
        System.out.println("There are " + maxRater.size() + " raters that have this maximum number of ratings");
        System.out.println("Below are the raters ID: ");
        System.out.println(maxRater);
        
        //find the number of ratings a particular movie has
        String movieId = "1798709";
        ArrayList<String> raterForMovie = new ArrayList();
        for(Rater rt: raterList){
            if(rt.hasRating(movieId)) {
                raterForMovie.add(rt.getID());
            }
        }
        System.out.println("There are " + raterForMovie.size() + " raters that rater movie with ID " + movieId);
        
        //Determine how many different movies have been rated by all these raters
        ArrayList<String> ratedMovie = new ArrayList();
        for(Rater rt: raterList){
            ArrayList<String> currMovie = rt.getItemsRated();
            for(String s: currMovie){
                if(!ratedMovie.contains(s)) {
                    ratedMovie.add(s);
                }
            }
        }
        System.out.println(ratedMovie.size() + " different movies have been rated by all these raters");
    }
}
