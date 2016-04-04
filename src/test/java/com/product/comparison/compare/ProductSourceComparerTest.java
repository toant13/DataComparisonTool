package com.product.comparison.compare;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import com.product.comparison.StatusPrinter;

public class ProductSourceComparerTest {

	private void cleanup(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	public void productSourceComparer_ValidFilesNoLevelMap_True() {

		String productFile = this.getClass()
				.getResource("/InputFiles/product.csv").getPath();
		String sourceFile = this.getClass()
				.getResource("/InputFiles/source.csv").getPath();
		String mapFile = this.getClass().getResource("/map/keymap.txt")
				.getPath();

		String fileName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String outputPath = this.getClass().getResource("/output").getPath()
				+ System.getProperty("file.separator") + fileName + ".txt";
		cleanup(outputPath);
		StatusPrinter statusPrinter = StatusPrinter
				.getStatusPrinter(outputPath);

		try {
			ProductSourceComparer comparer = new ProductSourceComparer(
					productFile, sourceFile, mapFile, statusPrinter);

			boolean result = comparer.runCompare();

			assertTrue(result);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void productSourceComparer_InvalidProduct_False() {
		String productFile = this.getClass()
				.getResource("/InputFiles/invalidproduct.csv").getPath();
		String sourceFile = this.getClass()
				.getResource("/InputFiles/source.csv").getPath();
		String mapFile = this.getClass().getResource("/map/keymap.txt")
				.getPath();
		String fileName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String outputPath = this.getClass().getResource("/output").getPath()
				+ System.getProperty("file.separator") + fileName + ".txt";
		cleanup(outputPath);
		StatusPrinter statusPrinter = StatusPrinter
				.getStatusPrinter(outputPath);
		try {
			ProductSourceComparer comparer = new ProductSourceComparer(
					productFile, sourceFile, mapFile, statusPrinter);

			boolean result = comparer.runCompare();

			assertFalse(result);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void productSourceComparer_InvalidSource_False() {
		String productFile = this.getClass()
				.getResource("/InputFiles/product.csv").getPath();
		String sourceFile = this.getClass()
				.getResource("/InputFiles/invalidsource.csv").getPath();
		String mapFile = this.getClass().getResource("/map/keymap.txt")
				.getPath();
		String fileName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String outputPath = this.getClass().getResource("/output").getPath()
				+ System.getProperty("file.separator") + fileName + ".txt";
		cleanup(outputPath);
		StatusPrinter statusPrinter = StatusPrinter
				.getStatusPrinter(outputPath);
		try {
			ProductSourceComparer comparer = new ProductSourceComparer(
					productFile, sourceFile, mapFile, statusPrinter);

			boolean result = comparer.runCompare();

			assertFalse(result);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void productSourceComparer_ValidFilesMultilevelOnlyMap_True() {
		String productFile = this.getClass()
				.getResource("/InputFiles/levelproduct.csv").getPath();
		String sourceFile = this.getClass()
				.getResource("/InputFiles/levelsource.csv").getPath();
		String mapFile = this.getClass().getResource("/map/levelmap.txt")
				.getPath();
		String fileName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String outputPath = this.getClass().getResource("/output").getPath()
				+ System.getProperty("file.separator") + fileName + ".txt";
		cleanup(outputPath);
		StatusPrinter statusPrinter = StatusPrinter
				.getStatusPrinter(outputPath);
		try {
			ProductSourceComparer comparer = new ProductSourceComparer(
					productFile, sourceFile, mapFile, statusPrinter);

			boolean result = comparer.runCompare();
			assertTrue(result);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void productSourceComparer_ValidFilesAndFullMap_True() {
		String productFile = this.getClass()
				.getResource("/InputFiles/levelproduct.csv").getPath();
		String sourceFile = this.getClass()
				.getResource("/InputFiles/levelsource.csv").getPath();
		String mapFile = this.getClass().getResource("/map/fullmap.txt")
				.getPath();
		String fileName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String outputPath = this.getClass().getResource("/output").getPath()
				+ System.getProperty("file.separator") + fileName + ".txt";
		cleanup(outputPath);
		StatusPrinter statusPrinter = StatusPrinter
				.getStatusPrinter(outputPath);
		try {
			ProductSourceComparer comparer = new ProductSourceComparer(
					productFile, sourceFile, mapFile, statusPrinter);

			boolean result = comparer.runCompare();
			assertTrue(result);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
