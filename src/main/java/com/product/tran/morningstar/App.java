package com.product.tran.morningstar;

public class App {

	public static void main(String[] args) throws Exception {
		String productFile = "/Users/toantran/Documents/Java_development/workspace/DataComparisonTool/src/test/resources/InputFiles/product.csv";
		String morningStarFile = "/Users/toantran/Documents/Java_development/workspace/DataComparisonTool/src/test/resources/InputFiles/morningstar.csv";
		String mapFile = "/Users/toantran/Documents/Java_development/workspace/DataComparisonTool/src/test/resources/map/map.txt";

		MorningStarComparer comparer = new MorningStarComparer(productFile,
				morningStarFile, mapFile);
		comparer.runCompare();
	}
}
