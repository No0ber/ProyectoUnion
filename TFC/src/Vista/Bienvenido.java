package Vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class Bienvenido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTextField txtUsername;
	private TextPrompt placeholder;
	private static JTextField textPassword;
	private JPasswordField passwordField;

	public Bienvenido(Listener listener) {
		setTitle("\u00A1Bienvenido!");
		setBounds(100, 100, 300, 240);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//----------Logeación----------
	    JLabel lblNewLabel = new JLabel("Inicio de sesi\u00F3n");
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setBounds(10, 22, 264, 18);
	    contentPanel.add(lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("");
		txtUsername.setBounds(39, 51, 210, 20);
		contentPanel.add(txtUsername);
		txtUsername.setColumns(10);
	    placeholder = new TextPrompt("Nombre de usuario", txtUsername);
	    placeholder.setForeground(Color.LIGHT_GRAY);
	    
	    textPassword = new JPasswordField();
	    textPassword.setBounds(39, 82, 210, 20);
	    contentPanel.add(textPassword);
	    placeholder = new TextPrompt("Contraseña", textPassword);
	    placeholder.setForeground(Color.LIGHT_GRAY);
	    
	    //----------Registración----------
	    JLabel lblRegistro = new JLabel("\u00BFNo tienes una cuenta? Reg\u00EDstrate aqu\u00ED.");
	    lblRegistro.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		//Muestra la ventana de registro cuando se pulsa el botón.
	    		Interfaz.showRegistrar();
	    	}
	    });
	    lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
	    lblRegistro.setBounds(10, 149, 264, 25);
	    contentPanel.add(lblRegistro);
	    lblRegistro.setForeground(Color.BLUE);
	    
	    JButton btnLogin = new JButton("Iniciar sesi\u00F3n");
	    btnLogin.setBounds(86, 113, 114, 23);
	    contentPanel.add(btnLogin);
	    btnLogin.addActionListener(listener);
	    btnLogin.setActionCommand("Logeando");

	}
	
	public String getUser() {
		return txtUsername.getText();
	}
	
	public String getPass() {
		return textPassword.getText();
	}
	
	public void deleteContent() {
		txtUsername.setText("");
		textPassword.setText("");
	}
}
