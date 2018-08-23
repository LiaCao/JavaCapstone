
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String[] myDirectors;
    
    public DirectorsFilter(String directors) {
        myDirectors = directors.trim().replaceAll(",\\s+",",").split(",");;
    }
    
    @Override
    public boolean satisfies(String id) {
        String dir = MovieDatabase.getDirector(id);
        for(String s : myDirectors) {
            if(dir.indexOf(s) != -1){
                return true;
            }
        }
        return false;
    }

}
