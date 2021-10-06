package LauCesar.AnalizadorAmbiente.SoloPruebasTensorflow;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class principal {

	private static final Instant INICIO = Instant.now();

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(4);
		ExecutorService executor2 = Executors.newSingleThreadExecutor();

		executor.submit(new principal.TareaSegundoPlano("manzana"));
		executor.submit(new principal.TareaSegundoPlano("Pera"));
		executor.submit(new principal.TareaSegundoPlano("apple"));
		System.out.println("pendejo");
		executor.submit(new principal.TareaSegundoPlano("2piza"));
		executor.submit(new principal.TareaSegundoPlano("2platano"));
		executor.submit(new principal.TareaSegundoPlano("2sandia"));
		ExecutorService executor3 = Executors.newSingleThreadExecutor();
		ExecutorService executor4 = Executors.newSingleThreadExecutor();

		executor.submit(new principal.TareaSegundoPlano("3manzana"));
		executor.submit(new principal.TareaSegundoPlano("3Pera"));
		executor.submit(new principal.TareaSegundoPlano("3apple"));
		System.out.println("bonita");
		executor.submit(new principal.TareaSegundoPlano("4piza"));
		executor.submit(new principal.TareaSegundoPlano("4platano"));
		executor.submit(new principal.TareaSegundoPlano("4rosas"));

		executor.shutdown();

		executor2.shutdown();
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
