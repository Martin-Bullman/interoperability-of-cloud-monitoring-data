package main.message_sender;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

import java.util.Properties;
/**
 * The `MessageSenderMain` class is the entry point for the MessageSender application.
 * <p>
 * It loads configuration settings, initializes the `MessageSender` class, and controls the message sending process.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class MessageSenderMain {
	/**
	 * The main method for the MessageSender application. It reads configuration properties, initializes
	 * the MessageSender, and controls the process of monitoring and sending messages.
	 *
	 * @param args Command-line arguments (not used).
	 */
	public static void main (String[] args) {
		// Initialize variables and properties
		Properties prop = new Properties();
		InputStream config = null;
		MessageSender sender = null;

		int rounds = 0;
		int counter = 0;
		int monInterval = 0;
		boolean doSendMsg = false;

		// Load configuration file and initialize settings
		try {
			config = new FileInputStream("config/sender.properties");
			prop.load(config);

			doSendMsg = Boolean.parseBoolean(prop.getProperty("sendMsg"));
			monInterval = Integer.parseInt(prop.getProperty("monInterval"));
			rounds = Integer.parseInt(prop.getProperty("rounds"));

			System.out.println("Connection Settings : " + prop.getProperty("rabbitHost") + "   " + prop.getProperty("rabbitUser") + "   " +  prop.getProperty("rabbitPass"));
			System.out.println("sender here");

			if (doSendMsg) {
				sender = new MessageSender(prop.getProperty("rabbitHost"), prop.getProperty("rabbitUser"), prop.getProperty("rabbitPass"), "small");
			}
		}
		catch (IOException f) {
			System.err.println("Error while loading and processing configuration file: " + f.getMessage());
		}
		finally {
			if (config != null) {
				try {
					config.close();
				}
				catch (IOException exception) {
					System.err.println("Error while closing configuration file: " + exception.getMessage());
				}
			}
		}

		// Start monitoring and processing
		try {
			int serializer = 0;
			int fileSize = 0;

			while (true) {
				chooseSerializer(serializer);

				while (counter < 5) {
					counter++;

					switch (counter) {
						case 1:
							printHumanReadableFormat("Serializing JSON data... ");

							if (doSendMsg) {
								sender.sendJson(serializer);
							}

							break;
						case 2:
							printHumanReadableFormat("Serializing CSV data... ");

							if (doSendMsg) {
								sender.sendCsv(serializer);
							}

							break;
						case 3:
							printHumanReadableFormat("Serializing XML data... ");

							if (doSendMsg) {
								sender.sendXml(serializer);
							}

							break;
						case 4:
							printHumanReadableFormat("Serializing ArrayList data... ");

							if (doSendMsg) {
								sender.sendArrayList(serializer);
							}

							break;
						default:
							printHumanReadableFormat("Serializing HashMap data... ");

							if (doSendMsg) {
								sender.sendHashMap(serializer);
							}

							break;
					}

					Thread.sleep(5000);  // monitoring intervals
				}

				counter = 0;
				serializer++;

				if (serializer > 5) {
					if (fileSize == 0) {
						sender = new MessageSender(
								prop.getProperty("rabbitHost"),
								prop.getProperty("rabbitUser"),
								prop.getProperty("rabbitPass"),
								"medium");

						fileSize = 1;
					}
					else {
						sender = new MessageSender(
								prop.getProperty("rabbitHost"),
								prop.getProperty("rabbitUser"),
								prop.getProperty("rabbitPass"),
								"small");

						fileSize = 0;
					}
					serializer = 0;
				}
			}
		}
		catch (InterruptedException e) {
			System.err.println("Monitor processing and sending to graphite failed: " + e.getMessage());
		}

		System.out.println("Successfully completed monitoring");
	}

	/**
	 * Prints a message to the console in a human-readable format.
	 *
	 * @param message The message to be printed.
	 */
	public static void printHumanReadableFormat (String message) {
		System.out.println();
		System.out.println(message);
		System.out.println();
	}

	/**
	 * Prints a serializer description based on the serializer index.
	 *
	 * @param serializer The serializer index.
	 */
	public static void chooseSerializer (int serializer) {
		if (serializer == 0) {
			printSerializerUsed("              *** CBOR SERIALIZER ***             ");
		}
		else if (serializer == 1) {
			printSerializerUsed("              *** BSON SERIALIZER ***             ");
		}
		else if (serializer == 2) {
			printSerializerUsed("            *** HESSIAN SERIALIZER ***            ");
		}
		else if (serializer == 3) {
			printSerializerUsed("             *** JAVA SERIALIZER ***              ");
		}
		else if (serializer == 4) {
			printSerializerUsed("             *** KRYO SERIALIZER ***              ");
		}
		else if (serializer == 5) {
			printSerializerUsed("         *** MESSAGEPACK SERIALIZER ***           ");
		}
	}

	/**
	 * Prints a message to the console to indicate the serializer being used.
	 *
	 * @param message The message indicating the serializer being used.
	 */
	public static void printSerializerUsed (String message) {
		System.out.println();
		System.out.println(message);
		System.out.println();
	}

}
