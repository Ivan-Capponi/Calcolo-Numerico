package ast;

import derivation.Visitor;

public class Log extends UnaryOperation {
	
	public Log(Operation op) {
		super(op);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitLog(op);
	}
	
	public String toString(){
		return "log(" + op.toString() + ")";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.log(op.getNumericResult(val));
	}
}
