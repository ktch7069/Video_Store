/**
 * Cd contains instance variables for Cds only and uses the interface
 * isSkipTrackable to rewind and fast forward the cd.
 * 
 * @ Kate Grabowski
 * @ Last Updated 17/08/06
 */
public class Cd extends Audio implements isSkipTrackable
{
    protected int noOfTracks;  // amount of tracks on the album  
    /*
     * Constructor for objects of class Cd
     */
    public Cd(String aTitle, int aYear, String aGenre, String aMediaType, int aDuration, String anArtist, int anoOfTracks)
    {
        super(aTitle, aYear, aGenre, aMediaType, aDuration, anArtist); 

        noOfTracks = anoOfTracks;
    }

    public int getNoOfTracks()
    {
    	return noOfTracks;
    }
    
    /*
     * This method allows the cd to skip to the previous track, 
     * checking first to make sure it isn't already on track 1 
     * in which case it can not got to the last track.
     * O(1)
     * 
     * @ Kate Grabowski
     * @ Last Updated 17/08/06
     */
    public int rewind(int trackNo, int i)
    {
        if((i < trackNo) && (trackNo < noOfTracks))
        {
            trackNo -= i;
            return trackNo;
        }   
        else
            return trackNo;
    }
    
    /*
     * This method allows the cd to skip to the next track, 
     * checking first to make sure it isn't on the last track
     * in which case it can not go any further.
     * O(1)
     * 
     * @ Kate Grabowski
     * @ Last Updated 17/08/06
     */   
    public int fastForward(int trackNo, int i)
    {
        if((i <= (noOfTracks - trackNo)) && (trackNo < noOfTracks))
        {
            trackNo += i;
            return trackNo;
        }
        else
            return trackNo;
    }
    
}