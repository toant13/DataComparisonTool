package com.product.comparison.load;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 
 * Loads column maps
 * 
 * @author toantran
 *
 */
public class MapLoader {

	private final static String DELIMITER = ":";
	private final static String MAPCOLUMNPSPLIT = "~";

	private Map<String, List<String>> KeyToColumnsMapper;
	private Map<String, String> columnsToTypeMapper;

	public MapLoader() {
		this.KeyToColumnsMapper = new HashMap<String, List<String>>();
		this.columnsToTypeMapper = new HashMap<String, String>();
	}

	/**
	 * loads data in map file
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public void load(String fileName) throws Exception {
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach((row) -> {
				try {
					mapKeyToColumns(row);
					mapColumnsToType(row);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

			});
		} catch (IOException e) {
			throw new Exception(e);
		}
	}

	/**
	 * maps keys to columns for comparisons
	 * 
	 * @param row
	 */
	private void mapKeyToColumns(String row) {
		String[] inputs = row.split(DELIMITER);
		String key = inputs[MapPosition.KEY_INDEX.getPosition()];
		if (this.KeyToColumnsMapper.containsKey(key)) {
			List<String> columnlist = this.KeyToColumnsMapper.get(key);
			String columns = inputs[MapPosition.COLUMNS_INDEX.getPosition()];
			columnlist.add(columns);
		} else {
			List<String> columnlist = new ArrayList<String>();
			String columns = inputs[MapPosition.COLUMNS_INDEX.getPosition()];
			columnlist.add(columns);
			this.KeyToColumnsMapper.put(key, columnlist);
		}
	}

	/**
	 * maps columns to comparison type
	 * 
	 * @param row
	 * @throws Exception
	 */
	private void mapColumnsToType(String row) throws Exception {
		String[] inputs = row.split(DELIMITER);
		String columns = inputs[MapPosition.COLUMNS_INDEX.getPosition()];

		if (this.columnsToTypeMapper.containsKey(columns)) {
			throw new Exception(
					"Can't be more than one comparison type for the same column");
		} else {
			String type = inputs[MapPosition.COMPARE_TYPE_INDEX.getPosition()];
			this.columnsToTypeMapper.put(columns, type);
		}
	}

	/**
	 * Return Product or Source data column name from key given
	 * productcolumn~sourcecolumn splits to productcolumn and sourcecolumn
	 * 
	 * @param columnKey
	 * @param productOrSource
	 * @return
	 */
	public static String getColumnName(String columnKey, String productOrSource) {
		String[] column = columnKey.split(MAPCOLUMNPSPLIT);
		String columnName = null;
		switch (productOrSource.toUpperCase()) {
		case "PRODUCT":
			columnName = column[0];
			break;
		case "SOURCE":
			columnName = column[1];
			break;
		}
		return columnName;
	}

	public Map<String, List<String>> getKeyToColumnsMapper() {
		return KeyToColumnsMapper;
	}

	public Map<String, String> getColumnsToTypeMapper() {
		return columnsToTypeMapper;
	}
}
