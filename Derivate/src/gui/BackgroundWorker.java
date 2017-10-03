package gui;

import javax.swing.SwingWorker;

public class BackgroundWorker extends SwingWorker<String, String> {
	Launcher l;
	String s;
	Double val;
	boolean matlab;
	
	public BackgroundWorker(Launcher l, String s, Double val, boolean matlab){
		if (s == null || s == null || val == null)
			throw new IllegalArgumentException();
		
		this.l = l;
		this.s = s;
		this.val = val;
		this.matlab = matlab;
	}
	@Override
	protected String doInBackground() throws Exception {
		GraphViewer graphViewer = new GraphViewer(l.getGraph());
		ToolBox tool = new ToolBox();
		tool.setVisible(true);
		graphViewer.setVisible(true);
		l.launch(s, val, matlab);
		return null;
	}

}
