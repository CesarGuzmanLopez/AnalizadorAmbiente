package LauCesar.AnalizadorAmbiente.services;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JTextArea;
import LauCesar.AnalizadorAmbiente.Modelos.*;

public class ArduinoToJava implements Runnable {
	private List<DatoSensor> Data;
	private Object o;
	private volatile boolean pause;

	private volatile boolean finalizado;
	private int numDatos;
	JTextArea text;

	public ArduinoToJava(String Puerto, JTextArea a) {
		Data = new LinkedList<DatoSensor>();
		pause = false;
		o = new Object();
		finalizado = false;
		numDatos = 0;
		this.text = a;

	}

	public ArduinoToJava(String Puerto) {
		Data = new LinkedList<DatoSensor>();
		pause = false;
		o = new Object();
		finalizado = false;
		numDatos = 0;
	}

	public boolean getFinalizado() {
		return finalizado;
	}

	public void Pause() {
		pause = true;
	}

	public int getNumDatos() {
		return numDatos;
	}

	public void resume() {
		pause = false;
		synchronized (o) {
			o.notifyAll();
		}
	}

	@Override
	public void run() {
		int i = 0;
		while (!Thread.currentThread().isInterrupted() && i++ < 10000 && !finalizado) {
			try {
				if (!pause)
					AgregaUnDatoSensor();
				else
					synchronized (o) {
						o.wait();
					}

			} catch (InterruptedException e) {
				System.err.println("Hubo un error al pausar la lectura de datos");
			} catch (Exception e) {
				System.err.println("Hubo un error en aggregar Dato sensor");
			}

		}
		finalizado = true;

	}

	public void finalizar() {
		finalizado = false;
	}

	public List<DatoSensor> getData() {
		return Data;
	}

	public void AgregaUnDatoSensor() throws InterruptedException {
		try {
			DatoSensor a, b, c;
			a = new DatoSensor(Math.random() * 10, tipodesensor.gas);
			b = new DatoSensor(Math.random() * 10, tipodesensor.temperatura);
			c = new DatoSensor(Math.random() * 10, tipodesensor.polvo);
			Data.add(a);
			Data.add(b);
			Data.add(c);
			numDatos += 3;
			if (text != null)
				text.append(a.toString() + b.toString() + c.toString());
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
		Thread.sleep(400);

	}

}
