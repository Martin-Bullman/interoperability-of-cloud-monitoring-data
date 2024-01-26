package main.message_sender;

import main.formatters.*;
import main.serializers.*;
import main.helpers.FileHelper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * The `MessageSender` class is responsible for sending data to an AMQP message broker, offering support for
 * various data formats and serialization methods to facilitate interoperability.
 * <p>
 * This class provides functionality to send messages in different formats, including JSON, CSV, XML,
 * ArrayList, and HashMap. It allows the selection of different serialization methods like CBOR, BSON, Hessian,
 * Java Serialization, Kryo, and MessagePack. The class establishes connections with the message broker,
 * initializes channels, and sends serialized data to the specified exchange.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class MessageSender {
	/**
	 * A helper class for handling files and data formats.
	 */
	FileHelper helper = new FileHelper( );

	/**
	 * A JSON data formatter.
	 */
	JsonFormatter json = null;

	/**
	 * An XML data formatter.
	 */
	XmlFormatter xml = null;

	/**
	 * A CSV data formatter.
	 */
	CsvFormatter csv = null;

	/**
	 * An ArrayList data formatter.
	 */
	ArrayListFormatter list = null;

	/**
	 * A HashMap data formatter.
	 */
	HashMapFormatter hashMap = null;

	/**
	 * The name of the AMQP exchange to be used for message exchange.
	 */
	private static final String EXCHANGE_NAME = "jobs";

	/**
	 * Serializer for BSON format.
	 */
	private final BsonSerializer bson = new BsonSerializer();

	/**
	 * Serializer for CBOR format.
	 */
	private final CborSerializer cbor = new CborSerializer();

	/**
	 * Serializer for Hessian format.
	 */
	private final HessianSerializer hessian = new HessianSerializer();

	/**
	 * Serializer for Java serialization format.
	 */
	private final JavaSerializer java = new JavaSerializer();

	/**
	 * Serializer for Kryo format.
	 */
	private final KryoSerializer kryo = new KryoSerializer();

	/**
	 * Serializer for MessagePack format.
	 */
	private final MessagePackSerializer msgPack = new MessagePackSerializer();

	/**
	 * Factory for creating AMQP connections.
	 */
	ConnectionFactory factory;

	/**
	 * AMQP connection instance.
	 */
	Connection connect;

	/**
	 * AMQP channel for communication.
	 */
	Channel chan;

	/**
	 * Constructs a new MessageSender with the specified parameters.
	 *
	 * @param host The broker hostname or IP address.
	 * @param user The username for authentication.
	 * @param pass The password for authentication.
	 * @param fileSize The size identifier for data files.
	 */
	public MessageSender (String host, String user, String pass, String fileSize) {
		// Initialize file paths and formatters
		String csvFile  = System.getProperty("user.dir") + "/datafiles/" + fileSize + "_metric_csv_data.csv";
		String jsonFile = System.getProperty("user.dir") + "/datafiles/" + fileSize + "_json_data.json";
		String xmlFile  = System.getProperty("user.dir") + "/datafiles/" + fileSize + "_xml_data.xml";

		json    = new JsonFormatter(new File(jsonFile));
		xml     = new XmlFormatter(new File(xmlFile));
		csv     = new CsvFormatter(new File(csvFile));
		list    = new ArrayListFormatter(csv.convertToArrayList());
		hashMap = new HashMapFormatter(csv.convertToHashMap());

		// Initialize the Rabbit AMQP connection
		init(host, user, pass);
	}

	/**
	 * Initializes the AMQP connection and channel for sending messages.
	 *
	 * @param host The broker hostname or IP address.
	 * @param user The username for authentication.
	 * @param pass The password for authentication.
	 */
	private void init (String host, String user, String pass) {
		factory = new ConnectionFactory();
		factory.setHost(host);

		try {
			connect = factory.newConnection();
		}
		catch (IOException e) {
			System.err.println("Failure while setting up a new connection for sending....");
			e.printStackTrace();
		}
		catch (TimeoutException e) {
			System.err.println("Connection timed out on sender");
			e.printStackTrace();
		}

		try {
			chan = connect.createChannel();
			chan.exchangeDeclare(EXCHANGE_NAME, "direct", true); // Set up the exchange
			// chan.exchangeDeclare(EXCHANGE_NAME, "direct"); // Set up the exchange
		}
		catch (IOException ex) {
			System.err.println("Failure while creating a new channel for sending");
			ex.printStackTrace();
		}
	}

	/**
	 * Publishes a message to the AMQP exchange with the specified routing key.
	 *
	 * @param msg The byte array representing the message to be sent.
	 */
	public void sendMessage (byte[] msg) {
		try {
			String routingKey = "monitor";
			chan.basicPublish(EXCHANGE_NAME, routingKey, null, msg);
		}
		catch (IOException e) {
			System.err.println("Failure while publishing message");
			e.printStackTrace();
		}
	}

	/**
	 * Sends a JSON-formatted message using the specified serializer.
	 *
	 * @param serializer An integer representing the serializer to use (0 for CBOR, 1 for BSON, 3 for Java, 4 for Kryo, or other for MessagePack).
	 */
	public void sendJson (int serializer) {
		byte[] jsonBytesArray = switch (serializer) {
            case 0 -> cbor.serializeString(json.fileToString());
            case 1 -> bson.serializeString(json.fileToString());
            case 3 -> java.serializeString(json.fileToString());
            case 4 -> kryo.serializeString(json.fileToString());
            default -> msgPack.serializeString(json.fileToString());
        };

        System.out.println(json.fileToString());
		System.out.println("Data size: " + jsonBytesArray.length + " Bytes");

		if (jsonBytesArray != null) {
			// Send message body
			sendMessage(jsonBytesArray);
		}
	}

	/**
	 * Sends a CSV-formatted message using the specified serializer.
	 *
	 * @param serializer An integer representing the serializer to use (0 for CBOR, 1 for BSON, 3 for Java, 4 for Kryo, or other for MessagePack).
	 */
	public void sendCsv (int serializer) {
		byte[] csvBytesArray = null;

		switch (serializer) {
			case 0:
				csvBytesArray = cbor.serializeString(csv.fileToString());
				break;
			case 1:
				csvBytesArray = bson.serializeString(csv.fileToString());
				break;
			//case 2:
				// TODO: Handle Hessian serialization
				//break;
			case 3:
				csvBytesArray = java.serializeString(csv.fileToString());
				break;
			case 4:
				csvBytesArray = kryo.serializeString(csv.fileToString());
				break;
			default:
				csvBytesArray = msgPack.serializeString(csv.fileToString());
				break;
		}

		System.out.println(csv.fileToString());
		System.out.println("Data size: " + csvBytesArray.length + " Bytes");

		if (csvBytesArray != null) {
			// Send the message body
			sendMessage(csvBytesArray);
		}
	}

	/**
	 * Sends an XML-formatted message using the specified serializer.
	 *
	 * @param serializer An integer representing the serializer to use (0 for CBOR, 1 for BSON, 3 for Java, 4 for Kryo, or other for MessagePack).
	 */
	public void sendXml (int serializer) {
		byte[] xmlBytesArray = null;

		switch (serializer) {
			case 0:
				xmlBytesArray = cbor.serializeString(xml.fileToString());
				break;
			case 1:
				xmlBytesArray = bson.serializeString(xml.fileToString());
				break;
			//case 2:
				// TODO: Handle Hessian serialization
				//break;
			case 3:
				xmlBytesArray = java.serializeString(xml.fileToString());
				break;
			case 4:
				xmlBytesArray = kryo.serializeString(xml.fileToString());
				break;
			default:
				xmlBytesArray = msgPack.serializeString(xml.fileToString());
				break;
		}

		System.out.println(xml.fileToString());
		System.out.println("Data size: " + xmlBytesArray.length + " Bytes");

		if (xmlBytesArray != null) {
			// Send the message body
			sendMessage(xmlBytesArray);
		}
	}
	/**
	 * Sends a message containing an ArrayList using the specified serializer.
	 *
	 * @param serializer An integer representing the serializer to use (0 for CBOR, 1 for BSON, 3 for Java, 4 for Kryo, or other for MessagePack).
	 */
	public void sendArrayList (int serializer) {
		byte[] listBytesArray = null;

		switch (serializer) {
			case 0:
				listBytesArray = cbor.serializeArrayList(list.getArrayList());
				break;
			case 1:
				listBytesArray = bson.serializeArrayList(list.getArrayList());
				break;
			//case 2:
				// TODO: Handle Hessian serialization
				//break;
			case 3:
				listBytesArray = java.serializeArrayList(list.getArrayList());
				break;
			case 4:
				listBytesArray = kryo.serializeArrayList(list.getArrayList());
				break;
			default:
				listBytesArray = msgPack.serializeArrayList(list.getArrayList());
				break;
		}

		System.out.println(list.getArrayList().toString());
		System.out.println("Data size: " + listBytesArray.length + " Bytes");

		if (listBytesArray != null) {
			// Send the message body
			sendMessage(listBytesArray);
		}
	}


	/**
	 * Sends a message containing a HashMap using the specified serializer.
	 *
	 * @param serializer An integer representing the serializer to use (0 for BSON, 1 for BSON, 3 for Java, 4 for Kryo, or other for MessagePack).
	 */
	public void sendHashMap (int serializer) {
		byte[] mapBytesArray = null;

		switch (serializer) {
			case 0:
				mapBytesArray = bson.serializeHashMap(hashMap.getHashMap());
				break;
			case 1:
				mapBytesArray = bson.serializeHashMap(hashMap.getHashMap());
				break;
			//case 2:
				// TODO: Handle Hessian serialization
				//break;
			case 3:
				mapBytesArray = java.serializeHashMap(hashMap.getHashMap());
				break;
			case 4:
				mapBytesArray = kryo.serializeHashMap(hashMap.getHashMap());
				break;
			default:
				mapBytesArray = msgPack.serializeHashMap(hashMap.getHashMap());
				break;
		}

		hashMap.printHashMap();
		System.out.println("Data size: " + mapBytesArray.length + " Bytes");

		if (mapBytesArray != null) {
			// Send the message body
			sendMessage(mapBytesArray);
		}
	}

	/**
	 * Closes the open connection and channel associated with the message sender.
	 * <p>
	 * This method is responsible for releasing resources and closing connections properly.
	 **/
	public void closeConnections () {
		try {
			chan.close();
			connect.close();
		}
		catch (IOException e) {
			System.err.println("Failure while closing the connection or channel");
			e.printStackTrace();
		}
		catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
}
