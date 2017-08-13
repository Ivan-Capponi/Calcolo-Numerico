package ast;

import derivation.Visitor;

public class Division extends BinaryOperation {

	public Division(Operation left, Operation right) {
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
		return v.visitDiv(left, right);
	}
	
	public String toString(){
		return "(" + left.toString() + ")/(" + right.toString() + ")";
	}

	@Override
	public Double getNumericResult(Double val) {
		return left.getNumericResult(val) / right.getNumericResult(val);
	}
}
