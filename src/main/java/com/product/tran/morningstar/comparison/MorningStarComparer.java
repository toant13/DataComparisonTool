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
	
	private StatusPrinter statusPrinter;

	private static final String LEVEL_DELIMITER = ",";

	public MorningStarComparer(String productFile, String morningStarFile,
			String mapFile) throws Exception {
		this.mapLoader = new MapLoader();
		mapLoader.load(mapFile);

		this.productLoader = new DataLoader();
		productLoader.load(productFile);

		this.morningStarLoader = new DataLoader();
		morningStarLoader.load(morningStarFile);
		
		statusPrinter = StatusPrinter.getStatusPrinter();
	}

	public void runCompare() throws Exception {
		Map<String, String> columnMapper = mapLoader.getColumnMapper();

		for (Map.Entry<String, String> column : columnMapper.entrySet()) {	
//			print out headers
			if (compareAttributes(column.getKey(),
					column.getValue().split(LEVEL_DELIMITER))) {
				statusPrinter.printStatus("way to go idaho. All match for: "
						+ column.getKey());
			} else {
				statusPrinter.printStatus("column not found, you fucked up: "
						+ column.getKey() + " and " + column.getValue());
			}
		}
	}

	private boolean compareAttributes(String productColumnName,
			String[] morningStarColumns) {
		Map<String, List<String>> productValuesMap = productLoader
				.getColumnValuesMap();

		List<String> productValues = productValuesMap.get(productColumnName);

		for (String productValue : productValues) {
			if (!checkMorningStar(productColumnName, productValue,
					morningStarColumns, 0)) {
				statusPrinter.printStatus("not found: " + productValue);
			} else {
				statusPrinter.printStatus("found: " + productValue);
			}
		}
		return true;
	}

	private boolean checkMorningStar(String productColumnName,
			String productValue, String[] morningStarColumns, int startIndex) {
		if ((morningStarColumns.length - 1) < startIndex) {
			return false;
		}

		if (startIndex > 0) {
			statusPrinter.printStatus("testing second level for " + productColumnName
					+ ": " + productValue + " with: "
					+ morningStarColumns[startIndex]);
		}

		Map<String, List<String>> morningStarValuesMap = morningStarLoader
				.getColumnValuesMap();
		List<String> morningstarValues = morningStarValuesMap
				.get(morningStarColumns[startIndex]);
		if (!contains(productColumnName, productValue, morningstarValues,
				startIndex)) {
			return checkMorningStar(productColumnName, productValue,
					morningStarColumns, startIndex + 1);
		}
		return true;
	}

	private boolean contains(String productColumnName, String productValue,
			List<String> morningstarValues, int startIndex) {

		Comparer comparer = getComparer(productColumnName);

		boolean areMatched = morningstarValues.stream().anyMatch(
				(morningstarValue) -> (comparer.checkData(productValue,
						morningstarValue)));
		return areMatched;
	}

	private Comparer getComparer(String productColumnName) {
		Map<String, String> typeMapper = mapLoader.getTypeMapper();
		String type = typeMapper.get(productColumnName);
		return ComparerFactory.getComparer(type);
	}

}
