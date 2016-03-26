package com.product.tran.dashboard.comparison;

import java.util.List;
import java.util.Map;

/**
 * @author toantran
 *
 */
public class ProductComparer {

	private MapLoader mapLoader;
	private DataLoader productLoader;
	private DataLoader morningStarLoader;

	private StatusPrinter statusPrinter;

	private static final String LEVEL_DELIMITER = ",";

	public ProductComparer(String productFile, String morningStarFile,
			String mapFile) throws Exception {
		this.mapLoader = new MapLoader();
		mapLoader.load(mapFile);

		this.productLoader = new DataLoader();
		productLoader.load(productFile);

		this.morningStarLoader = new DataLoader();
		morningStarLoader.load(morningStarFile);

		statusPrinter = StatusPrinter.getStatusPrinter();
	}

	/**
	 * Calls call compare functions necessary to compare product data again morningstar's
	 * 
	 * @throws Exception
	 */
	public void runCompare() throws Exception {
		Map<String, String> columnMapper = mapLoader.getColumnMapper();

		for (Map.Entry<String, String> column : columnMapper.entrySet()) {
			// print out headers
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

	/**
	 * Compares all mapped data between product and morning star
	 * 
	 * @param productColumnName
	 * @param morningStarColumns
	 * @return
	 */
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

	/**
	 * check all attribute of column against morningstar's equivalent column data
	 * 
	 * @param productColumnName
	 * @param productValue
	 * @param morningStarColumns
	 * @param startIndex
	 * @return
	 */
	private boolean checkMorningStar(String productColumnName,
			String productValue, String[] morningStarColumns, int startIndex) {
		if ((morningStarColumns.length - 1) < startIndex) {
			return false;
		}

		if (startIndex > 0) {
			statusPrinter.printStatus("testing second level for "
					+ productColumnName + ": " + productValue + " with: "
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

	/**
	 * Checks if morningstar data has the product column value and whether it
	 * matches based on comparer criteria
	 * 
	 * @param productColumnName
	 * @param productValue
	 * @param morningstarValues
	 * @param startIndex
	 * @return
	 */
	private boolean contains(String productColumnName, String productValue,
			List<String> morningstarValues, int startIndex) {

		Comparer comparer = getComparer(productColumnName);

		boolean areMatched = morningstarValues.stream().anyMatch(
				(morningstarValue) -> (comparer.checkData(productValue,
						morningstarValue)));
		return areMatched;
	}

	/**
	 * Returns type of comparer according to column given
	 * 
	 * @param productColumnName
	 * @return
	 */
	private Comparer getComparer(String productColumnName) {
		Map<String, String> typeMapper = mapLoader.getTypeMapper();
		String type = typeMapper.get(productColumnName);
		return ComparerFactory.getComparer(type);
	}

}
