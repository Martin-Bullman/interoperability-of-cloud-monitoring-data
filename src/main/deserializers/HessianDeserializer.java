package main.deserializers;

import com.caucho.hessian.io.Hessian2Input;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for deserializing binary data of various Java data types using Hessian.
 * <p>
 * The primary purpose of this class is to provide methods for deserializing binary data into Java objects such as
 * ArrayList, HashMap, String, and File, among others. This class utilizes the Hessian2Input library to achieve deserialization.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class HessianDeserializer {
	/**
	 Deserializes binary data into a Java object using Hessian serialization.
	 *
	 * @param bytesArray The byte array containing serialized data.
	 * @return The deserialized object.
	 */
	private Object deserialize (final byte[] bytesArray) {
		Object deserializedData = null;

		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytesArray);
			Hessian2Input in = new Hessian2Input(bais);

			in.startMessage();
			deserializedData = in.readObject();
			in.completeMessage();

			in.close();
			bais.close();
		}
		catch (IOException exception) {
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
    	Object arrayListData = deserialize(arrayListBytesArray);
    	
    	return (ArrayList<String>) arrayListData;
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
}
