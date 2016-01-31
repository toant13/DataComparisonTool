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

		for (Map.Entry<String, String> column : columnMapper.entrySet()) {
			if (!compareAttributes(column.getKey(),
					column.getValue().split(LEVEL_DELIMITER), 0)) {
			} else {

				System.out.println();
			}
		}
	}

	private boolean compareAttributes(String productColumnName,
			String[] morningStarColumns, int startIndex) {
		if ((morningStarColumns.length - 1) < startIndex) {
			return false;
		}

		Map<String, List<String>> productValuesMap = productLoader
				.getColumnValuesMap();
		Map<String, List<String>> morningStarValuesMap = morningStarLoader
				.getColumnValuesMap();

		List<String> productValues = productValuesMap.get(productColumnName);
		List<String> morningstarValues = morningStarValuesMap
				.get(morningStarColumns[startIndex]);
		for (String productValue : productValues) {
			if (!contains(productColumnName, productValue, morningstarValues)) {
				return compareAttributes(productColumnName, morningStarColumns,
						startIndex + 1);
			}
		}
		return true;
	}

	private boolean contains(String productColumnName, String productValue,
			List<String> morningstarValues) {

		/**** initial testing ****/
		Comparer comparer = getComparer(productColumnName);
		

		boolean areMatched = morningstarValues.stream().anyMatch(
				(p) -> (comparer.checkData(productValue,p)));

		System.out.println(areMatched);
		
		return areMatched;
	}
	
	
	/**** initial testing. turn into factory****/
	private Comparer getComparer(String productColumnName){
		Map<String, String> typeMapper = mapLoader.getTypeMapper();
		String type = typeMapper.get(productColumnName);
		Comparer comparer = null;
		switch (type) {
		case "ALPHA":
			System.out.println("alpha");
			comparer = new AlphaComparer(100);
			break;
		case "NUMERIC":
			System.out.println("numeric");
			comparer = new NumericComparer(1);
			break;
		}
		
		return comparer;
	}

}
