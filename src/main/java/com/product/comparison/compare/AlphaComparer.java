package com.product.comparison.compare;

public class AlphaComparer extends Comparer{

	public AlphaComparer(int threshold) {
		super(threshold);
	}

	@Override
	public boolean checkData(String value1, String value2) {
		return value1.equals(value2);
	}




}
