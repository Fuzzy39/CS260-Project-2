package main;



import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Memo extends StackPane 
{

	private MemoRecord data;
	private VBox vbox;
	
	public Memo(MemoRecord record) {
		// TODO Auto-generated constructor stub
		data = record;
		
		// Construct the main body
		vbox = new VBox();
		getChildren().add(vbox);
		
		// TitleBar
		HBox titleBar = new HBox();
		vbox.getChildren().add(titleBar);
		titleBar.setSpacing(15);
		
		// header
		Label header = new Label(getTitle());
		header.setPrefWidth(130); // I'd like to figure out some way to know how wide a string is in pixels.
		header.setMaxWidth(130);
		HBox.setMargin(header, new Insets(5));
		header.setStyle("-fx-font-weight: bold");
		
		// close button
		Button close = new Button("X");
		close.setCancelButton(true);
		titleBar.getChildren().addAll(header, close);
		
		
		// label
		if(!(getMemoBody().equals("")))
		{
			Label note = new Label(getMemoBody());
			VBox.setMargin(note, new Insets(5));
			note.setMaxWidth(150);
			note.setPrefWidth(150);
			vbox.getChildren().add(note);
		}
	
		
		this.setBackground(Background.fill(record.backgroundColor())); 
		relocate(data.location().getX(), data.location().getY());
	}
	
	/**
	 * get the persistent state of the memo.
	 * @return
	 */
	public MemoRecord getData()
	{
		return data;
	}
	
	/**
	 * Get the title of a Memo.
	 * @return
	 */
	public String getTitle()
	{
		return data.note().split("\n")[0];
	}
	
	/**
	 * Get the main text body of a Memo.
	 * @return
	 */
	public String getMemoBody()
	{
		int index = data.note().indexOf('\n');
		
		if(index == -1 || index+1 >= data.note().length())
		{
			return "";
		}
		
		return data.note().substring(index+1);
	}
}
