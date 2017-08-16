package gui;
import org.jgrapht.graph.DefaultDirectedGraph;

import ast.*;
import tokenizer.AbstractTreeBuilder;
import tokenizer.GraphComputation;

public class MainClass {

	public static void main(String[] args) {
		AbstractTreeBuilder treeGenerator = new AbstractTreeBuilder("log(2x+5)");
		Operation tree = treeGenerator.getTree();
		DefaultDirectedGraph <String, String> expressionGraph = new DefaultDirectedGraph <String, String>(String.class);
		GraphComputation comp = new GraphComputation(expressionGraph);
		try { tree.accept(comp); } catch (Exception e) { e.printStackTrace();}
		System.out.println(expressionGraph);
	}

}
