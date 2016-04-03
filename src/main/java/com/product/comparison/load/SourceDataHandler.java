package com.product.comparison.load;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.csv.CSVRecord;

public class SourceDataHandler extends DataHandler {

	public SourceDataHandler(String strFile) throws FileNotFoundException,
			IOException {
		super(strFile);
	}

	public CSVRecord getRow(String columnName, String value) throws Exception {
		CSVRecord result = null;
		for (CSVRecord record : super.records) {
			if (record.get(columnName).equals(value)) {
				result = record;
				break; //break from look cause record found
			}
		}
		// Collections.sort(list, (p1, p2)
		// ->p1.get("product_shortname").compareTo(p2.get("product_shortname")));
		// for more efficient algorithm nlogn instead of n^2
		if (result == null) {
			throw new Exception("No record found for attribute: " + columnName
					+ " and value: " + value);
		} else {
			return result;
		}
	}

}
