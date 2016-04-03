package com.product.comparison.compare;

import static org.junit.Assert.assertEquals;

import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import com.product.comparison.load.DataHandler;

public class SourceColumnNameLoaderTest {

	@Test
	public void getSourceColumnName_levelColumn_FundMergAwayDate()
			throws Exception {
		String productFile = this.getClass()
				.getResource("/InputFiles/product.csv").getPath();

		DataHandler productData = new DataHandler(productFile);
		CSVRecord productRecord = productData.getRecords().get(0);

		String columnString = "Vehicle Status|Merged Away=Fund Merge Away Date,Liquidated Date=Fund Liquidation Date";

		String actual = SourceColumnNameLoader.getSourceColumnName(
				productRecord, columnString);

		String expected = "Fund Merge Away Date";

		assertEquals(expected, actual);
	}

	@Test
	public void getSourceColumnName_noLevelColumn_VehicleString()
			throws Exception {
		String productFile = this.getClass()
				.getResource("/InputFiles/product.csv").getPath();

		DataHandler productData = new DataHandler(productFile);
		CSVRecord productRecord = productData.getRecords().get(0);

		String columnString = "Vehicle Status";

		String actual = SourceColumnNameLoader.getSourceColumnName(
				productRecord, columnString);

		String expected = "Vehicle Status";

		assertEquals(expected, actual);
	}

	@Test(expected = Exception.class)
	public void getSourceColumnName_invalidColumnString_Exception()
			throws Exception {
		String productFile = this.getClass()
				.getResource("/InputFiles/product.csv").getPath();

		DataHandler productData = new DataHandler(productFile);
		CSVRecord productRecord = productData.getRecords().get(0);

		String columnString = "Vehicle Status|Merged Away<Fund Merge Away Date,Liquidated Date<Fund Liquidation Date";

		SourceColumnNameLoader.getSourceColumnName(productRecord, columnString);

	}
	
	
	@Test
	public void getSourceColumnName_SingleLevelColumnString_FundMergAwayDate()
			throws Exception {
		String productFile = this.getClass()
				.getResource("/InputFiles/product.csv").getPath();

		DataHandler productData = new DataHandler(productFile);
		CSVRecord productRecord = productData.getRecords().get(0);

		String columnString = "Vehicle Status|Merged Away=Fund Merge Away Date";

		String actual = SourceColumnNameLoader.getSourceColumnName(
				productRecord, columnString);

		String expected = "Fund Merge Away Date";

		assertEquals(expected, actual);
	}
}
