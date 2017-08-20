package ast;

import derivation.Visitor;

public class Atan extends UnaryOperation {

	public Atan(Operation op) {
		super(op);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitAtan(op);
	}

	public String toString(){
		return "atan(" + op.toString() + ")";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.atan(op.getNumericResult(val));
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Atan)) return false;
		Atan abs = (Atan) o;
		return (op.equals(abs.op));
	}
	
	public int hashCode(){
		return 17 * op.hashCode();
	}
}
