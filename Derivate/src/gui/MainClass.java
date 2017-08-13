package gui;

import org.jgrapht.DirectedGraph;

import ast.Operation;
import tokenizer.AbstractTreeBuilder;
import tokenizer.GraphComputation;

public class MainClass {

	public static void main(String[] args) {
		AbstractTreeBuilder treeGenerator = new AbstractTreeBuilder("log(x+5)");
		Operation tree = treeGenerator.getTree();
		DirectedGraph <Operation, Operation> expressionGraph = tree.accept(new GraphComputation());
		System.out.println(expressionGraph);
		}

}
