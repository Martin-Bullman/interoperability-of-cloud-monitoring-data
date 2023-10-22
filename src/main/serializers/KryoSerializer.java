package main.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;

/**
 * Class for serializing various Java data types into binary data using Kyro.
 * <p>
 * The primary purpose of this class is to provide methods for serializing binary data into Java objects such as
 * ArrayList, HashMap, String, and File, among others. This class utilizes the Kryo library to achieve serialization.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class KryoSerializer {
	/**
	 * Default constructor for the KryoSerializer class.
	 */
	public KryoSerializer () {

	}

	/**
	 * Serializes an ArrayList of strings into a byte array.
	 *
	 * @param arrayList ArrayList to be serialized.
	 * @return A byte array containing the serialized data.
	 */
	public byte[] serializeArrayList (final ArrayList<String> arrayList) {
		Kryo kryo = new Kryo();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Output output = new Output(baos);

		try {
			kryo.writeClassAndObject(output,  arrayList);
			output.flush();
			output.close();
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
		Kryo kryo = new Kryo();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Output output = new Output(baos);
		Map<String, Object> object = new HashMap<String, Object>(hashMap);

		try {
			kryo.writeClassAndObject(output, object);
			output.flush();
			output.close();
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
    	Kryo kryo = new Kryo();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Output output = new Output(baos);

		try {
			kryo.writeObject(output, string);
			output.flush();
			output.close();
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
		Kryo kryo = new Kryo();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Output output = new Output(baos);

		try {
			kryo.writeObject(output, file);
			output.flush();
			output.close();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
    	
        return baos.toByteArray();
    }
}
