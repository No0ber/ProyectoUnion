package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

import Controlador.Controlador;

public class ComentariosEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitulo;
	private JTextField txtFecha;
	private JTextField txtHora;
	private JTextField txtDescrip;
	private static ArrayList<String> comentarios;
	private static ArrayList<String> usuarios;
	private static ArrayList<String> asuntos;
	
	private JList<String> listComentarios, listUsuarios, listAsunto;
	
	private static DefaultListModel<String> modeloComentarios = new DefaultListModel<>();
	private static DefaultListModel<String> modeloUsuarios = new DefaultListModel<>();
	private static DefaultListModel<String> modeloAsuntos = new DefaultListModel<>();

	public ComentariosEvento(Listener listener) {
		setTitle("Detalles y comentarios");
		setBounds(100, 100, 488, 392);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(20, 11, 200, 14);
		contentPanel.add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitulo.setEditable(false);
		txtTitulo.setBounds(20, 36, 200, 20);
		contentPanel.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.setHorizontalAlignment(SwingConstants.CENTER);
		txtFecha.setEditable(false);
		txtFecha.setBounds(250, 36, 86, 20);
		contentPanel.add(txtFecha);
		txtFecha.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setBounds(250, 11, 86, 14);
		contentPanel.add(lblFecha);
		
		txtHora = new JTextField();
		txtHora.setHorizontalAlignment(SwingConstants.CENTER);
		txtHora.setEditable(false);
		txtHora.setBounds(365, 36, 86, 20);
		contentPanel.add(txtHora);
		txtHora.setColumns(10);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setBounds(365, 11, 86, 14);
		contentPanel.add(lblHora);
		
		JLabel lblDescrip = new JLabel("Descripci\u00F3n");
		lblDescrip.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrip.setBounds(20, 77, 431, 14);
		contentPanel.add(lblDescrip);
		
		txtDescrip = new JTextField();
		txtDescrip.setEditable(false);
		txtDescrip.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescrip.setBounds(20, 102, 431, 20);
		contentPanel.add(txtDescrip);
		txtDescrip.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(222, 180, 229, 125);
		contentPanel.add(scrollPane);
		
		listComentarios = new JList<String>(modeloComentarios);
		scrollPane.setViewportView(listComentarios);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 180, 99, 125);
		contentPanel.add(scrollPane_1);
		
		listUsuarios = new JList<String>(modeloUsuarios);
		scrollPane_1.setViewportView(listUsuarios);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(118, 180, 105, 125);
		contentPanel.add(scrollPane_2);
		
		listAsunto = new JList<String>(modeloAsuntos);
		scrollPane_2.setViewportView(listAsunto);
		
		JButton btnNewComentario = new JButton("A\u00F1adir comentario");
		btnNewComentario.setBounds(102, 319, 265, 23);
		contentPanel.add(btnNewComentario);
		btnNewComentario.setActionCommand("NewComentario");
		btnNewComentario.addActionListener(listener);
	}
	
	public void setCamposEvent(String titulo, String fecha, String hora, String descripcion) {
		txtTitulo.setText(titulo);
		txtFecha.setText(fecha);
		txtHora.setText(hora);
		txtDescrip.setText(descripcion);
	}
	
	public void setListComentUsers() {
		comentarios = Controlador.getArrayComentarios();
		usuarios = Controlador.getArrayComentUser();
		asuntos = Controlador.getArrayComentAsunto();
		
		for(int i=0;i<comentarios.size();i++) {
			modeloComentarios.addElement(comentarios.get(i));
		}
		
		for(int j=0;j<usuarios.size();j++) {
			modeloUsuarios.addElement(usuarios.get(j));
		}
		
		for(int k=0;k<asuntos.size();k++) {
			modeloAsuntos.addElement(asuntos.get(k));
		}
	}
	
	public void clearListComentarios() {
		modeloComentarios.clear();
		modeloUsuarios.clear();
		modeloAsuntos.clear();
	}
}
