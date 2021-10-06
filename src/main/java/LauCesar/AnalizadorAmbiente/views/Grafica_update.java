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
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import LauCesar.AnalizadorAmbiente.Exceptions.ExcepcionLectura;
import LauCesar.AnalizadorAmbiente.Interfaces.Lectura_datos_Sensor;
import LauCesar.AnalizadorAmbiente.Modelos.DatoSensor;
import LauCesar.AnalizadorAmbiente.Modelos.tipodesensor;

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

	public Grafica_update(Lectura_datos_Sensor a) {

		Lectura_Sensores = a;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		XYSeriesCollection data = new XYSeriesCollection();

		SensorEstatico tempe = new SensorEstatico(tipodesensor.temperatura);
		data.addSeries(tempe);

		JFreeChart chart = ChartFactory.createXYStepChart("temperatura", "Tiempo", "Temperatura", data,
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel panel = new ChartPanel(chart);

		panel.setBackground(Color.WHITE);
		panel.setBounds(53, 100, 503, 307);

		contentPane.add(panel);

		setVisible(true);

		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		threadPool.submit(() -> {
			System.out.println(isVisible());
			while (isVisible()) {
			
				try {
					tempe.reset();
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExcepcionLectura e) {
					System.err.println("Hubo un error al intentar acceder a los datos ");
				}
			}
		});

	}

	private class SensorEstatico extends XYSeries {

		private static final long serialVersionUID = 1L;
		private int i;
		private tipodesensor key;

		public SensorEstatico(Comparable<tipodesensor> key) {
			super(key);
			i=0;
			this.key = (tipodesensor) key;
			System.out.println(this.key);

		}

		public void reset() throws ExcepcionLectura {

			while (getItemCount() > 40)
				this.remove(this.getMinX());
			DatoSensor[] LosDatos = Datos();
			for(;i<LosDatos.length;i++) {
				this.add(this.getMaxX() + 1, LosDatos[i].Dato);
			}

		}

		private DatoSensor[] Datos()  throws ExcepcionLectura {
			switch(key) {
			case gas:
				return Lectura_Sensores.TodosLosDatosGas();
			case humedad:
				return Lectura_Sensores.TodosLosDatosTemperatura();
			case polvo:
				return Lectura_Sensores.TodosLosDatosGas();
			default:
				 throw new ExcepcionLectura();
			
			}
		}
	}

}
