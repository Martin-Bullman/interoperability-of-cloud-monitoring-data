package main.formatters;

import java.util.HashMap;

/**
 * This interface defines methods for formatting and converting data in ArrayList format.
 * <p>
 * Implementing classes should provide implementations for these methods to convert data between various formats
 * such as JSON, HashMap, XML, and CSV.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public interface IArrayListFormatter {
	/**
	 * Converts data to a CSV (Comma-Separated Values) string with a specified delimiter.
	 *
	 * @param delimiter The character used as a delimiter in the CSV string.
	 * @return A CSV representation of the data.
	 */
	public String convertToCsv(char delimiter);

	/**
	 * Converts data to a HashMap where keys are strings and values are objects.
	 *
	 * @return A HashMap representation of the data.
	 */
	public HashMap<String, Object> convertToHashMap();

	/**
	 * Converts data to a JSON (JavaScript Object Notation) string.
	 *
	 * @return A JSON representation of the data.
	 */
	public String convertToJson();

	/**
	 * Converts data to an XML (eXtensible Markup Language) string.
	 *
	 * @return An XML representation of the data.
	 */
	public String convertToXml();
}

