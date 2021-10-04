package LauCesar.AnalizadorAmbiente.views;

import javax.swing.JFrame;


public class Graficar extends JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	public Graficar() {

		setTitle("Grafica");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 552, 463);

		double[] x = { 1, 2, 3 };
		double[] y = { 5, 4, 3 };

		// create your PlotPanel (you can use it as a JPanel)
	//	Plot2DPanel plot = new Plot2DPanel();

		// add a line plot to the PlotPanel
	//	plot.addLinePlot("my plot", x, y);

		setVisible(true);

	}
}
