package main.serializers;

import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;

import main.helpers.MapConverter;

/**
 * Class for serializing binary data of various Java data types using MessagePack.
 * <p>
 * The primary purpose of this class is to provide methods for deserializing binary data into Java objects such as
 * ArrayList, HashMap, String, and File, among others. This class utilizes the kryo library to achieve deserialization.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class MessagePackSerializer {
	/**
	 * Serializes an ArrayList of strings into a byte array.
	 *
	 * @param arrayList ArrayList to be serialized.
	 * @return A byte array containing the serialized data.
	 */
	public byte[] serializeArrayList (final ArrayList<String> arrayList) {
		byte[] bytes = null;

		try {
			MessagePack msgpack = new MessagePack();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Packer packer = msgpack.createPacker(baos);

		    packer.write(arrayList);
		    bytes = baos.toByteArray();
			packer.flush();
		    baos.flush();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return bytes;
	}

	/**
	 * Serializes an HashMap of strings as keys and Objects as values into a byte array.
	 *
	 * @param hashMap HashMap to be serialized.
	 * @return A byte array containing the serialized data.
	 */
	public byte[] serializeHashMap (final HashMap<String, Object> hashMap) {
		byte[] bytes = null;

		try {
			MessagePack msgpack = new MessagePack();
			ByteArrayOutputStream baos  = new ByteArrayOutputStream();
			Packer packer = msgpack.createPacker(baos);

			MapConverter converter = new MapConverter();
			Map<String, String> newMap = converter.convertHashMapTypes(hashMap);

			packer.write(newMap);
			bytes = baos.toByteArray();
			packer.flush();
			baos.flush();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return bytes;
	}

	/**
	 * Serializes a String into a byte array.
	 *
	 * @param string String to be serialized.
	 * @return A byte array containing the serialized data.
	 */
	public byte[] serializeString (final String string) {
		byte[] bytes = null;

		try {
			MessagePack msgpack = new MessagePack();
			ByteArrayOutputStream baos  = new ByteArrayOutputStream();
			Packer packer = msgpack.createPacker(baos);

	        packer.write(string);
	        bytes = baos.toByteArray();
			packer.flush();
			baos.flush();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return bytes;
    }

	/**
	 * Serializes a File into a byte array.
	 *
	 * @param file File to be serialized.
	 * @return A byte array containing the serialized data.
	 */
	public byte[] serializeFile (final String file) {
		byte[] bytes = null;
		FileOutputStream fileOut = null;

		try {
			fileOut = new FileOutputStream(file);
			MessagePack msgpack = new MessagePack();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Packer packer = msgpack.createPacker(baos);

	        packer.write(fileOut);
	        bytes = baos.toByteArray();
			packer.flush();
			baos.flush();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return bytes;
    }
}
