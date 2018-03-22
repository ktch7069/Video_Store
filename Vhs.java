/**
 * Vhs holds instance variables for Vhs and rewind & fast forward methods
 * 
 * @ Kate Grabowski 
 * @ Last Updated 06/09/06
 */
public class Vhs extends Visual implements isSkipTrackable
{
    // instance variables - replace the example below with your own
    protected int lengthOfVhs;

    public Vhs(String aTitle, String aDirector, int aYear, String aGenre, String aMediaType, int aDuration, String aStudio, String aClassification)
    {
        // initialise instance variables
        super(aTitle, aDirector, aYear, aGenre, aMediaType, aDuration, aStudio, aClassification);
    }

    /*
     * Allows users to rewind the Vhs. Parameter i is how many minutes 
     * they want to rewind by, and positionOfMovie is in minutes.
     * 
     * Kate Grabowski
     * Last Updated 18/08/06
     * @ param positionOfMovie, i
     * @ return int
     */
    public int rewind(int positionOfMovie, int i)
    {  
       if((i <= positionOfMovie) && (positionOfMovie < lengthOfVhs))
       {
            positionOfMovie -= i;
            return positionOfMovie;
       }
       else
            return 0;
    }

    /*
     * Allows users to fast forward the Vhs. Parameter i is how many minutes 
     * they want to fast forward by, and positionOfMovie is in minutes.
     * 
     * Kate Grabowski
     * Last Updated 18/08/06
     * @ param positionOfMovie, i
     * @ return int
     */    
    public int fastForward(int positionOfMovie, int i)
    {
        if((i <= (lengthOfVhs - positionOfMovie)) && (positionOfMovie < lengthOfVhs))
        {
            positionOfMovie = positionOfMovie + i;
            return positionOfMovie;
        }
        else
            return positionOfMovie;
    }
}
