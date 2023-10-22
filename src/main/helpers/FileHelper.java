package main.helpers;

import java.io.*;

/**
 * Class which contains methods to perform operations on files.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class FileHelper {
	/**
	 * Converts a given File object into a string representation by reading the file's contents.
	 *
	 * @param file The input file to be converted.
	 * @return A string containing the contents of the file.
	 */
	public String convertFileToString (final File file) {
		StringBuilder fileData = null;

		try {
			fileData = new StringBuilder(1000);
	        BufferedReader reader = new BufferedReader(new FileReader(file));
		 
			char[] charBuffer = new char[1024];
			int numRead = 0;
			
			while ((numRead = reader.read(charBuffer)) != -1 ) {
			    String readData = String.valueOf(charBuffer, 0, numRead);
				fileData.append(readData);
				charBuffer = new char[1024];
			}
		 
			reader.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return fileData.toString();
	}

	/**
	 * Writes a byte array to a file with the given file path.
	 *
	 * @param arrayOfBytes The byte array containing the data to be written to the file.
	 * @param filePath The file path where the data will be saved.
	 */
	public void writeBytesArrayToFile (final byte[] arrayOfBytes, final String filePath) {
		FileOutputStream stream = null;
		BufferedOutputStream bos = null;

		try {
			stream = new FileOutputStream(new File(filePath));
			bos = new BufferedOutputStream(stream);
			bos.write(arrayOfBytes);
		    bos.close();
			stream.close();
		} 
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Writes the given string data to a file.
	 *
	 * @param dataAsString The string data to be written to the file.
	 * @param filePath The path to the file where the data should be saved.
	 */
	public void writeStringDataToFile (String dataAsString, final String filePath) {
		try(PrintWriter out = new PrintWriter(filePath)) {
		    out.println(dataAsString);
			System.out.println( "File written to disk!!!" );
		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Converts a given string into a file and writes the string's contents to the file.
	 *
	 * @param stringFile The input string to be converted into a file.
	 * @param filePath The file path where the data will be saved.
	 *
	 * @return The file path where the string data is saved.
	 */
	public String convertStringToFile (final String stringFile, final String filePath) {
		try (PrintWriter out = new PrintWriter(filePath)) {
			out.println(stringFile);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return filePath;
	}

	/**
	 * Prints each byte in the given byte array to the console.
	 *
	 * @param arrayOfBytes The byte array to be printed.
	 */
	public void printByteArray (final byte[] arrayOfBytes) {
        for (byte arrayOfByte : arrayOfBytes) {
            System.out.println(arrayOfByte);
        }
	}

	/**
	 * Finds and returns the size of the given file in bytes.
	 *
	 * @param inputFile The file for which to find the size.
	 * @return The size of the file in bytes or 0 if the file does not exist.
	 */
	public double findFileSize (File inputFile) {
		double bytes = 0;
		
		if (inputFile.exists()) {
			bytes = inputFile.length();
		}
		else {
			System.out.println("File does not exists!");
		}
		
		return bytes;
	}
}
