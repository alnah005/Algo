package ch06;

public class RefSortedList<T extends Comparable<T>> extends RefUnsortedList<T> implements ListInterface<T>

{
	public RefSortedList() {
		super();
	}

	public void add(T last, T first, T number)
	// Adds element to this list.
	{
		LLNode<T> prevLoc; // trailing reference
		LLNode<T> location; // traveling reference
		T listElement, listElement2; // current list element being compared

		// Set up search for insertion point.
		location = list;
		prevLoc = null;

		// Find insertion point.
		while (location != null) {
			listElement = location.getLast();
			listElement2 = location.getFirst();
			if (listElement.compareTo(last) == 0 && listElement2.compareTo(first) <= 0)
			// list element < add element alphabetically
			// if both contacts have the same last name, compare first names
			{// insert member
				prevLoc = location; // setting links
				location = location.getLink();// setting links
			} else if (listElement.compareTo(last) < 0) {
				// list element < add element alphabetically
				// compare last names
				prevLoc = location; // setting links
				location = location.getLink(); // setting links
			} else
				break; // otherwise the position is fit for insertion
		}

		// Prepare node for insertion.
		LLNode<T> newNode = new LLNode<T>(last, first, number);

		// Insert node into list.
		if (!this.containsNumber(number)) {
			// if one of the contacts already has the number
			if (prevLoc == null) {
				// Insert as first node
				prevLoc = list;
				newNode.setLink(list);
				list = newNode;

			} else {
				// Insert elsewhere.
				newNode.setLink(location);
				prevLoc.setLink(newNode);
			}
			numElements++;

		} else {// if number hasn't been registered
			System.out.println("This number: " + number + " has already been assigned to a contact");
		} // print out that that number has been assigned
	}
}
