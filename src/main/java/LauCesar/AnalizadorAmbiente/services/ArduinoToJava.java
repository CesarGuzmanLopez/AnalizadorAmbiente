package LauCesar.AnalizadorAmbiente.services;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JTextArea;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import com.panamahitek.PanamaHitek_MultiMessage;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import LauCesar.AnalizadorAmbiente.Exceptions.DispositivoNoConectado;
import LauCesar.AnalizadorAmbiente.Exceptions.ExcepcionLectura;
import LauCesar.AnalizadorAmbiente.Modelos.*;

public class ArduinoToJava implements Runnable {
	private List<DatoSensor> Data;
	private Object o;
	private volatile boolean pause;
	private volatile boolean finalizado;
	private int numDatos;
	JTextArea text;
	protected PanamaHitek_Arduino ino;
	private Escucha_sensor listener;
	String Puerto;
	public boolean error = false;
	protected PanamaHitek_MultiMessage multi;

	public ArduinoToJava(String Puerto, JTextArea a) {
		Data = new LinkedList<DatoSensor>();
		pause = false;
		o = new Object();
		finalizado = false;
		numDatos = 0;
		this.text = a;
		this.Puerto = Puerto;
		ino = new PanamaHitek_Arduino();
		if (a != null)
			if (Puerto != null)
				inicializaSensor();
	}

	public ArduinoToJava(String Puerto) {
		Data = new LinkedList<DatoSensor>();
		pause = false;
		o = new Object();
		finalizado = false;
		numDatos = 0;
		ino = new PanamaHitek_Arduino();
		this.Puerto = Puerto;
		if (Puerto != null)
			inicializaSensor();

	}

	public ArduinoToJava() {
		Data = new LinkedList<DatoSensor>();
		pause = false;
		o = new Object();
		finalizado = false;
		numDatos = 0;
		ino = new PanamaHitek_Arduino();

	}

	public void inicializaTarde(String Puerto) {
		this.Puerto = Puerto;
		inicializaSensor();
	}

	public List<String> getPuertos() {
		return ino.getSerialPorts();
	}

	private void inicializaSensor() {
		listener = new Escucha_sensor();

		multi = new PanamaHitek_MultiMessage(3, ino);

		try {
			ino.arduinoRXTX(Puerto, 9600, listener);

			error = false;
		} catch (Exception e) {
			System.err.println("Aqui no esta el sensor");
			error = true;
		}
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
				System.err.println("Hubo un error en agregar Dato sensor" + e.getMessage());
			}

		}
		finalizado = true;

	}

	public void finalizar() {
		finalizado = true;
	}

	public List<DatoSensor> getData() {
		return Data;
	}

	public void AgregaUnDatoSensor() throws InterruptedException, DispositivoNoConectado, ExcepcionLectura {
		try {
			Thread.sleep(100);
			ino.sendByte('H');
			ino.sendByte('p');
			ino.sendByte('t');
			ino.sendByte('h');
			Thread.sleep(100);
		} catch (ArduinoException e) {
			throw new ExcepcionLectura(e.getMessage());
		} catch (SerialPortException e) {
			throw new DispositivoNoConectado(e.getMessage());
		}
		 
	}

	class Escucha_sensor implements SerialPortEventListener {
		@Override
		public void serialEvent(SerialPortEvent spe) {
			try {
				if (ino.isMessageAvailable()) {
					String mensaje=ino.printMessage();
 					numDatos++;
 					String[] Separado = mensaje.split(":");
 					if(Separado.length==2 && Separado[0].length()>1 && Separado[1].length()>1 ) {
 						DatoSensor x = new DatoSensor(mensaje);
 						Data.add(x);
 						text.append(x.toString());
 					}
				}
			} catch (SerialPortException | ArduinoException ex) {
				System.out.println("Hay un error" + ex.getMessage());
			}
		}
	};

}
