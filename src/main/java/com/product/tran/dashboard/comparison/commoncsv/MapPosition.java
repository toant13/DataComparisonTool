package com.product.tran.dashboard.comparison.commoncsv;

public enum MapPosition {

	KEY_INDEX(0), COLUMNS_INDEX(1), COMPARE_TYPE_INDEX(2);

	private final int position;
	
	private MapPosition(int position) {
        this.position = position;
    }
	
	public int getPosition(){
		return this.position;
	}

}
