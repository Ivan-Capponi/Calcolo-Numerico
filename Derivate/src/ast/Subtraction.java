package ast;

import derivation.Visitor;

public class Subtraction extends BinaryOperation {

	public Subtraction(Operation left, Operation right) {
		super(left, right);
	}

	public Operation getLeft(){
		return left;
	}
	
	public Operation getRight(){
		return right;
	}
	
	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitSub(left, right);
	}
	
	public String toString(){
		return "(" + left.toString() + ")-(" + right.toString() + ")";
	}

	@Override
	public Double getNumericResult(Double val) {
		return left.getNumericResult(val) - right.getNumericResult(val);
	}
}
