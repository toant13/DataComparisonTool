package com.product.tran.dashboard.comparison.commoncsv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class DataHandler {

	private final static char DELIMITER = ',';
	protected List<CSVRecord> records;
	protected Map<String, Integer> headerMap;

	public DataHandler(String strFile) throws FileNotFoundException,
			IOException {
		CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(DELIMITER);
		CSVParser parser = new CSVParser(new FileReader(strFile), format);

		this.headerMap = parser.getHeaderMap();
		this.records = parser.getRecords();

		parser.close();
	}

}
