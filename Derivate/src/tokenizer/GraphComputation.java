package tokenizer;

import java.awt.Color;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import ast.*;
import derivation.DerivateComputation;
import derivation.Visitor;

public class GraphComputation implements Visitor <Operation> {

	private Graph graph;
	private DerivateComputation derivation = new DerivateComputation();
	private SimpleVar x = new SimpleVar();
	private String mulConst = "1";
	private String divConst = "-1";
	
	public GraphComputation(Graph graph){
		if (graph == null) throw new IllegalArgumentException("Invalid graph");
		this.graph = graph;
		Node added = graph.addNode(x.toString());
		added.addAttribute("ui.label", x.toString());
		added.addAttribute("ui.color", Color.CYAN);
	}
	
	
	@Override
	public Operation visitAdd(Operation left, Operation right) {
		Addition add = new Addition(left.accept(this), right.accept(this));
		Node added = graph.addNode(add.toString());
		added.addAttribute("ui.label", add.toString());
		graph.addEdge(new Division(left,add).toString(), left.toString(), add.toString());
		graph.addEdge(new Division(right,add).toString(), right.toString(), add.toString());
		return add;
	}

	@Override
	public Operation visitSub(Operation left, Operation right) {
		Subtraction sub = new Subtraction(left.accept(this), right.accept(this));
		Node added = graph.addNode(sub.toString());
		added.addAttribute("ui.label", sub.toString());
		graph.addEdge(new Division(left,sub).toString(), left.toString(), sub.toString());
		graph.addEdge(new Negate(new Division(right,sub)).toString(), right.toString(), sub.toString());
		return sub;
	}

	@Override
	public Operation visitMul(Operation left, Operation right) {
		Product prod = new Product(left.accept(this), right.accept(this));
		Node added = graph.addNode(prod.toString());
		added.addAttribute("ui.label", prod.toString());
		graph.addEdge(mulConst, left.toString(), prod.toString());
		graph.addEdge(mulConst, right.toString(), prod.toString());
		return prod;
	}

	@Override
	public Operation visitDiv(Operation left, Operation right) {
		Division div = new Division(left.accept(this), right.accept(this));
		Node added = graph.addNode(div.toString());
		added.addAttribute("ui.label", div.toString());
		graph.addEdge(mulConst, left.toString(), div.toString());
		graph.addEdge(divConst.toString(), right.toString(), div.toString());
		return div;
	}

	@Override
	public Operation visitSin(Operation op) {
		Sin sin = new Sin(op.accept(this));
		Node added = graph.addNode(sin.toString());
		added.addAttribute("ui.label", sin.toString());
		graph.addEdge(sin.accept(derivation).toString(), op.toString(), sin.toString());
		return sin;
	}

	@Override
	public Operation visitCos(Operation op) {
		Cos cos = new Cos(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge(cos.accept(derivation).toString(), op.toString(), cos.toString());
		return cos;
	}

	@Override
	public Operation visitTan(Operation op) {
		Tan cos = new Tan(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge(cos.accept(derivation).toString(), op.toString(), cos.toString());
		return cos;
	}

	@Override
	public Operation visitAtan(Operation op) {
		Atan cos = new Atan(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge(cos.accept(derivation).toString(), op.toString(), cos.toString());
		return cos;
	}

	@Override
	public Operation visitAcos(Operation op) {
		Acos cos = new Acos(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge(cos.accept(derivation).toString(), op.toString(), cos.toString());
		return cos;
	}

	@Override
	public Operation visitAsin(Operation op) {
		Asin cos = new Asin(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge(cos.accept(derivation).toString(), op.toString(), cos.toString());
		return cos;
	}

	@Override
	public Operation visitSqrt(Operation op) {
		Sqrt cos = new Sqrt(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge(cos.accept(derivation).toString(), op.toString(), cos.toString());
		return cos;
	}

	@Override
	public Operation visitLog(Operation op) {
		Log cos = new Log(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge(cos.accept(derivation).toString(), op.toString(), cos.toString());
		return cos;
	}

	@Override
	public Operation visitPow(Operation op, Operation exp) {
		Pow prod = new Pow(op.accept(this), exp.accept(this));
		Node added = graph.addNode(prod.toString());
		added.addAttribute("ui.label", prod.toString());
		graph.addEdge(mulConst, op.toString(), prod.toString());
		graph.addEdge(mulConst, exp.toString(), prod.toString());
		return prod;
	}

	@Override
	public Operation visitConst(String c) {
		Constant constant = new Constant(c);
		Node added = graph.addNode(constant.toString());
		added.addAttribute("ui.label", constant.toString());
		return constant;
	}

	@Override
	public Operation visitSimpleVar() {
		return x;
	}

	@Override
	public Operation visitAbs(Operation op) {
		Abs cos = new Abs(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge(cos.accept(derivation).toString(), op.toString(), cos.toString());
		return cos;
	}

	@Override
	public Operation visitExp(Operation op) {
		Exp cos = new Exp(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge(cos.accept(derivation).toString(), op.toString(), cos.toString());
		return cos;
	}


}
