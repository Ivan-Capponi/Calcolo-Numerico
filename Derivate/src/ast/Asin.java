package ast;

import derivation.Visitor;

public class Asin extends UnaryOperation {

	public Asin(Operation op) {
		super(op);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitAsin(op);
	}
	
	public String toString(){
		return "asin(" + op.toString() + ")";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.asin(op.getNumericResult(val));
	}
}
