package main.serializers;

import com.caucho.hessian.io.Hessian2Output;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;

/**
 * Class for serializing various Java data types into binary data using Hessian.
 * <p>
 * The primary purpose of this class is to provide methods for serializing binary data into Java objects such as
 * ArrayList, HashMap, String, and File, among others. This class utilizes the BSON4Jackson library to achieve serialization.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class HessianSerializer {
	/**
	 * Stores the serialised data.
	 */
	private ByteArrayOutputStream baos = null;

	/**
	 * Hessian output to work with ByteArrayOutputStream.
	 */
	private Hessian2Output out = null;

	/**
	 * Default constructor for the HessianSerializer class.
	 */
	public HessianSerializer () {
		baos = new ByteArrayOutputStream();
		out  = new Hessian2Output(baos);
	}

	/**
	 * Serializes an ArrayList of strings into a byte array.
	 *
	 * @param arrayList ArrayList to be serialized.
	 * @return A byte array containing the serialized data.
	 */
    public byte[] serializeArrayList (final ArrayList<String> arrayList) {
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Hessian2Output out = new Hessian2Output(baos);
    	
    	try {
    		out.startMessage();
    		out.writeObject(arrayList);
    		out.completeMessage();
    		out.close();
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
		Hessian2Output out = new Hessian2Output(baos);
    	
    	try {
    		out.startMessage();
    		out.writeObject(hashMap);
    		out.completeMessage();
    		out.close();
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
		Hessian2Output out = new Hessian2Output(baos);
    	
    	try {
    		out.startMessage();
    		out.writeObject(string);
    		out.completeMessage();
    		out.close();
    	}
		catch (Exception exception) {
			exception.printStackTrace();
		}
    	
    	return baos.toByteArray();
    }
}
