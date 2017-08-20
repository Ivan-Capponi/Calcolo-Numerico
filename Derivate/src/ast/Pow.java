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
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Pow)) return false;
		Pow abs = (Pow) o;
		return (left.equals(abs.left) && right.equals(abs.right));
	}
	
	public int hashCode(){
		return 71 * (left.hashCode() + right.hashCode());
	}
}
