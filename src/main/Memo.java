package main;
import javafx.geometry.*;
import javafx.scene.paint.Color;

public class Memo 
{
	private int ID;
	private Point2D location;
	private String note;
	private Color foregroundColor;
	private Color backgroundColor;
	
	
	public Memo(int ID, Point2D location, String note)
	{
		this.ID = ID;
		this.location= location;
		this.note = note;
		foregroundColor = Color.BLACK; // black
		backgroundColor = Color.YELLOW;
	}


	public Point2D getLocation() {
		return location;
	}


	public void setLocation(Point2D location) {
		this.location = location;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public Color getForegroundColor() {
		return foregroundColor;
	}


	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
	}


	public Color getBackgroundColor() {
		return backgroundColor;
	}


	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}


	public int getID() {
		return ID;
	}
	
	
}
