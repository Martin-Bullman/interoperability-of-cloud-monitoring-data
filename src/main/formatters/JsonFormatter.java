package main.formatters;

import main.helpers.FileHelper;

import java.io.*;
import java.util.*;
import org.json.java.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Class which provides methods to format and manipulate JSON monitoring data.
 * <p>
 * It supports conversion to various data formats, such as ArrayList, XML, HashMap, and CSV.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class JsonFormatter implements IJsonFormatter {
	/**
	 * JSON file given as input.
	 */
	private File inputFile = null;

	/**
	 * JSON data represented as a Java string.
	 */
	private String inputString = "";

	/**
	 * Constructor which takes a string of monitoring data as input.
	 *
	 * @param inputString The monitoring data.
	 */
	public JsonFormatter (String inputString) {
		this.inputString = inputString;
	}

	/**
	 * Constructor which takes a JSON file of monitoring data as input.
	 *
	 * @param inputFile The monitoring data.
	 */
	public JsonFormatter (File inputFile) {
		this.inputFile = inputFile;
	}

	/**
	 * Parse a JSONObject into an ArrayList of strings.
	 * <p>
	 * This method recursively traverses a JSONObject and converts its key-value pairs into strings.
	 * The resulting strings will have a format like "key: value". If a value in the JSONObject is another
	 * JSONObject, this method will recursively parse it.
	 *
	 * @param jsonObject The JSONObject to be parsed.
	 * @return An ArrayList of strings representing the key-value pairs from the JSONObject.
	 */
	public ArrayList<String> parseJson (JSONObject jsonObject) {
		ArrayList<String> jsonArrayList = new ArrayList<>();
		
		for (Object key : jsonObject.keySet()) {
	        String keyStr = (String) key;
	        Object keyValue = jsonObject.get(keyStr);
	        jsonArrayList.add( "\"" + keyStr + "\": " + keyValue );
	        
	        if (keyValue instanceof JSONObject) {
	            parseJson((JSONObject) keyValue);
	        }
	    }
		
		return jsonArrayList;
	}

	/**
	 * Converts the content of the file into a JSONObject.
	 * <p>
	 * This method reads the content of a file and attempts to parse it into a JSONObject. It assumes that the
	 * file contains valid JSON data. If the file content cannot be parsed into a JSONObject, it may throw an
	 * exception.
	 *
	 * @return A JSONObject representing the data from the file.
	 */
	public JSONObject convertToJsonObject () {
		return new JSONObject(fileToString());
	}

	/**
	 * Converts a JSONObject into an ArrayList of strings.
	 * <p>
	 * This method takes a JSONObject and converts its content into an ArrayList of strings. Each element in
	 * the ArrayList represents a key-value pair from the JSONObject. Nested objects are recursively expanded
	 * to their string representations. The resulting ArrayList may contain JSON key-value pairs in string format.
	 *
	 * @return An ArrayList of strings representing the content of the JSONObject.
	 */
	public ArrayList<String> convertToArrayList () {
		return parseJson(convertToJsonObject());
	}

	/**
	 * Converts a JSON file into a HashMap of key-value pairs.
	 * <p>
	 * This method reads the content of a JSON file and converts it into a HashMap where keys are strings and
	 * values can be of various types represented as Objects. The JSON file is parsed using the Jackson
	 * ObjectMapper library. If successful, it returns the resulting HashMap.
	 *
	 * @return A HashMap of key-value pairs extracted from the JSON file.
	 */
	public HashMap<String, Object> convertToHashMap () {
		HashMap<String, Object> jsonHashMap = null;

		try {
			JsonFactory factory = new JsonFactory();
	    	ObjectMapper mapper = new ObjectMapper(factory);

			TypeReference <HashMap<String, Object>> typeRef = new TypeReference <HashMap<String, Object>>(){};
			jsonHashMap = mapper.readValue(inputFile, typeRef);
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	
		return jsonHashMap;
	}

	/**
	 * Converts JSON data or content from a file into a well-formatted XML string.
	 * <p>
	 * This method takes either JSON data provided as input or reads the content of a file to convert it into
	 * an XML string. It uses the JSON-java library to perform the conversion. The resulting XML string is then
	 * formatted for readability. The method returns the well-formatted XML string.
	 *
	 * @return A well-formatted XML string representing the JSON data or file content.
	 */
	public String convertToXml () {
		String xmlString        = XML.toString( new JSONObject( "{\"root\" : " + (inputString.equals( "" ) ? fileToString( ) : inputString) + "}" ) );
		DefaultXmlFormatter xml = new DefaultXmlFormatter();
		xmlString               = xmlString.replace( "\n", "" );

		return  xml.prettyPrintXmlString( xmlString );
	}

	/**
	 * Converts a JSON string into a CSV (Comma-Separated Values) string.
	 * <p>
	 * This method takes a JSON string as input and transforms it into a CSV string, which is a plain-text
	 * format used to represent tabular data.
	 *
	 * @return The CSV representation of the JSON data as a string.
	 */
	public String convertToCsv () {
		// Need to create a POJO class to handle data stored in JSON String. 
		JsonNode rootJsonNode = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
		    rootJsonNode = mapper.readTree(fileToString());
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
		
		Iterator<String> fe = rootJsonNode.fieldNames();
		String element = fe.next() ;
		JSONObject json = new JSONObject(fileToString());
	
		return CDL.toString( new JSONArray(json.get(element).toString()));
	}

	/**
	 * Converts the contents of a file into a string representation.
	 * <p>
	 * This method reads the contents of a file and converts them into a single string.
	 *
	 * @return A string representation of the file's contents.
	 */
	public String fileToString () {
		FileHelper fileString = new FileHelper();
        return fileString.convertFileToString(inputFile);
	}
}
