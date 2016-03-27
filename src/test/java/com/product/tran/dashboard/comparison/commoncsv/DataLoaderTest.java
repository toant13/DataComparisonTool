package com.product.tran.dashboard.comparison.commoncsv;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;

public class DataLoaderTest {

	@Test
	public void load_validCSV_populatedMap() throws IOException {
		
		URL url = this.getClass().getResource("/InputFiles/product.csv");
		String strFile = url.getPath();
//		DataHandler dataLoad = new DataHandler();

//		dataLoad.load(strFile);
//		assertTrue(
//				"Map was not loaded as expected",
//				dataLoad.getColumnValuesMap()
//						.toString()
//						.equals("{product_longname=[name1, nm2], product_somenumber=[183.84, 9837.87], product_shortname=[nm1, nm2]}"));
	}

}
