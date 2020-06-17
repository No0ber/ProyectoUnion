package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;

import Controlador.Controlador;

public class Chateo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMensaje;
	private JList<String> listMensajes;
	private ArrayList<String> mensajes = new ArrayList<String>();
	private ArrayList<String> usuarios = new ArrayList<String>();
	
	private static DefaultListModel<String> modeloMensajes = new DefaultListModel<>();
	
	public Chateo(Listener listener) {
		setBounds(100, 100, 450, 346);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 239);
		contentPanel.add(scrollPane);
		
		listMensajes = new JList<String>(modeloMensajes);
		scrollPane.setViewportView(listMensajes);
		
		txtMensaje = new JTextField();
		txtMensaje.setBounds(10, 255, 414, 20);
		contentPanel.add(txtMensaje);
		txtMensaje.setColumns(10);
		
		JButton btnEnviarMensaje = new JButton("Enviar mensaje");
		btnEnviarMensaje.setBounds(155, 279, 125, 23);
		contentPanel.add(btnEnviarMensaje);
		btnEnviarMensaje.setActionCommand("EnviarMensaje");
		btnEnviarMensaje.addActionListener(listener);
		
		Thread hilo = new Thread(runnable);
		hilo.start();
	}
	
	public void setListMensajes() {
		mensajes = Controlador.getArrayMensajes();
		usuarios = Controlador.getArrayMensajeUser();
		
		for(int i=0;i<mensajes.size();i++) {
			modeloMensajes.addElement(usuarios.get(i) + ": " + mensajes.get(i));
		}
	}
	
	public void clearListMensajes() {
		modeloMensajes.clear();
	}
	
	public String getMensaje() {
		return txtMensaje.getText();
	}
	
	public void clearTextFieldMensaje() {
		txtMensaje.setText("");
	}
	
	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(800);
					
					Controlador.listarMensajes(Contactos.getContactoToChat());
					
					clearListMensajes();
					
					setListMensajes();
					
				}catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	};
}
