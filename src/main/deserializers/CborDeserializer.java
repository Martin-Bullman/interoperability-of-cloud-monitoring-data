package main.deserializers;

import com.naphaso.cbor.CborParser;
import com.naphaso.cbor.io.*;
import com.naphaso.cbor.type.CborObject;

import java.io.ByteArrayInputStream;

/**
 * Class for deserializing binary data of various Java data types using CBOR.
 * <p>
 * The primary purpose of this class is to provide methods for deserializing binary data into Java objects such as
 * ArrayList, HashMap, String, and File, among others. This class utilizes the CBOR library to achieve deserialization.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class CborDeserializer {
	/**
	 * Deserializes binary data into a CborObject object using CborParser serialization.
	 *
	 * @param bytesArray The byte array containing serialized data in CBOR format.
	 * @return The deserialized CBOR object.
	 *
	 * @throws CborParseException if there's an issue with parsing the CBOR data.
	 */
	private CborObject deserialize (final byte[] bytesArray) {
		CborObject deserializedData = null;

		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytesArray);
			Input input = new StreamInput(bais);
			CborParser cborParser = new CborParser();
			deserializedData = cborParser.parse(input);
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}

		return deserializedData;
	}

	/**
	 * Deserializes an ArrayList byte array into a CBOR object.
	 *
	 * @param arrayListBytesArray The serialized ArrayList.
	 * @return The deserialized CBOR object.
	 *
	 * @throws CborParseException if there's an issue with parsing the CBOR data.
	 */
	public CborObject deserializeArrayList (final byte[] arrayListBytesArray) {
		return deserialize(arrayListBytesArray);
	}

	/**
	 * Deserializes a HashMap byte array into a CBOR object.
	 *
	 * @param hashMapBytesArray The serialized HashMap.
	 * @return The deserialized CBOR object.
	 *
	 * @throws CborParseException if there's an issue with parsing the CBOR data.
	 */
	public CborObject deserializeHashMap (final byte[] hashMapBytesArray) {
		return deserialize(hashMapBytesArray);
	}

	/**
	 * Deserializes a String byte array into a CBOR object.
	 *
	 * @param stringBytesArray The serialized String.
	 * @return The deserialized CBOR object.
	 *
	 * @throws CborParseException if there's an issue with parsing the CBOR data.
	 */
	public CborObject deserializeString (final byte[] stringBytesArray) {
		return deserialize(stringBytesArray);
	}
}
