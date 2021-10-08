package LauCesar.AnalizadorAmbiente.views;

import java.awt.EventQueue;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Graficar extends JFrame {

	/**
	 * 
 	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graficar frame = new Graficar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Graficar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		XYSeriesCollection data = new XYSeriesCollection();
		//SensorEstatico tempe = new SensorEstatico("temperatura");
		//data.addSeries(tempe);
		JFreeChart chart = ChartFactory.createXYStepChart("temperatura", "x", "y", data, PlotOrientation.VERTICAL, true,
				true, false);

		ChartPanel panel = new ChartPanel(chart);

		
		panel.setBackground(Color.WHITE);
		panel.setBounds(53, 100, 503, 307);

		contentPane.add(panel);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	tempe.reset();
			}
		});
		btnNewButton.setBounds(29, 26, 89, 23);
		contentPane.add(btnNewButton);

		setVisible(true);
	}
}


