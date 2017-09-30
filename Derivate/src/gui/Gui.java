package gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gui extends JFrame {
	private static final long serialVersionUID = -7164912363050202532L;
	private JPanel contentPane;
	private JTextField textField;
	private final JButton btnAnalyze = new JButton("Analyze");
	public boolean READY = false;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 * @param ready2 
	 */
	
	public void ready(){
		this.READY = true;
	}
	
	public String getText(){
		return textField.getText();
	}
	
	public Gui() {
		setAlwaysOnTop(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon/function.png"));
		setTitle("Numerical analysis for real functions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblInputAFunction = new JLabel("Input a function to analyze");
		JLabel label_1 = new JLabel("At:");
		lblInputAFunction.setFont(new Font("Cambria Math", Font.BOLD, 25));
		GridBagConstraints gbc_lblInputAFunction = new GridBagConstraints();
		gbc_lblInputAFunction.gridwidth = 6;
		gbc_lblInputAFunction.insets = new Insets(0, 0, 5, 5);
		gbc_lblInputAFunction.gridx = 2;
		gbc_lblInputAFunction.gridy = 1;
		contentPane.add(lblInputAFunction, gbc_lblInputAFunction);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JLabel lblFx = new JLabel("f(x) = ");
		lblFx.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		GridBagConstraints gbc_lblFx = new GridBagConstraints();
		gbc_lblFx.anchor = GridBagConstraints.EAST;
		gbc_lblFx.insets = new Insets(0, 0, 5, 5);
		gbc_lblFx.gridx = 1;
		gbc_lblFx.gridy = 2;
		contentPane.add(lblFx, gbc_lblFx);
		
		textField = new JTextField();
		textField.setFont(new Font("Cambria Math", Font.PLAIN, 12));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 6;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblAnalyzeAt = new JLabel("Analyze at:");
		lblAnalyzeAt.setFont(new Font("Cambria Math", Font.BOLD, 13));
		GridBagConstraints gbc_lblAnalyzeAt = new GridBagConstraints();
		gbc_lblAnalyzeAt.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnalyzeAt.gridx = 2;
		gbc_lblAnalyzeAt.gridy = 3;
		contentPane.add(lblAnalyzeAt, gbc_lblAnalyzeAt);
		
		JRadioButton rdbtnASpecificPoint = new JRadioButton("a specific point");
		JRadioButton rdbtnPositiveInfinity = new JRadioButton("positive infinity");
		JRadioButton rdbtnNegativeInfinity = new JRadioButton("negative infinity");
		rdbtnNegativeInfinity.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				rdbtnASpecificPoint.setSelected(false);
				rdbtnPositiveInfinity.setSelected(false);
				textField_1.setEnabled(false);
				label_1.setEnabled(false);
			}
		});
		rdbtnPositiveInfinity.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				rdbtnASpecificPoint.setSelected(false);
				rdbtnNegativeInfinity.setSelected(false);
				textField_1.setEnabled(false);
				label_1.setEnabled(false);
			}
		});
		rdbtnASpecificPoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				rdbtnPositiveInfinity.setSelected(false);
				rdbtnNegativeInfinity.setSelected(false);
				textField_1.setEnabled(true);
				label_1.setEnabled(true);
			}
		});
		rdbtnASpecificPoint.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		GridBagConstraints gbc_rdbtnASpecificPoint = new GridBagConstraints();
		gbc_rdbtnASpecificPoint.anchor = GridBagConstraints.WEST;
		gbc_rdbtnASpecificPoint.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnASpecificPoint.gridx = 3;
		gbc_rdbtnASpecificPoint.gridy = 3;
		contentPane.add(rdbtnASpecificPoint, gbc_rdbtnASpecificPoint);
		
		label_1.setEnabled(false);
		label_1.setFont(new Font("Cambria Math", Font.BOLD, 13));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 4;
		gbc_label_1.gridy = 3;
		contentPane.add(label_1, gbc_label_1);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 3;
		contentPane.add(textField_1, gbc_textField_1);
		
		rdbtnPositiveInfinity.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		GridBagConstraints gbc_rdbtnPositiveInfinity = new GridBagConstraints();
		gbc_rdbtnPositiveInfinity.anchor = GridBagConstraints.WEST;
		gbc_rdbtnPositiveInfinity.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnPositiveInfinity.gridx = 3;
		gbc_rdbtnPositiveInfinity.gridy = 4;
		contentPane.add(rdbtnPositiveInfinity, gbc_rdbtnPositiveInfinity);
		
		rdbtnNegativeInfinity.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		GridBagConstraints gbc_rdbtnNegativeInfinity = new GridBagConstraints();
		gbc_rdbtnNegativeInfinity.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNegativeInfinity.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNegativeInfinity.gridx = 3;
		gbc_rdbtnNegativeInfinity.gridy = 5;
		contentPane.add(rdbtnNegativeInfinity, gbc_rdbtnNegativeInfinity);
		
		JLabel label = new JLabel("Engine to use:");
		label.setFont(new Font("Cambria Math", Font.BOLD, 13));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 7;
		contentPane.add(label, gbc_label);
		
		JRadioButton rdbtnSoftwareEmbeddedfaster = new JRadioButton("software embedded (faster)");
		JRadioButton rdbtnMatlabEngine = new JRadioButton("matlab engine");
		rdbtnMatlabEngine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				rdbtnSoftwareEmbeddedfaster.setSelected(false);
			}
		});
		rdbtnSoftwareEmbeddedfaster.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				rdbtnMatlabEngine.setSelected(false);
			}
		});
		rdbtnSoftwareEmbeddedfaster.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		GridBagConstraints gbc_rdbtnSoftwareEmbeddedfaster = new GridBagConstraints();
		gbc_rdbtnSoftwareEmbeddedfaster.anchor = GridBagConstraints.WEST;
		gbc_rdbtnSoftwareEmbeddedfaster.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnSoftwareEmbeddedfaster.gridx = 3;
		gbc_rdbtnSoftwareEmbeddedfaster.gridy = 7;
		contentPane.add(rdbtnSoftwareEmbeddedfaster, gbc_rdbtnSoftwareEmbeddedfaster);
		
		rdbtnMatlabEngine.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		GridBagConstraints gbc_rdbtnMatlabEngine = new GridBagConstraints();
		gbc_rdbtnMatlabEngine.anchor = GridBagConstraints.WEST;
		gbc_rdbtnMatlabEngine.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMatlabEngine.gridx = 3;
		gbc_rdbtnMatlabEngine.gridy = 8;
		contentPane.add(rdbtnMatlabEngine, gbc_rdbtnMatlabEngine);
		GridBagConstraints gbc_btnAnalyze = new GridBagConstraints();
		gbc_btnAnalyze.anchor = GridBagConstraints.WEST;
		gbc_btnAnalyze.insets = new Insets(0, 0, 0, 5);
		gbc_btnAnalyze.gridx = 3;
		gbc_btnAnalyze.gridy = 9;
		contentPane.add(btnAnalyze, gbc_btnAnalyze);
		Gui thisOne = this;
		btnAnalyze.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (textField.getText().equals("") || (!rdbtnSoftwareEmbeddedfaster.isSelected() && !rdbtnMatlabEngine.isSelected()) || (!rdbtnASpecificPoint.isSelected() && !rdbtnPositiveInfinity.isSelected() && !rdbtnNegativeInfinity.isSelected()))
					return;
				else
				{
					Double val = null;
					try {
						if (textField_1.isEnabled())
							val = Double.valueOf(textField_1.getText());
					}
					catch(NumberFormatException exception) { return; }
					thisOne.dispose();
					BackgroundWorker bw = null;
					if (rdbtnASpecificPoint.isSelected())
						if (rdbtnSoftwareEmbeddedfaster.isSelected())
							bw = new BackgroundWorker(new Launcher(), textField.getText(), val, false);
						else
							bw = new BackgroundWorker(new Launcher(), textField.getText(), val, true);
					else if (rdbtnPositiveInfinity.isSelected())
						if (rdbtnSoftwareEmbeddedfaster.isSelected())
							bw = new BackgroundWorker(new Launcher(), textField.getText(), Double.POSITIVE_INFINITY, false);
						else
							bw = new BackgroundWorker(new Launcher(), textField.getText(), Double.POSITIVE_INFINITY, true);
					else if (rdbtnNegativeInfinity.isSelected())
								if (rdbtnSoftwareEmbeddedfaster.isSelected())
									bw = new BackgroundWorker(new Launcher(), textField.getText(), Double.NEGATIVE_INFINITY, false);
								else
									bw = new BackgroundWorker(new Launcher(), textField.getText(), Double.NEGATIVE_INFINITY, true);
					bw.execute();
				}
			}
		});
	}

}
