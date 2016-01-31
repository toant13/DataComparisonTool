package com.product.tran.morningstar;

import com.product.tran.common.load.DataLoader;
import com.product.tran.common.load.MapLoader;

public class MorningStarComparer {
	
	private MapLoader mapLoader;
	private DataLoader productLoader;
	private DataLoader morningStarLoader;

	public MorningStarComparer(String productFile, String morningStarFile, String mapFile) throws Exception{
		this.mapLoader = new MapLoader();
		mapLoader.load(mapFile);
		
		this.productLoader = new DataLoader();
		productLoader.load(productFile);
		
		this.morningStarLoader = new DataLoader();
		morningStarLoader.load(morningStarFile);
	}
	
	public void runCompare() throws Exception{
		
		
		
	}
}
