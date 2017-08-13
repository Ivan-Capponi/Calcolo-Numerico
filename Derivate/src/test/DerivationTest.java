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
		assertEquals((Double)(Math.floor(f.getDerivate().getNumericResult(5d)*1000)/1000), (Double)(Math.floor(4.0 * 1000)/1000));
	}
	
	@Test
	public void test_2() {
		Main.main(new String[]{"log(x)"});
		assertEquals((Double)(Math.floor(f.getDerivate().getNumericResult(2d)*1000)/1000), (Double)(Math.floor(0.5 * 1000)/1000));
	}
	
	@Test
	public void test_3() {
		Main.main(new String[]{"3x^2-2x"});
		assertEquals((Double)(Math.floor(f.getDerivate().getNumericResult(1d)*1000)/1000), (Double)(Math.floor(4.0 * 1000)/1000));
	}
	
	@Test
	public void test_4() {
		Main.main(new String[]{"1/(x*exp(x))"});
		assertEquals((Double)(Math.floor(f.getDerivate().getNumericResult(3d)*1000)/1000), (Double)(Math.floor((-0.022127585)*1000)/1000));
	}
	
	@Test
	public void test_5() {
		Main.main(new String[]{"1/sqrt(x)"});
		assertEquals((Double)(Math.floor(f.getDerivate().getNumericResult(5d)*1000)/1000), (Double)(Math.floor((-0.044721359)*1000)/1000));
	}
	
	@Test
	public void test_6() {
		Main.main(new String[]{"1/sqrt(1+x^2)"});
		assertEquals((Double)(Math.floor(f.getDerivate().getNumericResult(2d) * 1000)/1000), (Double)(Math.floor(-0.178885438*1000)/1000));
	}
	
	@Test
	public void test_7() {
		Main.main(new String[]{"sqrt(2x)"});
		assertEquals((Double)(Math.floor(f.getDerivate().getNumericResult(2d)*1000)/1000), (Double)(Math.floor((0.5)*1000)/1000));
	}
	
	@Test
	public void test_8(){
		Main.main(new String[]{"(2x^2+sqrt(x))^(6x)"});
		assertEquals((Double)(Math.floor(f.getDerivate().getNumericResult(7d) * 1000)/1000), (Double)(Math.floor(5.167364874 * 1000)/1000));
	}
}
