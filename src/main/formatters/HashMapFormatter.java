package main.formatters;

import main.helpers.FileHelper;

import java.io.File;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class which provides methods to format and manipulate HashMap monitoring data.
 * <p>
 * It supports conversion to various data formats, such as ArrayList, XML, JSON, and CSV.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class HashMapFormatter implements IHashMapFormatter {
	/**
	 * Comma Separated Value tab delimiter,
	 */
	private static final char CSV_DELIMITER = '\t';

	/**
	 * New line character.
	 */
	private static final String NEW_LINE_SEPARATOR = "\n";

	/**
	 * A String that represents a new line.
	 */
	private static final String NEW_LINE_MARKER = "n";
	
	/**
	 * Map to hold the HashMap data passed as input to the constructor.
	 */
	private final HashMap<String, Object> hashMapData;

	/**
	 * Constructor which takes a HashMap of monitoring data as input.
	 *
	 * @param hashMapData The monitoring data.
	 */
	public HashMapFormatter (HashMap<String, Object> hashMapData) {
		this.hashMapData = hashMapData;
	}

	/**
	 * Retrieves the HashMap containing formatted data.
	 *
	 * @return A HashMap containing the formatted data.
	 */
	public HashMap<String, Object> getHashMap () {
		return hashMapData;
	}

	/**
	 * Converts the data to JSON format.
	 * <p>
	 * Converts the current data into JSON format by first converting it to CSV format, saving it to a file,
	 * and then using a CsvFormatter to generate the JSON representation.
	 *
	 * @return A JSON representation of the data.
	 */
	public String convertToJson () {
		String dataFilesPath = System.getProperty("user.dir") + "/datafiles/data.text";

		String csvString = convertToCsv();
		FileHelper stringFile = new FileHelper();
    	String filePath = stringFile.convertStringToFile(csvString, dataFilesPath);
		CsvFormatter formatter = new CsvFormatter(new File(filePath));
		
		return formatter.convertToJson(CSV_DELIMITER);
	}

	/**
	 * Converts the data to XML format.
	 * <p>
	 * Converts the current HashMap data into XML format by first converting it to CSV format, saving it to a file,
	 * and then using a CsvFormatter to generate the XML representation.
	 *
	 * @return A string representing the data in XML format.
	 */
	public String convertToXml () {
		String dataFilesPath = System.getProperty("user.dir") + "/datafiles/data.text";

		String csvString = convertToCsv();
		FileHelper stringFile = new FileHelper();
    	String filePath = stringFile.convertStringToFile( csvString, dataFilesPath);
		CsvFormatter formatter = new CsvFormatter(new File( filePath));
		
		return formatter.convertToXml();
	}

	/**
	 * Converts the data to CSV format.
	 * <p>
	 * Converts the current HashMpa data into CSV format by processing the stored data in `hashMapData` and
	 * formatting it as CSV. Each row in the CSV represents an array of data values, and this method converts
	 * these arrays into CSV rows.
	 *
	 * @return A string representing the data in CSV format.
	 */
	public String convertToCsv () {
		StringBuffer csvString = new StringBuffer();
	    int keySetSize = hashMapData.keySet().size();
		int counter = 1;
		
		for (int i = 0; i < hashMapData.size(); i++) {
			String[] array = (String[]) hashMapData.get(Integer.toString(i));
			
			String newString = Arrays.toString(array);
			newString  = newString.replace( ",", "	");
			newString  = newString.replace( "[", "");
			newString  = newString.replace( "]", "");
			
			if (keySetSize != counter) {
				csvString.append(newString + NEW_LINE_SEPARATOR);
			}
			else {
				csvString.append(newString);
			}

			counter++;
		}
		
		return csvString.toString();
	}

	/**
	 * Converts the data to an ArrayList of strings.
	 * <p>
	 * Converts the data stored in the `hashMapData` into an ArrayList of strings. Each element in the ArrayList
	 * corresponds to a data value in the original structure. The data values are extracted from the HashMap and
	 * placed in the ArrayList, with a special marker to indicate new lines.
	 *
	 * @return An ArrayList of strings representing the data.
	 */
	public ArrayList<String> convertToArrayList () {
		ArrayList<String> strings = new ArrayList<String>();
		
		for (Map.Entry<String, Object> entry : hashMapData.entrySet()) {
			String[] array = (String[]) entry.getValue();

			for (int i = 0; i < array.length; i++) {
				strings.add(array[i]);
			}

			strings.add(NEW_LINE_MARKER);
		}
		
		return strings;
	}

	/**
	 * Print the contents of the data stored in the HashMap.
	 * <p>
	 * This method iterates over the entries in the `hashMapData` HashMap, printing each entry's key
	 * and its associated values. The values are stored as arrays of strings, and this method prints them,
	 * separated by the specified CSV delimiter.
	 */
	public void printHashMap () {
		for (Map.Entry<String, Object> entry : hashMapData.entrySet()) {
			String[] array = (String[]) entry.getValue();
			String printData = "";
			
			for ( int i = 0; i < array.length; i++) {
				printData += array[i] + CSV_DELIMITER;
			}
			
		    System.out.println("Key = " + entry.getKey() + ", Value = " + printData);
	    }
	}
	
	/**
	 * Convert the HashMap data in to a string.
	 * 
	 * @return - String representation of the data.
	 */
	public String convertToString () {
		return hashMapData.toString();
	}
}
