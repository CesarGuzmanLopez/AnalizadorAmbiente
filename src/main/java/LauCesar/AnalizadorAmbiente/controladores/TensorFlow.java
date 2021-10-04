package LauCesar.AnalizadorAmbiente.controladores;

import LauCesar.AnalizadorAmbiente.Exceptions.PaqueteriaNoEncontrada;
import LauCesar.AnalizadorAmbiente.Interfaces.Acceso_RED;
import LauCesar.AnalizadorAmbiente.Modelos.CalidadAmbiente;

public class TensorFlow implements Acceso_RED {

	@Override
	public float[] frecuenciasTemperatura() {
	 
		return null;
	}

	@Override
	public CalidadAmbiente Calidad_Aire() {

		return null;
	}

	@Override
	public CalidadAmbiente CapacidadBacteriana() {


		return null;
	}

	@Override
	public CalidadAmbiente ratioRespiratorio() {

		return null;
	}
	public TensorFlow() {
		
	}
	public void Analizar()throws PaqueteriaNoEncontrada {
		
	}

}
