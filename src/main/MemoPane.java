package main;

import java.util.Arrays;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
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
		
	}
	
	/**
	 * this lambda, attached to our children, assures that the linked list has the same
	 * contents as our children. Note that the MemoPane's children is the primary collection, and that
	 * our linked list is essentially a copy. Changing our children will update the linked list,
	 * but the opposite is not true.
	 * Also, observable list - really cool.
	 * @return
	 */
	private ListChangeListener<? extends Node> getOnChildrenChanged()
	{
		// Something I'm confused about: wildcards. Isn't <? extends Node> the same as <Node>?
		// I suppose not, technically. Does it matter though?
		// A google explained that it was important for generic method parameters, 
		// which makes enough sense.
		
		return (ListChangeListener.Change<? extends Node> change) -> 
		{
			// now, it's possible to not just reconstruct the entire linked list here,
			// but I'm lazy, so we'll just reconstruct the entire linked list.
			// What is that, O(n)? I mean, that's... actually not too horribly painful, considering
			// every other operation on the list is O(n).
			// I mean I suppose it also depends on how an ObservableList is constructed,
			// assuming it's an array, it's O(n). Probably not worse than that.
			
			// Wait, no, the way I had it set up it was O(n^2).
			// gotta do it better than that.
			
			
			// This is very clearly not the best way to do this, but it does work.
			for(Node node:change.getList())
			{
				if(!(node instanceof Memo))
				{
					throw new UnsupportedOperationException("A MemoPane can only contain Memos.");
				}
			}
			
			memos.clear();
			//memos.addAll((Memo[]) getChildren().toArray());
			
			assert Arrays.equals(memos.toArray(), getChildren().toArray());
				
		};
	}
	
	
	public void AddMemo(MemoRecord memo)
	{
		Memo toAdd = new Memo(memo);
		getChildren().add(toAdd);
		memos.add(toAdd);
		
	}	
	
	
	

}
