package com.product.comparison;

public class StatusPrinter {
	private static StatusPrinter instance;

	private StatusPrinter() {
	}

	public static StatusPrinter getStatusPrinter() {
		if (instance == null) {
			return new StatusPrinter();
		} else {
			return instance;
		}
	}

	public void printStatus(String message) {
		// will replace below with writing out to file
		System.out.println(message);
	}

}
