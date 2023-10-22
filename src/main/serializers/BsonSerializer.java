package main.serializers;

import de.undercouch.bson4jackson.BsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;

/**
 * Class for serializing various Java data types such as ArrayList, HashMap, String and File into binary data using BSON.
 * <p>
 * The primary purpose of this class is to provide methods for serializing binary data into Java objects such as
 * ArrayList, HashMap, String, and File, among others. This class utilizes the BSON4Jackson library to achieve serialization.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class BsonSerializer {
	/**
	 * Object mapper used for deserialization.
	 */
	private ObjectMapper mapper = null;

	/**
	 * Stores the serialised data.
	 */
	private ByteArrayOutputStream baos = null;

	/**
	 * Default constructor for the BsonSerializer class.
	 */
	public BsonSerializer () {
		this.baos   = new ByteArrayOutputStream();
		this.mapper = new ObjectMapper(new BsonFactory());
	}

	/**
	 * Serializes an ArrayList of strings into a byte array.
	 *
	 * @param arrayList ArrayList to be serialized.
	 * @return A byte array containing the serialized data.
	 */
	public byte[] serializeArrayList (final ArrayList<String> arrayList) {
	    try {
			mapper.writeValue(baos, arrayList);
		} 
	    catch (IOException ex) {
			ex.printStackTrace();
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
		try {
			mapper.writeValue(baos, hashMap);
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
		try {
			mapper.writeValue(baos, string);
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	    
	    return baos.toByteArray();
	}

	/**
	 * Serializes a File into a byte array.
	 *
	 * @param file File to be serialized.
	 * @return A byte array containing the serialized data.
	 */
	public byte[] serializeFile (final File file) {
		try {
			mapper.writeValue(baos, file);
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	    
	    return baos.toByteArray();
	}
}
