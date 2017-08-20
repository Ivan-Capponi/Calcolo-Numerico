package ast;

import derivation.Visitor;

public class Sin extends UnaryOperation {

	public Sin(Operation op) {
		super(op);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitSin(op);
	}

	public String toString(){
		return "sin(" + op.toString() + ")";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.sin(op.getNumericResult(val));
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Sin)) return false;
		Sin abs = (Sin) o;
		return (op.equals(abs.op));
	}
	
	public int hashCode(){
		return 47 * op.hashCode();
	}
}
