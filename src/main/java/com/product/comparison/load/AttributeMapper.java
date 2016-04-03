package com.product.comparison.load;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.product.comparison.compare.Comparer;
import com.product.comparison.compare.ComparerFactory;

/**
 * returns columns required for comparison
 * 
 * 
 * @author toantran
 *
 */
public class AttributeMapper {

	/**
	 * test method
	 * 
	 * @return
	 */
	public String getJoinId() {
		return "SPN ID";
	}

	public String productColumnName() {
		return "Closed Date";
	}

	public String productMapperColumnName() {
		return "Vehicle Status";
	}

	public String sourceColumnName(String productMapperValue) throws Exception {
		if (productMapperValue.equals("Merged Away")) {
			return "Fund Merge Away Date";
		} else if (productMapperValue.equals("Liquidated Date")) {
			return "Fund Liquidation Date";
		} else {
			throw new Exception("won't work");
		}
	}

	public boolean levelComparison(String productFile, String sourceFile)
			throws Exception {
		boolean overallResult = true;

		DataHandler productData = new DataHandler(productFile);
		SourceDataHandler sourceData = new SourceDataHandler(sourceFile);

		List<CSVRecord> productRecords = productData.getRecords();

		for (CSVRecord record : productRecords) {
			String joinIdValue = record.get(getJoinId());
			System.out.println(getJoinId() + ": " + joinIdValue);

			String mapColumnValue = record.get(productMapperColumnName());
			System.out.println(productMapperColumnName() + ": "
					+ mapColumnValue);

			String productCompareValue = record.get(productColumnName());
			System.out
					.println(productColumnName() + ": " + productCompareValue);

			CSVRecord sourceRecord = sourceData
					.getRow(getJoinId(), joinIdValue);
			String sourceColumnName = sourceColumnName(mapColumnValue);
			String sourceCompareValue = sourceRecord.get(sourceColumnName);
			System.out.println(sourceCompareValue);

			System.out.println("product value: " + productCompareValue
					+ " source value: " + sourceCompareValue);

			Comparer comparer = getComparer();
			boolean result = comparer.checkData(productCompareValue,
					sourceCompareValue);
			
			if (!result) {
				overallResult = result;
			}
		}

		return overallResult;
	}

	public Comparer getComparer() {
		return ComparerFactory.getComparer("ALPHA");
	}

}
