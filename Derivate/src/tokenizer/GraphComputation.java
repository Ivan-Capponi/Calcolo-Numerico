package tokenizer;

import org.jgrapht.DirectedGraph;

import ast.*;
import derivation.DerivateComputation;
import derivation.Visitor;

public class GraphComputation implements Visitor <Operation> {

	private DirectedGraph <String, String> graph;
	private DerivateComputation derivation = new DerivateComputation();
	private SimpleVar x = new SimpleVar();
	
	public GraphComputation(DirectedGraph <String, String> graph){
		if (graph == null) throw new IllegalArgumentException("Invalid graph");
		this.graph = graph;
		graph.addVertex(x.toString());
	}
	
	@Override
	public Operation visitAdd(Operation left, Operation right) {
		Addition add = new Addition(left.accept(this), right.accept(this));
		graph.addVertex(add.toString());
		graph.addEdge(left.toString(), add.toString(), new Division(left,add).toString());
		graph.addEdge(right.toString(), add.toString(), new Division(right,add).toString());
		return add;
	}

	@Override
	public Operation visitSub(Operation left, Operation right) {
		Subtraction sub = new Subtraction(left.accept(this), right.accept(this));
		graph.addVertex(sub.toString());
		graph.addEdge(left.toString(), sub.toString(), new Division(left,sub).toString());
		graph.addEdge(right.toString(), sub.toString(), new Negate(new Division(right,sub)).toString());
		return sub;
	}

	@Override
	public Operation visitMul(Operation left, Operation right) {
		Product prod = new Product(left.accept(this), right.accept(this));
		graph.addVertex(prod.toString());
		graph.addEdge(left.toString(), prod.toString(), new Constant("1").toString());
		graph.addEdge(right.toString(), prod.toString(), new Constant("1").toString());
		return prod;
	}

	@Override
	public Operation visitDiv(Operation left, Operation right) {
		Division div = new Division(left.accept(this), right.accept(this));
		graph.addVertex(div.toString());
		graph.addEdge(left.toString(), div.toString(), new Constant("1").toString());
		graph.addEdge(right.toString(), div.toString(), new Constant("-1").toString());
		return div;
	}

	@Override
	public Operation visitSin(Operation op) {
		Sin sin = new Sin(op.accept(this));
		graph.addVertex(sin.toString());
		graph.addEdge(op.toString(), sin.toString(), sin.accept(derivation).toString());
		return sin;
	}

	@Override
	public Operation visitCos(Operation op) {
		Cos cos = new Cos(op.accept(this));
		graph.addVertex(cos.toString());
		graph.addEdge(op.toString(), cos.toString(), cos.accept(derivation).toString());
		return cos;
	}

	@Override
	public Operation visitTan(Operation op) {
		Tan cos = new Tan(op.accept(this));
		graph.addVertex(cos.toString());
		graph.addEdge(op.toString(), cos.toString(), cos.accept(derivation).toString());
		return cos;
	}

	@Override
	public Operation visitAtan(Operation op) {
		Atan cos = new Atan(op.accept(this));
		graph.addVertex(cos.toString());
		graph.addEdge(op.toString(), cos.toString(), cos.accept(derivation).toString());
		return cos;
	}

	@Override
	public Operation visitAcos(Operation op) {
		Acos cos = new Acos(op.accept(this));
		graph.addVertex(cos.toString());
		graph.addEdge(op.toString(), cos.toString(), cos.accept(derivation).toString());
		return cos;
	}

	@Override
	public Operation visitAsin(Operation op) {
		Asin cos = new Asin(op.accept(this));
		graph.addVertex(cos.toString());
		graph.addEdge(op.toString(), cos.toString(), cos.accept(derivation).toString());
		return cos;
	}

	@Override
	public Operation visitSqrt(Operation op) {
		Sqrt cos = new Sqrt(op.accept(this));
		graph.addVertex(cos.toString());
		graph.addEdge(op.toString(), cos.toString(), cos.accept(derivation).toString());
		return cos;
	}

	@Override
	public Operation visitLog(Operation op) {
		Log cos = new Log(op.accept(this));
		graph.addVertex(cos.toString());
		graph.addEdge(op.toString(), cos.toString(), cos.accept(derivation).toString());
		return cos;
	}

	@Override
	public Operation visitPow(Operation op, Operation exp) {
		Pow prod = new Pow(op.accept(this), exp.accept(this));
		graph.addVertex(prod.toString());
		graph.addEdge(op.toString(), prod.toString(), new Constant("1").toString());
		graph.addEdge(exp.toString(), prod.toString(), new Constant("1").toString());
		return prod;
	}

	@Override
	public Operation visitConst(String c) {
		Constant constant = new Constant(c);
		graph.addVertex(constant.toString());	
		return constant;
	}

	@Override
	public Operation visitSimpleVar() {
		return x;
	}

	@Override
	public Operation visitAbs(Operation op) {
		Abs cos = new Abs(op.accept(this));
		graph.addVertex(cos.toString());
		graph.addEdge(op.toString(), cos.toString(), cos.accept(derivation).toString());
		return cos;
	}

	@Override
	public Operation visitExp(Operation op) {
		Exp cos = new Exp(op.accept(this));
		graph.addVertex(cos.toString());
		graph.addEdge(op.toString(), cos.toString(), cos.accept(derivation).toString());
		return cos;
	}


}
