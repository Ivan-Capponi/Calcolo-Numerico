package gui;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.graphstream.graph.Graph;
import org.graphstream.ui.j2dviewer.J2DGraphRenderer;
import org.graphstream.ui.swingViewer.GraphRenderer;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;

public class GraphViewer extends JFrame {

	private static final long serialVersionUID = -2745382167280049898L;
	private JPanel contentPane;

	public GraphViewer(Graph graph) {
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage("/icon/function.png"));
		setTitle("Graph visualization");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 400);
		contentPane = new JPanel();
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		GraphRenderer renderer = new J2DGraphRenderer();
		viewer.addView("view", renderer, false);
		viewer.addDefaultView(false);
		viewer.enableAutoLayout();
		View view = viewer.getView("view");
		setContentPane(contentPane);
		contentPane.add((Component) view);
		GridBagConstraints gbc_lblTest = new GridBagConstraints();
		gbc_lblTest.gridx = 8;
		gbc_lblTest.gridy = 6;
		contentPane.add((Component) view, gbc_lblTest);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//this.setLocationRelativeTo(null);
	}

}
