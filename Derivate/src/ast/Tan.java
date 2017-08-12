package ast;

import derivation.Visitor;

public class Tan extends UnaryOperation {

	public Tan(Operation op) {
		super(op);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitTan(op);
	}

	public String toString(){
		return "tan(" + op.toString() + ")";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.tan(op.getNumericResult(val));
	}
}
