package com.product.comparison;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class StatusPrinterTest {

	final private static String OUTPUTPATH = StatusPrinterTest.class
			.getResource("/output").getPath();
	final private static String OUTFILENAME = "out.txt";

	@Before
	public void cleanup() {

		String outputAbsolutePath = OUTPUTPATH
				+ System.getProperty("file.separator") + OUTFILENAME;
		File file = new File(outputAbsolutePath);

		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	public void printStatus_TwoStrings_OutputFileExists() {

		String outputAbsolutePath = OUTPUTPATH
				+ System.getProperty("file.separator") + OUTFILENAME;
		File file = new File(outputAbsolutePath);

		assertFalse(file.exists());

		StatusPrinter statusPrinter = StatusPrinter
				.getStatusPrinter(outputAbsolutePath);
		statusPrinter.printStatus("printing first line");
		statusPrinter.printStatus("printing second line");

		
		assertEquals(41, file.length());
		assertTrue(file.exists());
	}

}
