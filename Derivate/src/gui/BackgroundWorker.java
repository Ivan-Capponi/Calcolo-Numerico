package gui;

import javax.swing.SwingWorker;

public class BackgroundWorker extends SwingWorker<String, String> {
	Launcher l;
	String s;
	Double val;
	
	public BackgroundWorker(Launcher l, String s, Double val){
		if (s == null || s == null || val == null)
			throw new IllegalArgumentException();
		
		this.l = l;
		this.s = s;
		this.val = val;
	}
	@Override
	protected String doInBackground() throws Exception {
		l.launch(s, val);
		return null;
	}

}
