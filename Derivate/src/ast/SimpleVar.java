package ast;

import derivation.Visitor;

public class SimpleVar implements Operation {
	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitSimpleVar();
	}
	
	public String toString(){
		return "x";
 	}

	@Override
	public Double getNumericResult(Double val) {
		if (val == null) throw new NullPointerException("Variable set to null!");
		return val;
	}
}
