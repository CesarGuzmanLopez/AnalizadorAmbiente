package LauCesar.AnalizadorAmbiente.SoloPruebasTensorflow;

import java.time.Duration;
import java.time.Instant;

public class principal {

	private static final Instant INICIO = Instant.now();

	public static void main(String[] args) {


	}

	private static void Log(Object mensaje) {
		System.out.println(String.format("%s [%s] %s", Duration.between(INICIO, Instant.now()),
				Thread.currentThread().getName(), mensaje.toString()));
	}

	static class TareaSegundoPlano implements Runnable {
		String Nombre;

		TareaSegundoPlano(String nombre) {
			this.Nombre = nombre;
		}
		@Override
		public void run() {
			Log("Inicia: " + Nombre);
			for (int i = 0; i < 100000; i++)
				System.out.print(i % 1000 == 0 ? i + " " + Nombre + " \n" : "");
			Log("finaliza: " + Nombre);
		}
	}

}
