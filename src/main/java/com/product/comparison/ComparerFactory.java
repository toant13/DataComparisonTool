package com.product.comparison;

public class ComparerFactory {

	public static Comparer getComparer(String type) {
		Comparer comparer = null;
		switch (type) {
		case "ALPHA":
			comparer = new AlphaComparer(100);
			break;
		case "NUMERIC":
			comparer = new NumericComparer(1);
			break;
		}

		return comparer;
	}
}
