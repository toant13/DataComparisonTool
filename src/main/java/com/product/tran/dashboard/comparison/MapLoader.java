package com.product.tran.dashboard.comparison;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MapLoader {

	private final static String DELIMITER = ":";
	private final static String MAPCOLUMNPSPLIT = "~";

	private Map<String, List<String>> KeyToColumnsMapper;
	private Map<String, String> columnsToTypeMapper;

	public MapLoader() {
		this.KeyToColumnsMapper = new HashMap<String, List<String>>();
		this.columnsToTypeMapper = new HashMap<String, String>();
	}

	public void load(String fileName) throws Exception {
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach((row) -> {
				mapKeyToColumns(row);
				try {
					mapColumnsToType(row);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

			});
		} catch (IOException e) {
			throw new Exception(e);
		}
	}

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
