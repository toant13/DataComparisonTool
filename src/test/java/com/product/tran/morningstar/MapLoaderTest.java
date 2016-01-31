package com.product.tran.morningstar;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Test;

import com.product.tran.morningstar.MapLoader;

public class MapLoaderTest {

	@Test
	public void load_ValidMapFile_PopulatedMap() throws Exception {
		MapLoader mapLoader = new MapLoader();

		URL url = this.getClass().getResource("/map/map.txt");
		String fileName = url.getPath();
		mapLoader.load(fileName);

		assertTrue("Column mapper does not match", mapLoader.getColumnMapper()
				.toString().equals("{A1=B2,B4, A2=B3, A3=B1}"));
		assertTrue(
				"Type mapper does not match",
				mapLoader
						.getTypeMapper()
						.toString()
						.equals("{A1=ALPHA, B2=ALPHA, A2=NUMERIC, B3=NUMERIC, B4=ALPHA, A3=NUMERIC, B1=NUMERIC}"));
	}
	
	@Test(expected = NullPointerException.class)  
	public void load_InvalidMapFile_PopulatedMap() throws Exception {
		MapLoader mapLoader = new MapLoader();

		URL url = this.getClass().getResource("/map/map1.txt");
		String fileName = url.getPath();
		mapLoader.load(fileName);

		assertTrue("Column mapper does not match", mapLoader.getColumnMapper()
				.toString().equals("{A1=B2,B4, A2=B3, A3=B1}"));
		assertTrue(
				"Type mapper does not match",
				mapLoader
						.getTypeMapper()
						.toString()
						.equals("{A1=ALPHA, B2=ALPHA, A2=NUMERIC, B3=NUMERIC, B4=ALPHA, A3=NUMERIC, B1=NUMERIC}"));
	}

}
