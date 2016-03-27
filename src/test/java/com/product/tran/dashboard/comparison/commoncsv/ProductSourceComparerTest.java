package com.product.tran.dashboard.comparison.commoncsv;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductSourceComparerTest {

	@Test
	public void productSourceComparer_ValidFiles_NewInstance() {
		String productFile = this.getClass()
				.getResource("/InputFiles/product.csv").getPath();
		String sourceFile = this.getClass()
				.getResource("/InputFiles/source.csv").getPath();
		String mapFile = this.getClass().getResource("/map/keymap.txt")
				.getPath();

		try {
			ProductSourceComparer comparer = new ProductSourceComparer(
					productFile, sourceFile, mapFile);

			comparer.runCompare();

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
