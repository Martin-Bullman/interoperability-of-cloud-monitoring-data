package main.deserializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;

/**
 * Class for deserializing binary data of various Java data types using Kryo.
 * <p>
 * The primary purpose of this class is to provide methods for deserializing binary data into Java objects such as
 * ArrayList, HashMap, String, and File, among others. This class utilizes the kryo library to achieve deserialization.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class KryoDeserializer {
	/**
	 * Deserializes binary data into a Java object using Kryo serialization.
	 *
	 * @param bytesArray The byte array containing serialized data.
	 * @return The deserialized object.
	 */
	public Object deserialize (final byte[] bytesArray) {
		Object deserializedData = null;

		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytesArray);
			Input input = new Input(bais);
			Kryo kryo = new Kryo();

			deserializedData = kryo.readClassAndObject(input);
		}
		catch (Exception exception) {
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
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings("unchecked")
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

	/**
	 * Deserializes binary data into a File.
	 *
	 * @param fileBytesArray - The serialized File.
	 * @return - Deserialized File.
	 */
	public File deserializeFile (final byte[] fileBytesArray) {
		Object fileData = deserialize(fileBytesArray);
		
		return (File) fileData;
	}
}
