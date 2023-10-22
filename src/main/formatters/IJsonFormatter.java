package main.formatters;

import org.json.java.JSONObject;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * This interface defines methods for formatting and converting data in JSON format.
 * <p>
 * Implementing classes should provide implementations for these methods to convert data between various formats
 * such as ArrayList, HashMap, XML, and CSV.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public interface IJsonFormatter {
	/**
	 * Converts data to a CSV (Comma-Separated Values) string.
	 *
	 * @return A CSV representation of the data.
	 */
	public String convertToCsv();

	/**
	 * Converts data to an ArrayList of strings.
	 *
	 * @return An ArrayList representation of the data.
	 */
	public ArrayList<String> convertToArrayList();

	/**
	 * Converts data to a HashMap.
	 *
	 * @return A HashMap representation of the data.
	 */
	public HashMap<String, Object> convertToHashMap();

	/**
	 * Converts data to a JSON object.
	 *
	 * @return A JSON representation of the data as a JSONObject.
	 */
	public JSONObject convertToJsonObject();

	/**
	 * Converts data to an XML string.
	 *
	 * @return An XML representation of the data.
	 */
	public String convertToXml();
}
