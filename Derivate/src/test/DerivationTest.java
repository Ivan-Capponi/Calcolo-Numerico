package test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import tokenizer.Main;

public class DerivationTest {
	private Main f;
	
	@Before
	public void setUp() throws Exception {
		f = new Main();
	}

	@Test
	public void test_1() {
		Main.main(new String[]{"4x"});
		assertEquals(f.getDerivate().getNumericResult(5d), (Double) 4.0);
	}
	
	@Test
	public void test_2() {
		Main.main(new String[]{"log(x)"});
		assertEquals(f.getDerivate().getNumericResult(2d), (Double) 0.5);
	}
	
	@Test
	public void test_3() {
		Main.main(new String[]{"3x^2-2x"});
		assertEquals(f.getDerivate().getNumericResult(1d), (Double) 4.0);
	}
	
	@Test
	public void test_4() {
		Main.main(new String[]{"1/(xe^x)"});
		assertEquals(f.getDerivate().getNumericResult(3d), (Double) (-0.022127585));
	}
	
	@Test
	public void test_5() {
		Main.main(new String[]{"1/sqrt(x)"});
		assertEquals(f.getDerivate().getNumericResult(5d), (Double) (-0.044721359));
	}
	
	@Test
	public void test_6() {
		Main.main(new String[]{"1/sqrt(1+x^2)"});
		assertEquals(f.getDerivate().getNumericResult(2d), (Double) (-0.178885438));
	}
}
