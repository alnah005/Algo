package ch06;

public class RefUnsortedList<T> implements ListInterface<T> {

	protected int numElements; // number of elements in this list
	protected LLNode<T> currentPos; // current position for iteration

	// set by find method
	protected boolean found; // true if element found, else false
	protected LLNode<T> location; // node containing element, if found
	protected LLNode<T> previous; // node preceeding location

	protected LLNode<T> list; // first node on the list

	public RefUnsortedList() {// default constructor
		numElements = 0;// number of list elements
		list = null;// first node is alone in the list
		currentPos = null;// the node in the program
	}

	public void add(T last, T first, T number)
	// Adds element that has a certain last name first name and number to this
	// list.
	{
		LLNode<T> newNode = new LLNode<T>(last, first, number);
		// passing on the values to LLNode constructor
		if (list == null) {// if there are no elements in the list
			list = newNode;// the new element is the first element
			newNode.setLink(null);// the new element's link is empty
		} else {// find the point where the list is empty
			currentPos = list;// set starting search point
			while (currentPos.getLink() != null) {
				// keep going down the list until you find the empty link
				currentPos = currentPos.getLink();
			}
			currentPos.setLink(newNode);
			// making the last element linked to the new element
			newNode.setLink(null);// the new element has an empty link
		}
		numElements++;
		// number of elements either way is increased by one element
	}

	protected LLNode findLast(T targetLast)
	// Searches list for an occurence of an element e such that
	// e has the same last name as an element in the list If successful, sets
	// instance variables
	// found to true, location to node containing last name, and previous
	// to the node that links to location. If not successful, sets
	// found to false.
	{
		location = list;// start in the beginning of the list
		found = false;
		while (location != null) {// if list is not empty and our element is not
									// in the last one
			if (location.getLast().equals(targetLast)) // if last names match
			{
				found = true;// location is found
				return location;// return the object with that last name
			} else {// location is not found
				previous = location;// switch locations
				location = location.getLink();// next location
			}
		}
		return null;// if not found return null
	}

	protected LLNode findFirst(T targetFirst)
	// Searches list for an occurence of an element e such that
	// e first name equals the first name of an element in the list). If
	// successful, sets instance variables
	// found to true, location to node containing first name, and previous
	// to the node that links to location. If not successful, sets
	// found to false.
	{
		location = list;// same concept as last name method
		found = false;// same concept as last name method

		while (location != null) {// same concept as last name method
			if (location.getFirst().equals(targetFirst)) // same concept as last
															// name method
			{
				found = true;// same concept as last name method
				return location;// same concept as last name method
			} else {
				previous = location;// same concept as last name method
				location = location.getLink();// same concept as last name
												// method
			}
		}
		return null;// same concept as last name method
	}

	protected LLNode findNumber(T targetNumber)
	// Searches list for an occurence of an element e such that
	// e number will have the same number as the number of an object in the
	// list. If successful, sets instance variables
	// found to true, location to node containing e, and previous
	// to the node that links to location. If not successful, sets
	// found to false.
	{
		location = list; // same concept as last name method
		found = false;// same concept as last name method

		while (location != null) {// same concept as last name method
			if (location.getNumber().equals(targetNumber)) // same concept as
															// last name method
			{
				found = true;// same concept as last name method
				return location;// same concept as last name method
			} else {
				previous = location;// same concept as last name method
				location = location.getLink();// same concept as last name
												// method
			}
		}
		return null;// same concept as last name method
	}

	public int size()
	// Returns the number of elements on this list.
	{
		return numElements;
	}

	public boolean containsLast(T last)
	// Returns true if this list contains an element e such that
	// e last name is the same of an element in the list; otherwise, returns
	// false.
	{
		if (findLast(last) == (null)) {// if the method returns null
			found = false; // method makes found false
		} else {
			found = true; // otherwise true
		}
		return found; // returns the result
	}

	public boolean containsNumber(T number)
	// Returns true if this list contains an element e such that
	// e number is the same of an element in the list; otherwise, returns
	// false.
	{
		if (findNumber(number) == (null)) { // same concept as previous method
			found = false; // same concept as previous method
		} else {
			found = true; // same concept as previous method
		}
		return found; // same concept as previous method
	}

	public boolean containsFirst(T first)
	// Returns true if this list contains an element e such that
	// e first name is the same of an element in the list; otherwise, returns
	// false.
	{
		if (findFirst(first) == (null)) { // same concept as previous method
			found = false; // same concept as previous method
		} else {
			found = true; // same concept as previous method
		}
		return found; // same concept as previous method
	}

	public boolean remove(T last, T first, T number)
	// Removes an element e from this list such that e.equals(element)
	// with the same characteristics
	// and returns true; if no such element exists, returns false.
	{
		String contact = last + " " + first + " " + number;
		// write the characteristics to a string
		if (this.containsLast(last)) {
			// if the list has the last name in the list
			found = false;
			for (int i = 0; i < this.size(); i++) {// loop the whole list
				if (location != null) {
					if (location.toString().equals(contact)) {
						// if the two strings equal
						if (location == list) {
							// if the member of the list is the first element
							list = list.getLink();
							// making the last start at the next element
							found = true;// the member is deleted, thus true
						} else {
							previous.setLink(location.getLink());
							// make previous link link to the next element, so
							// deleted
							found = true;// the member is deleted
						}
						numElements--; // list elements are less by one
					}
					location = location.getLink();
					// if not found get next list element
				}
			}
		} else {
			found = false;
			// if loop finishes, the object is not found so nothing is removed
		}

		return found; // return result
	}

	public void compareLastNames(T lastName) {
		// a method that compares last names
		if (this.containsLast(lastName)) { // if last name is in the list
			for (int i = 0; i < this.size(); i++) {
				// loop around the entire list
				{
					if (location != null) {
						// if not the list node or the final node
						if (location.getLast().equals(lastName)) {
							// if last names match
							System.out.println(location.toString());
							// print out the list member
						}
						location = location.getLink();
						// if they dont match, go to the next element
					} else {
						location = list;// else, start over
					}
				}
			}
		} else {// if name is not in the list
			System.out.println("name wasn't found");
			// display result
		}

	}

	public void compareFirstNames(T firstName) {
		// same as previous method
		if (this.containsFirst(firstName)) {// same as previous method
			for (int i = 0; i < this.size(); i++) {// same as previous method
				{
					if (location != null) {// same as previous method
						if (location.getFirst().equals(firstName)) {
							// same as previous method
							System.out.println(location.toString());
							// same as previous method
						}
						location = location.getLink();
					} // same as previous method
					else {
						location = list;// same as previous method
					}
				}
			}
		} else {
			System.out.println("name wasn't found");// same as previous method
		}

	}

	public String toString()
	// Returns a nicely formatted string that represents this list.
	{
		LLNode<T> currNode = list;// loop around all list members
		String listString = ""; // starting string
		while (currNode != null) {
			listString = listString + currNode.getLast() + " " + currNode.getFirst() + " " + currNode.getNumber()
					+ "\n";
			// add strings of objects
			currNode = currNode.getLink(); // go to the next member
		}
		return listString;// return the final string
	}

	public void reset()
	// Initializes current position for an iteration through this list,
	// to the first element on this list.
	{
		currentPos = list;
	}

	public LLNode getNextNode(LLNode position) {
		LLNode next = position.getLink();
		return next;
	}

	public T getNextLast()
	// Preconditions: The list is not empty
	// The list has been reset
	// The list has not been modified since most recent reset
	//
	// Returns the last name at the next position on this list.
	// If the current position is the last element, then it advances the value
	// of the current position to the first element; otherwise, it advances
	// the value of the current position to the next element.
	{
		T next = currentPos.getLast();
		if (currentPos.getLink() == null)
			currentPos = list;
		else
			currentPos = currentPos.getLink();
		return next;
	}

	public T getNextFirst()
	// Preconditions: The list is not empty
	// The list has been reset
	// The list has not been modified since most recent reset
	//
	// Returns the first name at the next position on this list.
	// If the current position is the last element, then it advances the value
	// of the current position to the first element; otherwise, it advances
	// the value of the current position to the next element.
	{
		T next = currentPos.getFirst();
		if (currentPos.getLink() == null)
			currentPos = list;
		else
			currentPos = currentPos.getLink();
		return next;
	}

	public T getNextNumber()
	// Preconditions: The list is not empty
	// The list has been reset
	// The list has not been modified since most recent reset
	//
	// Returns the element at the current position on this list.
	// If the current position is the last element, then it advances the value
	// of the current position to the first element; otherwise, it advances
	// the value of the current position to the next element.
	{
		T next = currentPos.getNumber();
		if (currentPos.getLink() == null)
			currentPos = list;
		else
			currentPos = currentPos.getLink();
		return next;
	}

}
