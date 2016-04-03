package com.product.comparison.compare;

import static org.junit.Assert.*;

import org.junit.Test;

import com.product.comparison.compare.ProductSourceComparer;

public class ProductSourceComparerTest {

	@Test
	public void productSourceComparer_ValidFilesNoLevelMap_True() {
		String productFile = this.getClass()
				.getResource("/InputFiles/product.csv").getPath();
		String sourceFile = this.getClass()
				.getResource("/InputFiles/source.csv").getPath();
		String mapFile = this.getClass().getResource("/map/keymap.txt")
				.getPath();
		try {
			ProductSourceComparer comparer = new ProductSourceComparer(
					productFile, sourceFile, mapFile);

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
		try {
			ProductSourceComparer comparer = new ProductSourceComparer(
					productFile, sourceFile, mapFile);

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
		try {
			ProductSourceComparer comparer = new ProductSourceComparer(
					productFile, sourceFile, mapFile);

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
		try {
			ProductSourceComparer comparer = new ProductSourceComparer(
					productFile, sourceFile, mapFile);

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
		try {
			ProductSourceComparer comparer = new ProductSourceComparer(
					productFile, sourceFile, mapFile);

			boolean result = comparer.runCompare();
			assertTrue(result);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
