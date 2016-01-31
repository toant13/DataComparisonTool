package com.product.tran.morningstar.comparison;

public abstract class Comparer {

	protected int threshold;
	
	public Comparer(int threshold){
		this.threshold = threshold;
	}

	public abstract boolean checkData(String value1, String value2);
}
