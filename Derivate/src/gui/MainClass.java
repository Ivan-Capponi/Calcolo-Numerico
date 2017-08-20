package gui;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import ast.*;
import tokenizer.AbstractTreeBuilder;
import tokenizer.GraphComputation;

public class MainClass {

	public static void main(String[] args) {
		AbstractTreeBuilder treeGenerator = new AbstractTreeBuilder("(sqrt(3x+8))/(x+3)");
		Operation tree = treeGenerator.getTree();
		Graph graph = new SingleGraph("Numerical Analysis");
		GraphComputation comp = new GraphComputation(graph);
		try { tree.accept(comp); } catch (Exception e) { e.printStackTrace(); }
		graph.addAttribute("ui.quality: 4");
		graph.addAttribute("ui.antialias");
		graph.addAttribute("ui.stylesheet", "node { fill-mode: dyn-plain; fill-color: yellow; stroke-mode: plain; stroke-color: black; size: 50px; text-alignment: above; text-size: 15; }");
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Viewer view = graph.display();
		view.enableAutoLayout();
	}

}
