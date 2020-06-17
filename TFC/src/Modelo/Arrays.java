package Modelo;

import java.util.ArrayList;

public class Arrays {
	private static ArrayList<String> contactos, notas, eventMiembros, eventTitulos, 
	eventFechas, eventHoras, eventDescrip, comentarios, comentUsers, comentAsunto,
	mensajes, mensajeUser;
	private static ArrayList<Integer> conversaciones, eventos, idComentarios, dias, meses, anyos;
	
	public Arrays() {
		contactos = new ArrayList<String>();
		conversaciones = new ArrayList<Integer>();
		notas = new ArrayList<String>();
		eventMiembros = new ArrayList<String>();
		eventos = new ArrayList<Integer>();
		eventTitulos = new ArrayList<String>();
		eventFechas = new ArrayList<String>();
		eventHoras = new ArrayList<String>();
		eventDescrip = new ArrayList<String>();
		comentarios = new ArrayList<String>();
		comentUsers = new ArrayList<String>();
		comentAsunto = new ArrayList<String>();
		idComentarios = new ArrayList<Integer>();
		mensajes = new ArrayList<String>();
		mensajeUser = new ArrayList<String>();
		dias = new ArrayList<Integer>();
		meses = new ArrayList<Integer>();
		anyos = new ArrayList<Integer>();
	}
	
	//Métodos de los array que almacenan los días, meses y años por separado para ser tratados en el calendario.
	public static ArrayList<Integer> getEventDias() {
		return dias;
	}
	
	public static ArrayList<Integer> getEventMeses() {
		return meses;
	}
	
	public static ArrayList<Integer> getEventAnyos() {
		return anyos;
	}
	
	public static void addToArrayEventDias(int dia) {
		dias.add(dia);
	}
	
	public static void addToArrayEventMeses(int mes) {
		meses.add(mes);
	}
	
	public static void addToArrayEventAnyos(int anyo) {
		anyos.add(anyo);
	}
	
	public static void clearArrayEventDias() {
		dias.clear();
	}
	
	public static void clearArrayEventMeses() {
		meses.clear();
	}
	
	public static void clearArrayEventAnyos() {
		anyos.clear();
	}
	
	//----------Métodos del array que contiene los contactos----------
	public static void addToArrayContactos(String contacto) { //Añadir contactos al array.
		contactos.add(contacto);
	}
	
	public static String getContactoFromArray(int posicion) { //Obtener un contacto específico.
		return contactos.get(posicion);
	}
	
	public static void clearArrayContactos() { //Limpia la lista de contactos.
		contactos.clear();
	}
	
	public static int getArrayContactosSize() { //Devuelve el número total de contactos.
		return contactos.size();
	}
	
	public ArrayList<String> getArrayContactos() { //Devuelve el array contactos.
		return contactos;
	}
	
	public static int getArrayContactosIndexOf(String contacto) {
		return contactos.indexOf(contacto);
	}
	
	//----------Métodos del array que contiene los IDs de las conversaciones del usuario conectado----------
	public static void addIdToArrayConversaciones(int IdConver) { //Añadir IDs al array.
		conversaciones.add(IdConver);
	}
	
	public static int getArrayIdConver(int posicion) {
		return conversaciones.get(posicion);
	}
	
	public static void clearArrayIdConver() { //Limpia la lista de IDs.
		conversaciones.clear();
	}
	
	public static int getArrayIdConverSize() { //Devuelve el número total de conversaciones.
		return conversaciones.size();
	}
	
	//----------Métodos del array que contiene las notas del usuario conectado----------
	public static void addIdToArrayNotas(String nota) { //Añadir notas al array.
		notas.add(nota);
	}
	
	public static String getNotaFromArray(int posicion) { //Obtener una nota en concreto.
		return notas.get(posicion);
	}
	
	public static void clearArrayNotas() { //Limpia la lista de notas.
		notas.clear();
	}
	
	public static int getArrayNotasSize() { //Devuelve el número total de notas.
		return notas.size();
	}
	
	public ArrayList<String> getArrayNotas() { //Devuelve el array notas.
		return notas;
	}
	
	//----------Métodos del array que contiene los participantes del evento creado----------
	public void setArrayEventMiembros(ArrayList<String> miembros) { //Establece el array a partir de otro.
		eventMiembros = miembros;
	}
	
	public static String getEventMiembroArray(int miembro) { //Obtiene un miembro del array.
		return eventMiembros.get(miembro);
	}
	
	public static int getEventMiembrosSize() { //Obtiene el número total de miembros del evento.
		return eventMiembros.size();
	}
	
	//----------Métodos del array que contiene los ids de los eventos del usuario conectado----------
	public static void addToArrayEvents(int idEvento) { //Añade Ids al array.
		eventos.add(idEvento);
	}
	
	public static int getIdEventArray(int posicion) { //Obtiene el id de evento especificado.
		return eventos.get(posicion);
	}
	
	public static int getArrayEventIdSize() { //Obtiene el tamaño total del array de ids de evento.
		return eventos.size();
	}
	
	public static void clearIdEventArray() {
		eventos.clear();
	}
	
	//----------Métodos del array que contiene los titulos de los eventos a los que pertenece el usuario----------
	public static void addToArrayEventNames(String nomEvento) { //Añade titulos de evento al array.
		eventTitulos.add(nomEvento);
	}
	
	public static int getArrayEventNamesSize() { //Obtiene el tamaño total del array de titulos de eventos del usuario conectado.
		return eventTitulos.size();
	}
	
	public ArrayList<String> getArrayEventNames() { //Devuelve el array con los nombres de eventos en los que participa el usuario.
		return eventTitulos;
	}
	
	public static void clearArrayEventNames() { //Limpia el array.
		eventTitulos.clear();
	}
	
	//----------Métodos del array que contienen las fechas de los eventos del usuario conectado----------
	public static void addToArrayEventFecha(String fechaEvent) {
		eventFechas.add(fechaEvent);
	}
	
	public ArrayList<String> getArrayEventFechas() {
		return eventFechas;
	}
	
	public static void clearArrayEventFechas() {
		eventFechas.clear();
	}
	
	public static int getArrayEventFechasSize() {
		return eventFechas.size();
	}
	
	public static String getEventFecha(int posicion) {
		return eventFechas.get(posicion);
	}
	
	//----------Métodos del array que contienen las horas de los eventos del usuario conectado----------
	public static void addToArrayEventHora(String horaEvent) {
		eventHoras.add(horaEvent);
	}
	
	public ArrayList<String> getArrayEventHoras() {
		return eventHoras;
	}
	
	public static void clearArrayEventHoras() {
		eventHoras.clear();
	}
	
	//----------Métodos del array que contienen las descripciones de los eventos del usuario conectado----------
	public static void addToArrayEventDescrip(String descripEvent) {
		eventDescrip.add(descripEvent);
	}
	
	public ArrayList<String> getArrayEventDescrip() {
		return eventDescrip;
	}
	
	public static void clearArrayEventDescrip() {
		eventDescrip.clear();
	}
	
	//----------Métodos del array que contienen los comentarios del evento especificado----------
	public static void addToArrayComentarios(String comentario) {
		comentarios.add(comentario);
	}
	
	public ArrayList<String> getArrayComentarios() {
		return comentarios;
	}
	
	public static void clearArrayComentarios() {
		comentarios.clear();
	}
	
	//----------Métodos del array que contienen los usuarios que han escrito los comentarios---------
	public static void addToArrayComentUser(String comentUser) {
		comentUsers.add(comentUser);
	}
	
	public ArrayList<String> getArrayComentUser() {
		return comentUsers;
	}
	
	public static void clearArrayComentUser() {
		comentUsers.clear();
	}
	
	//----------Métodos del array que contienen los asuntos de los comentarios---------
	public static void addToArrayComentAsunto(String asunto) {
		comentAsunto.add(asunto);
	}
	
	public ArrayList<String> getArrayComentAsunto() {
		return comentAsunto;
	}
	
	public static void clearArrayComentAsunto() {
		comentAsunto.clear();
	}
	
	//----------Métodos del array que contienen los ids de los comentarios del evento seleccionado---------
	public static void addToArrayIdComentario(int idComent) {
		idComentarios.add(idComent);
	}
	
	public static int getArrayIdComentarioSize() {
		return idComentarios.size();
	}
	
	public static void clearArrayIdComentario() {
		idComentarios.clear();
	}
	
	public static int getIdComentario(int posicion) {
		return idComentarios.get(posicion);
	}
	
	//----------Métodos del array que contienen los mensajes del chat seleccionado---------
	public ArrayList<String> getArrayMensajes() {
		return mensajes;
	}
	
	public static void addToArrayMensajes(String mensaje) {
		mensajes.add(mensaje);
	}
	
	public static void clearArrayMensajes() {
		mensajes.clear();
	}
	
	//----------Métodos del array que contienen los usuarios que han mandado los mensajes---------
	public static void addToArrayMensajesUser(String usuario) {
		mensajeUser.add(usuario);
	}
	
	public ArrayList<String> getArrayMensajeUser() {
		return mensajeUser;
	}
	
	public static void clearArrayMensajeUser() {
		mensajeUser.clear();
	}
}
