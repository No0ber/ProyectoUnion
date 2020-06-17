package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Controlador;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class Eventos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JList<String> listTitulos, listFechas, listHoras;
	private static ArrayList<String> titulos;
	private static ArrayList<String> fechas;
	private static ArrayList<String> horas;
	private static ArrayList<String> descripciones;
	
	private static DefaultListModel<String> modeloTitulos = new DefaultListModel<>();
	private static DefaultListModel<String> modeloFechas = new DefaultListModel<>();
	private static DefaultListModel<String> modeloHoras = new DefaultListModel<>();

	public Eventos(Listener listener) {
		setTitle("Eventos");
		setBounds(100, 100, 436, 530);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo del evento");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 196, 14);
		contentPanel.add(lblTitulo);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setBounds(321, 11, 89, 14);
		contentPanel.add(lblHora);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setBounds(216, 11, 95, 14);
		contentPanel.add(lblFecha);
		
		JScrollPane scrollPaneTitulos = new JScrollPane();
		scrollPaneTitulos.setBounds(10, 30, 196, 415);
		contentPanel.add(scrollPaneTitulos);
		
		listTitulos = new JList<String>(modeloTitulos);
		scrollPaneTitulos.setViewportView(listTitulos);
		
		JScrollPane scrollPaneFechas = new JScrollPane();
		scrollPaneFechas.setBounds(216, 30, 95, 415);
		contentPanel.add(scrollPaneFechas);
		
		listFechas = new JList<String>(modeloFechas);
		listFechas.setEnabled(false);
		scrollPaneFechas.setViewportView(listFechas);
		
		JScrollPane scrollPaneHoras = new JScrollPane();
		scrollPaneHoras.setBounds(321, 30, 89, 415);
		contentPanel.add(scrollPaneHoras);
		
		listHoras = new JList<String>(modeloHoras);
		listHoras.setEnabled(false);
		scrollPaneHoras.setViewportView(listHoras);
		
		JButton btnDetallesEvent = new JButton("Ver detalles del evento");
		btnDetallesEvent.setBounds(216, 457, 194, 23);
		contentPanel.add(btnDetallesEvent);
		btnDetallesEvent.setActionCommand("VerDetallesEvento");
		
		JLabel lblSeleccion = new JLabel("Selecciona el evento");
		lblSeleccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccion.setBounds(10, 456, 196, 24);
		contentPanel.add(lblSeleccion);
		btnDetallesEvent.addActionListener(listener);
	}
	
	public void setListasEventThings() {
		titulos = Controlador.getArrayEventNames();
		fechas = Controlador.getArrayEventFechas();
		horas = Controlador.getArrayEventHoras();
		descripciones = Controlador.getArrayEventDescrip();
		
		for(int i=0;i<titulos.size();i++) {
			modeloTitulos.addElement(titulos.get(i));
			modeloFechas.addElement(fechas.get(i));
			modeloHoras.addElement(horas.get(i));
		}
	}
	
	public void clearListEventThings() {
		modeloTitulos.clear();
		modeloFechas.clear();
		modeloHoras.clear();
	}
	
	public int getSelectedEvent() {
		return titulos.indexOf(listTitulos.getSelectedValue());
	}
	
	//Getter para obtener los valores especificados.
	public String getSpecificEventName() {
		return listTitulos.getSelectedValue();
	}
	
	public String getSpecificFecha(int index) {
		return fechas.get(index);
	}
	
	public String getSpecificHora(int index) {
		return horas.get(index);
	}
	
	public String getSpecificDescrip(int index) {
		return descripciones.get(index);
	}
}
