package com.product.comparison;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.product.comparison.MapLoader;

public class MapLoaderTest {

	@Test
	public void load_ValidMap_PopulatedMaps() throws Exception {
		MapLoader mapLoader = new MapLoader();
		URL url = this.getClass().getResource("/map/keymap.txt");
		String fileName = url.getPath();
		mapLoader.load(fileName);

		Map<String, List<String>> actualKeyToColumnsMapper = mapLoader
				.getKeyToColumnsMapper();
		String expectedKeyToColumns = "{CUSIP=[Morningstar Rating~MORNINGSTAR_OVERALL_RATING_VL, SI Return~si], SPN ID=[Vehicle Name~Fund Name, Vehicle Status~Fund Status, Primary Prospectus Benchmark~name_v, Incep. Date~Fund Inception Date]}";
		assertEquals(expectedKeyToColumns, actualKeyToColumnsMapper.toString());

		Map<String, String> columnsToTypeMapper = mapLoader
				.getColumnsToTypeMapper();
		System.out.println(columnsToTypeMapper);
		String expectedColumnsToType = "{Morningstar Rating~MORNINGSTAR_OVERALL_RATING_VL=ALPHA, Vehicle Status~Fund Status=ALPHA, Vehicle Name~Fund Name=ALPHA, Primary Prospectus Benchmark~name_v=ALPHA, SI Return~si=NUMERIC, Incep. Date~Fund Inception Date=ALPHA}";
		assertEquals(expectedColumnsToType, columnsToTypeMapper.toString());
	}

	@Test(expected = NullPointerException.class)
	public void load_NullMap_NullPointerException() throws Exception {
		MapLoader mapLoader = new MapLoader();
		mapLoader.load(null);
	}

	@Test(expected = RuntimeException.class)
	public void load_InvalidMapWithDuplicateKeyToColumns_RuntimeException() throws Exception {
		MapLoader mapLoader = new MapLoader();
		URL url = this.getClass().getResource("/map/invalidkeymap.txt");
		String fileName = url.getPath();
		mapLoader.load(fileName);
	}

}
