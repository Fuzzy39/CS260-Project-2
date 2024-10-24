package main;

import java.util.Random;

import data.MemoRecord;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class is responsible for managing creation of new memos by the user.
 */
public class NewMemo 
{
	private Stage memoWindow;	
	private MemoPane addTo;
	int nextID;
	
	private Scene constructUI()
	{
		
		// create controls
		ColorPicker foreground = new ColorPicker();
		ColorPicker background = new ColorPicker();
		TextField title = new TextField("Memo "+nextID);
		TextArea text = new TextArea("Write up to 4\nlines of 20\ncharacter text!");
	
		Button confirm = new Button("Create Memo");
		GridPane body = new GridPane();
		Label label;
		
		// configure controls
		
		foreground.setValue(Color.BLACK);
		background.setValue(Color.YELLOW);
		
		// not sure what this sets
		title.setPrefColumnCount(20);
		title.setDisable(true);
		text.setPrefColumnCount(20);
		text.setPrefRowCount(4);
		
		confirm.setMaxWidth(Double.MAX_VALUE);
		
		body.setPadding(new Insets(10));
		body.setHgap(30);
		body.setVgap(10);
		
		
		
		// setup gridpane layout
		label = new Label("Foreground Color");
		GridPane.setConstraints(label, 0, 0, 1, 1);
		GridPane.setConstraints(foreground, 1, 0, 1, 1, HPos.RIGHT, VPos.TOP);
		body.getChildren().add(label);
		
		label =new Label("Background Color");
		GridPane.setConstraints(label, 0, 1, 1, 1);
		GridPane.setConstraints(background, 1, 1, 1, 1, HPos.RIGHT, VPos.TOP);
		body.getChildren().add(label);
		
		label =new Label("Title");
		GridPane.setConstraints(label, 0, 2, 1, 1);
		GridPane.setConstraints(title, 1, 2, 1, 1, HPos.RIGHT, VPos.TOP);
		body.getChildren().add(label);
		
		label =new Label("Memo Text");
		GridPane.setConstraints(label, 0, 3, 1, 1);
		GridPane.setConstraints(text, 1, 3, 1, 1, HPos.RIGHT, VPos.TOP);
		body.getChildren().add(label);
		
		GridPane.setConstraints(confirm, 0, 4, 2, 1, HPos.CENTER, VPos.BOTTOM, Priority.ALWAYS, Priority.NEVER);
		
		
		body.getChildren().addAll(foreground, background, title, text, confirm);
		
		
		// set confirm button behavior.
		
		confirm.setOnAction((ActionEvent e)->{
			MemoRecord mem = new MemoRecord(nextID, getMemoLocation(), getMemoNote(text), foreground.getValue(), background.getValue());
			addTo.AddMemo(mem);
			memoWindow.close();
		});
		
		
		
		
		return new Scene(body, 400, 250);
	}
	
	
	private Point2D getMemoLocation()
	{
		Bounds limit = addTo.getLayoutBounds();
		Point2D max = new Point2D(limit.getMaxX()-Memo.WIDTH, limit.getMaxY()-Memo.HEIGHT);
		Random rand = new Random();
		
		return new Point2D(rand.nextDouble(max.getX()), rand.nextDouble(max.getY()));
	}
	
	private String getMemoNote(TextArea from)
	{
		String[] lines = from.getText().split("\n", 4);
		String toReturn = "";
		for(String line : lines)
		{

			if(line.length()>20)
			{
				line = line.substring(0, 20); 
			}
			toReturn +=line+"\n";
		}
		
		return toReturn;
		
	}
	
	
	public NewMemo(MemoPane addTo) 
	{

		this.addTo = addTo;
		nextID = addTo.getNextID();
		memoWindow = new Stage();
		memoWindow.setScene(constructUI());
		memoWindow.setResizable(false);
		memoWindow.setTitle("New Memo");
		memoWindow.show();
	}
	
	
	public Stage getWindow()
	{
		return memoWindow;
	}

}
