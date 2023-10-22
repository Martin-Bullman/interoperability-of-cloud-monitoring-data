package main.formatters;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * This interface defines methods for formatting and converting data in CSV format.
 * <p>
 * Implementing classes should provide implementations for these methods to convert data between various formats
 * such as ArrayList, HashMap, XML, and JSON.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public interface ICsvFormatter {
	/**
	 * Converts data in CSV format into an ArrayList of strings.
	 *
	 * @return An ArrayList containing CSV data as strings.
	 */
	public ArrayList<String> convertToArrayList();

	/**
	 * Converts data in CSV format into a HashMap where keys are strings (e.g., column names) and values are objects (e.g., row data).
	 *
	 * @return A HashMap representing CSV data.
	 */
	public HashMap<String, Object> convertToHashMap();

	/**
	 * Converts data in CSV format into a JSON representation.
	 *
	 * @param csvDelimiter The character used as a delimiter in the CSV data.
	 * @return A string containing CSV data converted to JSON.
	 */
	public String convertToJson(char csvDelimiter);

	/**
	 * Converts data in CSV format into an XML representation.
	 *
	 * @return A string containing CSV data converted to XML.
	 */
	public String convertToXml();
}
