package com.product.tran.common.load;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import com.product.tran.common.load.DataLoad;

public class DataLoadTest {

	@Test
	public void load_validCSV_populatedMap() throws IOException {
		DataLoad dataLoad = new DataLoad();
		URL url = this.getClass().getResource("/InputFiles/product.csv");
		String strFile = url.getPath();
		
		dataLoad.load(strFile);
		assertTrue("Map was not loaded as expected",dataLoad.getColumnValuesMap().toString().equals("{product_longname=[name1, name2], product_somenumber=[183.84, 9837.87], product_shortname=[nm1, nm2]}"));
	}

}
