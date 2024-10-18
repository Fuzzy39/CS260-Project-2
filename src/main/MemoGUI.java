package main;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class MemoGUI extends Application 
{
	
	private Parent createRightPanel()
	{	
		VBox rightPanel = new VBox();
		rightPanel.setAlignment(Pos.TOP_CENTER);
		rightPanel.setFillWidth(true);
		rightPanel.setSpacing(10);
	
	
		// I would use an arrayList to create the buttons, but given that we're
		// not supposed to use the built in collection classes, I guess
		// we'll have to use my linked list, then.
		MyLinkedList<Button> buttons = new MyLinkedList<Button>();
		buttons.add(new Button("New Memo"));
		buttons.add(new Button("Read from File"));
		buttons.add(new Button("Merge from File"));
		buttons.add(new Button("Save to File"));
		buttons.add(new Button("Clear"));
		buttons.add(new Button("Exit")); // I don't see why we need a button to exit, that's what the X is for.
		
		// And yes, I'm aware I could've just made the HBox and added things to it, but
		// I wanted to feel that the effort I expended on making a decent Linked list implementation
		// should be worth something.
		//Button[] buttonArray = buttons.toArray(new Button[0]);
		System.out.println(buttons);
		System.out.println(buttons.size());
		System.out.println(Arrays.toString(buttons.toArray()));
		System.out.println(Arrays.toString(buttons.toArray(new Button[0])));
		rightPanel.getChildren().addAll(buttons);
	
		return rightPanel;
		
	}
	
	
	private Parent createContent()
	{
		
		return new Pane(createRightPanel());
	}
	@Override
    public void start(Stage stage) {
       
        Scene scene = new Scene(createContent(), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Obamazone, for all your obama needs");
        stage.show();
    }
	
 

}