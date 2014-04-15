package com.rohitsinha.codingchallenge;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * A simple Test which tests the {@link com.rohitsinha.codingchallenge.ArrayHopperApp} for different inputs
 * @author Rohit Sinha
 */
@RunWith(Parameterized.class)
public class ArrayHopperAppTest {

	private final String filepath;
	private final String expectedResult;
	private ArrayHopperApp appTest;

	public ArrayHopperAppTest(String filepath,
							  String expectedResult) {
		this.filepath = filepath;
		this.expectedResult = expectedResult;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> configs() {
		return Arrays.asList(new Object[][]{
				{"GivenSampleInput.txt", "0, 5, 9, out"},
				{"CustomInput1.txt", ArrayHopperApp.HOP_FAILURE},
				{"CustomInput2.txt", "0, 1, 2, 3, 4, 5, 6, 7, 8, 9, out"},
				{"CustomInput3.txt", ArrayHopperApp.HOP_FAILURE},
				{"CustomInput4.txt", "0, out"},
				{"CustomInput5.txt", "0, 11, 24, 36, 45, 58, 65, 72, 78, 86, out"},
				{"CustomInput6.txt", "0, 43, 101, 156, 207, 260, 311, 366, 417, 470, 525, 576, 631, 676, 728, 777, " +
						"830, 884, 935, 984, out"},
				{"CustomInput7.txt", "0, 251, 527, 796, 1067, 1333, 1594, 1870, 2142, 2388, 2671, 2950, 3226, 3484, " +
						"3745, 3990, 4268, 4511, 4789, 5041, 5310, 5576, 5856, 6136, 6407, 6676, 6928, 7195, 7465, " +
						"7734, 8004, 8249, 8510, 8770, 9023, 9288, 9558, 9828, out"}
		});
	}

	@Before
	public void initialize() {
		appTest = new ArrayHopperApp();
	}

	@Test
	public void testApp() {
		assertEquals(expectedResult, appTest.findHops(filepath));
	}
}