package main.deserializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.undercouch.bson4jackson.BsonFactory;
import org.json.java.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;

/**
 * Class for deserializing binary data of various Java data types using BSON.
 * <p>
 * The primary purpose of this class is to provide methods for deserializing binary data into Java objects such as
 * ArrayList, HashMap, String, and File, among others. This class utilizes the BSON4Jackson library to achieve
 * deserialization.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class BsonDeserializer {
	/**
	 * Object mapper used for deserialization.
	 */
	private ObjectMapper mapper = null;

	/**
	 * Stores the serialised data.
	 */
	private ByteArrayInputStream bais = null;
	
	/**
	 * Initializes ObjectMapper with BsonFactory and byte input stream for deserialization.
	 *
	 * @param bytesArray Byte array containing serialized data.
	 */
	private void setup (final byte[] bytesArray) {
		mapper = new ObjectMapper(new BsonFactory());
		bais   = new ByteArrayInputStream(bytesArray);
	}
	
	/**
	 * Deserializes a byte array into an ArrayList of Strings.
	 *
	 * @param arrayListBytesArray The serialized ArrayList.
	 * @return - Deserialized ArrayList.
	 */
	@SuppressWarnings( "unchecked" )
	public ArrayList<String> deserializeArrayList (final byte[] arrayListBytesArray) {
		ArrayList<String> listData = new ArrayList<>();

		try {
			setup(arrayListBytesArray);
			listData = mapper.readValue(bais, ArrayList.class);
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return listData;
	} 
	
	/**
	 * Deserializes a byte array into a HashMap with String keys and Object values.
	 * 
	 * @param hashMapBytesArray - The serialized HashMap.
	 * @return - Deserialized HashMap.
	 */
	@SuppressWarnings( "unchecked" )
	public HashMap<String, Object> deserializeHashMap (final byte[] hashMapBytesArray) {
		HashMap<String, Object> hashMapData = new HashMap<>();

		try {
			setup(hashMapBytesArray);
			hashMapData = mapper.readValue(bais, HashMap.class);
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
		
		return hashMapData;
	}
	
	/**
	 * Deserializes a byte array into a JSONObject representing a String.
	 * 
	 * @param jsonBytesArray - The serialized JSONObject.
	 * @return - Deserialized JSONObject.
	 */
	public JSONObject deserializeString(final byte[] jsonBytesArray) {
		JSONObject jsonData = null;

		try {
			setup(jsonBytesArray);
			jsonData = mapper.readValue(bais, JSONObject.class);
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
		
		return jsonData;
	}
	
	/**
	 * Deserializes a byte array into a File object.
	 * 
	 * @param fileBytesArray - The serialized file.
	 * @return - Deserialized file.
	 */
	public File deserializeFile (final byte[] fileBytesArray) {
		File stringData = null;

		try {
			setup(fileBytesArray);
			stringData = mapper.readValue(bais, File.class);
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
		
		return stringData;
	}
}
