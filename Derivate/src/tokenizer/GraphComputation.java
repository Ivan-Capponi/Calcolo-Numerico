package tokenizer;

import java.awt.Color;
import java.util.concurrent.atomic.AtomicInteger;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import ast.*;
import derivation.DerivateComputation;
import derivation.Visitor;

public class GraphComputation implements Visitor <Operation> {

	private Graph graph;
	private DerivateComputation derivation = new DerivateComputation();
	private SimpleVar x = new SimpleVar();
	private AtomicInteger id = new AtomicInteger(0);
	
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
		try
		{
			Node added = graph.addNode(add.toString());
			added.addAttribute("ui.label", add.toString());
			Edge e1 = graph.addEdge("" + id.incrementAndGet(), left.toString(), add.toString(), true);
			Edge e2 = graph.addEdge("" + id.incrementAndGet(), right.toString(), add.toString(), true);
			e1.setAttribute("Operation", new Division(left,add));
			e2.setAttribute("Operation", new Division(right,add));
			return add;
		}
		catch (Exception e) { return add; }
	}

	@Override
	public Operation visitSub(Operation left, Operation right) {
		Subtraction add = new Subtraction(left.accept(this), right.accept(this));
		try
		{
			Node added = graph.addNode(add.toString());
			added.addAttribute("ui.label", add.toString());
			Edge e1 = graph.addEdge("" + id.incrementAndGet(), left.toString(), add.toString(), true);
			Edge e2 = graph.addEdge("" + id.incrementAndGet(), right.toString(), add.toString(), true);
			e1.setAttribute("Operation", new Division(left,add));
			e2.setAttribute("Operation", new Negate(new Division(right,add)));
			return add;
		}
		catch (Exception e) { return add; }
	}

	@Override
	public Operation visitMul(Operation left, Operation right) {
		Product add = new Product(left.accept(this), right.accept(this));
		Node added = graph.addNode(add.toString());
		added.addAttribute("ui.label", add.toString());
		Edge e1 = graph.addEdge("" + id.incrementAndGet(), left.toString(), add.toString(), true);
		Edge e2 = graph.addEdge("" + id.incrementAndGet(), right.toString(), add.toString(), true);
		e1.setAttribute("Operation", new Constant("1"));
		e2.setAttribute("Operation", new Constant("1"));
		return add;
	}

	@Override
	public Operation visitDiv(Operation left, Operation right) {
		Division add = new Division(left.accept(this), right.accept(this));
		Node added = graph.addNode(add.toString());
		added.addAttribute("ui.label", add.toString());
		Edge e1 = graph.addEdge("" + id.incrementAndGet(), left.toString(), add.toString(), true);
		Edge e2 = graph.addEdge("" + id.incrementAndGet(), right.toString(), add.toString(), true);
		e1.setAttribute("Operation", new Constant("1"));
		e2.setAttribute("Operation", new Constant("-1"));
		return add;
	}

	@Override
	public Operation visitSin(Operation op) {
		Sin sin = new Sin(op.accept(this));
		Node added = graph.addNode(sin.toString());
		added.addAttribute("ui.label", sin.toString());
		graph.addEdge("" + id.incrementAndGet(), op.toString(), sin.toString(), true);
		return sin;
	}

	@Override
	public Operation visitCos(Operation op) {
		Cos cos = new Cos(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge("" + id.incrementAndGet(), op.toString(), cos.toString(), true);
		return cos;
	}

	@Override
	public Operation visitTan(Operation op) {
		Tan cos = new Tan(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge("" + id.incrementAndGet(), op.toString(), cos.toString(), true);
		return cos;
	}

	@Override
	public Operation visitAtan(Operation op) {
		Atan cos = new Atan(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge("" + id.incrementAndGet(), op.toString(), cos.toString(), true);
		return cos;
	}

	@Override
	public Operation visitAcos(Operation op) {
		Acos cos = new Acos(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge("" + id.incrementAndGet(), op.toString(), cos.toString(), true);
		return cos;
	}

	@Override
	public Operation visitAsin(Operation op) {
		Asin cos = new Asin(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge("" + id.incrementAndGet(), op.toString(), cos.toString(), true);
		return cos;
	}

	@Override
	public Operation visitSqrt(Operation op) {
		Sqrt cos = new Sqrt(op.accept(this));
		try
		{
			Node added = graph.addNode(cos.toString());
			added.addAttribute("ui.label", cos.toString());
			graph.addEdge("" + id.incrementAndGet(), op.toString(), cos.toString(), true);
			return cos;
		}
		catch (Exception e) { return cos; }
	}

	@Override
	public Operation visitLog(Operation op) {
		Log cos = new Log(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge("" + id.incrementAndGet(), op.toString(), cos.toString(), true);
		return cos;
	}

	@Override
	public Operation visitPow(Operation op, Operation exp) {
		Pow add = new Pow(op.accept(this), exp.accept(this));
		try
		{
			Node added = graph.addNode(add.toString());
			added.addAttribute("ui.label", add.toString());
			Edge e1 = graph.addEdge("" + id.incrementAndGet(), op.toString(), add.toString(), true);
			Edge e2 = graph.addEdge("" + id.incrementAndGet(), exp.toString(), add.toString(), true);
			e1.setAttribute("Operation", new Constant("1"));
			e2.setAttribute("Operation", new Constant("1"));
			return add;
		}
		catch (Exception e) { return add; }
	}

	@Override
	public Operation visitConst(String c) {
		Constant constant = new Constant(c);
		try {
			Node added = graph.addNode(constant.toString());
			added.addAttribute("ui.label", constant.toString());
			return constant;
		}
		catch (Exception e) { return constant; }
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
		graph.addEdge("" + id.incrementAndGet(), op.toString(), cos.toString(), true);
		return cos;
	}

	@Override
	public Operation visitExp(Operation op) {
		Exp cos = new Exp(op.accept(this));
		Node added = graph.addNode(cos.toString());
		added.addAttribute("ui.label", cos.toString());
		graph.addEdge("" + id.incrementAndGet(), op.toString(), cos.toString(), true);
		return cos;
	}
}
