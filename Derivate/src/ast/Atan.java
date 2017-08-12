package ast;

import derivation.Visitor;

public class Atan extends UnaryOperation {

	public Atan(Operation op) {
		super(op);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitAtan(op);
	}

	public String toString(){
		return "atan(" + op.toString() + ")";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.atan(op.getNumericResult(val));
	}
}
