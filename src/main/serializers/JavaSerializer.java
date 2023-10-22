package main.serializers;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;

/**
 * Class for serializing various Java data types into binary data using Java Objects.
 * <p>
 * The primary purpose of this class is to provide methods for serializing binary data into Java objects such as
 * ArrayList, HashMap, String, and File, among others. This class utilizes the ObjectOutputStream library to achieve
 * serialization.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class JavaSerializer {
	/**
	 * Default constructor for the JavaSerializer class.
	 */
	public JavaSerializer () {

	}

	/**
	 * Serializes an ArrayList of strings into a byte array.
	 *
	 * @param arrayList ArrayList to be serialized.
	 * @return A byte array containing the serialized data.
	 */
	public byte[] serializeArrayList (final ArrayList<String> arrayList) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
        	ObjectOutputStream out = new ObjectOutputStream(baos);
			out.writeObject(arrayList);
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
	public byte[] serializeHashMap (final HashMap<String, Object> hashMap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
        	ObjectOutputStream out = new ObjectOutputStream(baos);
			out.writeObject(hashMap);
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
	public byte[] serializeString (final String string) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
        	ObjectOutputStream out = new ObjectOutputStream(baos);
			out.writeObject(string);
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}

	    return baos.toByteArray();
	}
}
