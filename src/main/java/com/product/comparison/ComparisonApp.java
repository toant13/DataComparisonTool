package com.product.comparison;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ComparisonApp extends Application{
	
	public static void main(String[] args) {	
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/product/comparison/config/comparison_app.fxml"));
		Scene scene = new Scene(root, 300, 275);

        stage.setTitle("FiDa Comparer");
        stage.setScene(scene);
        stage.show();
	}


}
