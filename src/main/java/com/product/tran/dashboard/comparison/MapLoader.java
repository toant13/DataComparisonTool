package com.product.tran.dashboard.comparison;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MapLoader {

	private final static String DELIMITER = ":";
	private final static int FILE1_INPUT_INDEX = 0;
	private final static int FILE2_INPUT_INDEX = 1;
	private final static int COMPARE_TYPE_INDEX = 2;

	private Map<String, String> columnMapper;
	private Map<String, String> typeMapper;

	public MapLoader() throws Exception {
		columnMapper = new HashMap<String, String>();
		typeMapper = new HashMap<String, String>();
	}

	public void load(String fileName) throws Exception {
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach((row) -> {
				mapColumn(row);
				mapColumnToCompareType(row);
			});

		} catch (IOException e) {
			throw new Exception(e);
		}
	}

	public Map<String, String> getColumnMapper() {
		return columnMapper;
	}

	public void setColumnMapper(Map<String, String> columnMapper) {
		this.columnMapper = columnMapper;
	}

	public Map<String, String> getTypeMapper() {
		return typeMapper;
	}

	public void setTypeMapper(Map<String, String> typeMapper) {
		this.typeMapper = typeMapper;
	}

	private void mapColumn(String row) {
		String[] inputs = row.split(DELIMITER);
		columnMapper.put(inputs[FILE1_INPUT_INDEX], inputs[FILE2_INPUT_INDEX]);
	}

	private void mapColumnToCompareType(String row) {
		String[] inputs = row.split(DELIMITER);
		typeMapper.put(inputs[FILE1_INPUT_INDEX], inputs[COMPARE_TYPE_INDEX]);

		String[] fileTwoColumns = inputs[FILE2_INPUT_INDEX].split(",");
		for (String fileTwoColumn : fileTwoColumns) {
			typeMapper.put(fileTwoColumn, inputs[COMPARE_TYPE_INDEX]);
		}

	}
}
