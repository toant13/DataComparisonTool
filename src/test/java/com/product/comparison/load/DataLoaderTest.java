package com.product.comparison.load;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import com.product.comparison.load.DataHandler;

public class DataLoaderTest {

	@Test
	public void load_ValidCSV_populatedMap() throws IOException {
		URL url = this.getClass().getResource("/InputFiles/product.csv");
		String strFile = url.getPath();
		DataHandler dataLoad = new DataHandler(strFile);

		String expectedHeaders = "{SPN ID=0, Vehicle Name=1, Vehicle Status=2, Primary Prospectus Benchmark=3, Incep. Date=4, Morningstar Rating=5, CUSIP=6, SI Return=7, Closed Date=8}";
		assertEquals(expectedHeaders, dataLoad.getHeaderMap().toString());

		String expectedRecords = "[CSVRecord [comment=null, mapping={SPN ID=0, Vehicle Name=1, Vehicle Status=2, Primary Prospectus Benchmark=3, Incep. Date=4, Morningstar Rating=5, CUSIP=6, SI Return=7, Closed Date=8}, recordNumber=1, values=[123, vehicle1, Merged Away, benchmarkZ, 121303, ratingR, 1qaz, 123.01, 101112]], CSVRecord [comment=null, mapping={SPN ID=0, Vehicle Name=1, Vehicle Status=2, Primary Prospectus Benchmark=3, Incep. Date=4, Morningstar Rating=5, CUSIP=6, SI Return=7, Closed Date=8}, recordNumber=2, values=[456, vehicle2, Liquidated Date, benchmarkY, 121301, ratingPG, 2wsx, 123.01, 101314]], CSVRecord [comment=null, mapping={SPN ID=0, Vehicle Name=1, Vehicle Status=2, Primary Prospectus Benchmark=3, Incep. Date=4, Morningstar Rating=5, CUSIP=6, SI Return=7, Closed Date=8}, recordNumber=3, values=[789, vehicle3, Liquidated Date, benchmarkX, 121302, ratingX, 3edc, 123.01, 101516]], CSVRecord [comment=null, mapping={SPN ID=0, Vehicle Name=1, Vehicle Status=2, Primary Prospectus Benchmark=3, Incep. Date=4, Morningstar Rating=5, CUSIP=6, SI Return=7, Closed Date=8}, recordNumber=4, values=[987, vehicle4, Merged Away, benchmarkW, 121399, ratingG, 4rfv, 123.01, 111617]]]";
		assertEquals(expectedRecords, dataLoad.getRecords().toString());
	}

	@Test(expected = FileNotFoundException.class)
	public void load_NullFileLocation_FileNotFoundException()
			throws IOException {
		new DataHandler("doesntexist");
	}

}
