//----------------------------------------------------------------------------
// LLNode.java            by Dale/Joyce/Weems                  Chapter 3
//
// Implements <T> nodes for a Linked List.
//----------------------------------------------------------------------------

package ch06;

public class LLNode<T> // class to form linked list
{
	private LLNode<T> link;// node in each member of the list
	private T last, first, number; // each object of this class has three
									// characteristics

	public LLNode(T last, T first, T number)// default constructor
	{
		this.last = last;// setting initial values
		this.first = first;// setting initial values
		this.number = number;// setting initial values
		link = null;// no link yet
	}

	public String toString() {// converting the variables into a string
		String string = last + " " + first + " " + number;
		return string;// returning the string in the form last name first name
						// number
	}

	public void setLast(T last)// mutator
	// Sets info of this LLNode.
	{
		this.last = last;
	}

	public void setNumber(T number)// mutator
	// Sets info of this LLNode.
	{
		this.last = last;
	}

	public void setFirst(T first)// mutator
	// Sets info of this LLNode.
	{
		this.last = last;
	}

	public T getLast()// accessor
	// Returns info of this LLONode.
	{
		return last;
	}

	public T getNumber()// accessor
	// Returns info of this LLONode.
	{
		return number;
	}

	public T getFirst()// accessor
	// Returns info of this LLONode.
	{
		return first;
	}

	public void setLink(LLNode<T> link)// making the object to another object
	// Sets link of this LLNode.
	{
		this.link = link;
	}

	public LLNode<T> getLink()// accessor to the linked object
	// Returns link of this LLNode.
	{
		return link;
	}
}
