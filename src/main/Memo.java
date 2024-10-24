package main;



import data.MemoRecord;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class Memo extends StackPane 
{
	final static int WIDTH = 150;
	final static int HEIGHT = 110;

	private MemoRecord data;
	private VBox vbox;
	
	// A point in our coordinate space where the title bar was first clicked
	// during a drag operation. This is required to facilitate window dragging.
	private Point2D dragLocalStart;
	
	public Memo(MemoRecord record) {
		
		this.setPrefSize(WIDTH, HEIGHT);
		this.setMaxSize(WIDTH, HEIGHT);
		// TODO Auto-generated constructor stub
		data = record;
		
		// Construct the main body
		vbox = new VBox();
		vbox.setAlignment(Pos.TOP_LEFT);
		getChildren().add(vbox);
		
		// TitleBar
		HBox titleBar = new HBox();
		vbox.getChildren().add(titleBar);
		titleBar.setSpacing(15);
		
		// header
		Label header = new Label(getTitle());
		header.setPrefWidth(130); // I'd like to figure out some way to know how wide a string is in pixels.
		header.setMaxWidth(130);
		header.setTextFill(data.foregroundColor());
		HBox.setMargin(header, new Insets(5));
		header.setStyle("-fx-font-weight: bold");
		
		// close button
		Button close = new Button("X");
		close.setOnAction((ActionEvent e)->
		{
			// this may not be strictly true, but if not, we probably want a crash anyways.
			// since I'm not sure how to handle that situation.
			// For this program, Memos will only ever be in memoPanes, so it's a non-issue.
			Pane p = (Pane)getParent();
			p.getChildren().remove(this);
			
		});
		
		close.setCancelButton(true);
		titleBar.getChildren().addAll(header, close);
		
		
		
		// label
		if(!(getMemoBody().equals("")))
		{
			Label note = new Label(getMemoBody());
			VBox.setMargin(note, new Insets(5));
			note.setMaxWidth(150);
			note.setPrefWidth(150);
			note.setPrefHeight(70);
			note.setTextFill(data.foregroundColor());
			
			vbox.getChildren().add(note);
		}
	
		
		this.setBackground(Background.fill(record.backgroundColor())); 
		relocate(data.location().getX(), data.location().getY());
		setupDrag();
	}
	
	
	private void setupDrag()
	{
		this.setOnMousePressed((MouseEvent e)->
		{
			dragLocalStart= sceneToLocal(e.getSceneX(), e.getSceneY());
		});
		
		this.setOnMouseDragged((MouseEvent e)->
		{
			// grab the mouse location.
			Point2D loc = new Point2D(e.getSceneX(), e.getSceneY());
			Pane parent = (Pane)getParent();
		
			loc = parent.sceneToLocal(loc);
			loc = loc.subtract(dragLocalStart);
			
			
			// ensure that the bounds remain in the MemoPane.
			if(loc.getX()<0) loc = new Point2D(0, loc.getY());
			if(loc.getY()<0) loc = new Point2D(loc.getX(), 0);

			
			Bounds layoutBounds = this.getLayoutBounds();
			Point2D size = new Point2D(layoutBounds.getWidth(), layoutBounds.getHeight());
			Bounds parentBounds = parent.getLayoutBounds();
			Point2D parentSize = new Point2D(parentBounds.getWidth(), parentBounds.getHeight());
			
			// for the bottom and right sides, too.
			if(loc.getX()+size.getX()>parentSize.getX())
			{
				loc = new Point2D(parentSize.getX()-size.getX(), loc.getY());
			}
			
			if(loc.getY()+size.getY()>parentSize.getY())
			{
				loc = new Point2D(loc.getX(), parentSize.getY()-size.getY());
			}
			
			this.relocate(loc.getX(), loc.getY());
		});
		
		
		this.setOnMouseDragReleased((MouseEvent e)->{
			// update the data.
			Bounds bounds = getBoundsInParent();
			Point2D loc = new Point2D(bounds.getMinX(), bounds.getMinY());
			
			MemoRecord newData = new MemoRecord(
					data.ID(), loc, data.note(), data.foregroundColor(), data.backgroundColor());
			data = newData;
		});
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
		return "Memo "+data.ID();
	}
	
	/**
	 * Get the main text body of a Memo.
	 * @return
	 */
	public String getMemoBody()
	{
		return data.note();
	}
}
