package com.product.tran.morningstar.comparison;

import java.util.List;
import java.util.Map;

/**
 * @author toantran
 *
 */
public class MorningStarComparer {

	private MapLoader mapLoader;
	private DataLoader productLoader;
	private DataLoader morningStarLoader;

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
			compareAttributes(column.getKey(), column.getValue().split(","));
		}
	}

	public boolean compareAttributes(String column1, String[] columns2) {
		Map<String, List<String>> productValuesMap = productLoader
				.getColumnValuesMap();
		Map<String, List<String>> morningStarValuesMap = morningStarLoader
				.getColumnValuesMap();

		List<String> productRows = productValuesMap.get(column1);
		List<String> morningStarRows = morningStarValuesMap.get(columns2[0]);
		for (String productRow : productRows) {
			if (!morningStarRows.contains(productRow)) {
			}
		}
		
		return true;
	}

}
