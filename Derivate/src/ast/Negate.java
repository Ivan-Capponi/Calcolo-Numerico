package ast;

import derivation.Visitor;

public class Negate extends UnaryOperation {

	public Negate(Operation op) {
		super(op);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return null;
	}

	public String toString(){
		return "-" + op.toString();
 	}

	@Override
	public Double getNumericResult(Double val) {
		return -op.getNumericResult(val);
	}
}
