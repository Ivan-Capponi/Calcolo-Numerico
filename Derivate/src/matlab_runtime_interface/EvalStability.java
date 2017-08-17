package matlab_runtime_interface;

import org.graphstream.graph.Graph;
import ast.Operation;
import org.graphstream.graph.Edge;
import java.util.Iterator;

public class EvalStability {
	private Graph graph;
	private Double val;
	
	public EvalStability(Graph graph, Double val){
		if (graph == null || val == null) throw new IllegalArgumentException("Invalid graph or value");
		this.graph = graph;
		this.val = val;
	}
	
	public void eval(){
		Iterator <Edge> it = graph.getEdgeIterator();
		
		while (it.hasNext()){
			Edge e = it.next();
			Operation op = e.getAttribute("Operation");
		}
	}
}
