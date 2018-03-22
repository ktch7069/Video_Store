
/**
 * A superclass to Dvd and VHS classes which holds the two common instance variables for 
 * Visual items, namely Color type and Video Formate. This is a sub class to Media.
 * @author Kai-Yin Tchung
 * @version 16.08.6
 * Last updated by Kate Grabowski 06/09/06
 */
public class Visual extends Media

{  
    /**
     *instance variables
     *O(1) 
     */
    protected String director; 
    protected String studio;
    protected String classification;
    
    /**
     * Constructor
     * @param anId
     * @param aTitle
     * @param aYear
     * @param aGenre
     * @param aMediaType
     * @param aDuration
     * @param aStudio
     * @param aClassification
     * O(1)
     */
    public Visual(String aTitle, String aDirector, int aYear, String aGenre, String aMediaType, int aDuration, String aStudio, String aClassification)
    {
       super(aTitle, aYear, aGenre, aMediaType, aDuration);
       director = aDirector; 
       studio = aStudio;
       classification = aClassification;
    }
    
    /**
     * Kate Grabowski
     * O(1)
     * @ return director 
     */
    
    public String getDirector()
    {
        return director;
    }
    
    /**
     * @return studio
     * o(1)
     */
    public String getStudio()
    {
        return studio;
    }
    
    /**
     * @return classification
     * o(1)
     */
    public String getClassification()
    {
        return classification;
    } 
}

