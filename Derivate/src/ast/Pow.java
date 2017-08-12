package ast;

import derivation.Visitor;

public class Pow extends BinaryOperation {
	public Pow(Operation left, Operation right) {
		super(left,right);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitPow(left, right);
	}

	public String toString(){
		return "(" + left.toString() + ")^(" + right.toString() + ")";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.pow(left.getNumericResult(val), right.getNumericResult(val));
	}
}
