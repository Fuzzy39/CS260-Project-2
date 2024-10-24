package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import data.MemoLinkedList;
import data.MemoRecord;
import data.MyLinkedList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * This 'class' contains two static methods for reading and writing lists of memos to a file.
 */
public class FileIO
{

	/**
	 * Reads a list of memos from a file.
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static MyLinkedList<MemoRecord> readFile(String filename) throws FileNotFoundException, IOException
	{
		
		MyLinkedList<MemoRecord> toReturn = new MyLinkedList<MemoRecord>(); // that's a mouthful, isn't it.
		
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) 
		{
		    while(true) 
		    {
		    	MemoRecord mr;
		    	try
		    	{
		    		mr = readRecord(br);
		    	}
		    	// is this bad practice? yes. Am I justified in doing it here? probably not, no.
		    	catch(Exception e)
		    	{
		    		return toReturn;
		    	}
		    	
		    	if(mr==null)
		    	{
		    		return toReturn;
		    	}
		    	
		    	toReturn.add(mr);
		    }
		    
		}
		
	}
	
	
	private static MemoRecord readRecord(BufferedReader br) throws IOException, NumberFormatException
	{
		int ID;
		Point2D location;
		String note;
		Color fg;
		Color bg;
		
		// At this point in the project, I'd like to be done.
		
		// Also, please explain to me, why, why on earth would you make this memo format have
		// no half-decent indicator of how many lines long the memo should be?
		// Now I have to go around scrounging for Color.* Color.* ?
		// Terrible. Why? Nobody would do this, in the real world, surely.
		
		// ID
		String idString = br.readLine();
		if(idString == null ) return null;
		ID = Integer.parseInt(idString);
		
		// location
		String locationString = br.readLine();
		if(locationString == null) return null;	
		String[] locations = locationString.split(" ");
		if(locations.length != 2) return null;
		
		int x = Integer.parseInt(locations[0]);
		int y = Integer.parseInt(locations[1]);
		location = new Point2D(x, y);
		
		// Now, the incredibly annoying part.
		// It didn't have to be this way. But I digress.
		// We do not know how many lines the memo takes up. Somewhere between 0 and 4.
		note = "";
		String cursor = "";
		while(true)
		{
			cursor = br.readLine();
			if(cursor == null) return null;
			
			// poor proxies for the end of the memo text. who decided this was the format, seriously?
			if(cursor.startsWith("Color.") || cursor.startsWith("0x"))
			{
				break;
			}
			note += cursor+"\n";
		}
		
		// colors
		if(cursor.startsWith("Color."))
		{
			cursor = cursor.substring(6);
		}
		fg = Color.web(cursor);
		
		cursor = br.readLine();
		if(cursor == null) return null;
		if(cursor.startsWith("Color."))
		{
			cursor = cursor.substring(6);
		}
		
		bg = Color.web(cursor);
		
		return new MemoRecord(ID, location, note, fg, bg);
		
		
	}
	
	
	/**
	 * Writes a list of memos into a file.
	 * @param filename
	 * @param list
	 * @throws IOException
	 */
	public static void writeFile(String filename, MemoLinkedList list) throws IOException
	{
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename)))
		{
			for(Memo memo : list)
			{
				writeRecord(bw, memo.getData());
			}
		}
	}
	
	
	private static void writeRecord( BufferedWriter bw, MemoRecord memo) throws IOException
	{
		bw.write(memo.ID()+"\n");
		bw.write(((int)memo.location().getX()) + " "+ ((int)memo.location().getY())+"\n");
		bw.write(memo.note()+"\n");
		bw.write(memo.foregroundColor().toString()+"\n");
		bw.write(memo.backgroundColor().toString()+"\n");
	}
	
	
}
