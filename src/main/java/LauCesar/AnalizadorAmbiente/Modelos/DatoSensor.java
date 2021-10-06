package LauCesar.AnalizadorAmbiente.Modelos;

public class DatoSensor {
	public double Dato;
	public tipodesensor sensor;

	public DatoSensor(double Dato, tipodesensor sensor) {
		this.Dato = Dato;
		this.sensor = sensor;
	}
	@Override
	public String toString() {
		return sensor.toString() + " dato: "+this.Dato+"\n";
	}
}
