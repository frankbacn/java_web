package web_test.tests;

import org.junit.Test;

import java_web.bean.ImageBuder;

public class ImageBuderTest {
	@Test
	public void test1(){
		ImageBuder ib = new ImageBuder(120, 50);
		ib.drawENString(4);
	}

}
