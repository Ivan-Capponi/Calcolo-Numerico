package derivation;

import ast.Operation;

public interface Visitor <T> {
	T visitAdd(Operation left, Operation right);
	T visitSub(Operation left, Operation right);
	T visitMul(Operation left, Operation right);
	T visitDiv(Operation left, Operation right);
	T visitSin(Operation op);
	T visitCos(Operation op);
	T visitTan(Operation op);
	T visitAtan(Operation op);
	T visitAcos(Operation op);
	T visitAsin(Operation op);
	T visitSqrt(Operation op);
	T visitLog(Operation op);
	T visitPow(Operation op, Operation exp);
	T visitConst(String c);
	T visitSimpleVar();
	T visitAbs(Operation op);
	T visitExp(Operation op);
}
