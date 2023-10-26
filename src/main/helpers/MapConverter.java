package main.helpers;

import java.util.*;

/**
 * The `MapConverter` class provides a method to convert a Java HashMap into a Java Map with different types.
 * It enables the conversion of values stored in a HashMap into strings and populates a new Map with these
 * converted values.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class MapConverter {
	/**
	 * Converts a Java HashMap into a Java Map with different types.
	 *
	 * @param hashMap The source HashMap to be converted.
	 * @return A new Map containing the data from the original HashMap with values converted to strings.
	 */
    public Map<String, String> convertHashMapTypes(HashMap<String, Object> hashMap) {
    	Map<String, String> newMap = new HashMap<String, String>();
    	
    	for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
    		try {
    	        newMap.put(entry.getKey(), entry.getValue().toString());
    	    }
    		catch(ClassCastException exception) {
				exception.printStackTrace();
    	    }
    	}
    	
    	return newMap;
    }
}
