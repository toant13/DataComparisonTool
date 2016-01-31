/**
 * 
 */
package com.product.tran.morningstar.compare;

import static org.junit.Assert.*;

import org.junit.Test;

import com.product.tran.morningstar.compare.MorningStarComparer;

/**
 * @author toantran
 *
 */
public class MorningStarComparerTest {

	@Test
	public void test() throws Exception {
		String productFile = this.getClass().getResource("/InputFiles/product.csv").getPath();
		String morningStarFile = this.getClass().getResource("/InputFiles/morningstar.csv").getPath();
		String mapFile = this.getClass().getResource("/map/map.txt").getPath();
		
		MorningStarComparer comparer = new MorningStarComparer(productFile, morningStarFile, mapFile);
		comparer.runCompare();
	}

}
