package LauCesar.AnalizadorAmbiente.controladores;




import javax.swing.JTextArea;

import LauCesar.AnalizadorAmbiente.Interfaces.Lectura_datos_Sensor;
import LauCesar.AnalizadorAmbiente.Modelos.DatoSensor;

public class LecturaSensor implements Lectura_datos_Sensor {
	LecturaSensor(){
		
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
	public boolean LecturaDatos() {
		// TODO Auto-generated method stub
		return false;
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
	@Override
	public void ControladorDatos(JTextArea a) {
		// TODO Auto-generated method stub
		
	}

}
