package com.product.tran.morningstar.compare;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;

public class DataLoader {

	private Map<String, List<String>> columnValuesMap;

	public Map<String, List<String>> getColumnValuesMap() {
		return columnValuesMap;
	}

	public void setColumnValuesMap(Map<String, List<String>> columnValuesMap) {
		this.columnValuesMap = columnValuesMap;
	}

	public DataLoader() {
		columnValuesMap = new HashMap<String, List<String>>();
	}

	public void load(String strFile) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(strFile));

		int lineNumber = 0;

		String[] headerArray = reader.readNext();

		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			lineNumber++;
			loadMap(headerArray, nextLine);
		}
		reader.close();
	}

	private void loadMap(String[] header, String[] values) {
		for (int i = 0; i < values.length; i++) {
			if (columnValuesMap.containsKey(header[i])) {
				List<String> valuesList = columnValuesMap.get(header[i]);
				valuesList.add(values[i]);
			} else {
				List<String> valuesList = new ArrayList<String>();
				valuesList.add(values[i]);
				columnValuesMap.put(header[i], valuesList);
			}
		}
	}

}
