package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import Controlador.Controlador;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Interfaz extends JFrame {

	//Variables para mostrar el calendario del mes actual
	private static Calendar today = Calendar.getInstance();
	private static MonthPanel calendarioMesActual /*= new MonthPanel(today.get(Calendar.MONTH), today.get(Calendar.YEAR))*/;
	private static MonthPanel calendarioMesSiguiente /*= new MonthPanel(today.get(Calendar.MONTH)+1, today.get(Calendar.YEAR))*/;
		
	public static Listener listener;
	private JPanel contentPane;
	private static JPanel panelCalendarioActual, panelCalendarioSiguiente;
	private static Interfaz frame;
	private static Controlador controlador;
	private static JTextField txtTitulo;
	private static JTextField txtDescripcion;
	private static JTextField txtFecha;
	private static JTextField txtHora;
	private static JComboBox comBoxContactos;
	private static JList<String> listContactos;
	private static ArrayList<String> contactos, eventMiembros;
	
	private static DefaultListModel<String> modeloContactos = new DefaultListModel<>();
	
	public static void main(String[] args) {
		listener = new Listener();
		controlador = new Controlador();
		frame = new Interfaz(listener);
		
		//Visible();
	}

	public Interfaz(Listener listener) {
		setTitle("TFC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 768);
		setLocationRelativeTo(null);
		
		calendarioMesActual = new MonthPanel(today.get(Calendar.MONTH), today.get(Calendar.YEAR));
		calendarioMesSiguiente = new MonthPanel(today.get(Calendar.MONTH)+1, today.get(Calendar.YEAR));
		
		//Código para adaptar la ventana al diseño del sistema.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menú");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmContactos = new JMenuItem("Contactos");
		mnNewMenu.add(mntmContactos);
		mntmContactos.addActionListener(listener);
		mntmContactos.setActionCommand("Contactos");
		
		JMenuItem mntmNewNotas = new JMenuItem("Notas");
		mnNewMenu.add(mntmNewNotas);
		mntmNewNotas.addActionListener(listener);
		mntmNewNotas.setActionCommand("MostrarNotas");
		
		JMenuItem mntmDesconectar = new JMenuItem("Desconectar");
		mnNewMenu.add(mntmDesconectar);
		mntmDesconectar.addActionListener(listener);
		mntmDesconectar.setActionCommand("Desconectar");
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnNewMenu.add(mntmSalir);
		mntmSalir.addActionListener(listener);
		mntmSalir.setActionCommand("Salir");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Panel del calendario
		panelCalendarioActual = new JPanel();
		panelCalendarioActual.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Mes actual", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCalendarioActual.setBounds(10, 11, 217, 265);
		contentPane.add(panelCalendarioActual);
		
		/*panelCalendarioSiguiente = new JPanel();
		panelCalendarioSiguiente.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Mes siguiente", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCalendarioSiguiente.setBounds(237, 11, 217, 265);
		contentPane.add(panelCalendarioSiguiente);*/
		
		//Panel para la creación de eventos
		JPanel panelCrearEvento = new JPanel();
		panelCrearEvento.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Crear evento", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelCrearEvento.setBounds(10, 401, 526, 265);
		contentPane.add(panelCrearEvento);
		panelCrearEvento.setLayout(null);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(10, 40, 214, 20);
		panelCrearEvento.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(10, 96, 214, 20);
		panelCrearEvento.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo del evento");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 15, 214, 14);
		panelCrearEvento.add(lblTitulo);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n del evento");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(10, 71, 214, 14);
		panelCrearEvento.add(lblDescripcion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(319, 27, 197, 227);
		panelCrearEvento.add(scrollPane);
		
		listContactos = new JList<String>(modeloContactos);
		scrollPane.setViewportView(listContactos);

		comBoxContactos = new JComboBox();
		comBoxContactos.setBounds(10, 200, 214, 20);
		panelCrearEvento.add(comBoxContactos);
		
		JButton btnCrearEvento = new JButton("Crear evento");
		btnCrearEvento.setBounds(10, 231, 214, 23);
		panelCrearEvento.add(btnCrearEvento);
		btnCrearEvento.setActionCommand("CrearEvento");
		btnCrearEvento.addActionListener(listener);
		
		JButton btnPasar = new JButton(">");
		btnPasar.setBounds(234, 199, 75, 23);
		panelCrearEvento.add(btnPasar);
		btnPasar.setActionCommand("PasarContacto");
		btnPasar.addActionListener(listener);
		
		JLabel lblFecha = new JLabel("dd/mm/aaaa");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setBounds(10, 127, 96, 14);
		panelCrearEvento.add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setHorizontalAlignment(SwingConstants.CENTER);
		txtFecha.setDocument(new JTextFieldLimit(10));
		txtFecha.setBounds(10, 152, 96, 20);
		panelCrearEvento.add(txtFecha);
		txtFecha.setColumns(10);
		
		JLabel lblHora = new JLabel("hh:mm");
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setBounds(128, 127, 96, 14);
		panelCrearEvento.add(lblHora);
		
		txtHora = new JTextField();
		txtHora.setHorizontalAlignment(SwingConstants.CENTER);
		txtHora.setDocument(new JTextFieldLimit(5));
		txtHora.setBounds(128, 152, 96, 20);
		panelCrearEvento.add(txtHora);
		txtHora.setColumns(10);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(234, 231, 75, 23);
		panelCrearEvento.add(btnClear);
		btnClear.setActionCommand("ClearListaContactos");
		
		JButton btnVerEventos = new JButton("Ver eventos");
		btnVerEventos.setBounds(173, 677, 202, 23);
		contentPane.add(btnVerEventos);
		btnClear.addActionListener(listener);
		btnVerEventos.setActionCommand("VerEventos");
		btnVerEventos.addActionListener(listener);
	}
	
	public static void crearCalendarios() {
		panelCalendarioActual.add(calendarioMesActual);
		//panelCalendarioSiguiente.add(calendarioMesSiguiente);
	}
	
	public static void Visible(boolean miracion) {
		frame.setVisible(miracion);
	}
	
	//Se llama al método de la clase Listener para mostrar la ventana de registro porque en esa clase no puede ser static.
	public static void showRegistrar() {
		listener.showRegistrar();
	}
	
	//Métodos getter para obtener los datos del evento.
	public static String getTituloEvento() {
		return txtTitulo.getText();
	}
	
	public static String getDescripcionEvento() {
		return txtDescripcion.getText();
	}
	
	public static String getFechaEvento() {
		return txtFecha.getText();
	}
	
	public static String getHoraEvento() {
		return txtHora.getText();
	}
	
	//Setter para limpiar los textfield
	public static void limpiarTextFields() {
		txtTitulo.setText("");
		txtDescripcion.setText("");
		txtFecha.setText("");
		txtHora.setText("");
	}
	
	//Iguala el array
	public static void setComBoxContactos() {
		contactos = controlador.getArrayContactos();
		
		for(int i=0;i<contactos.size();i++) {
			comBoxContactos.addItem(contactos.get(i));
		}
	}
	
	//Funcionalidad del botón para pasar contactos.
	public static void addContactoToList() {
		modeloContactos.addElement(comBoxContactos.getSelectedItem().toString());
		Object seleccion = comBoxContactos.getSelectedItem();
		comBoxContactos.removeItem(seleccion);
	}
	
	//Funcionalidad botón clear.
	public static void clearListContactos() {
		modeloContactos.clear(); //Limpia la lista
		
		comBoxContactos.removeAllItems(); //Limpia el combobox
		
		setComBoxContactos(); //Vuelve a rellenar el combobox
	}
	
	//Limpia el combobox.
	public static void clearComBox() {
		comBoxContactos.removeAllItems();
	}
	
	//Llena un array con los participantes del evento creado.
	public static ArrayList<String> getEventContactosListArray() {
		eventMiembros = new ArrayList<String>();
		
		for(int i=0;i<modeloContactos.size();i++) {
			eventMiembros.add(modeloContactos.get(i));
		}
		
		return eventMiembros;
	}
	
	public static void setCalendars() {
		calendarioMesActual = new MonthPanel(today.get(Calendar.MONTH), today.get(Calendar.YEAR));
		calendarioMesSiguiente = new MonthPanel(today.get(Calendar.MONTH)+1, today.get(Calendar.YEAR));
			
	}
}
