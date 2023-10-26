package main.formatters;

import java.util.ArrayList;

/**
 * This interface defines methods for formatting and converting data in HashMap format.
 * <p>
 * Implementing classes should provide implementations for these methods to convert data between various formats
 * such as ArrayList, JSON, XML, and CSV.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public interface IHashMapFormatter {
	/**
	 * Converts data to a CSV (Comma-Separated Values) string.
	 *
	 * @return A CSV representation of the data.
	 */
    public String convertToCsv ();

	/**
	 * Converts data to an ArrayList of strings.
	 *
	 * @return An ArrayList representation of the data.
	 */
	public ArrayList<String> convertToArrayList ();

	/**
	 * Converts data to a JSON (JavaScript Object Notation) string.
	 *
	 * @return A JSON representation of the data.
	 */
	public String convertToJson ();

	/**
	 * Converts data to an XML (eXtensible Markup Language) string.
	 *
	 * @return An XML representation of the data.
	 */
	public String convertToXml ();
}
