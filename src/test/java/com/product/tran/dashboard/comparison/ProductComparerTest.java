/**
 * 
 */
package com.product.tran.dashboard.comparison;

import static org.junit.Assert.*;

import org.junit.Test;

import com.product.tran.dashboard.comparison.ProductComparer;

/**
 * @author toantran
 *
 */
public class ProductComparerTest {

	@Test
	public void test() throws Exception {
		String productFile = this.getClass().getResource("/InputFiles/product.csv").getPath();
		String morningStarFile = this.getClass().getResource("/InputFiles/morningstar.csv").getPath();
		String mapFile = this.getClass().getResource("/map/map.txt").getPath();
		
		ProductComparer comparer = new ProductComparer(productFile, morningStarFile, mapFile);
		comparer.runCompare();
	}

}
