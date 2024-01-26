package main.formatters;

import main.helpers.FileHelper;

import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * The DataInterchangeFormatter class is responsible for handling and converting data between various formats,
 * including CSV, XML, and JSON. It determines the input data type and chooses the most efficient conversion method.
 * <p>
 * This class provides constructors to initialize the formatter with different types of input data, such as a file,
 * an ArrayList, or a HashMap. It can automatically determine the data format based on the provided file extension
 * and choose the appropriate conversion method. The class also contains placeholders for methods to select the
 * most efficient serialization and deserialization methods based on data characteristics.
 * <p>
 * Supported input data formats include CSV (Comma-Separated Values), XML (Extensible Markup Language), and JSON
 * (JavaScript Object Notation).
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
 public class DataInterchangeFormatter {
	/**
	 * Comma Separated Value tab delimiter,
	 */
	private static final char CSV_DELIMITER = '\t';

	/**
	 * Comma Separated Values file type.
	 */
	private static final String CSV_FILE_TYPE = "csv";

	/**
	 * Extensible Markup Language file type.
	 */
	private static final String XML_FILE_TYPE = "xml";

	/**
	 * Javascript Object Notation file type.
	 */
	private static final String JSON_FILE_TYPE = "json";

	/**
	 * The name of the monitoring data file.
	 */
	private String fileName = "";

	/**
	 * The extension of the monitoring data file.
	 */
	private String fileExtension = "";

	/**
	 * The file containing monitoring data.
	 */
	private File inputFile = null;

	/**
	 * The list given as input to the constructor.
	 */
	private ArrayList<String> arrayList = null;

	/**
	 * The map given as input to the constructor.
	 */
	private HashMap<String, Object> hashMap =  null;

	/**
	 * Constructs a DataInterchangeFormatter object with the provided file name.
	 * <p>
	 * This constructor initializes the class, sets the input file, discovers the file type, and determines
	 * the most efficient format for conversion.
	 *
	 * @param filePath - The path to the file containing monitoring data.
	 */
	public DataInterchangeFormatter (String filePath) {
		FileHelper fileHelper = new FileHelper();

		this.inputFile = new File(filePath);
		this.fileName  = this.inputFile.getName();
		this.fileExtension = fileHelper.getFileType(filePath);

		System.out.println(fileExtension);
	}

	/**
	 * Constructs a DataInterchangeFormatter object with the provided ArrayList. This constructor
	 * initializes the class with a list of monitoring data, which can be used for conversion.
	 *
	 * @param arrayList - An ArrayList containing monitoring data.
	 */
	public DataInterchangeFormatter (ArrayList<String> arrayList) {
		this.arrayList = arrayList;
	}

	/**
	 * Constructs a DataInterchangeFormatter object with the provided HashMap. This constructor
	 * initializes the class with a HashMap containing monitoring data, which can be used for conversion.
	 *
	 * @param hashMap - A HashMap containing monitoring data.
	 */
	public DataInterchangeFormatter (HashMap<String, Object> hashMap) {
		this.hashMap = hashMap;
	}

	/**
	 * Determines the most efficient format to convert the given input data into and performs the conversion.
	 * <p>
	 * This method analyzes the file type (CSV, XML, or JSON) and uses the appropriate formatter to convert the data
	 * into a common format for further processing. If the file type is not recognized, a message is printed to the
	 * standard output.
	 */
	public void efficientFormat () {
		switch (fileExtension) {
			case CSV_FILE_TYPE:
				CsvFormatter cvs = new CsvFormatter(inputFile);
				cvs.convertToJson(CSV_DELIMITER);
				break;
					
			case XML_FILE_TYPE:
				XmlFormatter xml = new XmlFormatter(inputFile);
				xml.convertToJson();
				break;
					
			case JSON_FILE_TYPE:
				JsonFormatter json = new JsonFormatter(inputFile);
				json.convertToJsonObject();
				break;
			default: 
				System.out.print("Could not find file type!!!\n");
				break;
		}
	}

	/**
	 * TODO: Implement a solution to select the most efficient serialization method for the data.
	 * <p>
	 * This method should be responsible for evaluating the data and selecting the best serialization format
	 * (e.g., CSV, XML, JSON) based on factors such as data complexity, size, and specific requirements.
	 * <p>
	 * You can add logic to analyze the data and decide which serialization format is the most suitable.
	 * Once implemented, this method will automatically choose the best serialization method for the data.
	 */
	public void pickEfficientSerializer () {
		// TODO: Implement the logic to choose the most efficient serialization method.
	}

	/**
	 * TODO: Implement a solution to select the appropriate deserialization method for the data.
	 * <p>
	 * This method should be responsible for evaluating the data and choosing the best deserialization format
	 * (e.g., CSV, XML, JSON) based on factors such as the format of the data and specific requirements.
	 * <p>
	 * You can add logic to analyze the data and decide which deserialization format is the most suitable.
	 * Once implemented, this method will automatically choose the best deserialization method for the data.
	 */
	public void pickDeserializer() {
		// TODO: Implement the logic to choose the appropriate deserialization method.
	}

	/**
	 * Discover the file type of the given input data source.
	 * <p>
	 * This method analyzes the source (e.g., file name) and determines the file type based on its extension.
	 * It supports common file types such as CSV (Comma-Separated Values), XML (Extensible Markup Language),
	 * and JSON (JavaScript Object Notation).
	 *
	 * @throws IllegalArgumentException if the file type cannot be determined.
	 */
	public void discoverFileType () {
		if (! fileName.contains("\\.")) {
			String [] splitString = fileName.split("\\.");
			this.fileExtension = splitString[splitString.length - 1];
			
			System.out.println("file type: " + fileExtension);
		}
		else {
			throw new IllegalArgumentException("Invalid file, could not find file type!");
		}
	}

	/**
	 * Calculates the size of the input file in bytes.
	 *
	 * @return The size of the file in bytes.
	 */
	public double findFileSize () {
		FileHelper helper = new FileHelper();
		return helper.findFileSize(inputFile);
	}
}