package ast;

import derivation.Visitor;

public class Sin extends UnaryOperation {

	public Sin(Operation op) {
		super(op);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitSin(op);
	}

	public String toString(){
		return "sin(" + op.toString() + ")";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.sin(op.getNumericResult(val));
	}
}
