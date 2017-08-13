package ast;

import derivation.Visitor;

public class Abs extends UnaryOperation {
	
	public Abs(Operation op) {
		super(op);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitAbs(op);
	}
	
	public String toString(){
		return "|" + op.toString() + "|";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.abs(op.getNumericResult(val));
	}
}
