/**
 * Lp contains instance variables for Lps only.
 * 
 * @ Kate Grabowski
 * @ Last Updated 05/09/06
 */
public class Lp extends Audio
{
    // instance variables - replace the example below with your own
    protected int noOfTracks;
    /**
     * Constructor for objects of class Lp
     */
    public Lp(String aTitle, int aYear, String aGenre, String aMediaType, int aDuration, String anArtist, int aNoOfTracks)
    {
        // initialise instance variables
        super(aTitle, aYear, aGenre, aMediaType, aDuration, anArtist);
        noOfTracks = aNoOfTracks;
    }
    
    public int getNoOfTracks()
    {
    	return noOfTracks;
    }
}
