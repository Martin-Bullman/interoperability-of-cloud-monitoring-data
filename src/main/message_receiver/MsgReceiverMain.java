package main.message_receiver;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

import java.util.Properties;

/**
 * This class serves as the main application for receiving and processing messages from various sources.
 * <p>
 * It configures the message receiver, handles message deserialization using multiple formats, and continuously
 * receives and processes messages. The application allows flexible deserialization of messages based on different
 * serializers and provides real-time feedback on the deserialization format used.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class MsgReceiverMain {
	/**
	 * The main method of the application for receiving and processing messages. It configures the message receiver,
	 * handles various deserialization formats, and continuously receives and processes messages.
	 *
	 * @param args Command-line arguments (not used).
	 */
	public static void main (String[] args) {
		int counter = 0;
		Properties prop = new Properties();
		InputStream config = null;
		MessageReceiver receiver = null;

		try {
			config = new FileInputStream("config/broker.properties");
			prop.load(config);
			receiver = new MessageReceiver(prop.getProperty("hostIP"), prop.getProperty("rabbitUser"), prop.getProperty("rabbitPass"));
		}
		catch (IOException e) {
			System.err.println("Error while loading and processing configuration file: " + e.getMessage());
		}

		System.out.println(" [*] Waiting for messages. To exit, press CTRL+C");
		System.out.println();

		int deserializer = 0;

		while (true) {
			chooseDeserializer(deserializer);

			while (counter < 5) {
				counter++;

				switch (counter) {
					case 1:
						printHumanReadableFormat("Waiting for JSON data...");
						receiver.consumeJsonMsg(deserializer);
						break;

					case 2:
						printHumanReadableFormat("Waiting for CSV data...");
						receiver.consumeCsvMsg(deserializer);
						break;

					case 3:
						printHumanReadableFormat("Waiting for XML data...");
						receiver.consumeXmlMsg(deserializer);
						break;

					case 4:
						printHumanReadableFormat("Waiting for ArrayList data...");
						receiver.consumeArrayListMsg(deserializer);
						break;

					default:
						printHumanReadableFormat("Waiting for HashMap data...");
						receiver.consumeHashMapMsg(deserializer);
						break;
				}
			}
			counter = 0;
			deserializer++;

			if (deserializer > 5) {
				deserializer = 0;
			}
		}
	}

	/**
	 * Print the message indicating the serializer used.
	 *
	 * @param message The message to print.
	 */
	public static void printSerializerUsed (String message) {
		System.out.println();
		System.out.println(message);
		System.out.println();
	}

	/**
	 * Print a human-readable message.
	 *
	 * @param message The human-readable message to print.
	 */
	public static void printHumanReadableFormat (String message) {
		System.out.println();
		System.out.println(message);
	}

	/**
	 * Choose the deserializer based on the specified index and print the chosen deserializer format.
	 *
	 * @param deserializer The index of the deserializer to choose.
	 */
	public static void chooseDeserializer (int deserializer) {
		if (deserializer == 0) {
			printSerializerUsed("          *** CBOR DESERIALIZER ***     ");
		}
		else if (deserializer == 1) {
			printSerializerUsed("          *** BSON DESERIALIZER ***     ");
		}
		else if (deserializer == 2) {
			printSerializerUsed("          *** HESSIAN DESERIALIZER ***     ");
		}
		else if (deserializer == 3) {
			printSerializerUsed("          *** JAVA DESERIALIZER ***     ");
		}
		else if (deserializer == 4) {
			printSerializerUsed("          *** KRYO DESERIALIZER ***     ");
		}
		else if (deserializer == 5) {
			printSerializerUsed("          *** MESSAGEPACK DESERIALIZER ***     ");
		}
	}
}
