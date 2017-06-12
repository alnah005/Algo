package ch06;

import java.io.*;
import java.util.*;

public class TelephoneBook<T extends Comparable<T>> extends RefSortedList<T> implements ListInterface<T> {
	public TelephoneBook() {
		super();// pass the default constructor
	}

	private static String fileName = ""; // the contacts file path
	private static Scanner command; // scanner class object

	public void scanContacts() throws IOException {
		// method to get contacts into the system
		command = new Scanner(System.in);
		fileName = command.nextLine().trim();
		// get file path
		System.out.println("Path processed");
		// show that the the path name was scanned
		Scanner fileScanner = null;
		// a new scanner that proccesses the file's content
		try {
			fileScanner = new Scanner(new FileInputStream(fileName));
			// connecting the scanner to the file
			while (fileScanner.hasNext()) {
				// scanning all content
				T last = (T) fileScanner.next();
				// last name first
				T first = (T) fileScanner.next();
				// first name
				T number = (T) fileScanner.next();
				// number
				this.add((T) last, first, number);
				// add an object with these characteristics to our list
			}
			fileScanner.close();
			// after all scanning is complete closing the scanner
		} catch (IOException e) {
			System.out.println(e.getMessage());
			// handling exception
		}
		System.out.println("Contacts processed succesfully");
		// if done, show that all contacts were processed
	}

	public void quit() throws FileNotFoundException {
		// method that closes program
		File updatedContacts = new File(fileName);
		// creating a new file instead of the old file
		PrintWriter outputStream = null;
		// a print writer to output strings
		try {
			outputStream = new PrintWriter(new FileOutputStream(updatedContacts, false));
			// connecting the print writer to the file
			LLNode<T> currNode = list;
			// loop all list members
			while (currNode != null) {
				outputStream.println(currNode.toString());
				// print the strings of all memebrs to file
				currNode = currNode.getLink();
			}
			outputStream.close();
			// close the print writer
		} catch (FileNotFoundException e) {
			// handling exception
			System.out.println(e.getMessage());
		}
	}

	public String toString() {
		return super.toString();
	}

	public String deleteContact(T lastName, T firstName, T number) {
		// method to delete contact
		String output;
		// result
		if (this.remove(lastName, firstName, number))
		// if name was deleted boolean is true
		{
			output = "contact was deleted";
		} else { // otherwise
			output = "make sure you have the right contact";
		}
		return output;
	}

	public static void main(String[] args) throws IOException {
		TelephoneBook<String> one = new TelephoneBook<String>();
		System.out.println("Contact processor started.");
		// display info the user might want
		System.out.println("please input the source folder to your contacts");
		// command for the file path
		one.scanContacts();
		// starting the method that scans contacts
		for (int g = 0; g < 100; g++) {
			// make the program run 100 times
			System.out.println("Please type a command to continue");
			// display info the user might need
			System.out.println("Insert,search,delete,print,or quit");
			// listing commands
			command = new Scanner(System.in);
			if (command.hasNext()) {
				// if user inputs something
				String option;
				option = command.next();
				// what the user input
				if (option.trim().equalsIgnoreCase("insert")) {
					// command is to insert a new contact
					System.out.println("please input the last name, first name, then number");
					// instructions
					String lastName = command.next().trim().toLowerCase();
					// scan last name
					String firstName = command.next().trim().toLowerCase();
					// scan first name
					String number = command.next().trim();
					// scan number
					one.add(lastName, firstName, number);
					// add an object with that last and first names and number
				} else if (option.trim().equalsIgnoreCase("search")) {
					// command is to search for a contact
					System.out.println("write the name to search both first names and last names.");
					// instructions
					String name = command.next().trim().toLowerCase();
					// scanning name
					System.out.println("in first names,");
					one.compareFirstNames(name);
					// scan first names of objects in list
					System.out.println("in last names,");
					one.compareLastNames(name);
					// scan last names of objects in list
				} else if (option.trim().equalsIgnoreCase("delete")) {
					// command is to delete a contact
					System.out.println(
							"please input the last name,first name and number, in that order, of the contacts you want to delete");
					String lastName = command.next().trim().toLowerCase();
					// scan last name
					String firstName = command.next().trim().toLowerCase();
					// scan first name
					String number = command.next().trim();
					// scan number
					System.out.println(one.deleteContact(lastName, firstName, number));
				} else if (option.trim().equalsIgnoreCase("print")) {
					// if command is to print all contacts
					System.out.println(one.toString());
				} else if (option.trim().equalsIgnoreCase("quit")) {
					// if command is to quit the program
					System.out.println("thanks for using this program");
					one.quit();
					System.exit(0);
				} else {
					// if the command is something else
					System.out.println("Please type in a command that the system recognizes.");
					continue;
					// restart loop
				}
			} // command has next end

		} // loop end
	} // main method
}// class