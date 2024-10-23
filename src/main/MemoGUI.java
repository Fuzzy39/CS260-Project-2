package main;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class MemoGUI extends Application 
{
	private Stage mainWindow;
	private MemoPane memoPane;
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
		
		Button clear = new Button("Clear");
		clear.setOnAction((ActionEvent e)->{memoPane.getChildren().clear();});
		buttons.add(clear);
		
		Button exit = new Button("Exit");
		// I forgot java lambdas is a weird shoehorn that represent implementations of single method interfaces.
		// C# really feels polished in comparison, at least in this regard.
		exit.setOnAction((ActionEvent e)->{mainWindow.close();});
		buttons.add(exit); // I don't see why we need a button to exit, that's what the X is for, isn't it?
		// Also I've clearly forgotten everything to do with javafx.
		// Though I doubt I knew much of it in the first place. I've never used it outside of CS class, for tiny
		// projects.
		// This is probably a bit more substantial I suppose.
		// Would I choose this over swing? probably. css is nice, the fxml seems nice too, though I've not used it.
		// I don't feel like I understand how javafx works at all though. I suppose in order to learn that I should
		// read a bunch of the docs... but I doubt I'll do that unless I keep having javafx projects for school.
		// personally I'd rather learn C++ and try something like Qt or GTK, since I have a decent grasp on java.
		// but I've never used any of those things, so no idea how they compare.
		// Am I procrastinating by randomly talking? probably. Whoever's grading my assignment, hopefully you're having a nice day
		// and maybe this is interesting, I don't know. I hope my comments across everything don't seem too 
		// obnoxious. I know I don't really know what I'm doing that well on the grand scheme of things, so my 
		// opinions probably seem very silly.
		
		// but, uh, code. right.
		
		
		
		for(Button b: buttons)
		{
			// allows buttons to stretch.
			b.setMaxWidth(Double.MAX_VALUE);
		}
		
		// And yes, I'm aware I could've just made the HBox and added things to it, but
		// I wanted to feel that the effort I expended on making a decent Linked list implementation
		// should be worth something.
		//Button[] buttonArray = buttons.toArray(new Button[0]);
		rightPanel.getChildren().addAll(buttons);
	
		return rightPanel;
		
	}
	
	
	private Parent createContent()
	{
		memoPane = new MemoPane();
		memoPane.AddMemo(new MemoRecord(0, new Point2D(100,100), "This is 20 character\nTestTestTestTestTest\nAnother line", 
				Color.BLACK, Color.YELLOW));
		memoPane.AddMemo(new MemoRecord(0, new Point2D(300, 300), "No Body!\n", Color.WHITE, Color.DARKCYAN));
		HBox content = new HBox(memoPane, createRightPanel());
		HBox.setHgrow(memoPane, Priority.SOMETIMES	);
		return content;
	}
	@Override
    public void start(Stage stage) {
       
        Scene scene = new Scene(createContent(), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Obamazone, for all your obama needs");
        stage.show();
        mainWindow = stage;
    }
	
 

}
