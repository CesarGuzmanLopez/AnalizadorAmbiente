package LauCesar.AnalizadorAmbiente.views;

import java.awt.EventQueue;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class principal {

	private JFrame frmAnalizadorDeAmbiente;

	private panel_inicio panelinicio;
	private panel_analizar_datos pantallaAnalizar;
	private panel_log_datos pantallailogInicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal window = new principal();
					window.frmAnalizadorDeAmbiente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public principal() {
		initialize();
	}

	public principal(Ventanas_frame s) {
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAnalizadorDeAmbiente = new JFrame();
		frmAnalizadorDeAmbiente.setTitle("Analizador de Ambiente");
		frmAnalizadorDeAmbiente.setBounds(100, 100, 477, 373);
		frmAnalizadorDeAmbiente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelinicio = new panel_inicio(this);
		pantallaAnalizar = new panel_analizar_datos(this);
		pantallailogInicio = new panel_log_datos(this);

		frmAnalizadorDeAmbiente.getContentPane().add(panelinicio, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		frmAnalizadorDeAmbiente.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Guardar");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String texto = pantallailogInicio.textArea.getText();
				JFileChooser seleccion = new JFileChooser();
				int opcion = seleccion.showSaveDialog(null);
				if (opcion == JFileChooser.APPROVE_OPTION) {
					String ruta = seleccion.getSelectedFile().getAbsolutePath();
					try {
						FileWriter fw = new FileWriter(ruta);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(texto);
						bw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				String texto = pantallailogInicio.textArea.getText();
				JFileChooser seleccion = new JFileChooser();
				int opcion = seleccion.showSaveDialog(null);
				if (opcion == JFileChooser.APPROVE_OPTION) {
					String ruta = seleccion.getSelectedFile().getAbsolutePath();
					try {
						FileWriter fw = new FileWriter(ruta);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(texto);
						bw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		JMenu mnNewMenu_1 = new JMenu("Ayuda");
		menuBar.add(mnNewMenu_1);

	}

	public void cambiapantalla(Ventanas_frame Select) {
		pantallaAnalizar.setVisible(false);
		panelinicio.setVisible(false);
		pantallailogInicio.setVisible(false);

		frmAnalizadorDeAmbiente.getContentPane().removeAll();
		frmAnalizadorDeAmbiente.getContentPane().invalidate();
		switch (Select) {
		case analizar: {
			pantallaAnalizar.setVisible(true);
			frmAnalizadorDeAmbiente.getContentPane().add(pantallaAnalizar, BorderLayout.CENTER);

		}
			break;
		case inicio: {
			panelinicio.setVisible(true);
			frmAnalizadorDeAmbiente.getContentPane().add(panelinicio, BorderLayout.CENTER);
		}
			break;
		case log_datos: {
			pantallailogInicio.setVisible(true);

			frmAnalizadorDeAmbiente.getContentPane().add(pantallailogInicio, BorderLayout.CENTER);
		}
			break;
		default:
			throw new IllegalArgumentException("Ventana inexitente: " + Select);
		}
		frmAnalizadorDeAmbiente.getContentPane().revalidate();

	}

}
