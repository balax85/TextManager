package it.andrea.balasso.util;

import org.junit.Assert;
import org.junit.Test;

public class PostUtilTest {

	@Test
	public void calculateScoreTest() {
		Assert.assertTrue(PostUtil.calculateScore("ciao", "ciao test") == 1);
		Assert.assertTrue(PostUtil.calculateScore("cdfdd", "ciao test") == 1);
		Assert.assertTrue(PostUtil.calculateScore("ciao test", "ciao test") == 2);
		Assert.assertTrue(PostUtil.calculateScore("ciao test sss", "ciao test") == 2);
		Assert.assertTrue(PostUtil.calculateScore("ciao test red", "ciao red") == 2);
	}
	
}
