package ast;

import derivation.Visitor;

public class Addition extends BinaryOperation {

	public Addition(Operation left, Operation right) {
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
		return v.visitAdd(left, right);
	}
	
	@Override
	public String toString(){
		return "(" + left.toString() + ")+(" + right.toString() + ")";
	}
	
	public Double getNumericResult(Double val)
	{
		return left.getNumericResult(val) + right.getNumericResult(val);
	}
}
