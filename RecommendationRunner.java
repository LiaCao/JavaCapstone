
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class RecommendationRunner implements Recommender{
    
    public ArrayList<String> getItemsToRate (){
        ArrayList<String> recMovie = new ArrayList();
        
        String moviename = "ratedmoviesfull.csv";
        String ratingname = "ratings.csv";
        MovieDatabase.initialize(moviename);
        RaterDatabase.initialize(ratingname);
        
        int minimalRaters = 5;
        FourthRatings fourRate = new FourthRatings(); //create new fourthRatings object
        ArrayList<Rating> rat = fourRate.getAverageRatings(minimalRaters); //rec by average rating
        Collections.sort(rat); //sort from lowest to highest
        Collections.reverse(rat); //sort from highest to lowest
        
        for(int i = 0; i < 12 && i < rat.size(); i++) {
            String movieID = rat.get(i).getItem();
            recMovie.add(movieID);
        }
        
        return recMovie;
    }
    
    public void printRecommendationsFor (String webRaterID){
        ArrayList<String> recMovie = new ArrayList();
        String moviename = "ratedmoviesfull.csv";
        String ratingname = "ratings.csv";
        MovieDatabase.initialize(moviename);
        RaterDatabase.initialize(ratingname);
        
        int minimalRaters = 5;
        int numSimilarRaters = 30;
        
        FourthRatings fourRate = new FourthRatings(); //create new fourthRatings object
        ArrayList<Rating> similarRecommend = fourRate.getSimilarRatings(webRaterID,numSimilarRaters,minimalRaters);//sorted list
        
        if(similarRecommend.isEmpty()) {
            System.out.println("There are no movies that meet your criteria");
        } else{
            //build recommended movie list
            for(int i = 0; i < 15 && i < similarRecommend.size(); i++) {
                String movieID = similarRecommend.get(i).getItem();
                recMovie.add(movieID);
            }
            
            //use out StringBuilder to build HTML
            StringBuilder out = new StringBuilder();
            out.append("<html>" + "<style>"+"h1{color:darkblue; text-align: center;}"
                        +"body {background-color: beige;}"
                        +"table {table-layout: fixed;width: 100%;}"
                        +"th{height: 50px;background-color: MidnightBlue;color: white;text-align: center;}"
                        +"td{vertical-align: Center;text-align: center; background-color: Navy;color: white;}"
                        +"</style>"
                         +"<body>"
                          +"<h1><b>Movies Recommended</b></h1>"
                          +"<table>"
                          +"<tr>"
                            +"<th>Poster</th>"
                            +"<th>Movie Title</th>"
                            +"<th>Year Published</th>"
                            +"<th>Country Published</th>"
                            +"<th>Directors</th>"
                            +"<th>Duration</th>"
                          +"</tr>");
            for(String movie : recMovie){
                String posterURL = MovieDatabase.getPoster(movie);
                String title = MovieDatabase.getTitle(movie);
                int year = MovieDatabase.getYear(movie);
                String country = MovieDatabase.getCountry(movie);
                String director = MovieDatabase.getDirector(movie);
                int minute = MovieDatabase.getMinutes(movie);
                out.append("<tr>");
                out.append("<td><img src = \"" +posterURL + "\" width = \"200px\" height = \"300px\"/></td>");
                out.append("<td>" + title + "</td>");
                out.append("<td>" + year + "</td>");
                out.append("<td>" + country + "</td>");
                out.append("<td>" + director + "</td>");
                out.append("<td>" + minute + " mins</td>");
                out.append("</tr>");
            }
            out.append("</table></body></html>");
            System.out.println(out.toString());
        }
        
    }
}
