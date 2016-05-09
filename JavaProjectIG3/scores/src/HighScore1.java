package scores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ListIterator;

public class HighScore1 {
	String channelID;
	String AdressBegin="https://api.thingspeak.com/channels/";
	String AdressResult="/feeds.csv";
	String split=",";
	
	
	public HighScore1(String ID){
		this.channelID = ID;
	}
	public ArrayList<String[]> Scores()
	{
		String serverAdress=this.AdressBegin+this.channelID+this.AdressResult;
		ArrayList<String[]> Scorelist = new ArrayList<String[]>();
		URL Adress = null;
		try{
			Adress = new URL(serverAdress);
		
		try{
			InputStream input = Adress.openStream();
			InputStreamReader InputRead = new InputStreamReader(input);
			BufferedReader BufferRead = new BufferedReader(InputRead);
			
			String line;
			line=BufferRead.readLine();
			int i=0;
			
			while (line!=null){
				String[] data = line.split(this.split);
				if(i>0 && data.length==4)
				{
					String[] value = new String[2];
					value[0]=data[2];
					value[1]=data[3];
					Scorelist.add(value);
				}
				i++;
				
				line=BufferRead.readLine();
			}
			BufferRead.close(); 
			
			
		} catch (IOException e) {
			System.out.println("File can't be read from server");
		
	}
	
	return Scorelist;
	finally{
	}
		}

/**
 * Method allowing to return the best scores and ranking it
 * @exception NumberFormatException if conversion string to int failed
 * @return ArrayList<String[]> Countains the 10 best scoresand players associated to it 
 */
public ArrayList<String[]> getScore()
{
	ArrayList<String[]> UScore = this.Scores();
	ArrayList<String[]> RScore = new ArrayList<String[]>();
	int i=0;
	
	//Until the Uscore isn't empty and getting the 10 best scores then check for the better score and return the couple Score/Player
	while(UScore.size()>0 && i<10)
	{
		ListIterator iterator = UScore.listIterator();
		int max= -1;
		String[] MaxCouple = new String[2];
		
		
		while (iterator.hasNext()){
			String[] result =(String[]) iterator.next();
			try{
				int val = Integer.parseInt(result[0]);
				if(val>max){
					max=val;
					MaxCouple=result;
				}
		    }
		    catch(NumberFormatException e){
		        System.out.println("Problem occured while int conversion"+e.getMessage());
		    }
		}
		
		//ADD the Score/Player couple to the Ranked Score, remove it from Unranked Score 
		RScore.add(MaxCouple);
		UScore.remove(MaxCouple);
		i++;
	}
	return RScore;
	}
}