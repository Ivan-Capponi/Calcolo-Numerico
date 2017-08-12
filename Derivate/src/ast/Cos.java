package ast;

import derivation.Visitor;

public class Cos extends UnaryOperation {

	public Cos(Operation op) {
		super(op);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitCos(op);
	}
	
	public String toString(){
		return "cos(" + op.toString() + ")";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.cos(op.getNumericResult(val));
	}

}
