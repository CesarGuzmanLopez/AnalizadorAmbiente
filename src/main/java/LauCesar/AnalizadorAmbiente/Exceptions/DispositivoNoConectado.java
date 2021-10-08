package LauCesar.AnalizadorAmbiente.Exceptions;

public class DispositivoNoConectado extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DispositivoNoConectado(String a) {
		System.err.println( "Dispositivo no encontrado: "+a);
	}
}
