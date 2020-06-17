package Controlador;

import java.util.ArrayList;

import Modelo.Arrays;
import Modelo.Cifrado;
import Modelo.Conexiones;
import Modelo.Consultas;
import Vista.Interfaz;

public class Controlador {
	private static Conexiones conexiones;
	private static Consultas consultas;
	private static Cifrado cifrado;
	private static Arrays arrays;
	
	public Controlador() {
		conexiones = new Conexiones();
		consultas = new Consultas();
		cifrado = new Cifrado();
		arrays = new Arrays();
	}
	
	//--------------------REGISTRO y LOGIN--------------------
	//Comunicaci�n del registro.
	public static void registro(String nombre, String password, String telefono, String email) throws Exception {
		consultas.registro(nombre, password, telefono, email);
	}
	
	//Comunicaci�n del login.
	public static boolean login(String usuario, String contrase�a) throws Exception {
		return consultas.login(usuario, contrase�a);
	}
	
	//--------------------Trato de CONTACTOS--------------------
	//Comunicaci�n para a�adir contactos.
	public static boolean addContactos(String contacto) throws Exception {
		return consultas.addContactos(contacto);
	}
	
	//Communicaci�n para comprobar un contacto.
	public static boolean comprobarContacto(String contacto) throws Exception {
		return consultas.comprobarContacto(contacto);
	}
	
	//Comunicaci�n para mover el array de contactos.
	public static ArrayList<String> getArrayContactos() {
		return arrays.getArrayContactos();
	}
	
	//--------------------Trato de EVENTOS--------------------
	//Comunicaci�n para crear eventos.
	public static void crearEvento(String titulo, String descripcion, String fecha, String hora) throws Exception {
		consultas.crearEvento(titulo, descripcion, fecha, hora);
	}
	
	public static void listarEventos() throws Exception {
		consultas.listarEventos();
	}
	
	//Comunicaci�n para mover el array de los miembros de un evento.
	public static void setArrayEventMiembros() {
		arrays.setArrayEventMiembros(Interfaz.getEventContactosListArray());
	}
	
	//Comunicaci�n para mover el array con los titulos de los eventos a los que pertenece el usuario conectado.
	public static ArrayList<String> getArrayEventNames() {
		return arrays.getArrayEventNames();
	}
	
	//Comunicaci�n para mover el array con las fechas de los eventos a los que pertenece el usuario conectado.
	public static ArrayList<String> getArrayEventFechas() {
		return arrays.getArrayEventFechas();
	}
	
	//Comunicaci�n para mover el array con las horas de los eventos a los que pertenece el usuario conectado.
	public static ArrayList<String> getArrayEventHoras() {
		return arrays.getArrayEventHoras();
	}
	
	//Comunicaci�n para mover el array con las descripciones de los eventos a los que pertenece el usuario conectado.
	public static ArrayList<String> getArrayEventDescrip() {
		return arrays.getArrayEventDescrip();
	}
	
	//--------------------Trato de COMENTARIOS--------------------
	public static void crearComentario(String comentario, String asunto, String titEvento) throws Exception {
		consultas.crearComentario(comentario, asunto, titEvento);
	}
	public static ArrayList<String> getArrayComentarios() {
		return arrays.getArrayComentarios();
	}
	
	public static ArrayList<String> getArrayComentUser() {
		return arrays.getArrayComentUser();
	}
	
	public static ArrayList<String> getArrayComentAsunto() {
		return arrays.getArrayComentAsunto();
	}
	
	public static void listarComentarios(String titEvento) {
		consultas.listarComentarios(titEvento);
	}
		
	//--------------------Trato de NOTAS--------------------
	//Comunicaci�n para crearo notas.
	public static void crearNota(String nota) throws Exception {
		consultas.crearNota(nota);
	}
	
	//Comunicaci�n para borrar notas.
	public static void borrarNota(String nota) throws Exception {
		consultas.borrarNota(nota);
	}
	
	//Comunicaci�n para mover el array de notas.
	public static ArrayList<String> getArrayNotas() {
		return arrays.getArrayNotas();
	}
	
	//--------------------Trato de MENSAJES--------------------
	public static void enviarMensaje(String mensaje, String destinatario) throws Exception {
		consultas.enviarMensaje(mensaje, destinatario);
	}
	
	public static void listarMensajes(String destinatario) throws Exception {
		consultas.listarMensajes(destinatario);
	}
	
	public static ArrayList<String> getArrayMensajes() {
		return arrays.getArrayMensajes();
	}
	
	public static ArrayList<String> getArrayMensajeUser() {
		return arrays.getArrayMensajeUser();
	}
	
	//--------------------Trato del CALENDARIO--------------------
	public static ArrayList<Integer> getEventDias() {
		return arrays.getEventDias();
	}
	
	public static ArrayList<Integer> getEventMeses() {
		return arrays.getEventMeses();
	}
	
	public static ArrayList<Integer> getEventAnyos() {
		return arrays.getEventAnyos();
	}
}