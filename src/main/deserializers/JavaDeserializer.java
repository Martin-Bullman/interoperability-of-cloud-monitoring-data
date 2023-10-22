package main.deserializers;

import java.util.HashMap;
import java.io.IOException;
import java.io.ObjectInput;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;

/**
 * Class for deserializing binary data of various Java data types using Java Objects.
 * <p>
 * The primary purpose of this class is to provide methods for deserializing binary data into Java objects such as
 * ArrayList, HashMap, String, and File, among others. This class utilizes Java Objects to achieve
 * deserialization.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class JavaDeserializer {
	/**
	 * Deserializes binary data into an object.
	 *
	 * @param bytesArray The byte array containing serialized data.
	 * @return The deserialized object.
	 */
	private Object deserialize (final byte[] bytesArray) {
		Object deserializedData = null;

		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytesArray);
			ObjectInput in = new ObjectInputStream(bais);
			deserializedData = in.readObject();
		}
		catch (IOException | ClassNotFoundException exception) {
			exception.printStackTrace();
		}

		return deserializedData;
	}

	/**
	 * Deserializes binary data into a Java ArrayList of Strings.
	 *
	 * @param arrayListBytesArray - The serialized ArrayList.
	 * @return - Deserialized ArrayList.
	 */
	@SuppressWarnings( "unchecked" )
	public ArrayList<String> deserializeArrayList (final byte[] arrayListBytesArray) {
		Object arraylistData = deserialize(arrayListBytesArray);
		
		return (ArrayList<String>) arraylistData;
	}

	/**
	 * Deserializes binary data into a Java HashMap with String keys and Object values.
	 *
	 * @param hashMapBytesArray - The serialized HashMap.
	 * @return - Deserialized HashMap.
	 */
	@SuppressWarnings( "unchecked" )
	public HashMap<String, Object> deserializeHashMap (final byte[] hashMapBytesArray) {
		Object hashMapData = deserialize(hashMapBytesArray);
		
		return (HashMap<String, Object>) hashMapData;
	}

	/**
	 * Deserializes binary data into a Java String.
	 *
	 * @param stringBytesArray - The serialized string.
	 * @return - Deserialized String.
	 */
	public String deserializeString (final byte[] stringBytesArray) {
		Object stringData = deserialize(stringBytesArray);
		
		return (String) stringData;
	}
}
