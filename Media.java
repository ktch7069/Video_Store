 /**
 * Class Media stores instance variables and get methods common to Audio & Visual.
 * 
 * @ Kate Grabowski & Kai-Yin Tchung
 * @ Last update 18/08/06
 */
public class Media
{
    // instance variables common to Media, Audio & Visual classes
    protected int id; 
    protected String title;
    protected int year;
    protected String genre; // for audio objects - alternative heavy 
    protected String mediaType; // can be a  cd, lp, vhs or dvd 
    protected int duration;
    public static int lastAssignedId;

    public Media(String aTitle, int aYear, String aGenre, String aMediaType, int aDuration)
    {
    	id = ++lastAssignedId;
        title = aTitle;
        year = aYear;
        genre = aGenre;
        mediaType = aMediaType; 
        duration = aDuration;
    } 
   
    /*
     * O(1)
     */
    
    public int getId()
    {
        return id;
    }

    /**
     * @return title
     * o(1)
     */
    public String getTitle()
    {
        return title;
    }    
    
    /* 
     * O(1)
     */
    
    public int getYear()
    {
        return year;
    }
    
    /*
     * O(1)
     */
    
    public String getGenre()
    {
        return genre;
    }
    
    /*
     * O(1)
     */
    
    public String getMediaType()
    {
        return mediaType;
    }
    
    /**
     * @return duration
     * o(1)
     */
    public int getDuration()
    {
        return duration;
    }  

}
