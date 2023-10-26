package main.formatters;

import main.helpers.FileHelper;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import java.util.HashMap;
import java.util.ArrayList;

import org.w3c.dom.*;
import org.json.java.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.Source;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerException;

/**
 * Class which provides methods to format and manipulate XML monitoring data.
 * <p>
 * It supports conversion to various data formats, such as ArrayList, JSON, HashMap, and CSV.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class XmlFormatter implements IXmlFormatter {
	/**
	 * The indent factor of the XML file.
	 */
	private final static int PRETTY_PRINT_INDENT_FACTOR = 4;

	/**
	 * XML file given as input.
	 */
	private File inputFile = null;

	/**
	 * XML data represented as a Java string.
	 */
	private final String inputString = "";

	/**
	 * hashMap to store XML data.
	 */
	private final HashMap<String, Object> xmlHashMap = new HashMap<String, Object>();

	/**
	 * Constructor which takes a string of monitoring data as input.
	 */
	public XmlFormatter () {

	}

	/**
	 * Constructor which takes an XML file of monitoring data as input.
	 *
	 * @param inputFile The monitoring data.
	 */
    public XmlFormatter (File inputFile) {
    	this.inputFile = inputFile;
    }

	/**
	 * Recursively prints the content of an XML NodeList, including element names and text content.
	 * <p>
	 * This method traverses an XML NodeList, printing the names of elements and their associated text content.
	 * It does this recursively to handle nested elements.
	 *
	 * @param nodeList The XML NodeList to be printed.
	 */
    public void printXml (NodeList nodeList) {
    	 for (int i = 0; i < nodeList.getLength(); i++) {
             Node childNode = nodeList.item(i);
             
             if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                 System.out.println(childNode.getNodeName() + "=" + childNode.getTextContent());
             }

             NodeList children = childNode.getChildNodes();

             if (children != null) {
                 printXml(children);
             }
         }
    }

	/**
	 * Converts an XML file into a Document object for easy manipulation.
	 * <p>
	 * This method parses the provided XML file and converts it into a Document object, which can be used for
	 * convenient reading and manipulation of the XML's structure and content.
	 *
	 * @return The Document object representing the parsed XML file.
	 */
    public Document convertToDocumentObject () {
    	Document documentObject = null;
    	
    	try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            documentObject = dBuilder.parse(inputFile);
            documentObject.getDocumentElement().normalize();
    	}
    	catch(ParserConfigurationException | SAXException | IOException exception) {
			exception.printStackTrace();
    	}
    	
    	return documentObject;
    }

	/**
	 * Converts JSON data into an ArrayList of strings.
	 * <p>
	 * This method takes the JSON data, converts it to a file, and then uses a JsonFormatter to parse the JSON
	 * and generate an ArrayList of strings.
	 *
	 * @return An ArrayList of strings containing the data from the JSON.
	 */
    public ArrayList<String> convertToArrayList () {
		String dataFilesPath = System.getProperty("user.dir") + "/datafiles/data.text";
		String jsonString = convertToJson();
    	FileHelper stringFile = new FileHelper();
    	String filePath = stringFile.convertStringToFile(jsonString, dataFilesPath);
    	JsonFormatter jsonFormatter = new JsonFormatter(new File(filePath));
    	
    	return jsonFormatter.convertToArrayList();
    }

	/**
	 * Converts JSON data into a HashMap representation.
	 * <p>
	 * This method takes the JSON data, converts it to a file, and then uses a JsonFormatter to parse the JSON
	 * and generate a HashMap representation.
	 *
	 * @return A HashMap where keys are strings and values are objects, representing the JSON data.
	 */
    public HashMap<String, Object> convertToHashMap () {
		String dataFilesPath = System.getProperty("user.dir") + "/datafiles/data.text";
		String jsonString = convertToJson();
    	FileHelper stringFile = new FileHelper();
    	String filePath = stringFile.convertStringToFile(jsonString, dataFilesPath);
    	JsonFormatter jsonFormatter = new JsonFormatter(new File( filePath));

    	return jsonFormatter.convertToHashMap();
    }

	/**
	 * Converts XML data to a JSON string with pretty formatting.
	 * <p>
	 * This method parses XML data from a file and converts it to a JSON string while
	 * applying pretty formatting for improved readability.
	 *
	 * @return A JSON string with pretty formatting.
	 */
	public String convertToJson () {
    	String jsonPrettyString = "";
    	
    	try {
            JSONObject jsonObject = XML.toJSONObject(fileToString());
            jsonPrettyString = jsonObject.toString(PRETTY_PRINT_INDENT_FACTOR);
    	}
    	catch (JSONException exception) {
			exception.printStackTrace();
    	}
    	
    	return jsonPrettyString;
    }

	/**
	 * Converts JSON data to CSV format.
	 * <p>
	 * This method parses XML data from a file, converts it to JSON format and from there to CSV format.
	 * Returns the resulting CSV data as a string.
	 *
	 * @return A CSV string representing the XML data.
	 */
    public String convertToCsv () {
    	String jsonString = convertToJson();
    	String[] newString = jsonString.split("\\{", 3);
    	String newJsonString = "{" + newString[2].substring(0, newString[2].length() - 1);

		String dataFilesPath = System.getProperty("user.dir") + "/datafiles/data.text";
		FileHelper stringFile = new FileHelper();
    	String filePath = stringFile.convertStringToFile(newJsonString, dataFilesPath);
		JsonFormatter formatter = new JsonFormatter(new File(filePath));
		
		return formatter.convertToCsv();
    }

	/**
	 * Reads the contents of an XML and returns it as a string.
	 * <p>
	 * This method reads the contents of the specified XML file and returns them as a single string.
	 *
	 * @return The contents of the XML file as a string.
	 */
    public String fileToString () {
        FileHelper fileString = new FileHelper();
        return fileString.convertFileToString(inputFile);
    }

	/**
	 * Pretty prints an XML string, adding proper indentation.
	 * <p>
	 * This method takes an XML string as input and returns a pretty-printed version of the same XML with
	 * proper indentation.
	 *
	 * @param xmlString The XML string to be pretty-printed.
	 * @return The pretty-printed XML string.
	 */
    public String prettyPrintXmlString (String xmlString) {
    	StreamResult xmlOutput = null;
    	
    	try {
            Source xmlInput = new StreamSource( new StringReader(xmlString));
    	    StringWriter stringWriter = new StringWriter();
    	    xmlOutput = new StreamResult(stringWriter);
    	    
    	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    	    transformerFactory.setAttribute("indent-number", PRETTY_PRINT_INDENT_FACTOR);
    	    
    	    Transformer transformer = transformerFactory.newTransformer();
    	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    	    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    	    transformer.transform(xmlInput, xmlOutput);
    	} 
    	catch(TransformerException exception) {
			exception.printStackTrace();
    	}
    	
    	return xmlOutput.getWriter().toString();
    }
}
