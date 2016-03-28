package com.product.tran.dashboard.comparison;

import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;

/**
 * Class to do comparison of product data against source data using the loaded map
 * 
 * 
 * @author toantran
 *
 */
public class ProductSourceComparer {

	private MapLoader mapLoader;
	private DataHandler productData;
	private SourceDataHandler sourceData;

	private StatusPrinter statusPrinter;

	public ProductSourceComparer(String productFile, String sourceFile,
			String mapFile) throws Exception {
		this.mapLoader = new MapLoader();
		this.mapLoader.load(mapFile);

		this.productData = new DataHandler(productFile);
		this.sourceData = new SourceDataHandler(sourceFile);

		this.statusPrinter = StatusPrinter.getStatusPrinter();
	}

	/**
	 * Runs data comparison between product data and source data
	 * @return
	 * @throws Exception
	 */
	public boolean runCompare() throws Exception {
		boolean overallResults = true;
		for (Map.Entry<String, List<String>> key : this.mapLoader
				.getKeyToColumnsMapper().entrySet()) {

			boolean result = compareProductRows(key.getKey(), key.getValue());
			if (!result) {
				overallResults = result;
			}
		}
		return overallResults;
	}

	/**
	 * Comparison of all product rows against corresponding source row matching on key
	 * 
	 * @param key
	 * @param attributeList
	 * @return
	 * @throws Exception
	 */
	private boolean compareProductRows(String key, List<String> attributeList)
			throws Exception {
		boolean overallResults = true;
		for (CSVRecord productRecord : productData.records) {
			String value = productRecord.get(key);
			CSVRecord sourceRecord = this.sourceData.getRow(key, value);
			boolean result = compareAttributes(attributeList, productRecord,
					sourceRecord);
			if (!result) {
				overallResults = result;
			}
		}
		return overallResults;
	}

	/**
	 * Compares product attribute value gainst corresponding source attribute value
	 * 
	 * @param attributeList
	 * @param Product
	 * @param Source
	 * @return
	 */
	private boolean compareAttributes(List<String> attributeList,
			CSVRecord Product, CSVRecord Source) {
		boolean overallResults = true;
		for (String attribute : attributeList) {
			Comparer comparer = getComparer(attribute);
			String productColumnName = MapLoader.getColumnName(attribute,
					"PRODUCT");
			String productValue = Product.get(productColumnName);

			String sourceColumnName = MapLoader.getColumnName(attribute,
					"SOURCE");
			String sourceValue = Source.get(sourceColumnName);

			boolean result = comparer.checkData(productValue, sourceValue);
			printResults(result, productValue, sourceValue, attribute);

			if (!result) {
				overallResults = result;
			}
		}
		return overallResults;
	}

	/**
	 * Print results of when data doesn't match
	 * 
	 * @param matchResult
	 * @param productValue
	 * @param sourceValue
	 * @param attribute
	 */
	private void printResults(boolean matchResult, String productValue,
			String sourceValue, String attribute) {
		if (!matchResult) {
			statusPrinter.printStatus("product: " + productValue
					+ " does not match with source: " + sourceValue
					+ " for these columns: " + attribute);
		} else {
			// statusPrinter.printStatus("Product and Source match");
		}
	}

	/**
	 * Get comparer object to do execute comparisons
	 * 
	 * @param attribute
	 * @return
	 */
	private Comparer getComparer(String attribute) {
		String compareType = mapLoader.getColumnsToTypeMapper().get(attribute);
		return ComparerFactory.getComparer(compareType);
	}
}
