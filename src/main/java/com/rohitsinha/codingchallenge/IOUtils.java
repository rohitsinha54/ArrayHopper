package com.rohitsinha.codingchallenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for simple IO operations for {@link com.rohitsinha.codingchallenge.ArrayHopperApp}
 * @author Rohit Sinha
 */
public class IOUtils {
	/**
	 * Function to read the integers from the input file. Expects a non-negative integers which can be 0
	 *
	 * @param filepath : the path of the input file
	 * @return : a{@link java.util.ArrayList} read from the file
	 * @throws FileNotFoundException      : if the specified file does not exists
	 * @throws IOException                : if reading the file failed
	 * @throws NumberFormatException      : if there parsing to integer failed
	 * @throws java.lang.RuntimeException : if there is a negative integer in the file
	 */
	public static ArrayList<Integer> readFile(String filepath) throws IOException,
			NumberFormatException, RuntimeException {

		ArrayList<Integer> data = new ArrayList<Integer>();
		BufferedReader input = new BufferedReader(new FileReader(filepath));

		while (input.ready()) {
			String curLine = input.readLine();
			Integer i = Integer.parseInt(curLine);
			if (i >= 0)
				data.add(i);
			else
				throw new RuntimeException("Invalid Data - The input file should not contain negative integers");
		}
		if (input != null)
			input.close();
		return data;
	}

	/**
	 * A simple function which just writes to Standard output
	 *
	 * @param hopIndexes: the string to be written
	 */
	public static void write(String hopIndexes) {
		System.out.println(hopIndexes);
	}
}
