package tokenizer;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;

import ast.*;
import derivation.DerivateComputation;
import derivation.Visitor;

public class GraphComputation implements Visitor <DirectedGraph <Operation, Operation>> {
	DirectedGraph<Operation, Operation> graph = new DefaultDirectedGraph<Operation, Operation>(Operation.class);
	private DerivateComputation derivator = new DerivateComputation();
	
	public GraphComputation()
	{
		graph.addVertex(new SimpleVar());
	}
	
	@Override
	public DirectedGraph <Operation, Operation> visitAdd(Operation left, Operation right) {
		Operation add = new Addition(left, right);
		graph.addVertex(add);
		graph.addEdge(left, add, new Division(left,add));
		graph.addEdge(right, add, new Division(right,add));
		left.accept(this);
		right.accept(this);
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitSub(Operation left, Operation right) {
		Operation sub = new Subtraction(left, right);
		graph.addVertex(sub);
		graph.addEdge(left, sub, new Division(left,sub));
		graph.addEdge(right, sub, new Negate(new Division(right,sub)));
		left.accept(this);
		right.accept(this);
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitMul(Operation left, Operation right) {
		Operation prod = new Product(left, right);
		graph.addVertex(prod);
		graph.addEdge(left, prod, new Constant("1"));
		graph.addEdge(right, prod, new Constant("1"));
		left.accept(this);
		right.accept(this);
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitDiv(Operation left, Operation right) {
		Operation div = new Division(left, right);
		graph.addVertex(div);
		graph.addEdge(left, div, new Constant("1"));
		graph.addEdge(right, div, new Constant("-1"));
		left.accept(this);
		right.accept(this);
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitSin(Operation op) {
		Operation sin = new Sin(op);
		graph.addVertex(sin);
		graph.addEdge(op, sin, new Division(new Product(new SimpleVar(), sin.accept(derivator)),sin));
		op.accept(this);
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitCos(Operation op) {
		Operation cos = new Cos(op);
		graph.addVertex(cos);
		graph.addEdge(op, cos, new Division(new Product(new SimpleVar(), cos.accept(derivator)),cos));
		op.accept(this);
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitTan(Operation op) {
		Operation tan = new Tan(op);
		graph.addVertex(tan);
		graph.addEdge(op, tan, new Division(new Product(new SimpleVar(), tan.accept(derivator)),tan));
		op.accept(this);
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitAtan(Operation op) {
		Operation atan = new Atan(op);
		graph.addVertex(atan);
		graph.addEdge(op, atan, new Division(new Product(new SimpleVar(), atan.accept(derivator)),atan));
		op.accept(this);
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitAcos(Operation op) {
		Operation acos = new Acos(op);
		graph.addVertex(acos);
		graph.addEdge(op, acos, new Division(new Product(new SimpleVar(), acos.accept(derivator)),acos));
		op.accept(this);
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitAsin(Operation op) {
		Operation asin = new Asin(op);
		graph.addVertex(asin);
		graph.addEdge(op, asin, new Division(new Product(new SimpleVar(), asin.accept(derivator)),asin));
		op.accept(this);
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitSqrt(Operation op) {
		Operation sqrt = new Sqrt(op);
		graph.addVertex(sqrt);
		graph.addEdge(op, sqrt, new Division(new Product(new SimpleVar(), sqrt.accept(derivator)),sqrt));
		op.accept(this);
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitLog(Operation op) {
		Operation log = new Log(op);
		graph.addVertex(log);
		graph.addEdge(op, log, new Division(new Product(new SimpleVar(), log.accept(derivator)),log));
		op.accept(this);
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitPow(Operation op, Operation exp) {
		exp.accept(this);
		Operation pow = new Pow(op, exp);
		graph.addVertex(pow);
		graph.addEdge(op, pow, new Constant("1"));
		op.accept(this);
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitConst(String c) {
		graph.addVertex(new Constant(c));
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitSimpleVar() {
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitAbs(Operation op) {
		Operation abs = new Abs(op);
		graph.addVertex(abs);
		graph.addEdge(op, abs, new Constant("1"));
		op.accept(this);
		return graph;
	}

	@Override
	public DirectedGraph <Operation, Operation> visitExp(Operation op) {
		Operation exp = new Exp(op);
		graph.addVertex(exp);
		graph.addEdge(op, exp, new Constant("1"));
		op.accept(this);
		return graph;
	}

}
