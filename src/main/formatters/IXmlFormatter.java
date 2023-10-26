package main.formatters;

import java.util.HashMap;
import java.util.ArrayList;

import org.w3c.dom.Document;

/**
 * This interface defines methods for formatting and converting data in XML format.
 * <p>
 * Implementing classes should provide implementations for these methods to convert data between various formats
 * such as Document, ArrayList, HashMap, JSON, and CSV.
 *
 * @author [Your Name]
 * @version 1.0
 * @since [Date]
 */
public interface IXmlFormatter {
	/**
	 * Converts data to a CSV string.
	 *
	 * @return The CSV representation of the XML data.
	 */
	public String convertToCsv ();

	/**
	 * Converts data to an ArrayList of strings, where each string represents an XML element.
	 *
	 * @return An ArrayList of strings representing the XML data.
	 */
	public ArrayList<String> convertToArrayList ();

	/**
	 * Converts data to a HashMap, where keys are XML element names, and values are corresponding element data.
	 *
	 * @return A HashMap representing the XML data.
	 */
	public HashMap<String, Object> convertToHashMap ();

	/**
	 * Converts data to a JSON string.
	 *
	 * @return The JSON representation of the XML data.
	 */
	public String convertToJson ();

	/**
	 * Converts data to a Document object representing an XML structure.
	 *
	 * @return The Document object representing the XML structure.
	 */
	public Document convertToDocumentObject ();
}
