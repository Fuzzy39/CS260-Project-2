package main;

import javafx.scene.layout.Pane;

/**
 * A MemoPane Contains every Memo.
 */
public class MemoPane extends Pane
{
	
	public MemoPane() 
	{
		// TODO Auto-generated constructor stub
	}
	
	
	public void AddMemo(MemoRecord memo)
	{
		this.getChildren().add(new Memo(memo));
		
	}

}
