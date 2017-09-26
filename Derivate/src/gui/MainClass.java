package gui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import com.mathworks.engine.EngineException;

import ast.*;
import evaluation_environment.EvalStability;
import tokenizer.AbstractTreeBuilder;
import tokenizer.GraphComputation;
import tokenizer.TokenizerException;

public class MainClass {
	private static Graph graph;
	
	private static void graphSettings(){
		graph.addAttribute("ui.quality: 4");
		graph.addAttribute("ui.antialias");
		graph.addAttribute("ui.stylesheet", "node { fill-mode: dyn-plain; fill-color: yellow; stroke-mode: plain; stroke-color: black; size: 50px; text-alignment: center; text-size: 15; }");
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
	}
	
	private static void numericalStabilityTest(String operation) throws TokenizerException, EngineException{
		graph = new SingleGraph("Numerical Analysis");
		AbstractTreeBuilder treeGenerator = new AbstractTreeBuilder(operation);
		Operation tree = treeGenerator.getTree();
		GraphComputation comp = new GraphComputation(graph);
		try { tree.accept(comp); } catch (Exception e) { e.printStackTrace(); }
		graphSettings();
		graph.display();
		EvalStability eval = new EvalStability(graph, 0d);
		eval.eval();
		for (Operation op : eval.getUnstable())
			System.err.println(op.toString());
	}
	
	public static void main(String[] args) throws TokenizerException, InterruptedException, EngineException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		numericalStabilityTest(reader.readLine());
	}

}
