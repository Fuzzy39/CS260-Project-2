package main;



import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

public class Memo extends StackPane 
{

	private MemoRecord data;
	private HBox hbox;
	
	public Memo(MemoRecord record) {
		// TODO Auto-generated constructor stub
		data = record;
		
		// then make everything based on the data
		hbox = new HBox();
		getChildren().add(hbox);
		
		// label
		Label note = new Label(data.note());
		note.setMaxSize(100, 100);
		note.setPrefSize(100, 100);
		hbox.getChildren().add(note);
		
		// close button
		Button close = new Button("X");
		close.setCancelButton(true);
		hbox.getChildren().add(close);
		
		this.setBackground(Background.fill(record.backgroundColor())); 
		relocate(data.location().getX(), data.location().getY());
	}
}
