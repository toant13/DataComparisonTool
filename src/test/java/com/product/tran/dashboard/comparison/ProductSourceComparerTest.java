package com.product.tran.dashboard.comparison;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductSourceComparerTest {

	@Test
	public void productSourceComparer_ValidFiles_True() {
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
}
