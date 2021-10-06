package LauCesar.AnalizadorAmbiente.test;

import javax.swing.JTextArea;

import LauCesar.AnalizadorAmbiente.Exceptions.ExcepcionLectura;
import LauCesar.AnalizadorAmbiente.Interfaces.Lectura_datos_Sensor;
import LauCesar.AnalizadorAmbiente.Modelos.DatoSensor;
import LauCesar.AnalizadorAmbiente.Modelos.tipodesensor;

public class LecturaSensor implements Lectura_datos_Sensor {
	public LecturaSensor() {
		TodosLosDatos();
	}

	private DatoSensor[] TodosLosDatos;

	@Override
	public DatoSensor[] TodosLosDatos() {
		if (TodosLosDatos != null)
			return TodosLosDatos;

		TodosLosDatos = new DatoSensor[1000];
		int i = 0;
		for (; i < 333; i++) {
			TodosLosDatos[i] = new DatoSensor(Math.random() * 10, tipodesensor.gas);
		}
		for (; i < 666; i++) {
			TodosLosDatos[i] = new DatoSensor(Math.random() * 10, tipodesensor.temperatura);
		}
		for (; i < 1000; i++) {
			TodosLosDatos[i] = new DatoSensor(Math.random() * 10, tipodesensor.polvo);
		}
		return TodosLosDatos;
	}

	@Override
	public DatoSensor[] TodosLosDatosTemperatura() {
		// TODO Auto-generated method stub
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
	public String toString() {
		String Salida = "";
		for (DatoSensor a : TodosLosDatos) {
			Salida += "Sensor " + a.sensor + " Dato: " + a.Dato + "\n";
		}
		return Salida;
	}

	@Override
	public boolean Resume() throws ExcepcionLectura {

		throw new ExcepcionLectura();
		// false;
	}

	@Override
	public boolean PausaLectura() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Finalizar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String ImprimeDatos() {
		// TODO Auto-generated method stub
		return null;
	}

	public void ControladorDatos(JTextArea a) throws InterruptedException {
		String texto = "";
		for (DatoSensor b : TodosLosDatos) {
			texto += "sensor de: " + b.sensor + " " + b.Dato + "\n";
			a.setText(texto);
			Thread.sleep(2);
		}

	}

}
