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
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Controlador.Controlador;

public class Notas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNota;
	private static JList<String> list;
	private static ArrayList<String> notas;
	
	private static DefaultListModel<String> modeloNotas = new DefaultListModel<>();

	public Notas(Listener listener) {
		setTitle("Notas");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(214, 11, 210, 239);
		contentPanel.add(scrollPane);
		
		list = new JList<String>(modeloNotas);
		scrollPane.setViewportView(list);
		
		txtNota = new JTextField();
		txtNota.setBounds(10, 80, 194, 20);
		contentPanel.add(txtNota);
		txtNota.setColumns(10);
		
		JButton btnNewNota = new JButton("A\u00F1adir nota nueva");
		btnNewNota.setBounds(10, 111, 194, 23);
		contentPanel.add(btnNewNota);
		btnNewNota.setActionCommand("AddNota");
		btnNewNota.addActionListener(listener);
		
		JLabel lblAddNota = new JLabel("A\u00F1adir nota");
		lblAddNota.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNota.setBounds(10, 55, 194, 14);
		contentPanel.add(lblAddNota);
		
		JButton btnBorrarNota = new JButton("Borrar nota");
		btnBorrarNota.setBounds(10, 227, 194, 23);
		contentPanel.add(btnBorrarNota);
		btnBorrarNota.setActionCommand("BorrarNota");
		btnBorrarNota.addActionListener(listener);
	}
	
	//Getter para obtener la nota introducida.
	public String getNota() {
		return txtNota.getText();
	}
	
	//Limpia el textfield.
	public void limpiarTextField() {
		txtNota.setText("");
	}
	
	//Prepara la lista de notas.
	public void setListNotasWithNumbers() {
		notas = Controlador.getArrayNotas();
		
		for(int i=0;i<notas.size();i++) {
			modeloNotas.addElement((i+1)+". "+notas.get(i));
		}
	}
	
	public void setListNotas() {
		notas = Controlador.getArrayNotas();
		
		for(int i=0;i<notas.size();i++) {
			modeloNotas.addElement(notas.get(i));
		}
	}
	
	//Limpia la lista de notas.
	public void clearListNotas() {
		modeloNotas.clear();
	}
	
	//Getter para recoger una nota en específico de la lista.
	public String getListNota(int idNota) {
		return modeloNotas.get(idNota-1).toString();
	}
	
	//Elimina una nota.
	public void deteleNota(int idNota) {
		modeloNotas.remove(idNota-1);
	}
	
	public int getSizeListNotas() {
		return modeloNotas.size();
	}
}
