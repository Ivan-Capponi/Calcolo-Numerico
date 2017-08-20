package ast;

import derivation.Visitor;

public class Constant implements Operation {
	private String c;
	
	public Constant(String c) {
		this.c = c;
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitConst(c);
	}

	public String toString(){
		return c;
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Double.parseDouble(c);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Constant)) return false;
		Constant abs = (Constant) o;
		return (c.equals(abs.c));
	}
	
	public int hashCode(){
		return 23 * (int) Double.parseDouble(c);
	}
}
