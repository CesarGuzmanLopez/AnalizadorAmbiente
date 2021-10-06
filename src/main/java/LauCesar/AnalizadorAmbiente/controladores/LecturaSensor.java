package LauCesar.AnalizadorAmbiente.controladores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JTextArea;

import LauCesar.AnalizadorAmbiente.Interfaces.Lectura_datos_Sensor;
import LauCesar.AnalizadorAmbiente.Modelos.DatoSensor;
import LauCesar.AnalizadorAmbiente.services.ArduinoToJava;

public class LecturaSensor implements Lectura_datos_Sensor, Runnable {
	JTextArea AreaText;
	ArduinoToJava ATJ;
	boolean pausa;
	volatile int i;

	public LecturaSensor(JTextArea a, String Puerto) {
		this.AreaText = a;
		ATJ = new ArduinoToJava(Puerto,a);
		i = 0;
		pausa =false;
	}
	@Override
	public DatoSensor[] TodosLosDatos() {
		return null;
	}

	@Override
	public DatoSensor[] TodosLosDatosTemperatura() {
		return null;
	}

	@Override
	public DatoSensor[] TodosLosDatosHumedad() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DatoSensor[] TodosLosDatospolvo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DatoSensor[] TodosLosDatosGas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean PausaLectura() {
		ATJ.Pause();
		pausa=true;
		return true;
	}
	@Override
	public boolean Resume() {
		ATJ.resume();
		pausa = false;
		return true;
	}
	@Override
	public boolean Finalizar() {
		ATJ.finalizar();
		return true;
	}
	@Override
	public String ImprimeDatos() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void run() {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		threadPool.submit(ATJ);
		threadPool.shutdown();
	}
}
