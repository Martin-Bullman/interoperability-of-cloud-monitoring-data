package main.serializers;

import com.naphaso.cbor.io.*;
import com.naphaso.cbor.CborWriter;
import com.naphaso.cbor.serializers.*;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;

/**
 * Class for serializing various Java data types such as ArrayList, HashMap, String and File into binary data using CBOR.
 * <p>
 * The primary purpose of this class is to provide methods for serializing binary data into Java objects such as
 * ArrayList, HashMap, String, and File, among others. This class utilizes the BSON4Jackson library to achieve serialization.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class CborSerializer {

	/**
	 * Stores the serialised data.
	 */
	private ByteArrayOutputStream baos = null;

	/**
	 * Stream output to work with ByteArrayOutputStream.
	 */
	private Output output = null;

	/**
	 *  CBOR writer to work with Output.
	 */
	private CborWriter writer = null;

	/**
	 * Default constructor for the CborSerializer class.
	 */
	public CborSerializer () {
		baos   = new ByteArrayOutputStream();
		output = new StreamOutput(baos);
		writer = new CborWriter(output);
	}

	/**
	 * Serializes an ArrayList of strings into a byte array.
	 *
	 * @param arrayList ArrayList to be serialized.
	 * @return A byte array containing the serialized data.
	 */
	public byte[] serializeArrayList (ArrayList<String> arrayList) {
		final Serializer<ArrayList<String>> serializer = SerializerFactory.getInstance().getSerializer(arrayList.getClass());
	       
	    try {
	        serializer.write(arrayList).write(writer);
	    }
		catch (Exception exception) {
			exception.printStackTrace();
		}

	    return baos.toByteArray();
	}

	/**
	 * Serializes an HashMap of strings as keys and Objects as values into a byte array.
	 *
	 * @param hashMap HashMap to be serialized.
	 * @return A byte array containing the serialized data.
	 */
	public byte[] serializeHashMap (HashMap<String, Object> hashMap) {
		final Serializer<Map<String, Object>> serializer = SerializerFactory.getInstance().getSerializer(hashMap.getClass());
	       
	    try {
	        serializer.write(hashMap).write(writer);
	    }
		catch (Exception exception) {
			exception.printStackTrace();
		}

    	return baos.toByteArray();
	}

	/**
	 * Serializes a String into a byte array.
	 *
	 * @param string String to be serialized.
	 * @return A byte array containing the serialized data.
	 */
	public byte[] serializeString (String string) {
		final Serializer<String> serializer = SerializerFactory.getInstance().getSerializer(string.getClass());
	       
	    try {
	        serializer.write(string).write(writer);
	    }
		catch (Exception exception) {
			exception.printStackTrace();
		}

    	return baos.toByteArray();
	}
}
