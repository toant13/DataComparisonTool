package com.product.tran.morningstar;

import java.util.List;
import java.util.Map;

import com.product.tran.common.load.DataLoader;
import com.product.tran.common.load.MapLoader;

/**
 * @author toantran
 *
 */
public class MorningStarComparer {

	private MapLoader mapLoader;
	private DataLoader productLoader;
	private DataLoader morningStarLoader;

	public MorningStarComparer(String productFile, String morningStarFile,
			String mapFile) throws Exception {
		this.mapLoader = new MapLoader();
		mapLoader.load(mapFile);

		this.productLoader = new DataLoader();
		productLoader.load(productFile);

		this.morningStarLoader = new DataLoader();
		morningStarLoader.load(morningStarFile);
	}

	public void runCompare() throws Exception {
		Map<String, String> columnMapper = mapLoader.getColumnMapper();
		Map<String, String> typeMapper = mapLoader.getTypeMapper();
		
		Map<String, List<String>> productValuesMap = productLoader.getColumnValuesMap();
		Map<String, List<String>> morningStarValuesMap = morningStarLoader.getColumnValuesMap();
		
		for(Map.Entry<String, String> column : columnMapper.entrySet()){
			
		}
	}




}
