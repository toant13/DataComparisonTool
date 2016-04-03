package com.product.comparison.compare;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;

/**
 * 
 * Class responsible for getting source column name required for comparison
 * 
 * @author toantran
 *
 */
public class SourceColumnNameLoader {

	private static final String SPLIT = "|";
	private static final String NONMETASPLIT = "\\" + SPLIT;
	private static final String LEVELSEPARATOR = ",";
	private static final String LEVELMAPVALUEDELIMITER = "=";

	private static final int PRODUCTCOLUMNNAMEINDEX = 0;
	private static final int SOURCEMAPSTRINGINDEX = 1;
	private static final int PRODUCTNAMEMAPINDEX = 0;
	private static final int SOURCENAMEMAPINDEX = 1;

	/**
	 * Gets source column name according to column string given. If there are
	 * delimiters in string, that means there are levels of logic as to which
	 * source column to do comparison on
	 * 
	 * 
	 * @param productRecord
	 * @param columnString
	 * @return
	 * @throws Exception 
	 */
	public static String getSourceColumnName(CSVRecord productRecord,
			String columnString) throws Exception {
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

	/**
	 * Parses string and puts it in map
	 * 
	 * @param columnMapString
	 * @return
	 * @throws Exception 
	 */
	private static Map<String, String> getLevelMap(String columnMapString) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String[] levelColumns = columnMapString.split(LEVELSEPARATOR);

		for (String productToSourceString : levelColumns) {
			String[] productToSourceArray = productToSourceString
					.split(LEVELMAPVALUEDELIMITER);
			
			if (productToSourceArray.length != 2){
				throw new Exception("Column map string is in invalid format");
			}

			map.put(productToSourceArray[PRODUCTNAMEMAPINDEX],
					productToSourceArray[SOURCENAMEMAPINDEX]);
		}
		return map;
	}

	/**
	 * Get name of source column from map using value from product map column as
	 * key
	 * 
	 * @param productMapValue
	 * @param productToSourceLevelMap
	 * @return
	 */
	private static String getSourceColumnName(String productMapValue,
			Map<String, String> productToSourceLevelMap) {
		return productToSourceLevelMap.get(productMapValue);
	}
}
