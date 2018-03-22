import java.util.*;
import java.io.*;
/**
 * This class looks after the stock of all media objects.
 * It adds and removes media objects, and searches by different media 
 * attributes.
 * 
 * @ Kate Grabowski 
 * @ Last Updated 05/09/06
 */

public class MediaDatabase
{
    public List<Media> mediaStock = new ArrayList<Media>(); //arraylist of all audio (cd & lp) objects
    private HashMap mediaMap =new HashMap<String,Media>();
    private String mediaDbaseName;
    
    /**
     * 
     * @param aFile The name of a file to load the database from.
     * @throws FileNotFoundException
     * @author Fernleigh Adams
     */
    public MediaDatabase(String aFile) throws FileNotFoundException, IOException
    {
    	fillDatabase(aFile);
    	mediaDbaseName = aFile;
    }
    
    /**
     * !!!!!THIS METHOD NEEDS TO BE FIXED!!!!!
     * @param aFile The name of a file. A database will be constructed based on the information in this file.
     * @throws FileNotFoundException
     * @author 
     */
    public void fillDatabase(String aFile) throws FileNotFoundException, IOException
    {
		BufferedReader bReader = new BufferedReader(new FileReader(aFile));  
	    while(true)
	     {	
	    	System.out.println(aFile);
	    	 
	    	String line=bReader.readLine();
	    	
	    	 if(line==(null))
	    	 {
	    		 return;
	    	 }
	    	 
	    	 else if(line.equals("ITEM-CD"))
	    	 { 
	    		 String endCheck;
	    		 
	    		 String aTitle= bReader.readLine();
	    		 int aYear =Integer.parseInt(bReader.readLine());
	    		 String aGenre = bReader.readLine();
	    		 String aMediaType=bReader.readLine();
	    		 int aDuration=Integer.parseInt(bReader.readLine());
	    		 String anArtist = bReader.readLine();
	    		 int aNoOfTracks=Integer.parseInt(bReader.readLine());
	    		 
	    		 endCheck=bReader.readLine();
	    		 if(!endCheck.equals("END-A-CD-ITEM"))
	    		 {
	    			 throw new IOException("Database corrupted !!");
	    		 }
	    		
	    		 Cd aCd = new Cd(aTitle,aYear,aGenre,aMediaType,aDuration,anArtist, aNoOfTracks);
	    		 mediaMap.put(((Media)aCd).getTitle(),((Media)aCd));
	    		 mediaStock.add(aCd);
	    	 } 
	    	
	    	 else if(line.equals("ITEM-DVD"))
	    	 {
	    		
	    		 String endCheck;
	    		 
	    		 String aTitle= bReader.readLine();
	    		 String aDirector= bReader.readLine();
	    		 int aYear =Integer.parseInt(bReader.readLine());
	    		 String aGenre = bReader.readLine();
	    		 String aMediaType=bReader.readLine();
	    		 int aDuration =Integer.parseInt(bReader.readLine());
	    		 String aStudio= bReader.readLine();
	    		 String aClassification= bReader.readLine();
	    		 String aAdditionalFeatures= bReader.readLine();
	    		 
	    		 endCheck=bReader.readLine();
	    		 if(!endCheck.equals("END-A-DVD-ITEM"))
	    		 {
	    			 throw new IOException("Database corrupted !!");
	    		 }
	    		
	    		 DVD aDvd = new DVD( aTitle, aDirector,  aYear,  aGenre,  aMediaType, aDuration, aStudio,  aClassification,  aAdditionalFeatures); 
	    		 mediaMap.put(((Media)aDvd).getTitle(),((Media)aDvd));
	    		 mediaStock.add(aDvd); 
	    	 }
	    	 
	    	 else if(line.equals("ITEM-LP"))
	    	 {
	    		 String endCheck;
	    		 
	    		 String aTitle= bReader.readLine();
	    		 int aYear =Integer.parseInt(bReader.readLine());
	    		 String aGenre = bReader.readLine();
	    		 String aMediaType=bReader.readLine();
	    		 int aDuration=Integer.parseInt(bReader.readLine());
	    		 String anArtist = bReader.readLine();
	    		 int aNoOfTracks=Integer.parseInt(bReader.readLine());
	    		 
	    		 endCheck=bReader.readLine();
	    		 if(!endCheck.equals("END-A-LP-ITEM"))
	    		 {
	    			 throw new IOException("Database corrupted !!");
	    		 }
	    		 
	    		 Lp aLp = new Lp(aTitle, aYear, aGenre,  aMediaType, aDuration,  anArtist, aNoOfTracks);
	    		 mediaMap.put(((Media)aLp).getTitle(),((Media)aLp));
	    		 mediaStock.add(aLp);
	    		 
	    	 }
	    	 else if(line.equals("ITEM-VHS"))
	    	 {
	    		 String endCheck;
	    		 
	    		 String aTitle= bReader.readLine();
	    		 String aDirector= bReader.readLine();
	    		 int aYear =Integer.parseInt(bReader.readLine());
	    		 String aGenre = bReader.readLine();
	    		 String aMediaType=bReader.readLine();
	    		 int aDuration =Integer.parseInt(bReader.readLine());
	    		 String aStudio= bReader.readLine();
	    		 String aClassification= bReader.readLine();
	    		 
	    		 endCheck=bReader.readLine();
	    		 if(!endCheck.equals("END-A-VHS-ITEM"))
	    		 {
	    			 throw new IOException("Database corrupted !!");
	    		 }
	    		 
	    		 Vhs aVhs = new Vhs(aTitle, aDirector, aYear, aGenre, aMediaType, aDuration, aStudio, aClassification);
	    		 
	    		 mediaMap.put(((Media)aVhs).getTitle(),(Media)aVhs);
	    		 mediaStock.add(aVhs);
	    	
	    	 }
	    	 System.out.println(mediaStock.size());
	       }
	    
	   
    }
    
    /**
     * Writes Cd object to a text file with begin & end & fields delimeters
     * @author Kai T
     * @version 6/9/06
     * @param aCd
     * @throws IOException
     * Runtime cost is O(1) 
     */
    public void writeCdToDisk(Cd aCd) throws IOException
    {
    	FileWriter fWrite =new FileWriter(mediaDbaseName, true);
    	fWrite.append("ITEM-CD");
        fWrite.append("\n"); //"\n" is the field delimeter
        fWrite.append(aCd.getTitle());
        fWrite.append("\n");
        fWrite.append(((Integer)(aCd.getYear())).toString());
        fWrite.append("\n");
        fWrite.append(aCd.getGenre());
        fWrite.append("\n");
        fWrite.append(aCd.getMediaType());
        fWrite.append("\n");
        fWrite.append(((Integer)(aCd.getDuration())).toString());
        fWrite.append("\n");
        fWrite.append(aCd.getArtist());
        fWrite.append("\n");
        fWrite.append(((Integer)(aCd.getNoOfTracks())).toString());
        fWrite.append("\n");
        fWrite.append("END-A-CD-ITEM");
        fWrite.append("\n");
        fWrite.close();	
    }
    
    /**
     * Writes Lp object to a text file with begin & end & fields delimeters
     * @version 6/9/06
     * @param aLp
     * @throws IOException
     * Runtime cost is O(1)
     */ 
    public void writeLpToDisk(Lp aLp) throws IOException
    {
    	
    	FileWriter fWrite =new FileWriter(mediaDbaseName, true);
    	fWrite.append("ITEM-LP");
        fWrite.append("\n"); //"\n" is the field delimeter
        fWrite.append(aLp.getTitle());
        fWrite.append("\n");
        fWrite.append(((Integer)(aLp.getYear())).toString());
        fWrite.append("\n");
        fWrite.append(aLp.getGenre());
        fWrite.append("\n");
        fWrite.append(aLp.getMediaType());
        fWrite.append("\n");
        fWrite.append(((Integer)(aLp.getDuration())).toString());
        fWrite.append("\n");
        fWrite.append(aLp.getArtist());
        fWrite.append("\n");
        fWrite.append(((Integer)(aLp.getNoOfTracks())).toString());
        fWrite.append("\n");
        fWrite.append("END-A-LP-ITEM\n");
        fWrite.append("\n");
        fWrite.close();	
    }
    
    /**
     * Writes Vhs object to a text file with begin, end and field delimeter
     * @param aVhs
     * @throws IOException
     * Runtime cost is O(1)
     */ 
    public void writeVhsToDisk(Vhs aVhs) throws IOException
    {
    	
    	FileWriter fWrite =new FileWriter(mediaDbaseName, true);
    	fWrite.append("ITEM-VHS");
        fWrite.append("\n"); //"\n" is the field delimeter
        fWrite.append(aVhs.getTitle());
        fWrite.append("\n");
        fWrite.append(aVhs.getDirector());
        fWrite.append("\n");
        fWrite.append(((Integer)(aVhs.getYear())).toString());
        fWrite.append("\n");
        fWrite.append(aVhs.getGenre());
        fWrite.append("\n");
        fWrite.append(aVhs.getMediaType());
        fWrite.append("\n");
        fWrite.append(((Integer)(aVhs.getDuration())).toString());
        fWrite.append("\n");
        fWrite.append(aVhs.getStudio());
        fWrite.append("\n");
        fWrite.append(aVhs.getClassification());
        fWrite.append("\n");
        fWrite.append("END-A-VHS-ITEM");
        fWrite.append("\n");
        fWrite.close();	
    }
    
    /**
     * Writes DVD object to a text file with begin & end & fields delimeters
     * @author Kai T
     * @version 6/9/06
     * @param aDvd
     * @throws IOException
     * Runtime cost is O(1)
     */ 
    public void writeDvdToDisk(DVD aDvd) throws IOException
    {
    	
    	FileWriter fWrite =new FileWriter(mediaDbaseName, true);
    	fWrite.append("ITEM-DVD");
        fWrite.append("\n"); //"\n" is the field delimeter
        fWrite.append(aDvd.getTitle());
        fWrite.append("\n");
        fWrite.append(aDvd.getDirector());
        fWrite.append("\n");
        fWrite.append(((Integer)(aDvd.getYear())).toString());
        fWrite.append("\n");
        fWrite.append(aDvd.getGenre());
        fWrite.append("\n");
        fWrite.append(aDvd.getMediaType());
        fWrite.append("\n");
        fWrite.append(((Integer)(aDvd.getDuration())).toString());
        fWrite.append("\n");
        fWrite.append(aDvd.getStudio());
        fWrite.append("\n");
        fWrite.append(aDvd.getClassification());
        fWrite.append("\n");
        fWrite.append(aDvd.getAdditionalFeatures());
        fWrite.append("\n");
        fWrite.append("END-A-DVD-ITEM");
        fWrite.append("\n");
        fWrite.close();	
    }
    
    
    /*
     * Kate Grabowski 02/09/06
     * Adds Cds to the mediaStock arraylist.
     * O(1) 
     * @ param Cd
     **/    
  
    public void addCd(Cd aCd)
    {
    	mediaMap.put(aCd.getTitle(),aCd);
    	mediaStock.add(aCd);
    }
    
    /*
     * Kate Grabowski 02/09/06
     * Adds Lps to the mediaStock arraylist.
     * O(1) 
     * @ param Lp
     **/
    
    public void addLp(Lp aLp)
    {
        mediaStock.add(aLp);
        mediaMap.put(aLp.getTitle(),aLp);
    }    
    
    /*
     * Kate Grabowski 02/09/06
     * Adds DVDs to the mediaStock arraylist.
     * O(1)
     * @param DVD 
     **/
    
    public void addDVD(DVD aDvd)
    {
    	mediaMap.put(aDvd.getTitle(),aDvd);
    	mediaStock.add(aDvd);
    }
    
    /*
     * Kate Grabowski 02/09/06
     * Adds Vhs' to the mediaStock arraylist.
     * O(1)
     * @ param Vhs
     **/    
  
    public void addVhs(Vhs aVhs)
    {
        mediaStock.add(aVhs);
        mediaMap.put(aVhs.getTitle(),aVhs);
    }
             
    /*
     * Kate Grabowski 02/09/06
     * Removes Cds from the mediaStock arraylist.
     * O(1)
     * @ param Cd
     **/
     
    public void removeCd(Cd aCd)
    {
        mediaStock.remove(aCd);         
    } 

    /*
     * Kate Grabowski 02/09/06
     * Removes Lps from the mediaStock arraylist.
     * O(1)
     * @ param Lp
     **/
     
    public void removeLp(Lp aLp)
    {
        mediaStock.remove(aLp);         
    }
    
    /*
     * Kate Grabowski 02/09/06
     * Removes Dvds from the mediaStock arraylist.
     * O(1)
     * @ param DVD
     **/
     
    public void removeDVD(DVD aDvd)
    {
        mediaStock.remove(aDvd);         
    } 
    
    /*
     * Kate Grabowski 02/09/06
     * Removes Vhs' from the mediaStock arraylist.
     * O(1)
     * @ param Vhs
     **/

    public void removeVhs(Vhs aVhs)
    {
        mediaStock.remove(aVhs);         
    }     
    
    /*
     * Kate Grabowski 02/09/06
     * General method for all media objects.
     * Searches through the mediaStock arraylist for media objects
     * by their i.d.
     * O(n)
     * @ param anId
     * @ return Media
     **/    
    
    public Media searchById(int anId) throws NoMatchException
    {
        if(mediaStock.size() > 0)
        {
            for (Media media : mediaStock) 
            {
                if (media.getId() == anId) 
                {
                    return media;
                }
            }   
            return null;
        }
         else
        	 throw new NoMatchException();
    }   
    
    /*
     * Kate Grabowski 02/09/06
     * General method for all media objects.
     * Searches through the mediaStock arraylist for media objects
     * by their title. Checks first to make sure there is something 
     * in the arraylist.
     * O(n)
     * @ param aTitle
     * @ return ArrayList //of media objects with the title, aTitle
     **/     
     
    public ArrayList<Media> searchByTitle(String aTitle) throws NoMatchException
    {
         if(mediaStock.size() > 0)
         {
            ArrayList<Media> mediaByTitle = new ArrayList<Media>(); 
            for(int i = 0; i < mediaStock.size(); i++)
            {   
                Media media = (Media)mediaStock.get(i);
                if(media.getTitle().equalsIgnoreCase(aTitle))
                {
                    mediaByTitle.add(media);
                }
            }
            return mediaByTitle;
         }
         else
            throw new NoMatchException();
    }   

    /*
     * Kate Grabowski 02/09/06
     * General method for all media objects.
     * Searches through the mediaStock arraylist for media objects
     * by their genre. Checks first to make sure there is something 
     * in the arraylist.
     * O(n) 
     * @ param aGenre
     * @ return ArrayList //of media objects with genre, aGenre
     **/     
     
    public ArrayList<Media> searchByGenre(String aGenre) throws NoMatchException
    {
         if(mediaStock.size() > 0)
         {
            ArrayList<Media> mediaByGenre = new ArrayList<Media>();             
            for(int i = 0; i < mediaStock.size(); i++)
            {   
                Media media = (Media)mediaStock.get(i);
                if(media.getGenre().equalsIgnoreCase(aGenre))
                {
                    mediaByGenre.add(media);
                }
            }
            return mediaByGenre;
         }
         else
        	 throw new NoMatchException();
    }   

    /*
     * Kate Grabowski 05/09/06
     * General method for all media objects.
     * Searches through the mediaStock arraylist for media objects
     * by the year specified. Checks first to make sure there is something 
     * in the arraylist.
     * O(n) 
     * @ param aYear
     * @ return ArrayList //of media objects from aYear
     **/     
     
    public ArrayList<Media> searchByYear(int aYear) throws NoMatchException
    {
         if(mediaStock.size() > 0)
         {
            ArrayList<Media> mediaByYear = new ArrayList<Media>();             
            for(int i = 0; i < mediaStock.size(); i++)
            {   
                Media media = (Media)mediaStock.get(i);
                if(media.getYear() == aYear)
                {
                    mediaByYear.add(media);
                }
            }
            return mediaByYear;
         }
         else
        	 throw new NoMatchException();
    }   
    
    /*
     * Kate Grabowski 05/09/06
     * Method specific to audio objects.
     * Searches through the mediaStock arraylist for audio objects
     * by the artists name. Checks first to make sure there is something 
     * in the arraylist
     * O(n)
     * @ param aArtist
     * @ return ArrayList //of audio objects by aArtist
     **/     
     
    public ArrayList searchByArtist(String aArtist) throws NoMatchException
    {
         if(mediaStock.size() > 0)
         { 
            ArrayList<Audio> audioByArtist = new ArrayList<Audio>();
            for(int i = 0; i < mediaStock.size();)
            {   
                Media media = (Media)mediaStock.get(i);
                if(media.getMediaType().equalsIgnoreCase("cd") || media.getMediaType().equalsIgnoreCase("lp"))
                {
                    Audio audio = (Audio)mediaStock.get(i);
                    if(audio.getArtist().equalsIgnoreCase(aArtist))
                    {
                        audioByArtist.add(audio);
                    }
                    i++;
                }
                else
                {
                    i++;
                }
            }
            return audioByArtist;
         }
         else
        	 throw new NoMatchException();
    }
    
    /*
     * Kate Grabowski 06/09/06
     * General method for all media objects.
     * Searches through the mediaStock arraylist for media objects
     * by their mediaType. Checks first to make sure there is something
     * in the arraylist.
     * O(n)
     * @ param aMediaType
     * @ return ArrayList //of media objects by aMediaType
     **/

    public ArrayList<Media> searchByMediaType(String aMediaType) throws NoMatchException
    {
        if(mediaStock.size() > 0)
        {
            ArrayList<Media> mediaByType = new ArrayList<Media>();
            for(int i = 0; i < mediaStock.size(); i++)
            {
                Media media = (Media)mediaStock.get(i);
                if(media.getMediaType().equals(aMediaType))
                {
                    mediaByType.add(media);
                }
            }
            return mediaByType;
        }
        else
        	throw new NoMatchException();
    }



    /*
     * Kate Grabowski 05/09/06
     * Method specific to visual objects.
     * Searches through the mediaStock arraylist for visual objects
     * by a certain director. Checks first to make sure there is something
     * in the arraylist
     * O(n)
     * @ param aDirector
     * @ return ArrayList //of visual objects by aDirector
     **/

    public ArrayList searchByDirector(String aDirector) throws NoMatchException
    {
         if(mediaStock.size() > 0)
         {
            ArrayList<Visual> visualByDirector = new ArrayList<Visual>();
            for(int i = 0; i < mediaStock.size();)
            {
                Media media = (Media)mediaStock.get(i);
                if(media.getMediaType().equalsIgnoreCase("dvd") ||
media.getMediaType().equalsIgnoreCase("vhs"))
                {
                    Visual visual = (Visual)mediaStock.get(i);
                    if(visual.getDirector().equalsIgnoreCase(aDirector))
                    {
                        visualByDirector.add(visual);
                    }
                    i++;
                }
                else
                {
                    i++;
                }
            }
            return visualByDirector;
         }
         else
        	 throw new NoMatchException();
    }
    
    
    /*
     * Kate Grabowski 02/09/06
     * Prints out how many objects in the mediaStock ArrayList
     * O(1)
     * @ return int //number of objects in the ArrayList
     */
    
    public int printArrayList()
    {        
        int i = mediaStock.size();
        return i;
    }  

	public List<Media> getMediaDB()
	{
		return mediaStock;
	}
	
	/**
	 * Method searches the arraylist and prints out the one with matching title
	 * @param aInt
	 * @author Kai T
	 * @version 6/9/06
	 * Runtime Cost is O(1)
	 */
	public void searchSingleTitleByName(String s) 
	{	

		try
		{
		
		System.out.println("*****************************************");
		System.out.println("The detail of item "+ s  +" are:");
		System.out.print("TITLE :");
		System.out.println(((Media)mediaMap.get(s)).getTitle());
		System.out.print("YEAR : ");
		System.out.println(((Media)mediaMap.get(s)).getYear());
		System.out.print("MEDIA TYPE : ");
		System.out.println(((Media)mediaMap.get(s)).getMediaType());
		System.out.print("GENERE : ");
		System.out.println(((Media)(mediaMap.get(s))).getGenre());
		System.out.println("Thse size of the list is:" + mediaMap.size());
		System.out.println("*******************************************");

		}
		catch(IllegalArgumentException exception)
		{	
			System.out.print("Bad input !!" + exception.getMessage());
		}
		catch(NullPointerException exception)
		{
			System.out.print("NO such item !!" + exception.getMessage());
		}
		
	}
}

