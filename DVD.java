/**
 * This is the class where DVD objects are created under
 * it inherites from Visual Class, and visual inherites from Media Class 
 * @author Kai-Yin Tchung 
 * @version 15.08.06
 * Last updated by Kate Grabowski 06/09/06
 */
public class DVD extends Visual implements isSkipTrackable

{   
    //private boolean additionalFeatures = true;
    //public enum DvdType{SINGLE, DOUBLE}
    
    /**
     * Instance variables
     */
    private String additionalFeatures;
    protected int noOfScenes; // Kate
    
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
     * @param aAdditionalFeature
     * O(1)
     */
    public DVD(String aTitle, String aDirector, int aYear, String aGenre, String aMediaType, int aDuration, String aStudio, String aClassification, String aAdditionalFeatures)
    {
        super(aTitle, aDirector, aYear, aGenre, aMediaType, aDuration, aStudio, aClassification);
        additionalFeatures = aAdditionalFeatures;
    }
    
    /**
     * @return DVD additional feature
     * O(1)
     */
    public String getAdditionalFeatures()
    {
        return additionalFeatures;
    }

    public int getNoOfScenes()
    {
        return noOfScenes;
    }
    
    /*
     * Allows users to rewind the Dvd. Parameter i is how many scenes 
     * they want to rewind by.
     * 
     * Kate Grabowski
     * Last Updated 18/08/06
     * @ param sceneNo, i
     * @ return int
     */
    public int rewind(int sceneNo, int i)
    {
        if((i < sceneNo) && (sceneNo < noOfScenes))
        {
            sceneNo -= i;
            return sceneNo;
        }   
        else
            return sceneNo;
    } 

    /*
     * Allows users to fast forward the Dvd. Parameter i is how many scenes 
     * they want to fast forward by.
     * 
     * Kate Grabowski
     * Last Updated 18/08/06
     * @ param sceneNo, i
     * @ return int
     */
    public int fastForward(int sceneNo, int i)    
    {
        if((i <= (noOfScenes - sceneNo)) && (sceneNo < noOfScenes))
        {
            sceneNo += i;
            return sceneNo;
        }
        else
            return sceneNo;
    }
    
}
