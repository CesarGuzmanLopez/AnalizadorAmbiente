package LauCesar.AnalizadorAmbiente.views;

import java.awt.Color;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import LauCesar.AnalizadorAmbiente.Exceptions.ExcepcionLectura;
import LauCesar.AnalizadorAmbiente.Interfaces.Lectura_datos_Sensor;
import LauCesar.AnalizadorAmbiente.Modelos.DatoSensor;
import LauCesar.AnalizadorAmbiente.Modelos.tipodesensor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Grafica_update extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	JPanel contentPane;
	Lectura_datos_Sensor Lectura_Sensores;

	boolean first = true;
	JButton btnHumedad, btnTeperatura, B_Polvo, B_GAS;
	panelGrafica LastGrafica;

	public Grafica_update(Lectura_datos_Sensor a) {

		Lectura_Sensores = a;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		XYSeriesCollection datatempe = new XYSeriesCollection();
		XYSeriesCollection dataHumedad = new XYSeriesCollection();
		XYSeriesCollection dataPolvo = new XYSeriesCollection();
		XYSeriesCollection dataGas = new XYSeriesCollection();

		SensorEstatico tempe = new SensorEstatico(tipodesensor.temperatura);
		SensorEstatico Humedad = new SensorEstatico(tipodesensor.humedad);
		SensorEstatico Polvo = new SensorEstatico(tipodesensor.polvo);
		SensorEstatico Gas = new SensorEstatico(tipodesensor.gas);

		datatempe.addSeries(tempe);
		dataHumedad.addSeries(Humedad);
		dataPolvo.addSeries(Polvo); ;
		dataGas.addSeries(Gas);
		
		JFreeChart chartHumedad = ChartFactory.createXYStepChart("Humedad", "Tiempo", "Humedad",
				dataHumedad, PlotOrientation.VERTICAL, true, true, false);
		JFreeChart charttempe = ChartFactory.createXYStepChart("Temperatura", "Tiempo", "Temperatura",
				datatempe, PlotOrientation.VERTICAL, true, true, false);
		
		JFreeChart chartPolvo = ChartFactory.createXYStepChart("Particulas", "Tiempo", "Polvo",
				dataPolvo, PlotOrientation.VERTICAL, true, true, false);

		JFreeChart chartGas =ChartFactory.createXYStepChart("Gas", "Tiempo", "Gas", dataGas,
				PlotOrientation.VERTICAL, true, true, false);
		JFreeChart []TodasJFreeChart = new JFreeChart[10];
		
		TodasJFreeChart[0]=chartHumedad;
		TodasJFreeChart[1]=charttempe;
		TodasJFreeChart[2]=chartPolvo;
		TodasJFreeChart[3]=chartGas;
		for(int i=0; i<4;i++) {
			XYPlot plot = (XYPlot) TodasJFreeChart[0].getPlot();
			plot.getDomainAxis().setLabel("counts");
		}
		
		LastGrafica = new panelGrafica(ChartFactory.createXYStepChart("Temperatura", "Tiempo", "Temperatura", datatempe,
				PlotOrientation.VERTICAL, true, true, false));
		contentPane.add(LastGrafica);

		btnHumedad = new JButton("Humedad");
		btnHumedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activaBotones();
				btnHumedad.setEnabled(false);
				contentPane.invalidate();
				contentPane.remove(LastGrafica);
				LastGrafica = new panelGrafica(chartHumedad);
				contentPane.add(LastGrafica);
				contentPane.repaint();
				contentPane.validate();

			}
		});
		btnHumedad.setBounds(204, 36, 89, 47);

		contentPane.add(btnHumedad);

		btnTeperatura = new JButton("Teperatura");
		btnTeperatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activaBotones();
				btnTeperatura.setEnabled(false);

				contentPane.invalidate();
				contentPane.remove(LastGrafica);
				LastGrafica = new panelGrafica(charttempe);
				contentPane.add(LastGrafica);
				contentPane.repaint();
				contentPane.validate();
			}
		});
		btnTeperatura.setEnabled(false);
		btnTeperatura.setBounds(80, 36, 89, 47);
		contentPane.add(btnTeperatura);

		B_Polvo = new JButton("Polvo");
		B_Polvo.setBounds(340, 36, 89, 47);
		B_Polvo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activaBotones();
				B_Polvo.setEnabled(false);
				contentPane.invalidate();

				contentPane.remove(LastGrafica);
				LastGrafica = new panelGrafica(chartPolvo);
				contentPane.add(LastGrafica);
				contentPane.repaint();
				contentPane.validate();
			}
		});
		contentPane.add(B_Polvo);

		B_GAS = new JButton("Gas");
		B_GAS.setBounds(439, 36, 89, 47);
		B_GAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activaBotones();
				B_GAS.setEnabled(false);

				contentPane.invalidate();
				contentPane.remove(LastGrafica);
				LastGrafica = new panelGrafica(chartGas);
				contentPane.add(LastGrafica);
				contentPane.repaint();
				contentPane.validate();
			}
		});
		contentPane.add(B_GAS);

		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		threadPool.submit(() -> {
			setVisible(true);
		});
		threadPool.submit(() -> {
			// System.out.println(isVisible());
			while (isVisible() || first) {
				first = false;
				try {
					tempe.reset();
					Humedad.reset();
					Polvo.reset();
					Gas.reset();
					Thread.sleep(50);

				} catch (InterruptedException e) {
					// e.printStackTrace();
				} catch (ExcepcionLectura e) {
					System.err.println("Hubo un error al intentar acceder a los datos ");
				} catch (Exception e) {
					System.err.println("Error desconocido: " + e.getMessage());
				}
			}
		});

		threadPool.shutdown();
	}

	private void activaBotones() {
		btnHumedad.setEnabled(true);
		btnTeperatura.setEnabled(true);
		B_Polvo.setEnabled(true);
		B_GAS.setEnabled(true);
	}

	private class SensorEstatico extends XYSeries {
		private static final long serialVersionUID = 1L;
		private tipodesensor key;
		int todos;

		public SensorEstatico(Comparable<tipodesensor> key) {
			super(key);
			todos = 0;
			this.key = (tipodesensor) key;
		}

		public void reset() throws ExcepcionLectura {
			try {
				DatoSensor[] LosDatos = Datos();
				int size = LosDatos.length;
				if (todos >= size)
					return;
				while (this.getItemCount() > 40) {
					this.remove(getMinX());
				}
				int i = size < 40 ? 0 : (size - 40);
				for (; i < size; i++)
					add(i, LosDatos[i].Dato);
				todos = i;
			} catch (Exception e) {
				System.err.println("--" + e.getMessage());
			}
		}

		private DatoSensor[] Datos() throws ExcepcionLectura {
			switch (key) {
			case gas:
				return Lectura_Sensores.TodosLosDatosGas();
			case humedad:
				return Lectura_Sensores.TodosLosDatosHumedad();
			case polvo:
				return Lectura_Sensores.TodosLosDatospolvo();
			case temperatura:
				return Lectura_Sensores.TodosLosDatosTemperatura();
			default:
				throw new ExcepcionLectura();
			}
		}
	}

	class panelGrafica extends ChartPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public panelGrafica(JFreeChart chart) {
			super(chart);
			setBackground(Color.WHITE);
			setBounds(53, 100, 503, 307);
		}

	}
}
