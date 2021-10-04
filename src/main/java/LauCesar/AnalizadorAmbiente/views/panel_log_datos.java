package LauCesar.AnalizadorAmbiente.views;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 import javax.swing.JLabel;
import javax.swing.SwingConstants;

import LauCesar.AnalizadorAmbiente.test.LecturaSensor;

import java.awt.Font;
 import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class panel_log_datos extends JPanel {

	/**
	 * 
	 */
	principal pantallaPrincipal;
	

	
	
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	JTextArea textArea = new JTextArea();
	public panel_log_datos(principal a) {
		
		pantallaPrincipal=a;
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(170, 57, 253, 194);
		add(scrollPane);

		
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		JButton btnNewButton = new JButton("Pausar");
		btnNewButton.setBounds(10, 110, 115, 40);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Iniciar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LecturaSensor TodosLosDatos = new LecturaSensor();
				try {
					TodosLosDatos.ControladorDatos(textArea);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		btnNewButton_1.setBounds(10, 31, 115, 40);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Finalizar");
		btnNewButton_2.setBounds(10, 188, 115, 40);
		add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Lecura de datos");
		lblNewLabel.setFont(new Font("Lucida Sans", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(184, 11, 231, 39);
		add(lblNewLabel);
		
		JButton btnNewButton_3 = new JButton("Inicio");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaPrincipal.cambiapantalla(Ventanas_frame.inicio);
			}
		});
		btnNewButton_3.setBounds(20, 266, 89, 23);
		add(btnNewButton_3);
		

	}
}
