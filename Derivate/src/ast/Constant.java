package ast;

import derivation.Visitor;

public class Constant implements Operation {
	private String c;
	
	public Constant(String c) {
		this.c = c;
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitConst(c);
	}

	public String toString(){
		return c;
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Double.parseDouble(c);
	}
}
