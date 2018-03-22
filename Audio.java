/**
 * Audio contains instance variables common to Audio objects (cds & lps)
 * 
 * @ Kate Grabowski
 * @ Last Update 17/08/06
 */
public class Audio extends Media
{
    // instance variables common to cds & lps
    protected String artist;
    
    /*
      Constructor for objects of class Audio
     */
    
    public Audio(String aTitle, int aYear, String aGenre, String aMediaType, int aDuration, String anArtist)
    {
        super(aTitle, aYear, aGenre, aMediaType, aDuration);
        artist = anArtist; 
    }
        
    /*
     * Basic get methods
     * O(1)
     * 
     * @ Kate Grabowski
     * @ Last Updated 17/08/06 
     */
    public String getArtist()
    {
        return artist;
    }      
}
