package main.message_receiver;

import com.naphaso.cbor.type.CborObject;
import com.rabbitmq.client.*;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import main.deserializers.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * A versatile utility class designed to receive and process messages from A RabbitMQ messaging bus.
 * <p>
 * It offers the capability to deserialize incoming messages into multiple data formats, making it
 * suitable for a wide range of applications.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class MessageReceiver {
	/**
	 * The name of the message queue.
	 */
	private static final String QUEUE_NAME = "metric";

	/**
	 * The name of the AMQP Exchange.
	 */
	private static final String EXCHANGE_NAME = "jobs";

	/**
	 * Represents a CBOR object for JSON data.
	 */
	private CborObject jsonCborString = null;

	/**
	 * Represents a CBOR object for CSV data.
	 */
	private CborObject csvCborString = null;

	/**
	 * Represents a CBOR object for XML data.
	 */
	private CborObject xmlCborString = null;

	/**
	 * Stores the serialized JSON data in byte format.
	 */
	private byte[] jsonCborBytes = null;

	/**
	 * Stores the serialized CSV data in byte format.
	 */
	private byte[] csvCborBytes = null;

	/**
	 * Stores the serialized XML data in byte format.
	 */
	private byte[] xmlCborBytes = null;

	/**
	 * BsonDeserializer for deserializing BSON data.
	 */
	private final BsonDeserializer bson = new BsonDeserializer();

	/**
	 * CborDeserializer for deserializing CBOR data.
	 */
	private final CborDeserializer cbor = new CborDeserializer();

	/**
	 * HessianDeserializer for deserializing Hessian data.
	 */
	private final HessianDeserializer hessian = new HessianDeserializer();

	/**
	 * JavaDeserializer for deserializing Java serialized data.
	 */
	private final JavaDeserializer java = new JavaDeserializer();

	/**
	 * KryoDeserializer for deserializing Kryo-serialized data.
	 */
	private final KryoDeserializer kryo = new KryoDeserializer();

	/**
	 * MessagePackDeserializer for deserializing MessagePack data.
	 */
	private final MessagePackDeserializer msgPack = new MessagePackDeserializer();

	// Class instances
	ConnectionFactory factory;
	Connection connect;
	Channel chan;
	QueueingConsumer consumer;

	/**
	 * Class constructor to set up the message receiver.
	 *
	 * @param host The message broker host.
	 * @param user The username for connecting to the broker.
	 * @param pass The password for connecting to the broker.
	 */
	public MessageReceiver (String host, String user, String pass) {
		setUp(host, user, pass);
	}

	/**
	 * Set up the message receiver by establishing connections and channels.
	 *
	 * @param host The message broker host.
	 * @param user The username for connecting to the broker.
	 * @param pass The password for connecting to the broker.
	 */
	private void setUp (String host, String user, String pass) {
		factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setUsername(user);
		factory.setPassword(pass);

		try {
			connect = factory.newConnection();
		}
		catch (IOException e) {
			System.err.println("Failure while setting up a new connection for receiving");
			e.printStackTrace();
		}
		catch (TimeoutException e) {
			System.err.println("Connection timed out on receiver");
			e.printStackTrace();
		}

		try {
			chan = connect.createChannel();
			chan.exchangeDeclare(EXCHANGE_NAME, "direct", true);
			chan.queueDeclare(QUEUE_NAME, false, false, false, null);
			chan.queueBind(QUEUE_NAME, EXCHANGE_NAME, "monitor");
		}
		catch (IOException e) {
			System.err.println("Failure while creating a new channel, declaring exchange, or binding queue for receiving");
			e.printStackTrace();
		}

		try {
			consumer = new QueueingConsumer(chan);
			chan.basicConsume(QUEUE_NAME, true, consumer);
		}
		catch (IOException e) {
			System.err.println("Failure while setting up the consumer queue");
			e.printStackTrace();
		}
	}

	/**
	 * Retrieve the next message from the message queue.
	 *
	 * @return A Delivery object representing the next message in the queue.
	 */
	public Delivery getBytes () {
		QueueingConsumer.Delivery delivery = null;

		try {
			delivery = consumer.nextDelivery();
		}
		catch (ShutdownSignalException | ConsumerCancelledException | InterruptedException e) {
			System.err.println("Failure while consuming message");
			e.printStackTrace();
		}

		return delivery;
	}

	/**
	 * Consume and process a JSON message using the specified deserializer.
	 *
	 * @param deserializer An integer representing the deserializer choice.
	 */
	public void consumeJsonMsg (int deserializer) {
		QueueingConsumer.Delivery delivery = getBytes();
		byte[] bytesArray = delivery.getBody();
		Object data = "";

		switch (deserializer) {
			case 0:
				jsonCborBytes = bytesArray;
				jsonCborString = cbor.deserializeString(jsonCborBytes);
				System.out.println(cbor.deserializeString(bytesArray));
				break;
			case 1:
				System.out.println(jsonCborString);
				break;
			case 2:
				System.out.println(hessian.deserializeString(bytesArray));
				break;
			case 3:
				System.out.println(java.deserializeString(bytesArray));
				break;
			case 4:
				System.out.println(kryo.deserializeString(bytesArray));
				break;
			default:
				System.out.println(msgPack.deserializeString(bytesArray));
				break;
		}

		// Deserialize a String and print to the console.
		System.out.println("Data Size: " + bytesArray.length);
	}

	/**
	 * Consume and process a CSV message using the specified deserializer.
	 *
	 * @param deserializer An integer representing the deserializer choice.
	 */
	public void consumeCsvMsg (int deserializer) {
		QueueingConsumer.Delivery delivery = getBytes();
		byte[] bytesArray = delivery.getBody();
		Object data = "";

		switch (deserializer) {
			case 0:
				csvCborBytes = bytesArray;
				csvCborString = cbor.deserializeString(csvCborBytes);
				System.out.println(cbor.deserializeString(bytesArray));
				break;
			case 1:
				System.out.println(csvCborString);
				break;
			case 2:
				System.out.println(hessian.deserializeString(bytesArray));
				break;
			case 3:
				System.out.println(java.deserializeString(bytesArray));
				break;
			case 4:
				System.out.println(kryo.deserializeString(bytesArray));
				break;
			default:
				System.out.println(msgPack.deserializeString(bytesArray));
				break;
		}

		// Deserialize a String and print to the console.
		System.out.println("Data Size: " + bytesArray.length);
	}

	/**
	 * Consume and process an XML message using the specified deserializer.
	 *
	 * @param deserializer An integer representing the deserializer choice.
	 */
	public void consumeXmlMsg (int deserializer) {
		QueueingConsumer.Delivery delivery = getBytes();
		byte[] bytesArray = delivery.getBody();

		switch (deserializer) {
			case 0:
				xmlCborBytes = bytesArray;
				xmlCborString = cbor.deserializeString(xmlCborBytes);
				System.out.println(cbor.deserializeString(bytesArray));
				break;
			case 1:
				System.out.println(xmlCborString);
				break;
			case 2:
				System.out.println(hessian.deserializeString(bytesArray));
				break;
			case 3:
				System.out.println(java.deserializeString(bytesArray));
				break;
			case 4:
				System.out.println(kryo.deserializeString(bytesArray));
				break;
			default:
				System.out.println(msgPack.deserializeString(bytesArray));
				break;
		}

		// Deserialize a String and print to the console.
		System.out.println("Data Size: " + bytesArray.length);
	}

	/**
	 * Consume and process an ArrayList message using the specified deserializer.
	 *
	 * @param deserializer An integer representing the deserializer choice.
	 */
	public void consumeArrayListMsg (int deserializer) {
		QueueingConsumer.Delivery delivery = getBytes();
		byte[] bytesArray = delivery.getBody();

		switch (deserializer) {
			case 0:
				System.out.println(cbor.deserializeArrayList(bytesArray));
				break;
			case 1:
				System.out.println(bson.deserializeArrayList(bytesArray));
				break;
			case 2:
				System.out.println(hessian.deserializeArrayList(bytesArray));
				break;
			case 3:
				System.out.println(java.deserializeArrayList(bytesArray));
				break;
			case 4:
				System.out.println(kryo.deserializeArrayList(bytesArray));
				break;
			default:
				System.out.println(msgPack.deserializeArrayList(bytesArray));
				break;
		}

		System.out.println("Data Size: " + bytesArray.length);
	}

	/**
	 * Consume and process a HashMap message using the specified deserializer.
	 *
	 * @param deserializer An integer representing the deserializer choice.
	 */
	public void consumeHashMapMsg (int deserializer) {
		QueueingConsumer.Delivery delivery = getBytes();
		byte[] bytesArray = delivery.getBody();

		switch (deserializer) {
			case 0:
				System.out.println(bson.deserializeHashMap(bytesArray));
				break;
			case 1:
				System.out.println(bson.deserializeHashMap(bytesArray));
				break;
			case 2:
				System.out.println(hessian.deserializeHashMap(bytesArray));
				break;
			case 3:
				System.out.println(java.deserializeHashMap(bytesArray));
				break;
			case 4:
				System.out.println(kryo.deserializeHashMap(bytesArray));
				break;
			default:
				System.out.println(msgPack.deserializeHashMap(bytesArray));
				break;
		}

		System.out.println("Data Size: " + bytesArray.length);
	}

	/**
	 * Close the open connections and channels.
	 */
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