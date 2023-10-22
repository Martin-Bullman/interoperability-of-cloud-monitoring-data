package main.formatters;

import main.helpers.FileHelper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.Source;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.DOMException;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * Class which provides methods to format and manipulate CSV monitoring data.
 * <p>
 * It supports conversion to various data formats, such as ArrayList, XML, JSON, and HashMap.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class CsvFormatter implements ICsvFormatter {
	/**
	 * Character sequence to produce a single space.
	 */
	private final static String WHITE_SPACE_REGULAR_EXPRESSION = "\\s+";

	/**
	 * A String that represents a new line.
	 */
	private static final String NEW_LINE_MARKER = "n";

	/**
	 * Comma Separated Value tab delimiter,
	 */
	private static final String CSV_DELIMITER = "\t";
	
	/**
	 * CSV file given as input.
	 */
	private File inputFile  = null;

	/**
	 * CSV data represented as a Java string.
	 */
	private String inputString = "";

	/**
	 * The number of columns in the CSV input file.
	 */
	private int numColumns = 0;

	/**
	 * Constructor which takes a string of monitoring data as input.
	 *
	 * @param inputString The monitoring data.
	 */
	public CsvFormatter (String inputString) {
		this.inputString = inputString;
	}

	/**
	 * Constructor which takes a CSV file of monitoring data as input.
	 *
	 * @param inputFile The monitoring data.
	 */
	public CsvFormatter (File inputFile) {
		this.inputFile = inputFile;
	}

	/**
	 * Retrieves the number of columns in the input file by reading the first line and splitting it using the
	 * specified delimiter.
	 *
	 * @return The number of columns in the input file.
	 */
	public int getColumnLength () {
        Scanner scanner;

		try {
		    scanner = new Scanner( inputFile );
        
		    if (scanner.hasNextLine()) {
			    String line = scanner.nextLine();
			    String[] values = line.split(WHITE_SPACE_REGULAR_EXPRESSION);
				numColumns = values.length;
		    }

		    scanner.close();
		}
		catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		
        return numColumns;
	}

	/**
	 * Convert the contents of the CSV file to ArrayList representation.
	 * <p>
	 * Reads the input file, parses its content into a list of strings, and returns an ArrayList of strings.
	 * Each element of the ArrayList represents a data value found in the file.
	 *
	 * @return An ArrayList of strings containing the data from the input file.
	 */
	public ArrayList<String> convertToArrayList () {
		ArrayList<String> dataList = new ArrayList<>();
		
		try {
			Scanner scanner = new Scanner(inputFile);
		 
			while (scanner.hasNextLine()) {
				String rowOfData   = scanner.nextLine();
				String[] rowValues = rowOfData.split(WHITE_SPACE_REGULAR_EXPRESSION);
				
				for (int i = 0; i < rowValues.length; i++) {
					dataList.add(rowValues[i].trim());
					
					if (i == rowValues.length -1) {
						dataList.add(NEW_LINE_MARKER);
					}
				}
			}

			scanner.close();
		}
		catch (FileNotFoundException exception) {
			exception.printStackTrace( );
		}
		
		return dataList;
	}

	/**
	 * Convert the contents of the CSV file to HashMap representation.
	 * <p>
	 * Reads the input file, interprets its content as CSV data, and converts it into a HashMap representation.
	 * Each entry in the HashMap corresponds to a row of data in the file, with keys as row numbers (as strings)
	 * and values as arrays of column values.
	 *
	 * @return A HashMap where keys are row numbers (as strings) and values are arrays of column values.
	 */
	public HashMap<String, Object> convertToHashMap () {
		HashMap<String, Object> dataHashMap = new HashMap<>();
		int counter = 0;
		
		try {
		    Scanner scanner = new Scanner(inputFile);
		    
		    while (scanner.hasNextLine()) {
	            String rowOfData   = scanner.nextLine();
	            String[] rowValues = rowOfData.split(CSV_DELIMITER);
	            dataHashMap.put(Integer.toString(counter), rowValues);
	            counter++;
		    }

		    scanner.close();
		}
	    catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		
		return dataHashMap;
	}

	/**
	 * Convert the contents of the CSV file to JSON representation.
	 * <p>
	 * Reads the input file, uses the specified CSV delimiter to parse the CSV file. The resulting JSON is
	 * structured with a "root" element and formatted with pretty printing. Empty values in the CSV are
	 * represented as empty strings in the JSON.
	 *
	 * @param csvDelimiter The delimiter used in the CSV file.
	 * @return A JSON string representing the data from the CSV file.
	 */
	public String convertToJson (char csvDelimiter) {
		String jsonString = "";
		
        try {
        	ObjectMapper mapper = new ObjectMapper();
        	CsvMapper csvMapper = new CsvMapper();
        	CsvSchema schema = CsvSchema.builder()
					.setColumnSeparator(csvDelimiter)
	                .disableEscapeChar()
	                .disableQuoteChar()
	                .setUseHeader(false)
	                .setNullValue("")
		    		.build()
		    		.withHeader();

	        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader(Map.class).with(schema).readValues(inputFile);
        	List<Map<?, ?>> data = mappingIterator.readAll();
	        jsonString = "{\n\"root\" : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data) + "\n}";
	        jsonString = jsonString.replace("\"\" : null", "").replace("\r", "");
        }
        catch (IOException exception) {
			exception.printStackTrace();
        }
        
        return jsonString;
	}

	/**
	 * Convert the contents of the CSV file to JSON representation.
	 * <p>
	 * Reads the input file, It assumes that the first line of the CSV file contains column headers and uses them
	 * as keys in the resulting JSON objects. Each line in the CSV file represents a record in the JSON output.
	 * The JSON is formatted with pretty printing.
	 *
	 * @return A JSON representation of the data.
	 */
	public String convertToJson2 () {
		String fileColume = ""; 
		Scanner scanner = null;
		String jsonData = "{ \"root\" : [\n";
		
		try {
			scanner = new Scanner(inputFile);
			String lineOfColumnHeaders = scanner.nextLine();
			String[] columnHeaders = lineOfColumnHeaders.split(WHITE_SPACE_REGULAR_EXPRESSION);
				
			while (scanner.hasNextLine()) {
				jsonData += "{";
				fileColume = scanner.nextLine();
				String[] fileContents = fileColume.split(WHITE_SPACE_REGULAR_EXPRESSION);

			    for (int i = 0; i < columnHeaders.length ; i++) {
				    jsonData += "\"" + columnHeaders[i] + "\":" + "\"" + fileContents[i];
				    
				    if (i != columnHeaders.length - 1) {
				    	jsonData += "\", ";
				    }
				    else {
				    	jsonData += "\" ";
				    }
				    
				}
				    
			    if (scanner.hasNextLine()) {
					jsonData += "},\n";
				}
			    else {
			    	jsonData += "}\n";
			    }
			}
		}
		catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
			
		scanner.close();
		jsonData += "]}";
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			Object json = mapper.readValue(jsonData, Object.class);
			jsonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	
		return jsonData;
	}

	/**
	 * Convert the contents of the CSV file to an XML representation.
	 * <p>
	 * Reads the input file, The first line of the CSV file is assumed to contain column headers, which are
	 * used as element names in the resulting XML. Each line in the CSV file represents a record in the XML
	 * output. The XML is formatted with indentation for readability.
	 *
	 * @return An XML representation of the data.
	 */
	public String convertToXml () {
		int lineNumber = 0;
		String nextLine = "";
		List<String> headers = new ArrayList<String>();
		
	    DocumentBuilder domBuilder = null;
	    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	    StringWriter stringWriter = new StringWriter();
	    StreamResult xmlOutput = new StreamResult(stringWriter);
	    
	    try {
	    	BufferedReader csvReader = new BufferedReader(new FileReader(inputFile));
	        domBuilder = domFactory.newDocumentBuilder();
	        Document newDoc = domBuilder.newDocument();
	        Element rootElement = newDoc.createElement("root");
	        newDoc.appendChild(rootElement);

	        while ((nextLine = csvReader.readLine()) != null) {
	        	String[] str = nextLine.split( WHITE_SPACE_REGULAR_EXPRESSION );
	        	
                if (lineNumber == 0) {
                    for (String col : str) {
                        headers.add(col.trim());
                    }
                } 
                else {
                    Element rowElement = newDoc.createElement("row");
                    rootElement.appendChild(rowElement);
                    int columnHeader = 0;

                    for (String value : str) {
                        String header = headers.get(columnHeader);
                        Element currentElement = newDoc.createElement(header);
                        currentElement.appendChild( newDoc.createTextNode(value.trim()));
                        rowElement.appendChild(currentElement);
						columnHeader++;
                    }
                }

                lineNumber++;
            }
	        csvReader.close();
	        
	        try {
	            TransformerFactory tranFactory = TransformerFactory.newInstance();
	            Transformer aTransformer = tranFactory.newTransformer();
	            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
	            aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	            Source src = new DOMSource(newDoc);
	            aTransformer.transform(src, xmlOutput);
	        }
	        catch (TransformerException exception) {
				exception.printStackTrace();
	        } 
	    } 
	    catch(IOException | DOMException | ParserConfigurationException exception) {
			exception.printStackTrace();
	    } 

	    return xmlOutput.getWriter().toString();
	}

	/**
	 * Reads the contents of the input file and returns it as a string.
	 *
	 * @return A string containing the contents of the input file.
	 */
	public String fileToString () {
		FileHelper fileString = new FileHelper();
        return fileString.convertFileToString(inputFile);
	}
}
