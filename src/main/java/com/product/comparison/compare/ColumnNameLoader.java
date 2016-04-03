package com.product.comparison.compare;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;

public class ColumnNameLoader {

	private static final String SPLIT = "|";
	private static final String NONMETASPLIT = "\\" + SPLIT;
	private static final String LEVELSEPARATOR = ",";
	private static final String LEVELMAPVALUEDELIMITER = "=";

	private static final int PRODUCTCOLUMNNAMEINDEX = 0;
	private static final int SOURCEMAPSTRINGINDEX = 1;
	private static final int PRODUCTNAMEMAPINDEX = 0;
	private static final int SOURCENAMEMAPINDEX = 1;

	public static String getSourceColumnName(CSVRecord productRecord, String columnString) {
		if (columnString.contains(SPLIT)) {
			String[] mapString = columnString.split(NONMETASPLIT);
			String productColumnNameForMap = mapString[PRODUCTCOLUMNNAMEINDEX];

			String productMapValue = productRecord.get(productColumnNameForMap);

			return getSourceColumnName(productMapValue,
					getLevelMap(mapString[SOURCEMAPSTRINGINDEX]));

		} else {
			return columnString;
		}

	}

	private static Map<String, String> getLevelMap(String columnMapString) {
		Map<String, String> map = new HashMap<String, String>();
		String[] levelColumns = columnMapString.split(LEVELSEPARATOR);

		for (String productToSourceString : levelColumns) {
			String[] productToSourceArray = productToSourceString
					.split(LEVELMAPVALUEDELIMITER);

			map.put(productToSourceArray[PRODUCTNAMEMAPINDEX],
					productToSourceArray[SOURCENAMEMAPINDEX]);
		}
		return map;
	}

	private static String getSourceColumnName(String productMapValue,
			Map<String, String> productToSourceLevelMap) {
		return productToSourceLevelMap.get(productMapValue);
	}
}
