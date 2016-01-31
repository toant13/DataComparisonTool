package com.product.tran.morningstar.comparison;

import javafx.application.Application;
import javafx.stage.Stage;


public class ComparisonApp extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("PRODUCT");
		stage.show();
	}

//	public static void main(String[] args) throws Exception {
//		String productFile = "/Users/toantran/Documents/Java_development/workspace/DataComparisonTool/src/test/resources/InputFiles/product.csv";
//		String morningStarFile = "/Users/toantran/Documents/Java_development/workspace/DataComparisonTool/src/test/resources/InputFiles/morningstar.csv";
//		String mapFile = "/Users/toantran/Documents/Java_development/workspace/DataComparisonTool/src/test/resources/map/map.txt";
//		
//		MorningStarComparer comparer = new MorningStarComparer(productFile,
//				morningStarFile, mapFile);
//		comparer.runCompare();
//		
//		
//
//	}
}
