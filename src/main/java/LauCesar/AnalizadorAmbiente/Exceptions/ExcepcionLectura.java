package LauCesar.AnalizadorAmbiente.Exceptions;

public class ExcepcionLectura extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExcepcionLectura(String a) {
	
		System.err.println("Error cono descargar "+a);
	}
}
