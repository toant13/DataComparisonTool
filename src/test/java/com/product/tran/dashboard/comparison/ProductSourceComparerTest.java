/**
 * 
 */
package com.product.tran.dashboard.comparison;

import static org.junit.Assert.*;

import org.junit.Test;

import com.product.tran.dashboard.comparison.ProductSourceComparer;

/**
 * @author toantran
 *
 */
public class ProductSourceComparerTest {

	@Test
	public void test() throws Exception {
		String productFile = this.getClass().getResource("/InputFiles/product.csv").getPath();
		String morningStarFile = this.getClass().getResource("/InputFiles/morningstar.csv").getPath();
		String mapFile = this.getClass().getResource("/map/map.txt").getPath();
		
		ProductSourceComparer comparer = new ProductSourceComparer(productFile, morningStarFile, mapFile);
		comparer.runCompare();
	}

}
