package ast;

import derivation.Visitor;

public class Asin extends UnaryOperation {

	public Asin(Operation op) {
		super(op);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitAsin(op);
	}
	
	public String toString(){
		return "asin(" + op.toString() + ")";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.asin(op.getNumericResult(val));
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Asin)) return false;
		Asin abs = (Asin) o;
		return (op.equals(abs.op));
	}
	
	public int hashCode(){
		return 13 * op.hashCode();
	}
}
