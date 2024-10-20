package main;

import javafx.scene.layout.Pane;

/**
 * A MemoPane Contains every Memo.
 */
public class MemoPane extends Pane
{
	// I feel the need to note that, due to how my code is set up,
	// this list is essentially vestigial. 
	// the contents of this list should always be equal to the contents of
	// this.getChildren(), so this list really isn't necessary.
	private MemoLinkedList memos;
	
	public MemoPane() 
	{
		super();
		memos = new MemoLinkedList();
		// TODO Auto-generated constructor stub
	}
	
	
	public void AddMemo(MemoRecord memo)
	{
		Memo toAdd = new Memo(memo);
		getChildren().add(toAdd);
		memos.add(toAdd);
		
	}

}
