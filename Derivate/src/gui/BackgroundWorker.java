package gui;

import java.util.concurrent.Future;

import javax.swing.SwingWorker;

import com.mathworks.engine.MatlabEngine;

public class BackgroundWorker extends SwingWorker<String, String> {
	Launcher l;
	String s;
	Double val;
	boolean matlab;
	Future<MatlabEngine> eng;
	
	public BackgroundWorker(Launcher l, String s, Double val, boolean matlab, Future<MatlabEngine> eng){
		if (s == null || s == null || val == null)
			throw new IllegalArgumentException();
		
		this.l = l;
		this.s = s;
		this.val = val;
		this.matlab = matlab;
		this.eng = eng;
	}
	@Override
	protected String doInBackground() throws Exception {
		GraphViewer graphViewer = new GraphViewer(l.getGraph());
		ToolBox tool = new ToolBox();
		tool.setVisible(true);
		graphViewer.setVisible(true);
		l.launch(s, val, matlab, eng.get());
		return null;
	}

}
