package gui;
import java.io.IOException;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;

import ast.*;
import evaluation_environment.EvalStability;
import tokenizer.AbstractTreeBuilder;
import tokenizer.GraphComputation;
import tokenizer.TokenizerException;

public class Launcher {
	private static Graph graph = new SingleGraph("Numerical Analysis");
	
	public Launcher(){
		graph.addAttribute("ui.quality: 4");
		graph.addAttribute("ui.antialias");
		graph.addAttribute("ui.stylesheet", "node { fill-mode: dyn-plain; fill-color: yellow; stroke-mode: plain; stroke-color: black; size: 50px; text-alignment: center; text-size: 15; }");
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
	}
	
	private static void numericalStabilityTest(String operation, Double val, boolean matlab, MatlabEngine eng) throws TokenizerException, EngineException, InterruptedException{
		AbstractTreeBuilder treeGenerator = new AbstractTreeBuilder(operation);
		Operation tree = treeGenerator.getTree();
		GraphComputation comp = new GraphComputation(graph);
		try { tree.accept(comp); } catch (Exception e) { e.printStackTrace(); }
		EvalStability eval = new EvalStability(graph, val);
		eval.eval(matlab,eng);
		for (Operation op : eval.getUnstable())
			System.err.println(op.toString());
	}
	
	public void launch(String input, Double val, boolean matlab, MatlabEngine eng) throws TokenizerException, InterruptedException, EngineException, IOException {
		numericalStabilityTest(input, val, matlab, eng);
	}
	
	public static void main(String[] args) throws InterruptedException{
		Gui frame = new Gui();
		frame.setVisible(true);
	}

	public Graph getGraph() {
		return graph; 
	}

}
