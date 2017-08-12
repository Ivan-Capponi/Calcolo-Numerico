package ast;

import derivation.Visitor;

public class Product extends BinaryOperation {

	public Product(Operation left, Operation right) {
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
		return v.visitMul(left, right);
	}

	public String toString(){
		return "(" + left.toString() + ")*(" + right.toString() + ")";
	}

	@Override
	public Double getNumericResult(Double val) {
		return left.getNumericResult(val) * right.getNumericResult(val);
	}
}
