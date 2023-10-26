package main.deserializers;

import org.msgpack.MessagePack;
import org.msgpack.template.Template;
import org.msgpack.unpacker.Unpacker;
import static org.msgpack.template.Templates.tList;
import static org.msgpack.template.Templates.tMap;
import static org.msgpack.template.Templates.TString;

import java.io.File;
import java.util.Map;
import java.util.List;
import java.io.ByteArrayInputStream;

/**
 * Class for deserializing binary data of various Java data types using MessagePack.
 * <p>
 * The primary purpose of this class is to provide methods for deserializing binary data into Java objects such as
 * ArrayList, HashMap, String, and File, among others. This class utilizes the MessagePack library to achieve deserialization.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class MessagePackDeserializer {
	/**
	 * Unpacker used for deserialization.
	 */
	private Unpacker unpacker = null;

	/**
	 * Initializes MessagePack and byte input stream for deserialization.
	 *
	 * @param bytesArray Byte array containing serialized data.
	 */
	public void setup (final byte[] bytesArray) {
		MessagePack msgpack = new MessagePack();
		ByteArrayInputStream bais = new ByteArrayInputStream(bytesArray);
        this.unpacker = msgpack.createUnpacker(bais);
	}

	/**
	 * Deserializes binary data into a Java template ArrayList of Strings.
	 *
	 * @param arrayListBytesArray - The serialized ArrayList.
	 * @return - Deserialized ArrayList.
	 */
	public List<String> deserializeArrayList(final byte[] arrayListBytesArray) {
        Template<List<String>> listTmpl = tList(TString);
        List<String> listData = null;
        setup(arrayListBytesArray);
        
		try {
			listData = unpacker.read(listTmpl);
		} 
		catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return listData;
	}

	/**
	 * Deserializes binary data into a Java template HashMap with String keys and String values.
	 *
	 * @param hashMapBytesArray - The serialized HashMap.
	 * @return - Deserialized HashMap.
	 */
	public Map<String, String> deserializeHashMap (final byte[] hashMapBytesArray) {
		Template<Map<String, String>> mapTmpl = tMap(TString, TString);
		Map<String, String> hashMapData = null;
		setup(hashMapBytesArray);

		try {
	        hashMapData = unpacker.read(mapTmpl);
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return hashMapData;
	}

	/**
	 * Deserializes binary data into a Java String.
	 *
	 * @param stringBytesArray - The serialized string.
	 * @return - Deserialized String.
	 */
	public String deserializeString (final byte[] stringBytesArray) {
		String stringData = "";
		setup(stringBytesArray);
		 
		try {
			stringData = unpacker.read(String.class);
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return stringData;
	}

	/**
	 * Deserializes binary data into a File.
	 *
	 * @param fileBytesArray - The serialized File.
	 * @return - Deserialized File.
	 */
	public File deserializeFile (final byte[] fileBytesArray) {
		File fileData = null;
		setup(fileBytesArray);
		 
		try {
			fileData = unpacker.read(File.class);
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return fileData;
	}
}
