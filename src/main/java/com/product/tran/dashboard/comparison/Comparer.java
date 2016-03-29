package com.product.tran.dashboard.comparison;

/**
 * Abstract class to define comparers.
 * 
 * @author toantran
 *
 */
public abstract class Comparer {

	protected int threshold;
	
	/**
	 * Constructor with threshold for compares
	 * 
	 * @param threshold
	 */
	public Comparer(int threshold){
		this.threshold = threshold;
	}

	/**
	 * Checks data using required threshold
	 * 
	 * @param value1
	 * @param value2
	 * @return
	 */
	public abstract boolean checkData(String value1, String value2);
}
