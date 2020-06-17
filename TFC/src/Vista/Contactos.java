package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JScrollPane;

import Controlador.Controlador;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Contactos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JList<String> listContactos;
	private static ArrayList<String> contactos;
	
	private static DefaultListModel<String> modeloContactos = new DefaultListModel<>();
	private JTextField txtNewContacto;

	public Contactos(Listener listener) {
		setTitle("Tus contactos");
		setBounds(100, 100, 396, 284);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 190, 185);
		contentPanel.add(scrollPane);
		
		listContactos = new JList<String>(modeloContactos);
		scrollPane.setViewportView(listContactos);
		
		JButton btnAddContacto = new JButton("A\u00F1adir contacto");
		btnAddContacto.setBounds(210, 207, 160, 23);
		contentPanel.add(btnAddContacto);
		btnAddContacto.setActionCommand("AñadirContacto");
		btnAddContacto.addActionListener(listener);
		
		JButton btnChatear = new JButton("Chatea");
		btnChatear.setBounds(10, 207, 190, 23);
		contentPanel.add(btnChatear);
		btnChatear.setActionCommand("AbrirChat");
		
		txtNewContacto = new JTextField();
		txtNewContacto.setHorizontalAlignment(SwingConstants.CENTER);
		txtNewContacto.setBounds(210, 176, 160, 20);
		contentPanel.add(txtNewContacto);
		txtNewContacto.setColumns(10);
		
		JLabel lblAddContacto = new JLabel("A\u00F1ade un contacto:");
		lblAddContacto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddContacto.setBounds(210, 151, 160, 14);
		contentPanel.add(lblAddContacto);
		btnChatear.addActionListener(listener);
	}
	
	public void setListContactos() {
		contactos = Controlador.getArrayContactos();
		
		for(int i=0;i<contactos.size();i++) {
			modeloContactos.addElement(contactos.get(i));
		}
	}
	
	public void clearListContactos() {
		modeloContactos.clear();
	}
	
	public static String getContactoToChat() {
		return listContactos.getSelectedValue();
	}
	
	public String getNewContacto() {
		return txtNewContacto.getText();
	}
	
	public void clearTxtNewContacto() {
		txtNewContacto.setText("");
	}
}
