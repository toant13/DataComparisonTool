package com.product.comparison.load;

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

	/**
	 * Loads csv file to maps
	 * 
	 * @param strFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public DataHandler(String strFile) throws FileNotFoundException,
			IOException {
		CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(
				DELIMITER);
		CSVParser parser = new CSVParser(new FileReader(strFile), format);

		this.headerMap = parser.getHeaderMap();
		this.records = parser.getRecords();

		parser.close();
	}
		

	public List<CSVRecord> getRecords() {
		return records;
	}

	public Map<String, Integer> getHeaderMap() {
		return headerMap;
	}

}
