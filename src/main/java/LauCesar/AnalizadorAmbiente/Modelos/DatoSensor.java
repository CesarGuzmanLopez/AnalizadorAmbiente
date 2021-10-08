package LauCesar.AnalizadorAmbiente.Modelos;

import java.text.Collator;

public class DatoSensor {
	public double Dato;
	public tipodesensor sensor;

	public DatoSensor(double Dato, tipodesensor sensor) {
		this.Dato = Dato;
		this.sensor = sensor;
	}

	/**
	 * 
	 * @param SensorDato
	 * @implNote Este dato debe estar en la forma 
	 *  Tipo: (float)Valor
	 */
	public DatoSensor(String SensorDato) {
		
		String Sensor,Dato;
		String[] Separado = SensorDato.split(":");
		
		Sensor =Separado[0];
		Dato   =Separado[1];
		
		Collator comparador = Collator.getInstance();
		if(comparador.compare(Sensor, "Temperatura") == 0) 
			this.sensor = tipodesensor.temperatura;
		else if(comparador.compare(Sensor, "Humedad") == 0)
			this.sensor = tipodesensor.humedad;
		else if(comparador.compare(Sensor, "Polvo") == 0)
			this.sensor = tipodesensor.polvo;
		else if(comparador.compare(Sensor, "Humo") == 0)
			this.sensor = tipodesensor.gas;
		this.Dato = Double.parseDouble(Dato);
		
	}
	@Override
	public String toString() {
		return sensor.toString() + " dato: " + this.Dato + "\n";
	}
}
