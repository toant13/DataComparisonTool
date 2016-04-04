package com.product.comparison;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StatusPrinter {
	private static StatusPrinter instance;

	private File file;

	private StatusPrinter(String outputFilePath) {
		this.file = new File(outputFilePath);
		try {
			this.file.createNewFile();
		} catch (IOException e) {
			System.out.println("Error with file creation");
		}
	}

	public static StatusPrinter getStatusPrinter(String outputFilePath) {
		if (instance == null) {
			return new StatusPrinter(outputFilePath);
		} else {
			return instance;
		}
	}

	public void printStatus(String message) {
		FileWriter writer;
		try {
			writer = new FileWriter(file, true);
			writer.write(message + System.getProperty("line.separator"));
			writer.close();
		} catch (IOException e) {
			System.out.println("Error with FileWriter");
		}

	}

}
