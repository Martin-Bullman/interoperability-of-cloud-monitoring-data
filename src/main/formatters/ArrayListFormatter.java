package main.formatters;

import main.helpers.FileHelper;

import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class which provides methods to format and manipulate an ArrayList of monitoring data.
 * <p>
 * It supports conversion to various data formats, such as CSV, XML, JSON, and HashMap.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class ArrayListFormatter implements IArrayListFormatter {
	/**
	 * Comma Separated Value tab delimiter,
	 */
	private static final char CSV_DELIMITER = '\t';

	/**
	 * New line character.
	 */
	private static final String NEW_LINE_SEPARATOR = "\n";

	/**
	 * A String that represents a new line character.
	 */
	private static final String NEW_LINE_MARKER = "n";

	/**
	 * ArrayList to store the monitoring data.
	 */
	private ArrayList<String> monitoringData = null;
	
	/**
	 * Default constructor which takes an ArrayList of monitoring data as input.
	 * 
	 * @param monitoringData The monitoring data.
	 */
	public ArrayListFormatter (final ArrayList<String> monitoringData) {
		this.monitoringData = monitoringData;
	}

	/**
	 * Converts the data to JSON format by first converting it to XML format, saving it to a file, and then
	 * using an XmlFormatter to generate the JSON representation.
	 *
	 * @return A JSON representation of the data.
	 */
	public String convertToJson () {
        String csvString = convertToXml();
		String dataFilesPath = System.getProperty("user.dir") + "/datafiles/data.text";

		FileHelper helper = new FileHelper();
    	String filePath = helper.convertStringToFile(csvString, dataFilesPath);
    	XmlFormatter csvFormatter = new XmlFormatter(new File(filePath));
		
		return csvFormatter.convertToJson();
	}

	/**
	 * Converts the data to XML format by first converting it to CSV format, saving it to a file, and then
	 * using a CsvFormatter to generate the XML representation.
	 *
	 * @return A string representing the data in XML format.
	 */
	public String convertToXml () {
		String csvString = convertToCsv(CSV_DELIMITER);
		String dataFilesPath = System.getProperty("user.dir") + "/datafiles/data.text";

		FileHelper stringFile = new FileHelper();
    	String filePath = stringFile.convertStringToFile( csvString, dataFilesPath);
    	CsvFormatter csvFormatter = new CsvFormatter(new File(filePath));
		
		return csvFormatter.convertToXml();
	}
	
	/**
	 * A method to convert an ArrayList of data into CSV string of data.
	 * 
	 * @return - CSV string of data
	 */
	public String convertToCsv (char delimiter) {
		StringBuffer csvString = new StringBuffer();
		
		for (int i = 0; i < monitoringData.size() - 1 ; i++) {
			String value = monitoringData.get(i);
			
		    if (! value.equals(NEW_LINE_MARKER)) {
		    	csvString.append(value + delimiter);
		    }
		    else {
		    	csvString.append(NEW_LINE_SEPARATOR);
		    }
		}
		
		return csvString.toString();
	}

	/**
	 * Converts the monitoringData into a HashMap representation.
	 * Each entry in the HashMap represents a row of data with column values.
	 *
	 * @return A HashMap where keys are row numbers (as strings) and values are arrays of column values.
	 */
	public HashMap<String, Object> convertToHashMap () {
		HashMap<String, Object> hashMap = new HashMap<>();
		String[] stringList = new String[findNumColumns()];
		int keyCounter = 0; 
		int count = 0;
		
		for (String listObject : monitoringData) {
			if (! listObject.equals(NEW_LINE_MARKER)) {
				stringList[keyCounter] = listObject;
				
				if (keyCounter != findNumColumns() - 1) {
					keyCounter++;
				}
			}
			else {
				hashMap.put(Integer.toString(count), stringList);
				count++;
				keyCounter = 0;
				stringList = new String[findNumColumns()];
			}
		}
		
		return hashMap;
	}

	/**
	 * Determines the number of columns in the monitoringData.
	 *
	 * @return The count of columns in the monitoringData.
	 */
	public int findNumColumns () {
		int count = 0;
		
		for (String value : monitoringData) {
			if(! value.equals(NEW_LINE_MARKER)) {
				count++;
			}
			else {
				break;
			}
		}

		return count;
	}

	/**
	 * Generates a string representation of the monitoringData ArrayList by concatenating its elements.
	 *
	 * @return A string representing the contents of the monitoringData, with each element separated by a new line.
	 */
	public String printArrayList () {
		StringBuilder listString = new StringBuilder();

        for (String monitoringDatum : monitoringData) {
            listString.append(monitoringDatum);
            listString.append(NEW_LINE_SEPARATOR);
        }
		
		return listString.toString();
	}

	/**
	 * Retrieves the ArrayList of monitoring data.
	 *
	 * @return An ArrayList of strings containing monitoring data.
	 */
	public ArrayList<String> getArrayList () {
		return monitoringData;
	}

	/**
	 * Converts the ArrayList of monitoring data to its string representation.
	 *
	 * @return A string representation of the ArrayList.
	 */
	public String convertToString () {
		return monitoringData.toString();
	}
}
