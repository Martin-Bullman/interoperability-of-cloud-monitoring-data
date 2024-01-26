package tests;

import main.deserializers.*;
import main.formatters.*;
import main.serializers.*;

import java.io.File;
import java.util.List;

/**
 * The `Tester` class serves as a demonstration and testing environment for various data interchange formatters and
 * related operations. This class showcases the functionality of formatters such as JSON, XML, CSV, ArrayList,
 * and HashMap. It enables the evaluation of data interchange between different data formats and provides insights
 * into the conversion of data between these formats.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class DataInterchangeTester {
	/**
	 * Character sequence to produce a new line.
	 */
	private final static String NEW_LINE_SEPARATOR = "\n";

	/**
	 * A String that represents a new line.
	 */
	private static final String NEW_LINE_MARKER = "n";

	/**
	 * The main method of the `Tester` class serves as the entry point for the data interchange testing application.
	 *
	 * @param args Command line arguments.
	 */
	public static void main (String[] args) {
		// Sample cloud monitoring data files.
		String csvFile  = System.getProperty("user.dir") + "/datafiles/large_metric_csv_data.csv";
    	String jsonFile = System.getProperty("user.dir") + "/datafiles/large_json_data.json";
    	String xmlFile  = System.getProperty("user.dir") + "/datafiles/large_xml_data.xml";

    	DataInterchangeFormatter formatter = new DataInterchangeFormatter(jsonFile);
    	System.out.println("File Size: " + formatter.findFileSize() + " Bytes\n");

    	// Tester for JSON data interchange class.
		JsonFormatter json = new JsonFormatter(new File(jsonFile));

    	System.out.println("Convert JSON to a ArrayList.");
    	System.out.println(json.convertToArrayList() + NEW_LINE_SEPARATOR);

    	System.out.println("Convert JSON to a HashMap.");
    	System.out.println(json.convertToHashMap() + NEW_LINE_SEPARATOR);

    	System.out.println("Convert JSON to a XML.");
    	System.out.println(json.convertToXml() + NEW_LINE_SEPARATOR);

    	System.out.println("Convert JSON to CSV.");
    	System.out.println(json.convertToCsv() + NEW_LINE_SEPARATOR);
    	System.out.println();


    	// Tester for the XML data interchange class
		XmlFormatter xml = new XmlFormatter(new File(xmlFile));

		System.out.println("Convert XML to ArrayList.");
    	System.out.println(xml.convertToArrayList() + NEW_LINE_SEPARATOR);

    	System.out.println("Convert XML to HashMap.");
    	System.out.println(xml.convertToHashMap() + NEW_LINE_SEPARATOR);

    	System.out.println("Convert XML to JSON.");
    	System.out.println(xml.convertToJson() + NEW_LINE_SEPARATOR);

    	System.out.println("Convert XML to CSV.");
    	System.out.println(xml.convertToCsv() + NEW_LINE_SEPARATOR);
    	System.out.println();


        //Tester For Comma Separate Values data interchange Class.
		CsvFormatter csv = new CsvFormatter(new File(csvFile));

    	System.out.println("Convert CSV to ArrayList.");
    	System.out.println(csv.convertToArrayList() + NEW_LINE_SEPARATOR);

    	System.out.println("Convert CSV to HashMap.");
    	System.out.println(csv.convertToHashMap() + NEW_LINE_SEPARATOR);

    	System.out.println("Convert CSV to JSON.");
    	System.out.println(csv.convertToJson('\t') + NEW_LINE_SEPARATOR);

    	System.out.println("Convert CSV to XML.");
    	System.out.println(csv.convertToXml() + NEW_LINE_SEPARATOR);
    	System.out.println();


    	// Tester for the Array List data interchange class.
		ArrayListFormatter list = new ArrayListFormatter(csv.convertToArrayList());

    	System.out.println("Convert Array List to CSV");
    	System.out.println(list.convertToCsv('\t') + NEW_LINE_SEPARATOR);

    	System.out.println("Convert Array List to XML");
    	System.out.println(list.convertToXml() + NEW_LINE_SEPARATOR);

    	System.out.println("Convert Array List to JSON");
    	System.out.println(list.convertToJson() + NEW_LINE_SEPARATOR);

    	System.out.println("Convert Array List to HashMap");
    	System.out.println(list.convertToHashMap());
    	System.out.println();


    	// Tester for the HashMap data interchange class.
		HashMapFormatter hashMap = new HashMapFormatter(csv.convertToHashMap());

        System.out.println("Convert HashMap to CSV: ");
    	System.out.println(hashMap.convertToCsv() + NEW_LINE_SEPARATOR);

    	System.out.println("Convert HashMap to XML: ");
    	System.out.println(hashMap.convertToXml() + NEW_LINE_SEPARATOR);

    	System.out.println("Convert HashMap to JSON: ");
		System.out.println(hashMap.convertToJson() + NEW_LINE_SEPARATOR);

    	System.out.println("Convert hash Map to ArrayList: ");
		System.out.println(hashMap.convertToArrayList() + NEW_LINE_SEPARATOR);
    }

	/**
	 * This method prints the results of various data serialization and deserialization tests for different data
	 * interchange formatters.It demonstrates the serialization of data in various formats, such as MessagePack,
	 * BSON, Kryo, CBOR, Hessian, and Java Serialization, and then displays the byte array sizes for the serialized data.
	 *
	 * @param format The ArrayListFormatter containing data for serialization and testing.
	 */
	public static void printResults (ArrayListFormatter format) {
		// Tester for Message Pack serialization and deserialization.
    	MessagePackSerializer msgPackSerializer = new MessagePackSerializer();
    	MessagePackDeserializer msgPackDeserializer = new MessagePackDeserializer();

    	byte[] arrayListBytesArray1 = msgPackSerializer.serializeArrayList(format.getArrayList());
    	System.out.println("MessagePack Deserialized ArrayList  ByteArraySize: " + arrayListBytesArray1.length);
    	//System.out.println( msgPackDeserializer.deserializeArrayList( arrayListBytesArray1 ) + "\n" );

    	byte[] hashMapBytesArray1 = msgPackSerializer.serializeHashMap( format.convertToHashMap() );
    	System.out.println("Message Pack Deserialized HashMap   ByteArraySize: " +  hashMapBytesArray1.length);
    	//System.out.println( msgPackDeserializer.deserializeHashMap( hashMapBytesArray1 ) + "\n" );

    	byte[] jsonBytesArray1 = msgPackSerializer.serializeString(format.convertToJson());
    	System.out.println("Message Pack Deserialized JSON      ByteArraySize: " + jsonBytesArray1.length);
    	System.out.println( msgPackDeserializer.deserializeString( jsonBytesArray1 ) + "\n");

    	byte[] xmlBytesArray1 = msgPackSerializer.serializeString(format.convertToXml() );
    	System.out.println("Message Pack Deserialized XML        ByteArraySize: " + xmlBytesArray1.length);
    	//System.out.println( msgPackDeserializer.deserializeString( xmlBytesArray1 ) + "\n");

    	byte[] csvBytesArray1 = msgPackSerializer.serializeString(format.convertToCsv( '\t'));
    	System.out.println("Message Pack Deserialized CSV        ByteArraySize: " + csvBytesArray1.length);
    	//System.out.println( msgPackDeserializer.deserializeString( csvBytesArray1 ) + "\n");
    	System.out.println();


    	// Tester for the BSON serializer and deserializer.
    	BsonSerializer bsonSerializer = new BsonSerializer( );
    	BsonDeserializer bsonDeserializer = new BsonDeserializer( );

    	byte[] arrayListBytesArray2 = bsonSerializer.serializeArrayList( format.getArrayList() );
    	System.out.println( "BSON Deserialized ArrayList    ByteArraysize: " + arrayListBytesArray2.length );
    	//System.out.println( bsonDeserializer.deserializeArrayList( arrayListBytesArray2 ) + "\n" );

    	byte[] hashMapBytesArray2 = bsonSerializer.serializeHashMap( format.convertToHashMap() );
    	System.out.println( "BSON Deserialized HashMap      ByteArraySize: " + hashMapBytesArray2.length );
    	//System.out.println( bsonDeserializer.deserializeHashMap( hashMapBytesArray2 ) + "\n" );

    	byte[] jsonBytesArray2 = bsonSerializer.serializeString( format.convertToJson()  );
    	System.out.println( "BSON Deserialized JSON         Byte array size: " + jsonBytesArray2.length );
    	//System.out.println( bsonDeserializer.deserializeString( jsonBytesArray2 ) + "\n" );

    	byte[] xmlBytesArray2 = bsonSerializer.serializeString( format.convertToXml() );
    	System.out.println( "BSON Deserialized XML          Byte array size: " + xmlBytesArray2.length );
    	//System.out.println( bsonDeserializer.deserializeString( xmlBytesArray2 ) + "\n" );

    	byte[] csvBytesArray2 = bsonSerializer.serializeString( format.convertToCsv( '\t' ) );
    	System.out.println( "BSON Deserialized CSV          Byte array size: " + csvBytesArray2.length );
    	//System.out.println( bsonDeserializer.deserializeString( csvBytesArray2 ) + "\n" );
    	System.out.println();


    	// Tester for Kryo serializer and deserializer.
    	KryoSerializer kryoSerializer = new KryoSerializer( );
    	KryoDeserializer kryoDeserializer = new KryoDeserializer( );

    	byte[] arrayListBytesArray3 = kryoSerializer.serializeArrayList( format.getArrayList() );
    	System.out.println( "KRYO Deserialized ArrayList    ByteArraySize: " + arrayListBytesArray3.length );
    	//System.out.println( kryoDeserializer.deserializeArrayList( arrayListBytesArray3 ) + "\n" );

    	byte[] hashMapBytesArray3 = kryoSerializer.serializeHashMap( format.convertToHashMap()  );
    	System.out.println( "Kryo Deserialized HashMap      ByteArraySize: " + hashMapBytesArray3.length );
    	//System.out.println(kryoDeserializer.deserializeHashMap( hashMapBytesArray3 ) + "\n" );

    	byte[] jsonBytesArray3 = kryoSerializer.serializeString( format.convertToJson( )  );
    	System.out.println( "Kryo Deserialized JSON         ByteArraySize: " + jsonBytesArray3.length );
    	//System.out.println( kryoDeserializer.deserializeString( jsonBytesArray3 ) + "\n" );

    	byte[] xmlBytesArray3 = kryoSerializer.serializeString( format.convertToXml() );
    	System.out.println( "Kryo Deserialized XML          ByteArraySize: " + xmlBytesArray3.length );
    	//System.out.println( kryoDeserializer.deserializeString( xmlBytesArray3 ) + "\n" );

    	//byte[] csvBytesArray3 = kryoSerializer.serializeString( format.convertToCsv( '\t' ) );
    	//System.out.println( "Kryo Deserialized CSV          ByteArraySize: " + csvBytesArray3.length );
    	//System.out.println( kryoDeserializer.deserializeString( csvBytesArray3 ) + "\n" );
    	System.out.println();


    	// Tester for the Cbor serializer and deserializer
    	CborSerializer cborSerializer = new CborSerializer( );
    	CborDeserializer cborDeserializer = new CborDeserializer( );

    	byte[] arrayListBytesArray4 = cborSerializer.serializeArrayList( format.getArrayList() );
    	System.out.println( "CBOR Deserialized ArrayList    ByteArraySize: " + arrayListBytesArray4.length );
    	//System.out.println( cborDeserializer.deserializeArrayList( arrayListBytesArray4 ) + "\n" );

    	//byte[] hashMapBytesArray4 = cborSerializer.serializeHashMap( format.convertToHashMap() );
    	//System.out.println( "Cbor Deserialized Hash Map     ByteArraySize: " + hashMapBytesArray4.length   );
    	//System.out.println( cborDeserializer.deserializeHashMap( hashMapBytesArray4 ) );

    	byte[] jsonBytesArray4 = cborSerializer.serializeString( format.convertToJson()  );
    	System.out.println( "Cbor Deserialized JSON         ByteArraySize: " + jsonBytesArray4.length );
    	//System.out.println( cborDeserializer.deserializeString( jsonBytesArray4 ) + "\n" );

    	byte[] xmlBytesArray4 = cborSerializer.serializeString( format.convertToXml() );
    	System.out.println( "Cbor Deserialized XML          ByteArraySize: " + xmlBytesArray4.length );
    	//System.out.println( cborDeserializer.deserializeString( xmlBytesArray4 ) + "\n" );

    	byte[] csvBytesArray4 = cborSerializer.serializeString( format.convertToCsv('\t') );
    	System.out.println( "Cbor Deserialized CSV          ByteArraySize: " + csvBytesArray4.length );
    	//System.out.println( cborDeserializer.deserializeString( csvBytesArray4 ) + "\n" );
    	//System.out.println( format.get).toString());


    	// Tester for the Hessian serializer and deserializer.
    	HessianSerializer hessianSerializer = new HessianSerializer( );
    	HessianDeserializer hessianDeserializer = new HessianDeserializer( );

    	//byte[] arrayListBytesArray5 = hessianSerializer.serializeArrayList( format.getArrayList()  );
		//System.out.println( "Hessian Deserialized ArrayList ByteArraySize: " + arrayListBytesArray5.length   );
    	//System.out.println( hessianDeserializer.deserializeArrayList( arrayListBytesArray5 ) + "\n");

    	//byte[] hashMapBytesArray5 = hessianSerializer.serializeHashMap( format.convertToHashMap() );
    	//System.out.println( "Hessian Deserialized Hash Map  ByteArraySize: " + hashMapBytesArray5.length   );
    	//System.out.println( hessianDeserializer.deserializeHashMap( hashMapBytesArray5 ) + "\n" );

    	//byte[] jsonBytesArray5 = hessianSerializer.serializeString( format.convertToJson() );
    	//System.out.println( "Hessian Deserialized JSON      ByteArraySize: " + jsonBytesArray5.length );
    	//System.out.println( hessianDeserializer.deserializeString( jsonBytesArray5 ) + "\n" );

    	//byte[] xmlBytesArray5 = hessianSerializer.serializeString( format.convertToXml()  );
    	//System.out.println( "Hessian Deserialized XML       ByteArraySize: " + xmlBytesArray5.length );
    	//System.out.println( hessianDeserializer.deserializeString( xmlBytesArray5 ) + "\n" );

    	//byte[] csvBytesArray5 = hessianSerializer.serializeString( format.convertToCsv( '\t' ) );
    	//System.out.println( "Hessian Deserialized CSV       ByteArraySize: " + csvBytesArray5.length );
    	//System.out.println( hessianDeserializer.deserializeString( csvBytesArray5 ) + "\n" );
    	//System.out.println();

    	// Tester for the Java serializer and deserializer.
    	JavaSerializer javaSerializer = new JavaSerializer( );
    	JavaDeserializer javaDeserializer = new JavaDeserializer( );

    	byte[] arrayListBytesArray6 = javaSerializer.serializeArrayList( format.getArrayList() );
    	System.out.println( "Java Deserialized ArrayList  ByteArraySize: " + arrayListBytesArray6.length );
    	//System.out.println( javaDeserializer.deserializeArrayList( arrayListBytesArray6 ) + "\n");

    	byte[] hashMapBytesArray6 = javaSerializer.serializeHashMap( format.convertToHashMap() );
    	System.out.println( "Java Deserialized Hash Map   ByteArraySize: " + hashMapBytesArray6.length   );
    	//System.out.println( javaDeserializer.deserializeHashMap( hashMapBytesArray6 ) + "\n" );

    	byte[] jsonBytesArray6 = javaSerializer.serializeString( format.convertToJson()  );
    	System.out.println( "Java Deserialized JSON       ByteArraySize: " + jsonBytesArray6.length );
    	//System.out.println( javaDeserializer.deserializeString( jsonBytesArray6 ) + "\n" );

    	byte[] xmlBytesArray6 = javaSerializer.serializeString( format.convertToXml() );
    	System.out.println( "Java Deserialized XML        ByteArraySize: " + xmlBytesArray6.length );
    	//System.out.println( javaDeserializer.deserializeString( xmlBytesArray6 ) + "\n" );

    	byte[] csvBytesArray6 = javaSerializer.serializeString( format.convertToCsv('\t') );
    	System.out.println( "Java Deserialized CSV        ByteArraySize: " + csvBytesArray6.length );
    	//System.out.println( javaDeserializer.deserializeString( csvBytesArray6 ) + "\n" );
	}

	/**
	 * Formats a list of strings into a single string, where each element of the list is separated by a tab character,
	 * and the "NEW_LINE_MARKER" is used to insert a newline character.
	 *
	 * @param list The list of strings to be formatted.
	 * @return A formatted string containing the elements of the list with tabs as separators and newlines for markers.
	 */
	public static String printList (List<String> list) {
		StringBuilder listString = new StringBuilder();

        for (String val : list) {
            if (!val.equals(NEW_LINE_MARKER)) {
                listString.append(val).append("    "); // Using tab character for separation.
            }
			else {
                listString.append(NEW_LINE_SEPARATOR); // Insert a newline character.
            }
        }

		return listString.toString();
	}

}