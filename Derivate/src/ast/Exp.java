package ast;

import derivation.Visitor;

public class Exp extends UnaryOperation {
	
	public Exp(Operation op) {
		super(op);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitExp(op);
	}
	
	public String toString(){
		return "(e^(" + op.toString() + "))";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.exp(op.getNumericResult(val));
	}
}
