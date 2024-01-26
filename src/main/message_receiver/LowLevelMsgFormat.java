package main.message_receiver;

import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.template.Template;
import org.msgpack.unpacker.Unpacker;
import org.msgpack.annotation.Message;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.msgpack.template.Templates.TString;
import static org.msgpack.template.Templates.tMap;

/**
 * The LowLevelMsgFormat class describes the message format for specifying monitored metrics of
 * Cloud physical and virtual machines. It provides methods for serializing and deserializing
 * messages using MessagePack.
 * <p>
 * This class defines the structure of the message format, including the cloud name, machine details,
 * timestamp, and monitoring rate. It also encapsulates the logic for converting these messages to byte
 * arrays and vice versa.
 *
 * @author Vincent C. Emeakaroha
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
@Message
public class LowLevelMsgFormat {
	/**
	 * Template for serializing and de-serializing map objects.
	 */
	public Template<Map<String, Map<String, String>>> hostTmpl = tMap(TString, tMap(TString, TString));

	/**
	 * The cloud name for the message.
	 */
	public String name;

	/**
	 * Details of the monitored machines, organized as a map of hosts and their metrics.
	 */
	public LinkedHashMap<String, HashMap<String, String>> machineDetails;

	/**
	 * The timestamp of the monitoring data.
	 */
	public String timestamp;

	/**
	 * The rate of monitoring.
	 */
	public String rate;

	/**
	 * The MessagePack instance for serialization and deserialization.
	 */
	MessagePack msgpack;

	/**
	 * The Packer for packing data for serialization.
	 */
	Packer packer;

	/**
	 * The Unpacker for unpacking data during deserialization.
	 */
	Unpacker unpacker;

	/**
	 * The output stream for storing serialized data.
	 */
	ByteArrayOutputStream out;

	/**
	 * The input stream for reading data during deserialization.
	 */
	ByteArrayInputStream in;

	/**
	 * The byte array containing the de-serialized cloud name.
	 */
	byte[] bytes;

	/**
	 * The de-serialized cloud name.
	 */
	String dName;

	/**
	 * The de-serialized details of monitored machines.
	 */
	Map<String, Map<String, String>> dMachine;

	/**
	 * The de-serialized timestamp of monitoring data.
	 */
	String dTime;

	/**
	 * The de-serialized rate of monitoring.
	 */
	String dRate;

	// Constructor
	public LowLevelMsgFormat () {
		bytes = null;
		machineDetails = new LinkedHashMap<String, HashMap<String, String>>();
		name = "";
		rate = "";
		msgpack = new MessagePack();
		out = new ByteArrayOutputStream();
		packer = msgpack.createPacker(out);
	}

	/**
	 * A method to serialize the message body and produce a byte array.
	 *
	 * @return The byte array representing the serialized message.
	 */
	public byte[] serializeMessage () {
		try {
			packer.write(name);
			packer.write(machineDetails);
			packer.write(timestamp);
			packer.write(rate);
			bytes = out.toByteArray();
		}
		catch (IOException e) {
			System.err.println("Failure while encoding message with Packer");
			e.printStackTrace();
		}

		return bytes;
	}

	/**
	 * A method to de-serialize the received message byte array.
	 *
	 * @param msg The received message byte array.
	 */
	public void deserializeMessage (byte[] msg) {
		dName = "";
		dTime = "";
		dRate = "";
		dMachine = new HashMap<String, Map<String, String>>();

		try {
			in = new ByteArrayInputStream(msg);
			unpacker = msgpack.createUnpacker(in);

			dName = unpacker.readString();
			dMachine = unpacker.read(hostTmpl);
			dTime = unpacker.read(String.class);
			dRate = unpacker.readString();
		}
		catch (IOException e) {
			System.err.println("Failure while decoding message with UnPacker");
			e.printStackTrace();
		}
	}

	/**
	 * Sets the cloud name for the message.
	 *
	 * @param value The cloud name.
	 */
	public void setName (String value) {
		this.name = value;
	}

	/**
	 * Sets the host and metric value pairs for the message.
	 *
	 * @param value A map containing host and metric value pairs.
	 */
	public void setMachineDetails (LinkedHashMap<String, HashMap<String, String>> value) {
		this.machineDetails = value;
	}

	/**
	 * Sets the timestamp for monitoring.
	 *
	 * @param value A timestamp value.
	 */
	public void setTimestamp (Timestamp value) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");
		this.timestamp = formatter.format(value);
	}

	/**
	 * Sets the rate of monitoring.
	 *
	 * @param value The rate of monitoring.
	 */
	public void setMonitoringRate (String value) {
		this.rate = value;
	}

	/**
	 * Gets the de-serialized cloud name.
	 *
	 * @return The cloud name from the de-serialized message.
	 */
	public String getDeserializedName () {
		return dName;
	}

	/**
	 * Gets the de-serialized host details.
	 *
	 * @return A map containing host and metric value pairs from the de-serialized message.
	 */
	public Map<String, Map<String, String>> getDeserializedMetrics() {
		return dMachine;
	}

	/**
	 * Gets the de-serialized frequency of monitoring metrics.
	 *
	 * @return The frequency of monitoring metrics from the de-serialized message.
	 */
	public String getDeserializedMonitoringRate () {
		return dRate;
	}

	/**
	 * Gets the de-serialized timestamp for monitoring.
	 *
	 * @return The timestamp for monitoring from the de-serialized message.
	 */
	public String getDeserializedTimeStamp () {
		return dTime;
	}
}
