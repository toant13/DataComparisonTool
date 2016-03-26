package com.product.tran.dashboard.comparison;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ComparisonApp extends Application{
	
	public static void main(String[] args) {
//		String productFile = "/Users/toantran/Documents/Java_development/workspace/DataComparisonTool/src/test/resources/InputFiles/product.csv";
//		String morningStarFile = "/Users/toantran/Documents/Java_development/workspace/DataComparisonTool/src/test/resources/InputFiles/morningstar.csv";
//		String mapFile = "/Users/toantran/Documents/Java_development/workspace/DataComparisonTool/src/test/resources/map/map.txt";
//		
//		MorningStarComparer comparer = new MorningStarComparer(productFile,
//				morningStarFile, mapFile);
//		comparer.runCompare();
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/morningstar/comparison/config/comparison_app.fxml"));
		Scene scene = new Scene(root, 300, 275);

        stage.setTitle("FiDa Comparer");
        stage.setScene(scene);
        stage.show();
	}


}
