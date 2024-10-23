package main;

/**
 * This class exists to facilitate meeting project requirements.
 * I doubt it will end up being tremendously useful given how I've set things up, but eh.
 */
public class MemoLinkedList extends MyLinkedList<Memo>
{

	private MyLinkedList<Memo>.MyItr iterator;
	
	public MemoLinkedList()
	{
		super();
		
		// I have never seen a .new before. That's kinda funky, isn't it?
		iterator = this.new MyItr(this);
	}
	
	public void insertAfter(Memo memo)
	{
		// this will always throw an exception since this method wasn't implemented.
		iterator.add(memo);
	}

	/**
	 * The method will find a location to which
     * applies the insertion or deletion operation.
	 * @param memoID
	 * @return returns true if a memo with the specified ID is in the list.
	 */
	public boolean search(int memoID)
	{
		iterator = this.new MyItr(this);
		Memo memo = null;
		// a do while loop??????? wow.
		// thought I'd never use one.
		// this is harder to read though, so it's probably bad to do this, in general
		// mostly hard to read because nobody ever uses a do-while loop.
		do 
		{
			if(!iterator.hasNext()) 
			{	
					return false;
			}
			
			memo = iterator.next();
		}
		while(memo.getData().ID() != memoID);
		
		return true;
		
	}
	
	/**
	 * Same as remove per project reuquirments.
	 * It seems rather odd, from my perspective, for this 'cursor' to be persistent across multiple
	 * api calls. the java iterator does it, but you very rarely use the iterator directly so you don't
	 * have to think about it's existence. Feels like an implementation detail.
	 * Maybe I'm just naive.
	 * But that's not what javadoc is for.
	 * When a list is not empty, remove the cursor pointing node from the list.
	 * After deleting the cursor pointing element,
set the cursor to the following node.
	 * @param memoID
	 */
	public void memoRemove(int memoID)
	{
		// I should probably implement this method
		iterator.remove();
		if(!search(memoID))
		{
			iterator = this.new MyItr(this);
		}
	}
	
	/**
	 * This is the exact same as addAll.
	 * @param other
	 */
	public void merge(MemoLinkedList other)
	{
		this.addAll(other);
	}
	
	/**
	 * Get a memo by ID. Does not change the cursor.
	 * @param memoID
	 * @return
	 */
	public Memo retrive(int memoID)
	{
		// this loop basically shows why I'm not using this class for much, it's very simple
		// and natural syntax if you implement java's iterator.
		// based on my understanding, I won't even be using a memo's id for anything.
		// eh.
		// Though I suppose having these sorts of utility methods are very nice.
		// Makes me appreciate C#'s linq more.
		for(Memo memo : this)
		{
			if(memo.getData().ID() == memoID)
			{
				return memo;
			}
		}
		return null;
	}
	
	/**
	 * 
	 */
	public void goToBeggining()
	{
		iterator = this.new MyItr(this);
	}
	
	public void goToEnd()
	{
		while(iterator.hasNext())
		{
			iterator.next();
		}
	}
	
	public void goToPrior()
	{
		throw new UnsupportedOperationException("Not implemented.");
		// I also didn't implement this in the iterator... so meh.
	}
	
	public void goToNext()
	{
		iterator.next();
	}
	
}
