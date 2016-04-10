package com.product.comparison;

import java.io.File;

import com.product.comparison.compare.ProductSourceComparer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ComparisonApp extends Application {

	@FXML
	private Label statusBar;
	private Stage stage;
	private File productFile;
	private File sourceFile;
	private File mapFile;
	private File outputfile;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(
				"/product/comparison/config/comparison_app.fxml"));
		Scene scene = new Scene(root);

		stage.setTitle("FiDa Comparison tool");
		stage.setScene(scene);
		stage.show();
		this.stage = stage;
	}

	@FXML
	protected void selectProductFile(ActionEvent event) {
		System.out.println("product");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Product File");
		fileChooser.getExtensionFilters().add(
				new FileChooser.ExtensionFilter("CSV files", "*.csv"));
		this.productFile = fileChooser.showOpenDialog(this.stage);
	}

	@FXML
	protected void selectSourceFile(ActionEvent event) {
		System.out.println("sourceFile");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Source File");
		fileChooser.getExtensionFilters().add(
				new FileChooser.ExtensionFilter("CSV files", "*.csv"));
		this.sourceFile = fileChooser.showOpenDialog(this.stage);
	}

	@FXML
	protected void selectMapFile(ActionEvent event) {
		System.out.println("mapFile");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Map File");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("txt files", "*.txt"));
		this.mapFile = fileChooser.showOpenDialog(this.stage);
	}

	@FXML
	protected void selecOutputLocation(ActionEvent event) {
		System.out.println("output");
		DirectoryChooser directoryChooder = new DirectoryChooser();
		this.outputfile = directoryChooder.showDialog(this.stage);
	}

	@FXML
	protected void runComparison(ActionEvent event) throws Exception {
		System.out.println("runcomparison");
		System.out.println(this.productFile.getName());
		System.out.println(this.sourceFile.getName());
		System.out.println(this.mapFile.getName());
		System.out.println(this.outputfile.getAbsolutePath());

		StatusPrinter statusPrinter = StatusPrinter
				.getStatusPrinter(this.outputfile.getAbsolutePath() + System.getProperty("file.separator") + "output.txt");
		ProductSourceComparer comparer = new ProductSourceComparer(
				this.productFile.getAbsolutePath(),
				this.sourceFile.getAbsolutePath(),
				this.mapFile.getAbsolutePath(), statusPrinter);
		
		Boolean results = comparer.runCompare();		

		statusBar.setText("Current Status: Done. All matches? " + results);
	}

}
