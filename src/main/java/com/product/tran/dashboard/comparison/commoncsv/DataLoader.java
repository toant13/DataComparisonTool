package com.product.tran.dashboard.comparison.commoncsv;

import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class DataLoader {
	
	
	
	public void load(String strFile) throws IOException {
		CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
		CSVParser records = new CSVParser(new FileReader(strFile), format);
		
		System.out.println(records.getHeaderMap());
//		System.out.println(records.getRecords().size());
		
		
		for (CSVRecord record : records) {
			System.out.println(record.get("product_somenumber"));
			System.out.println(record.get("product_longname"));
			System.out.println(record.get("product_shortname"));
		}
		
		records.close();
	}
}
