package LauCesar.AnalizadorAmbiente.Interfaces;

 
import java.util.List;

import LauCesar.AnalizadorAmbiente.Exceptions.ExcepcionLectura;
import LauCesar.AnalizadorAmbiente.Modelos.DatoSensor;

public interface Lectura_datos_Sensor {

	static boolean FuncionaSensorTemperaturaHumedad = true;
	static boolean FuncionaSensorPolvo = true;
	static boolean FuncionaSensorGas = true;
	abstract public boolean Resume() throws ExcepcionLectura;
	abstract public boolean PausaLectura() throws ExcepcionLectura;
	abstract public boolean Finalizar();
	abstract public String ImprimeDatos();
	abstract public DatoSensor[] TodosLosDatos();
	abstract public DatoSensor[] TodosLosDatosTemperatura();
	abstract public DatoSensor[] TodosLosDatosHumedad();
	abstract public DatoSensor[] TodosLosDatospolvo();
	abstract public DatoSensor[] TodosLosDatosGas();
	abstract public List<String> getPuertos();  
}
