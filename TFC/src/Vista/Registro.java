package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Registro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private TextPrompt placeholder;
	private JTextField textUsername;
	private JPasswordField textPass;
	private JPasswordField textPassConfirm;
	private JTextField textTelefono;
	private JTextField textEmail;
	private JButton btnRegistro;

	public Registro(Listener listener) {
		setTitle("Registraci\u00F3n");
		setBounds(100, 100, 450, 250);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblRegistro = new JLabel("Introduzca sus datos:");
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRegistro.setBounds(10, 23, 414, 14);
		contentPanel.add(lblRegistro);
		
		//Textfield para introducir el nombre de usuario.
		textUsername = new JTextField();
		textUsername.setBounds(10, 60, 194, 20);
		contentPanel.add(textUsername);
		textUsername.setColumns(10);
		placeholder = new TextPrompt("Nombre de usuario", textUsername);
	    placeholder.setForeground(Color.LIGHT_GRAY);
		
	    //Cuadros para introducir la contraseña y probar que es correcta.
		textPass = new JPasswordField();
		textPass.setBounds(230, 60, 194, 20);
		contentPanel.add(textPass);
		textPass.setColumns(10);
		placeholder = new TextPrompt("Contraseña", textPass);
	    placeholder.setForeground(Color.LIGHT_GRAY);
		
		textPassConfirm = new JPasswordField();
		textPassConfirm.setBounds(230, 91, 194, 20);
		contentPanel.add(textPassConfirm);
		textPassConfirm.setColumns(10);
		placeholder = new TextPrompt("Confirma la contraseña", textPassConfirm);
	    placeholder.setForeground(Color.LIGHT_GRAY);
		
	    //Campo para introducir el teléfono.
		textTelefono = new JTextField();
	    textTelefono.setDocument(new JTextFieldLimit(9));
		textTelefono.setBounds(10, 91, 194, 20);
		contentPanel.add(textTelefono);
		textTelefono.setColumns(10);
		placeholder = new TextPrompt("Teléfono", textTelefono);
	    placeholder.setForeground(Color.LIGHT_GRAY);
		
	    //Campo para introducir el correo electrónico.
		textEmail = new JTextField();
		textEmail.setBounds(10, 122, 414, 20);
		contentPanel.add(textEmail);
		textEmail.setColumns(10);
		placeholder = new TextPrompt("Correo electrónico", textEmail);
	    placeholder.setForeground(Color.LIGHT_GRAY);
		
	    //Botón de registro.
		btnRegistro = new JButton("Registrarse");
		btnRegistro.setBounds(167, 166, 98, 23);
		contentPanel.add(btnRegistro);
		btnRegistro.addActionListener(listener);
		btnRegistro.setActionCommand("Registro");
	}
	
	public String getUser() {
		return textUsername.getText();
	}
	
	public String getPass() {
		return textPass.getText();
	}
	
	public String getPassConfirm() {
		return textPassConfirm.getText();
	}
	
	public String getTelefono() {
		return textTelefono.getText();
	}
	
	public String getEmail() {
		return textEmail.getText();
	}
}
