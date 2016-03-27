package com.product.tran.dashboard.comparison;

import java.math.BigDecimal;

public class NumericComparer extends Comparer {

	public NumericComparer(int threshold) {
		super(threshold);
	}

	@Override
	public boolean checkData(String value1, String value2) {
		Double doubleValue1 = convertData(value1);
		Double doubleValue2 = convertData(value2);
		return Math.abs(doubleValue1 - doubleValue2) < this.threshold;
	}


	private Double convertData(String value) {
		BigDecimal doubleValue = new BigDecimal(value);
		doubleValue = doubleValue.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return doubleValue.doubleValue();
	}

}
