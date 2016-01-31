package com.product.tran.morningstar.comparison;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author toantran
 *
 */
public class MorningStarComparer {

	private MapLoader mapLoader;
	private DataLoader productLoader;
	private DataLoader morningStarLoader;

	private static final String LEVEL_DELIMITER = ",";

	public MorningStarComparer(String productFile, String morningStarFile,
			String mapFile) throws Exception {
		this.mapLoader = new MapLoader();
		mapLoader.load(mapFile);

		this.productLoader = new DataLoader();
		productLoader.load(productFile);

		this.morningStarLoader = new DataLoader();
		morningStarLoader.load(morningStarFile);
	}

	public void runCompare() throws Exception {
		Map<String, String> columnMapper = mapLoader.getColumnMapper();
		Map<String, String> typeMapper = mapLoader.getTypeMapper();

		for (Map.Entry<String, String> column : columnMapper.entrySet()) {
			if (!compareAttributes(column.getKey(),
					column.getValue().split(LEVEL_DELIMITER), 0)) {

			}
		}
	}

	private boolean compareAttributes(String column1, String[] columns2,
			int startIndex) {
		if ((columns2.length - 1) > startIndex) {
			return false;
		}

		Map<String, List<String>> productValuesMap = productLoader
				.getColumnValuesMap();
		Map<String, List<String>> morningStarValuesMap = morningStarLoader
				.getColumnValuesMap();
		

		List<String> productRows = productValuesMap.get(column1);
		List<String> morningStarRows = morningStarValuesMap
				.get(columns2[startIndex]);
		for (String productRow : productRows) {
			if (!morningStarRows.contains(productRow)) {
				return compareAttributes(column1, columns2, startIndex + 1);
			}
		}
		return true;
	}

	private boolean contains(String productValue, List<String> morningstarValues) {
		Map<String, String> typeMapper = mapLoader.getTypeMapper();
		
		String clazz = typeMapper.get(productValue);

		
		boolean t = morningstarValues.stream().anyMatch((p) -> (p.equals(productValue)));
		
		return false;
	}

}
