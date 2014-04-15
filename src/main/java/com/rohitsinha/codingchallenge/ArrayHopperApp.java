package com.rohitsinha.codingchallenge;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Class to find the minimum jump indexes to travel through an array
 * @author Rohit Sinha
 */
public class ArrayHopperApp {
	public static final String HOP_FAILURE = "failure";
	public static final String HOP_OUT = "out";
	private static final Logger LOGGER = Logger.getLogger(ArrayHopperApp.class.getName());
	private static final int NUMBER_OF_VALID_ARGUMENTS = 1;
	private static final int STATUS_FAILURE = 1;

	public static void main(String[] args) {
		switch (args.length) {
			case NUMBER_OF_VALID_ARGUMENTS:
				ArrayHopperApp arrayHopperObject = new ArrayHopperApp();
				String hopIndexes = arrayHopperObject.findHops(args[0]);
				IOUtils.write(hopIndexes);
				break;
			default:
				LOGGER.severe("Error: Invalid number of arguments");
				printUsage();
				break;
		}
	}

	/**
	 * A simple function to print the basic usage
	 */
	private static void printUsage() {
		LOGGER.info("Usage: ArrayHopper takes " + NUMBER_OF_VALID_ARGUMENTS + " arguments");
		LOGGER.info("filepath: the path to the input file containing array data");
	}

	/**
	 * Function to read the input file, find minimum hop indices and prepare formatted output through other helper
	 * methods
	 *
	 * @param filepath: the path of the file containing array values
	 * @return : the output contains indices of minimum hops
	 */
	public String findHops(String filepath) {
		String hopIndexes = null;
		try {
			ArrayList<Integer> array = IOUtils.readFile(filepath);
			hopIndexes = prepareOutput(findHopsHelper(array));
		} catch (FileNotFoundException fne) {
			LOGGER.severe("Caught FileNotFoundException: Unable to find the specified input file" + fne.getMessage());
			System.exit(STATUS_FAILURE);
		} catch (NumberFormatException nfe) {
			LOGGER.severe("Caught NumberFormatException: Unable to parse the data in input file: " + filepath + " " +
					nfe
					.getMessage());
			System.exit(STATUS_FAILURE);
		} catch (IOException ioe) {
			LOGGER.severe("Caught IOException: Unable to to read the specified input file" + ioe.getMessage());
			System.exit(STATUS_FAILURE);
		} catch (RuntimeException rte) {
			LOGGER.severe("Caught RunTimeException: " + rte.getMessage());
			System.exit(STATUS_FAILURE);
		}
		return hopIndexes;
	}

	/**
	 * Function to prepare a valid well formatted result string
	 *
	 * @param hopIndexes: a {@link java.util.ArrayList} of hop indices which is empty if array cannot be hopped
	 * @return : a String which contains hop indices with formatting or failure message
	 */
	private String prepareOutput(ArrayList<String> hopIndexes) {
		if (hopIndexes == null || hopIndexes.size() == 0) {
			return HOP_FAILURE;
		} else {
			hopIndexes.add(HOP_OUT);
			String result = hopIndexes.toString();
			return result.substring(1, result.length() - 1);    // remove the starting and ending [ ]
		}
	}

	/**
	 * Function to find minimum hop indices for a given array
	 *
	 * @param array: the array containing integers
	 * @return : a {@link java.util.ArrayList} containing hop indices if hopping beyond array is possible else null
	 */
	private ArrayList<String> findHopsHelper(ArrayList<Integer> array) {
		ArrayList<String> hops = new ArrayList<String>();
		int lReach = 0, curReach = 0, selIndex = 0;
		int i;
		for (i = 0; i < array.size(); i++) {
			if (i > curReach) break;    // if the index is beyond current reach then just break out
			if (i > lReach) {
				lReach = curReach;    // update last reach
				hops.add(String.valueOf(selIndex));
			}
			int sum = i + array.get(i);    // update current reach
			if (sum > curReach) selIndex = i;
			curReach = Math.max(curReach, sum);
		}
		if ((i > lReach) && (hops.size() > 0) && (Integer.parseInt(hops.get(hops.size() - 1)) != selIndex)) {
			// if i was beyond last reach and hops are present then add the last selected index
			hops.add(String.valueOf(selIndex));
		}

		if (lReach >= array.size() - 1)    // if last reachable index was greater than or equal to last index of array
			// then return the hop indices as hopping is possible
			return hops;
		else
			return null;
	}
}
