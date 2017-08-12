package ast;

import derivation.Visitor;

public class Sqrt extends UnaryOperation {
	public int GRADE = 2;
	
	public Sqrt(Operation op) {
		super(op);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitSqrt(op);
	}

	public String toString(){
		return "sqrt(" + op.toString() + ")";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.sqrt(op.getNumericResult(val));
	}
}
