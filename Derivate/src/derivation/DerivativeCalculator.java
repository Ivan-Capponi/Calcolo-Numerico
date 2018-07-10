package derivation;

import ast.*;

public class DerivativeCalculator implements Visitor<Operation> {

	@Override
	public Operation visitAdd(Operation left, Operation right) {
		return new Addition(left.accept(this), right.accept(this));
	}

	@Override
	public Operation visitSub(Operation left, Operation right) {
		return new Subtraction(left.accept(this), right.accept(this));
	}

	@Override
	public Operation visitMul(Operation left, Operation right) {
		return new Addition(new Product(left.accept(this), right), new Product(left, right.accept(this)));
	}

	@Override
	public Operation visitDiv(Operation left, Operation right) {
		Operation denominator = new Pow(right, new Constant("2"));
		Operation numerator = new Subtraction(new Product(left.accept(this), right), new Product(left, right.accept(this)));
		return new Division(numerator, denominator);
	}

	@Override
	public Operation visitSin(Operation op) {
		return new Product(new Cos(op), op.accept(this));
	}

	@Override
	public Operation visitCos(Operation op) {
		return new Product(new Negate(new Sin(op)), op.accept(this));
	}

	@Override
	public Operation visitTan(Operation op) {
		return new Division(op.accept(this), new Pow(new Cos(op), new Constant("2")));
	}

	@Override
	public Operation visitAtan(Operation op) {
		return new Division(op.accept(this), new Addition(new Constant("1"), new Pow(op, new Constant("2"))));
	}

	@Override
	public Operation visitAcos(Operation op) {
		return new Negate(new Division(op.accept(this), new Sqrt(new Subtraction(new Constant("1"), new Pow(op, new Constant("2"))))));
	}

	@Override
	public Operation visitAsin(Operation op) {
		return new Division(op.accept(this), new Sqrt(new Subtraction(new Constant("1"), new Pow(op, new Constant("2")))));
	}

	@Override
	public Operation visitSqrt(Operation op) {
		return new Division(op.accept(this), new Product(new Constant("2"), new Sqrt(op)));
	}

	@Override
	public Operation visitLog(Operation op) {
		return new Division(op.accept(this), op);
	}

	@Override
	public Operation visitPow(Operation op, Operation exp) {
		return new Product(new Pow(op,exp), new Addition(new Product(exp.accept(this), new Log(op)), new Division(new Product(exp, op.accept(this)), op)));
	}

	@Override
	public Operation visitConst(String c) {
		return new Constant("0");
	}

	@Override
	public Operation visitSimpleVar() {
		return new Constant("1");
	}

	@Override
	public Operation visitAbs(Operation op) {
		return new Division(new Product(new Abs(op), op.accept(this)), op);
	}

	@Override
	public Operation visitExp(Operation op) {
		return new Product(new Exp(op), op.accept(this));
	}
}
