package ast;

import derivation.Visitor;

public class Acos extends UnaryOperation {

	public Acos(Operation op) {
		super(op);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitAcos(op);
	}
	
	public String toString(){
		return "acos(" + op.toString() + ")";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.acos(op.getNumericResult(val));
	}
}
