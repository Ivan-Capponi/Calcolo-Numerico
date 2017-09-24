package evaluation_environment;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import com.mathworks.engine.EngineException;

import ast.Operation;
import org.graphstream.graph.Edge;

import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Iterator;

public class EvalStability {
	private Graph graph;
	private Double val;
	private DecimalFormat df = new DecimalFormat("#.####");
	
	public EvalStability(Graph graph, Double val){
		if (graph == null || val == null) throw new IllegalArgumentException("Invalid graph or value");
		this.graph = graph;
		this.val = val;
		df.setRoundingMode(RoundingMode.CEILING);
	}
	
	public void eval() throws EngineException{
		Iterator <Edge> it = graph.getEdgeIterator();
		LimitMatlab l = new LimitMatlab();
		
		while (it.hasNext()){
			Edge e = it.next();
			Operation op = e.getAttribute("Operation");
			System.out.println("OP: " + op.toString());
			
			if (op != null && e.getTargetNode().getAttribute("ui.color") != null && e.getTargetNode().getAttribute("ui.color") == Color.RED)
				continue;
			
			if (op != null){
				//Limit l = new Limit(op, val);
				l.setValue(op, val);
				if (l.exists()){
					double limitVal = l.getLimit();
					Node target = e.getTargetNode();
					
					if ((int) limitVal == Integer.MAX_VALUE)
					{
						e.addAttribute("ui.style", "fill-color: red;");
						target.setAttribute("ui.color", Color.RED);
						//target.setAttribute("ui.label", "INF");
						break;
					}
					target.setAttribute("ui.color", Color.GREEN);
					//target.setAttribute("ui.label", df.format(limitVal));
					e.addAttribute("ui.style", "fill-color: green;");
				}
				else
				{
					Node target = e.getTargetNode();
					e.addAttribute("ui.style", "fill-color: red;");
					target.setAttribute("ui.color", Color.RED);
					//target.setAttribute("ui.label", l.rightLimit());
				}
			}
		}
		
		l.close();
	}
	
	public Double getVal(){
		return val;
	}
	
	public Graph getGraph(){
		return graph;
	}
}
