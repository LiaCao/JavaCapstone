
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter {
    private int min, max;
    
    public MinutesFilter(int minMinutes, int maxMinutes) {
        min = minMinutes;
        max = maxMinutes;
    }
    
    @Override
    public boolean satisfies(String id) {
        if(MovieDatabase.getMinutes(id) <= max && MovieDatabase.getMinutes(id) >= min){
            return true;
        } else{
            return false;
        }
    }

}