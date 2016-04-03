package com.product.comparison.load;

import org.junit.Test;

public class AttributeMapperTest {

	@Test
	public void test() throws Exception {
		AttributeMapper attributeMapper = new AttributeMapper();

		String productFile = this.getClass()
				.getResource("/InputFiles/product.csv").getPath();
		String sourceFile = this.getClass()
				.getResource("/InputFiles/source.csv").getPath();

		attributeMapper.levelComparison(productFile, sourceFile);
	}

}
