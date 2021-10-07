package LauCesar.AnalizadorAmbiente.controladores;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JTextArea;

import LauCesar.AnalizadorAmbiente.Interfaces.Lectura_datos_Sensor;
import LauCesar.AnalizadorAmbiente.Modelos.DatoSensor;
import LauCesar.AnalizadorAmbiente.Modelos.tipodesensor;
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
		int numdatos =  ATJ.getNumDatos();
		DatoSensor [] Todos = new DatoSensor[i];
		for(int i=0; i<numdatos; i++) {
			Todos[i]=ATJ.getData().get(i);
		}
		return Todos;
	}

	@Override
	public DatoSensor[] TodosLosDatosTemperatura() {
		List<DatoSensor> Datos = new LinkedList<DatoSensor>();
		int numdatos = ATJ.getNumDatos();
		for(int i=0; i<numdatos; i++) {
			if(ATJ.getData().get(i).sensor == tipodesensor.temperatura)
				Datos.add( ATJ.getData().get(i));
		}
		numdatos = Datos.size();
		DatoSensor [] Todos = new DatoSensor[numdatos];
		for(int i=0; i<numdatos; i++) {
			Todos[i]=Datos.get(i);
		}
		return Todos;
	}

	@Override
	public DatoSensor[] TodosLosDatosHumedad() {
		List<DatoSensor> Datos = new LinkedList<DatoSensor>();
		int numdatos = ATJ.getNumDatos();
		for(int i=0; i<numdatos; i++) {
			if(ATJ.getData().get(i).sensor == tipodesensor.humedad)
				Datos.add( ATJ.getData().get(i));
		}
		numdatos = Datos.size();
		DatoSensor [] Todos = new DatoSensor[numdatos];
		for(int i=0; i<numdatos; i++) {
			Todos[i]=Datos.get(i);
		}
		return Todos;
	}

	@Override
	public DatoSensor[] TodosLosDatospolvo() {
		List<DatoSensor> Datos = new LinkedList<DatoSensor>();
		int numdatos = ATJ.getNumDatos();
		for(int i=0; i<numdatos; i++) {
			if(ATJ.getData().get(i).sensor == tipodesensor.polvo)
				Datos.add( ATJ.getData().get(i));
		}
		numdatos = Datos.size();
		DatoSensor [] Todos = new DatoSensor[numdatos];
		for(int i=0; i<numdatos; i++) {
			Todos[i]=Datos.get(i);
		}
		return Todos;
	}

	@Override
	public DatoSensor[] TodosLosDatosGas() {
		List<DatoSensor> Datos = new LinkedList<DatoSensor>();
		int numdatos = ATJ.getNumDatos();
		for(int i=0; i<numdatos; i++) {
			if(ATJ.getData().get(i).sensor == tipodesensor.gas)
				Datos.add( ATJ.getData().get(i));
		}
		numdatos = Datos.size();
		DatoSensor [] Todos = new DatoSensor[numdatos];
		for(int i=0; i<numdatos; i++) {
			Todos[i]=Datos.get(i);
		}
		return Todos;
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
		String salida="";
		int numdatos =  ATJ.getNumDatos();
		for(int i=0; i<numdatos; i++) {
			salida +=" "+ATJ.getData().get(i).sensor +" "+ATJ.getData().get(i).Dato;
		}
		return salida;
	}
	@Override
	public void run() {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		threadPool.submit(ATJ);
		threadPool.shutdown();
	}
}
