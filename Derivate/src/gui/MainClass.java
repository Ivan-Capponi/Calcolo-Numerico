package gui;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import ast.*;
import evaluation_environment.EvalStability;
import tokenizer.AbstractTreeBuilder;
import tokenizer.GraphComputation;
import tokenizer.TokenizerException;

public class MainClass {

	public static void main(String[] args) throws TokenizerException, InterruptedException {
		AbstractTreeBuilder treeGenerator = new AbstractTreeBuilder("cos(2x)-(cos(x))^2");
		Operation tree = treeGenerator.getTree();
		Graph graph = new SingleGraph("Numerical Analysis");
		GraphComputation comp = new GraphComputation(graph);
		try { tree.accept(comp); } catch (Exception e) { e.printStackTrace(); }
		graph.addAttribute("ui.quality: 4");
		graph.addAttribute("ui.antialias");
		graph.addAttribute("ui.stylesheet", "node { fill-mode: dyn-plain; fill-color: yellow; stroke-mode: plain; stroke-color: black; size: 50px; text-alignment: above; text-size: 15; }");
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Viewer view = graph.display();
		View v = view.getDefaultView();
		v.getCamera().setAutoFitView(true);	
		Thread.sleep(5000);
		EvalStability eval = new EvalStability(graph, 0d);
		eval.eval();
	}

}
