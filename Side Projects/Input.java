import java.util.*;

public class Input implements MyBigDecimal {
	private static String number1; // string of natural numbers of the first
									// number
	private static String number2; // string of natural numbers of the second
									// number
	private static String decimal1;// string of decimals of the first number
	private static String decimal2;// string of decimals of the second number
	public int[] naturalNumbers; // array of the natural number of any number
	public int[] decimals;// array of the decimals of any number
	public static String operation; // the operation variable

	private static int[] stringToArray(String number) {// method that converts a
														// string to a set of
		// numbers in an array and checks if the string is all numbers or
		// decimal point
		int[] newArray = new int[number.length()];// make an array of the same
													// size as the string
													// processed
		int counter = 0; // number of decimals in string
		for (int a = 0; a < number.length(); a++) {// checking each character in
													// the string and converting
													// it back to integers
			int digit = (int) number.charAt(a);// typecasting
			switch (digit) {
			case 46:
				counter++;
				continue;// decimal character
			case 48:
				digit = 0;
				break;// 0
			case 49:
				digit = 1;
				break;// 1
			case 50:
				digit = 2;
				break;// 2
			case 51:
				digit = 3;
				break;// 3
			case 52:
				digit = 4;
				break;// 4
			case 53:
				digit = 5;
				break;// 5
			case 54:
				digit = 6;
				break;// 6
			case 55:
				digit = 7;
				break;// 7
			case 56:
				digit = 8;
				break;// 8
			case 57:
				digit = 9;
				break;// 9
			}
			if (digit <= 9 && digit >= 0 && counter < 2) {// digit is not from
															// 0-9 and there is
															// one decimal only
				newArray[a] = digit;// put it in an array
			} else {
				System.out.println("input is invalid, program terminated");
				Input.startProgram();
			}
		}
		return newArray; // return the array made from the string
	}

	private static String arrayToString(int[] number) {// method that converts
														// from an array to
														// string
		String g = "";
		for (int z = 0; z < number.length; z++) {// put each array member the
													// string
			g = g + number[z];
		}
		return g;// return the string
	}

	public static void startProgram() {// method that scans input, calls
										// processor method to check it, runs
										// program from start to ending.
		// also calls the addition and subtraction methods and asks for
		// operation
		Scanner keyboard = new Scanner(System.in);
		System.out.println("I'll do basic mathematical operations to your big decimal");
		System.out.println("please type your decimal");
		String entireLine1 = keyboard.nextLine().trim();// scanning first input
														// entirely
		if (entireLine1.isEmpty() || !(entireLine1.contains("."))) {// if input
																	// is blank
																	// or
																	// doesn't
																	// have a
																	// decimal
			System.out.println("input invalid, please make sure to include a decimal point.");
			Input.startProgram();// recursion of the whole program
		}
		Input.stringToArray(entireLine1);// calling the method that checks
											// numeral input
		number1 = entireLine1.substring(0, entireLine1.indexOf("."));// natural
																		// numbers
		decimal1 = entireLine1.substring(entireLine1.indexOf(".") + 1);// decimals
		Input one = new Input();// creating a new object of class input
		one.processor(number1, decimal1); // processing the object and setting
											// values
		System.out.println("now input second number.");
		Input two = new Input();// second object
		String entireLine2 = keyboard.nextLine().trim();// scanning input
		if (entireLine1.isEmpty() || !(entireLine1.contains("."))) {// if
																	// nothing
																	// is input
																	// or just a
																	// decimal
			System.out.println("input invalid, please make sure to include a decimal point.");
			Input.startProgram();// recursion
		}
		if (entireLine2.isEmpty() || !(entireLine2.contains("."))) {// same
																	// thing to
																	// the
																	// second
																	// input
			System.out.println("input invalid, please make sure to include a decimal point.");
			Input.startProgram();// recursion of the whole program
		}
		Input.stringToArray(entireLine2);// calling the method that checks
											// numeral input
		number2 = entireLine2.substring(0, entireLine2.indexOf("."));// naturals
		decimal2 = entireLine2.substring(entireLine2.indexOf(".") + 1);// decimals
		two.processor(number2, decimal2);// processing the object and setting
											// values
		System.out.println("If you want to add them enter \"a\", if you want to subrtract enter\"s\"");
		operation = keyboard.next();// the operation
		if (operation.equalsIgnoreCase("s")) {// subtraction
			System.out.println(one.subtraction(two));// calling subtraction
														// method
		} else if (operation.equalsIgnoreCase("a")) {// addition
			System.out.println(one.addition(two));// calling addition method
		} else {
			System.out.println("The program doesn't support what you enetered sorry.");
		}
		Input.startProgram();// recursion of the whole program
	}

	public void processor(String number, String decimal) {
		naturalNumbers = new int[number.length()];// saving to object variables
		decimals = new int[decimal.length()];// saving to object variables
		naturalNumbers = Input.stringToArray(number);// converting strings to
														// arrays
		decimals = Input.stringToArray(decimal);// converting strings to arrays
		System.out.println("number processed and is in the correct format");
	}

	public String addition(Input two) {// addition method
		int[] naturalNumbersOperation;// result of natural numbers addition
										// variable
		int[] decimalsOperation;// result of decimals addition
		if (this.naturalNumbers.length < two.naturalNumbers.length) {// if
																		// second
																		// number
																		// bigger
																		// than
																		// first
			naturalNumbersOperation = new int[(two.naturalNumbers.length)];// make
																			// result
																			// variable
																			// have
			// the length of the bigger
		} else {// or the other way around
			naturalNumbersOperation = new int[this.naturalNumbers.length];
		}
		if (this.decimals.length < two.decimals.length) {// same thing with
															// decimals
			decimalsOperation = new int[two.decimals.length];
		} else {
			decimalsOperation = new int[this.decimals.length];
		}
		for (int i = 0; i < decimalsOperation.length; i++) {// adding the
															// decimals to the
															// result variable
			if (i < this.decimals.length) {
				decimalsOperation[i] = decimalsOperation[i] + this.decimals[i];
			}
			if (i < two.decimals.length) {
				decimalsOperation[i] = decimalsOperation[i] + two.decimals[i];
			}

		}
		for (int i = (decimalsOperation.length - 1); i >= 0; i--) {// rounding
																	// up
																	// formula
			if (decimalsOperation[i] > 9) {
				if (i > 0) {
					decimalsOperation[i - 1] = decimalsOperation[i - 1] + decimalsOperation[i] / 10;
					decimalsOperation[i] = decimalsOperation[i] - 10;
				} else {

					naturalNumbersOperation[naturalNumbersOperation.length
							- 1] = naturalNumbersOperation[naturalNumbersOperation.length - 1]
									+ decimalsOperation[i] / 10;
					decimalsOperation[i] = decimalsOperation[i] - 10;
				}
			}
		}
		int difference = (this.naturalNumbers.length) - (two.naturalNumbers.length);// length
																					// difference
																					// solver
		if (difference < 0) {
			difference = -difference;
			for (int z = 0; z < this.naturalNumbers.length; z++) {
				naturalNumbersOperation[z + difference] = naturalNumbersOperation[z + difference]
						+ this.naturalNumbers[z] + two.naturalNumbers[z + difference];
			}
			for (int z = 0; z < difference; z++) {
				naturalNumbersOperation[z] = naturalNumbersOperation[z] + two.naturalNumbers[z];
			}
		} else {
			for (int z = 0; z < two.naturalNumbers.length; z++) {
				naturalNumbersOperation[z + difference] = naturalNumbersOperation[z + difference]
						+ this.naturalNumbers[z + difference] + two.naturalNumbers[z];
			}
			for (int z = 0; z < difference; z++) {
				naturalNumbersOperation[z] = naturalNumbersOperation[z] + this.naturalNumbers[z];
			}
		}
		String string = "";
		for (int i = (naturalNumbersOperation.length - 1); i >= 0; i--) {// rounding
																			// up
																			// formula
			if (naturalNumbersOperation[i] > 9) {
				if (i > 0) {
					naturalNumbersOperation[i - 1] = naturalNumbersOperation[i - 1] + naturalNumbersOperation[i] / 10;
					naturalNumbersOperation[i] = naturalNumbersOperation[i] - 10;
				} else {
					string = "" + naturalNumbersOperation[i] / 10;
					naturalNumbersOperation[i] = naturalNumbersOperation[i] - 10;
				}

			}
		}
		string = string + Input.arrayToString(naturalNumbersOperation) + "." + Input.arrayToString(decimalsOperation);
		return string;// added numbers string
	}

	public String subtraction(Input two) {// subtraction method
		boolean negativeAnswer = false;
		int[] naturalNumbersOperation = null;
		int[] decimalsOperation;
		if (this.naturalNumbers.length < two.naturalNumbers.length) {// if the
																		// second
																		// number
																		// has
																		// bigger
																		// length
			naturalNumbersOperation = new int[two.naturalNumbers.length];
			negativeAnswer = true;// the answer is negative
		} else if (this.naturalNumbers.length == two.naturalNumbers.length) {// if
																				// they
																				// have
																				// the
																				// same
																				// lengths
			for (int i = 0; i < this.naturalNumbers.length; i++) {// checking
																	// the rest
																	// of the
																	// digits
				if (this.naturalNumbers[i] < two.naturalNumbers[i]) { // if
																		// other
																		// digits
																		// are
																		// bigger
					naturalNumbersOperation = new int[two.naturalNumbers.length];// array
																					// has
																					// the
																					// length
																					// of
																					// the
																					// bigger
					negativeAnswer = true;// and the number is negative
					break;
				} else if (this.naturalNumbers.length == 1) {// if its only one
																// natural
																// number
					naturalNumbersOperation = new int[this.naturalNumbers.length];// array
																					// size
																					// is
																					// one
					for (int z = 0; z < this.decimals.length; z++) {// check the
																	// decimals
																	// difference
						if (this.decimals[z] < two.decimals.length) {// if the
																		// second
																		// number
																		// is
																		// bigger
							negativeAnswer = true;// negative answer
							break;// break loop
						} else if (this.decimals.length == 1) {// if decimals
																// have one
																// digit
							negativeAnswer = true;// negative answer
							break;// break loop
						} else if (this.decimals[z] == two.decimals[z]) {// if
																			// decimals
																			// are
																			// equal
							continue;// continue loop
						} else {
							negativeAnswer = false;// if the first number is
													// bigger
							break;// break loop
						}
					}
				} else if (this.naturalNumbers[i] == two.naturalNumbers[i]) {// if
																				// theyre
																				// equal
					continue;// break
				} else {
					naturalNumbersOperation = new int[two.naturalNumbers.length];// anything
																					// else
					negativeAnswer = false;// positive answer
					break;
				}
			}
		} else {
			naturalNumbersOperation = new int[this.naturalNumbers.length];// use
																			// size
																			// of
																			// first
																			// number
		}
		if (this.decimals.length <= two.decimals.length) {// if second number is
															// smaller
			decimalsOperation = new int[two.decimals.length];// answer has that
																// size
		} else {
			decimalsOperation = new int[this.decimals.length];// else answer has
																// other size
		}
		for (int i = 0; i < decimalsOperation.length; i++) {// loop all numbers
															// in the problem
			if (negativeAnswer) {// if its a negative answer take the bigger
									// number
				if (i < this.decimals.length) {// use all array members
					decimalsOperation[i] = decimalsOperation[i] - this.decimals[i];// subtract
																					// smaller
																					// from
																					// answer
				}
				if (i < two.decimals.length) {// use all array members
					decimalsOperation[i] = decimalsOperation[i] + two.decimals[i];// add
																					// bigger
																					// number
																					// to
																					// answer
				}
			} else {
				if (i < this.decimals.length) {// if the answer is positive
					decimalsOperation[i] = decimalsOperation[i] + this.decimals[i];// add
																					// bigger
																					// to
																					// answer
				}
				if (i < two.decimals.length) {
					decimalsOperation[i] = decimalsOperation[i] - two.decimals[i];// subtract
																					// smaller
																					// from
																					// answer
				}
			}

		}
		for (int i = (decimalsOperation.length - 1); i >= 0; i--) {// rounding
																	// up loop
			if (decimalsOperation[i] < 0) {// if addition of the numbers is
											// negative
				if (i > 0) {// if the number is not the first number after the
							// decimal point
					decimalsOperation[i - 1] = decimalsOperation[i - 1] - 1;// minus
																			// one
																			// from
																			// previous
																			// number
					decimalsOperation[i] = decimalsOperation[i] + 10;// add ten
																		// to
																		// number
																		// to
																		// remove
																		// the
																		// negative
				} else {// if its the number after the decimal
					naturalNumbersOperation[naturalNumbersOperation.length
							- 1] = naturalNumbersOperation[naturalNumbersOperation.length - 1] - 1;
					// minus one from the last number in the natural numbers
					// array
					decimalsOperation[i] = decimalsOperation[i] + 10;// add ten
																		// to
																		// the
																		// first
																		// number
																		// after
																		// the
																		// decimal
				}
			}
		}
		int difference = (this.naturalNumbers.length) - (two.naturalNumbers.length);// difference
																					// between
																					// sizes
		if (negativeAnswer) {// if answer is negative consider the bigger number
			difference = -difference;// use the difference in sizes to control
										// the array
			for (int z = 0; z < this.naturalNumbers.length; z++) {
				naturalNumbersOperation[z + difference] = naturalNumbersOperation[z + difference]
						- this.naturalNumbers[z] + two.naturalNumbers[z + difference];
				// add numbers to numbers in the same position from the decimal
			}
			for (int z = 0; z < difference; z++) {// and the bigger number is
													// just added
				naturalNumbersOperation[z] = naturalNumbersOperation[z] + two.naturalNumbers[z];
			}
		} else {
			for (int z = 0; z < two.naturalNumbers.length; z++) {// same thing
																	// as
																	// previous
																	// but other
																	// way
																	// around
				naturalNumbersOperation[z + difference] = naturalNumbersOperation[z + difference]
						+ this.naturalNumbers[z + difference] - two.naturalNumbers[z];
			}
			for (int z = 0; z < difference; z++) {
				naturalNumbersOperation[z] = naturalNumbersOperation[z] + this.naturalNumbers[z];
			}
		}
		String string = "";// answer
		for (int i = (naturalNumbersOperation.length - 1); i >= 0; i--) {// rounding
																			// up
			if (naturalNumbersOperation[i] < 0) {// if sum is negative
				if (i > 0) {
					naturalNumbersOperation[i - 1] = naturalNumbersOperation[i - 1] - 1;// subtract
																						// one
																						// from
																						// previous
					// number
					naturalNumbersOperation[i] = naturalNumbersOperation[i] + 10;// add
																					// ten
																					// to
																					// the
																					// number
				}
			}
		}
		if (negativeAnswer) {// if the answer is negative add a negative sign to
								// answer
			string = "-";
		}
		string = string + Input.arrayToString(naturalNumbersOperation) + "." + Input.arrayToString(decimalsOperation);
		// converting all arrays to strings
		return string;// return the string
	}

	public static void main(String[] args) {// main method
		Input.startProgram();// starting program
	}
}
