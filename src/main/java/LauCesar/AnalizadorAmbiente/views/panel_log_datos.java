package LauCesar.AnalizadorAmbiente.views;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import LauCesar.AnalizadorAmbiente.controladores.LecturaSensor;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import LauCesar.AnalizadorAmbiente.Modelos.Puertos;

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
		pantallaPrincipal = a;
		
		setLayout(null);
		LecturaSensor Arduino = new LecturaSensor(textArea, "NOdo");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(170, 90, 253, 194);
		add(scrollPane);
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		JButton btnNewButton = new JButton("Pausar");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton.getText() == "Pausar") {
					Arduino.PausaLectura();
					btnNewButton.setText("Continuar");
				} else {
					Arduino.Resume();
					btnNewButton.setText("Pausar");
				}
			}
		});
		btnNewButton.setBounds(10, 110, 115, 40);
		add(btnNewButton);
		JButton btnNewButton_1 = new JButton("Iniciar");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExecutorService threadPool = Executors.newFixedThreadPool(3);
				threadPool.submit(Arduino);
				btnNewButton_1.setEnabled(false);
			}
		});
		btnNewButton_1.setBounds(10, 31, 115, 40);
		add(btnNewButton_1);
		JButton btnNewButton_2 = new JButton("Finalizar");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(false);
				Arduino.Finalizar();
			}
		});
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
		
		JComboBox<Puertos> comboBox = new JComboBox<Puertos>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem()!= Puertos.None) {
					btnNewButton_1.setEnabled(true);
					btnNewButton_2.setEnabled(true);
					btnNewButton.setEnabled(true);
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<Puertos>(Puertos.values()));
		comboBox.setBounds(205, 57, 70, 22);
		add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Puerto");
		lblNewLabel_1.setBounds(163, 57, 46, 14);
		add(lblNewLabel_1);
		JButton btnNewButton_4 = new JButton("Monitorear Datos");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Grafica_update(Arduino);
			}
		});
		btnNewButton_4.setBounds(285, 56, 130, 23);
		add(btnNewButton_4);

	}
}
