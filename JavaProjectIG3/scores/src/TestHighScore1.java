package scores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

/**
 * TestHighScore1 class allowing to select a fake score and associating it to anew player
 * @version 1.0
 * @author Nathanael Rastout 
 * @since 2016-05-04
 */
public class TestHighScore1 {
	/**
	 * @param arg
	 * @exception NumberFormatException when conversion string to int failed
	 * @exception FileNotFoundException when problem occurred during opening
	 * @exception IOException when a problem occurred during reading
	 */
	public static void main (String [] arg){
		ArrayList<Integer> scoreTable= new ArrayList<Integer>();
		String PName="";
		int PScore;
		String Separator=",";
		Scanner scan = new Scanner(System.in);
		
		//New Highscore object
		HighScore1 best = new HighScore1("11315");
		
		//Getting the 10 highscores from ThingSpeak server
		ArrayList<String[]> bestList = best.getScore();
		
		//Affichage du tableau des 10 meilleurs scores 
		System.out.println("HighSCoreRanking");
		
		//Using an iterator to reach the best scores
		ListIterator iterator = bestList.listIterator();
		while (iterator.hasNext())
		{
			String[] res =(String[]) iterator.next();
			System.out.println(res[0]+" | "+res[1]);
		}
		
		//Asking for a player Name
		try{
			System.out.println("Enter player Name :");
			PName = scan.nextLine();
		}
	
		
		try {
			//Opening score file
			String file = "scoreSamples.txt";
			BufferedReader BufferRead = new BufferedReader(new FileReader(file));
			String line = BufferRead.readLine();
			while(line!=null){
			      String[] data = line.split(Separator);
			      try
			      {
			    	  //Adding score in the table
			    	  scoreTable.add(Integer.parseInt(data[0]));
			      }
			      // Converting Exception
			      catch(NumberFormatException e){
			        System.out.println("Problème survenu lors de la conversion de string en int."+e.getMessage());
			      }
			      line=BufferRead.readLine();
			}
			BufferRead.close();
			
			//Reading and opening exception
		} 
		catch (IOException ex) {
			System.out.println("Problème survenu dans la lecture du fichier"+ex.getMessage());
		}
		} 
		
		//Generating random number to select a score
		Random random = new Random();
		int index = random.nextInt(scoreTable.size());
		
		PScore=scoreTable.get(index);
		
		//Printing player score
		System.out.println("Woow you scored" +PScore+ "points, Amazing ! ");
		
	}
}