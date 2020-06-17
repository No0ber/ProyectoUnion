package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Consultas {
	
	private static String usuarioConectado; //Contiene el usuario conectado para su uso posterior.
	
	//----------Método de registro----------
	public void registro(String nombre, String password, String telefono, String email) throws Exception {
		Connection conexion = Conexiones.conectar();
		
		//Sentencia para inserción de usuario nuevo.
		String sentenciaResgitro = "INSERT INTO usuario (username, pass, telefono, email, newmsg) VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement sentencia = null;
		
		try {
			sentencia = conexion.prepareStatement(sentenciaResgitro);
			sentencia.setString(1, nombre);
			sentencia.setBytes(2, Cifrado.cifra(password));
			sentencia.setString(3, telefono);
			sentencia.setString(4, email);
			sentencia.setBoolean(5, false);
			sentencia.executeUpdate();
			sentencia.close();
			
			System.out.println("Registro externo satisfactorio.");
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		  
		}
		
		Conexiones.desconectar(conexion);
	}
	
	//----------Método de login----------
	public boolean login(String usuario, String contraseña) throws Exception {
		Connection conexion = Conexiones.conectar();
		
		//Sentencias para comprobar usuario y contraseña del login.
		String sentenciaUser = "SELECT username FROM usuario WHERE username = ?";
		String sentenciaPass = "SELECT username FROM usuario WHERE pass = ?";
		
		PreparedStatement sentencia = null;
		ResultSet resultado = null;

		boolean loginCorrecto = false;
		boolean estaRegistrado = false;
		boolean contrasenaBien = false;
		
		try {
			sentencia = conexion.prepareStatement(sentenciaUser);
			sentencia.setString(1, usuario);
			resultado = sentencia.executeQuery();
			
			if(resultado.next()) {
				estaRegistrado = true;
				
			}else {
				estaRegistrado = false;
				
			}
			sentencia.close();
			resultado.close();
			
			sentencia = conexion.prepareStatement(sentenciaPass);
			sentencia.setBytes(1, Cifrado.cifra(contraseña));
			resultado = sentencia.executeQuery();
			
			if(resultado.next()) {
				contrasenaBien = true;
			}else {
				contrasenaBien = false;
			}
			
			if(estaRegistrado & contrasenaBien) {
				System.out.println("Login correcto.");
				loginCorrecto = true;
				usuarioConectado = usuario;
				listarContactos(usuarioConectado); //Almacena los contactos.
				listarConversaciones(usuarioConectado); //Almacena las conversaciones.
				listarNotas(usuarioConectado); //Almacena las notas.
				listarEventos(); //Almacena los titulos de eventos.
			}else {
				System.out.println("Login erróneo.");
				loginCorrecto = false;
			}
			
			sentencia.close();
			resultado.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
			
		}
		
		Conexiones.desconectar(conexion);
		return loginCorrecto;
	}
	
	//----------Método para agregar contactos----------
	public boolean addContactos(String contacto) throws Exception {
		Connection conexion = Conexiones.conectar();
		
		//Sentencia que comprueba si el usuario añadido existe.
		String sentenciaCheckUser = "SELECT username FROM usuario WHERE username = ?";
		//Sentencia que añade el contacto.
		String sentenciaAddContacto = "INSERT INTO contactos (username1, username2) VALUES (?, ?)";
		
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		boolean existe = false;
		
		try {
			//Busca que el usuario que se quiere añadr existe en la base de datos.
			sentencia = conexion.prepareStatement(sentenciaCheckUser);
			sentencia.setString(1, contacto);
			resultado = sentencia.executeQuery();
			
			//La variable boolean existe es un comprobador.
			if(resultado.next()) {
				existe = true;
			}else {
				existe = false;
			}
			sentencia.close();
			resultado.close();
			
			//Si existe el usuario a registrar, procede a añadirlo.
			if(existe) {
				sentencia = conexion.prepareStatement(sentenciaAddContacto);
				sentencia.setString(1, usuarioConectado);
				sentencia.setString(2, contacto);
				sentencia.executeUpdate();
				sentencia.close();
				
				sentencia = conexion.prepareStatement(sentenciaAddContacto);
				sentencia.setString(1, contacto);
				sentencia.setString(2, usuarioConectado);
				sentencia.executeUpdate();
				sentencia.close();
				
				//Cada vez que se añade un contacto se crea su conversación.
				crearConversacion(contacto);
				
				//Al añadir un nuevo contacto el array con los contactos se vuelve a rellenar para actualizarse.
				listarContactos(usuarioConectado);
				
				//Como se crean conversaciones al añadir contactos, es necesario actualizar el array con los ids de las conversaciones.
				listarConversaciones(usuarioConectado);
				
				System.out.println("Usuario añadido correctamente.");
			} else { //Si no, lo rechaza.
				System.out.println("Usuario no encontrado o inexistente.");
			}
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		  
		}
		
		Conexiones.desconectar(conexion);
		return existe;
	}
	
	//----------Método para listar los contactos----------
	//Este método se ejecuta en el método login() y addContactos().
	public void listarContactos(String usuarioConectado) throws Exception {
		Connection conexion = Conexiones.conectar();
		
		//Sentencia para obtener los contacto del usuario conectado.
		String sentenciaGetContactos = "SELECT username2 FROM contactos WHERE username1 = ?";
		
		//Vacia el array antes de usarlo para no crear datos duplicados.
		Arrays.clearArrayContactos();
		
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = conexion.prepareStatement(sentenciaGetContactos);
			sentencia.setString(1, usuarioConectado);
			resultado = sentencia.executeQuery();
			
			while(resultado.next()) {
				Arrays.addToArrayContactos(resultado.getString(1));
			}
			
			sentencia.close();
			resultado.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		  
		}
		
		Conexiones.desconectar(conexion);
	}
	
	//----------Método para comprobar si el contacto ya está añadido----------
	public boolean comprobarContacto(String contacto) throws Exception {
		Connection conexion = Conexiones.conectar();
		
		//Sentencia para verificar si el contacto está en la base de datos.
		String sentenciaCheckContacto = "SELECT * FROM contactos WHERE username1 = ? AND username2 = ?";
		
		boolean estaRegistrado = false;

		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = conexion.prepareStatement(sentenciaCheckContacto);
			sentencia.setString(1, contacto);
			sentencia.setString(2, usuarioConectado);
			resultado = sentencia.executeQuery();
			
			if(resultado.next()) {
				estaRegistrado = true;
			} else {
				estaRegistrado = false;
			}
			
			sentencia.close();
			resultado.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		  
		}
		
		Conexiones.desconectar(conexion);
		return estaRegistrado;
	}
	
	//----------Método para crear conversaciones----------
	//Este método se ejecuta al añadir contactos en addContactos().
	public void crearConversacion(String contacto) throws Exception {
		Connection conexion = Conexiones.conectar();
		
		int lastID = 1;
		
		//Sentencia para añadir una conversación a la tabla.
		String sentenciaAddConversacion = "INSERT INTO conversacion (idconversacion) VALUES (default)";
		//Sentencia para obtener el último registro id de conversación.
		String sentenciaGetLastConversacion = "SELECT idconversacion FROM conversacion ORDER BY idconversacion DESC LIMIT 1";
		//Sentencia que añade y relacion los usuarios con la conversacion correspondiente (la última creada).
		String sentenciaAddToParticipa = "INSERT INTO participa (username, idconversacion) VALUES (?, ?)";

		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = conexion.prepareStatement(sentenciaAddConversacion);
			sentencia.executeUpdate();
			sentencia.close();
			
			sentencia = conexion.prepareStatement(sentenciaGetLastConversacion);
			resultado = sentencia.executeQuery();
			
			if(resultado.next()) {
				lastID = resultado.getInt(1);
			}
			
			resultado.close();
			
			//Registra las relaciones de los usuarios con la conversación.
			sentencia = conexion.prepareStatement(sentenciaAddToParticipa);
			sentencia.setString(1, usuarioConectado);
			sentencia.setInt(2, lastID);
			sentencia.executeUpdate();
			
			sentencia = conexion.prepareStatement(sentenciaAddToParticipa);
			sentencia.setString(1, contacto);
			sentencia.setInt(2, lastID);
			sentencia.executeUpdate();
			
			sentencia.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		  
		}
		
		Conexiones.desconectar(conexion);
	}
	
	//----------Método para listar las conversaciones----------
	//Este método se ejecuta en el método login() y addContactos().
	public void listarConversaciones(String usuarioConectado) throws Exception {
		Connection conexion = Conexiones.conectar();
		
		//Sentencia que selecciona las conversaciones del usuario conectado.
		String sentenciaGetConversaciones = "SELECT idconversacion FROM	participa WHERE username = ?";
		
		//Vacia el array de conversaciones para que no haya duplicidad
		Arrays.clearArrayIdConver();
		
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = conexion.prepareStatement(sentenciaGetConversaciones);
			sentencia.setString(1, usuarioConectado);
			resultado = sentencia.executeQuery();
			
			while(resultado.next()) { //Añade los IDs de conversación del usuario conectado al array.
				Arrays.addIdToArrayConversaciones(resultado.getInt(1));
			}

			sentencia.close();
			resultado.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		  
		}
		
		Conexiones.desconectar(conexion);
	}
	
	//----------Método para enviar mensajess----------
	public void enviarMensaje(String mensaje, String destinatario) throws Exception {
		Connection conexion = Conexiones.conectar();
		
		
		int idConver = 0;
		
		//System.out.println(destinatario);
		//System.out.println(idConver);
		
		//Sentencia para registrar el mensaje.
		String sentenciaMandarMensaje = "INSERT INTO mensaje (username, idconversacion, texto) VALUES (?, ?, ?)";
		
		String sentenciaGetConversacion = "SELECT idconversacion FROM participa WHERE username = ? and idconversacion in(SELECT idconversacion FROM participa WHERE username = ?);";
		
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = conexion.prepareStatement(sentenciaGetConversacion);
			sentencia.setString(1, usuarioConectado);
			sentencia.setString(2, destinatario);
			resultado = sentencia.executeQuery();
			
			if(resultado.next()) {
				idConver = resultado.getInt(1);
			}
			
			sentencia = conexion.prepareStatement(sentenciaMandarMensaje);
			sentencia.setString(1, usuarioConectado);
			sentencia.setInt(2, idConver);
			sentencia.setString(3, mensaje);
			sentencia.executeUpdate();
			
			sentencia.close();
			resultado.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		  
		}
		
		Conexiones.desconectar(conexion);
	}
	
	//----------Método para listar mensajes----------
	public void listarMensajes(String destinatario) throws Exception {
		Connection conexion = Conexiones.conectar();
		
		int idConver = 0;
		
		String sentenciaGetConversacion = "SELECT idconversacion FROM participa WHERE username = ? and idconversacion in(SELECT idconversacion FROM participa WHERE username = ?);";
		
		String sentenciaGetMensajes = "SELECT username, texto FROM mensaje WHERE idconversacion = ?";
		
		Arrays.clearArrayMensajes();
		Arrays.clearArrayMensajeUser();
		
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = conexion.prepareStatement(sentenciaGetConversacion);
			sentencia.setString(1, usuarioConectado);
			sentencia.setString(2, destinatario);
			resultado = sentencia.executeQuery();
			
			if(resultado.next()) {
				idConver = resultado.getInt(1);
			}
			
			sentencia = conexion.prepareStatement(sentenciaGetMensajes);
			sentencia.setInt(1, idConver);
			resultado = sentencia.executeQuery();
			
			while(resultado.next()) {
				Arrays.addToArrayMensajesUser(resultado.getString(1));
				Arrays.addToArrayMensajes(resultado.getString(2));
			}
			
			
			sentencia.close();
			resultado.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		  
		}
		
		Conexiones.desconectar(conexion);
	}
	
	//----------Método para crear eventos----------
	public void crearEvento(String titulo, String descripcion, String fecha, String hora) throws Exception {
		Connection conexion = Conexiones.conectar();
		
		int lastId = 0;
		
		//Sentencia para añadir eventos con los parametros pasados.
		String sentenciaAddEvento = "INSERT INTO evento (titulo, descripcion, fecha, hora) VALUES (?, ?, ?, ?)";
		
		//Sentencia para seleccionar la última conversación.
		String sentenciaGetLastConver = "SELECT idevento FROM evento ORDER BY idevento DESC LIMIT 1";
		
		//Sentencia para relacionar el último evento con los participantes.
		String sentenciaRelacionarEventMiembros = "INSERT INTO crea (username, idevento) VALUES (?, ?)";
		
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = conexion.prepareStatement(sentenciaAddEvento);
			sentencia.setString(1, titulo);
			sentencia.setString(2, descripcion);
			sentencia.setString(3, fecha);
			sentencia.setString(4, hora);
			sentencia.executeUpdate();
			sentencia.close();
			
			if(Arrays.getEventMiembrosSize()==0) { //Añade solo al usuario.
				sentencia = conexion.prepareStatement(sentenciaGetLastConver);
				resultado = sentencia.executeQuery();
			
				if(resultado.next()) {
					lastId = resultado.getInt(1);
				}
				
				sentencia = conexion.prepareStatement(sentenciaRelacionarEventMiembros);
				sentencia.setString(1, usuarioConectado);
				sentencia.setInt(2, lastId);
				sentencia.executeUpdate();
				
			} else { //Añade a todos los usuario seleccionados.
				sentencia = conexion.prepareStatement(sentenciaGetLastConver);
				resultado = sentencia.executeQuery();
			
				if(resultado.next()) {
					lastId = resultado.getInt(1);
				}
				
				sentencia = conexion.prepareStatement(sentenciaRelacionarEventMiembros);
				sentencia.setString(1, usuarioConectado);
				sentencia.setInt(2, lastId);
				sentencia.executeUpdate();
				
				sentencia = conexion.prepareStatement(sentenciaRelacionarEventMiembros);
				
				for(int i=0;i<Arrays.getEventMiembrosSize();i++) {
					sentencia.setString(1, Arrays.getEventMiembroArray(i));
					sentencia.setInt(2, lastId);
					sentencia.executeUpdate();
				}
			}
			
			listarEventos();
			
			sentencia.close();
			resultado.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		  
		}
		
		Conexiones.desconectar(conexion);
	}
	
	//----------Método para listar eventos----------
	public void listarEventos() throws Exception {
		Connection conexion = Conexiones.conectar();
		
		String[] partes;
		
		//Sentencia para listar los eventos del usuario.
		String sentenciaListarEventos = "SELECT idevento FROM crea WHERE username = ?";
		
		//Sentencia para guardar los titulos de los eventos a los que pertenece el usuario conectado.
		String sentenciaGetEventTitulos = "SELECT titulo FROM evento WHERE idevento = ?";
		
		//Sentencia para guardar las fechas de los eventos a los que pertenece el usuario conectado.
		String sentenciagetEventFechas = "SELECT fecha FROM evento WHERE idevento = ?";
		
		//Sentencia para guardar las horas de los eventos a los que pertenece el usuario conectado.
		String sentenciagetEventHoras = "SELECT hora FROM evento WHERE idevento = ?";
		
		//Sentencia para guardar las horas de los eventos a los que pertenece el usuario conectado.
		String sentenciagetEventDescrip = "SELECT descripcion FROM evento WHERE idevento = ?";
		
		//Limpiar arrays antes de usarlos.
		Arrays.clearIdEventArray();
		Arrays.clearArrayEventNames();
		Arrays.clearArrayEventFechas();
		Arrays.clearArrayEventHoras();
		Arrays.clearArrayEventDescrip();
		Arrays.clearArrayEventDias();
		Arrays.clearArrayEventMeses();
		Arrays.clearArrayEventAnyos();
		
		PreparedStatement sentencia = null;
		PreparedStatement sentenciaTitulos = null;
		PreparedStatement sentenciaFechas = null;
		PreparedStatement sentenciaHoras = null;
		PreparedStatement sentenciaDescrip = null;
		ResultSet resultado = null;
		ResultSet resultadoTitulos = null;
		ResultSet resultadoFechas = null;
		ResultSet resultadoHoras = null;
		ResultSet resultadoDescrip = null;
		
		try {
			sentencia = conexion.prepareStatement(sentenciaListarEventos);
			sentencia.setString(1, usuarioConectado);
			resultado = sentencia.executeQuery();
			
			while(resultado.next()) {
				Arrays.addToArrayEvents(resultado.getInt(1));
			}
			
			sentencia.close();
			
			sentenciaTitulos = conexion.prepareStatement(sentenciaGetEventTitulos);
			sentenciaFechas = conexion.prepareStatement(sentenciagetEventFechas);
			sentenciaHoras = conexion.prepareStatement(sentenciagetEventHoras);
			sentenciaDescrip = conexion.prepareStatement(sentenciagetEventDescrip);
			
			for(int i=0;i<Arrays.getArrayEventIdSize();i++) {
				sentenciaTitulos.setInt(1, Arrays.getIdEventArray(i));
				resultadoTitulos = sentenciaTitulos.executeQuery();
				
				sentenciaFechas.setInt(1, Arrays.getIdEventArray(i));
				resultadoFechas = sentenciaFechas.executeQuery();
				
				sentenciaHoras.setInt(1, Arrays.getIdEventArray(i));
				resultadoHoras = sentenciaHoras.executeQuery();
				
				sentenciaDescrip.setInt(1, Arrays.getIdEventArray(i));
				resultadoDescrip = sentenciaDescrip.executeQuery();
				
				while(resultadoTitulos.next()) {
					Arrays.addToArrayEventNames(resultadoTitulos.getString(1));
				}
				
				while(resultadoFechas.next()) {
					Arrays.addToArrayEventFecha(resultadoFechas.getString(1));
				}
				
				while(resultadoHoras.next()) {
					Arrays.addToArrayEventHora(resultadoHoras.getString(1));
				}
				
				while(resultadoDescrip.next()) {
					Arrays.addToArrayEventDescrip(resultadoDescrip.getString(1));
				}
			}
			
			sentenciaTitulos.close();
			sentenciaFechas.close();
			sentenciaHoras.close();
			sentenciaDescrip.close();
			
			/*resultadoTitulos.close();
			resultadoFechas.close();
			resultadoHoras.close();
			resultadoDescrip.close();*/
			
			for(int i=0;i<Arrays.getArrayEventFechasSize();i++) {
				partes = Arrays.getEventFecha(i).split("/");
				
				Arrays.addToArrayEventDias(Integer.parseInt(partes[0]));
				Arrays.addToArrayEventMeses(Integer.parseInt(partes[1]));
				Arrays.addToArrayEventAnyos(Integer.parseInt(partes[2]));
			}
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		  
		}
		
		Conexiones.desconectar(conexion);
	}
	
	//----------Método para crear comentarios----------
	public void crearComentario(String comentario, String asunto, String titEvento) throws Exception {
		Connection conexion = Conexiones.conectar();
		
		//Sentencia que selecciona el id del evento donde se realiza el comentario.
		String sentenciaGetIdEvento = "SELECT idevento FROM evento WHERE titulo = ?";
		int idEvento = 0;
		
		//Sentencia para registrar un comentario nuevo.
		String sentenciaNewComentario = "INSERT INTO comentario (asunto, contenido) VALUES (?, ?)";
		
		//Sentencia que obtiene el último id comentario insertado.
		String sentenciaGetLastComentario = "SELECT idcomentario FROM comentario ORDER BY idcomentario DESC LIMIT 1";
		int lastComentario = 0;
		
		//Sentencia que crea la relación evento-usuario-comentario
		String sentenciaNewComentRelacion = "INSERT INTO envia (username, idevento, idcomentario) VALUES (?, ?, ?)";
		
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = conexion.prepareStatement(sentenciaGetIdEvento);
			sentencia.setString(1, titEvento);
			resultado = sentencia.executeQuery();
			
			if(resultado.next()) {
				idEvento = resultado.getInt(1);
			}
			
			sentencia = conexion.prepareStatement(sentenciaNewComentario);
			sentencia.setString(1, asunto);
			sentencia.setString(2, comentario);
			sentencia.executeUpdate();
			
			sentencia = conexion.prepareStatement(sentenciaGetLastComentario);
			resultado = sentencia.executeQuery();
			
			if(resultado.next()) {
				lastComentario = resultado.getInt(1);
			}
			
			resultado.close();
			
			sentencia = conexion.prepareStatement(sentenciaNewComentRelacion);
			sentencia.setString(1, usuarioConectado);
			sentencia.setInt(2, idEvento);
			sentencia.setInt(3, lastComentario);
			sentencia.executeUpdate();
			
			//listarComentarios(usuarioConectado);
			
			sentencia.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		  
		}
		
		Conexiones.desconectar(conexion);
	}
	
	//----------Método para listar comentarios----------
	public void listarComentarios(String titEvento) {
		Connection conexion = Conexiones.conectar();
		
		//Sentencia que selecciona el id del evento seleccionado.
		String sentenciaGetIdEvento = "SELECT idevento FROM evento WHERE titulo = ?";
		int idEvento = 0;
		
		//Sentencia que selecciona el usuario que ha mandado.
		String sentenciaGetComentUsers = "SELECT username, idcomentario FROM envia WHERE idevento = ?";
		
		//Sentencia que selecciona el asunto y contenido del comentario correspondiente.
		String sentenciaGetComentariosFull = "SELECT asunto, contenido FROM comentario WHERE idcomentario = ?";
		
		//Vacía los array para evitar duplicidades
		Arrays.clearArrayIdComentario();
		Arrays.clearArrayComentUser();
		Arrays.clearArrayComentarios();
		Arrays.clearArrayComentAsunto();
		
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = conexion.prepareStatement(sentenciaGetIdEvento);
			sentencia.setString(1, titEvento);
			resultado = sentencia.executeQuery();
			
			if(resultado.next()) {
				idEvento = resultado.getInt(1);
			}
			
			sentencia = conexion.prepareStatement(sentenciaGetComentUsers);
			sentencia.setInt(1, idEvento);
			resultado = sentencia.executeQuery();
			
			while(resultado.next()) {
				Arrays.addToArrayComentUser(resultado.getString(1));
				Arrays.addToArrayIdComentario(resultado.getInt(2));
			}
			
			sentencia = conexion.prepareStatement(sentenciaGetComentariosFull);
			for(int i=0;i<Arrays.getArrayIdComentarioSize();i++) {
				sentencia.setInt(1, Arrays.getIdComentario(i));
				resultado = sentencia.executeQuery();
				
				while(resultado.next()) {
					Arrays.addToArrayComentAsunto(resultado.getString(1));
					Arrays.addToArrayComentarios(resultado.getString(2));
				}
			}
			
			sentencia.close();
			resultado.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		  
		}
			
		Conexiones.desconectar(conexion);
	}
	
	//----------Método para crear notas----------
	public void crearNota(String nota) throws Exception {
		Connection conexion = Conexiones.conectar();
		
		//Sentencia para añadir notas.
		String sentenciaAddNota = "INSERT INTO notas (username, descripcion) VALUES (?, ?)";

		PreparedStatement sentencia = null;
		
		try {
			sentencia = conexion.prepareStatement(sentenciaAddNota);
			sentencia.setString(1, usuarioConectado);
			sentencia.setString(2, nota);
			sentencia.executeUpdate();
			
			listarNotas(usuarioConectado);
			
			sentencia.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		  
		}
		
		Conexiones.desconectar(conexion);
	}
	
	//----------Método para listar notas----------
	//Este método se ejecuta en login() y cada vez que se registra una nota nueva.
	public void listarNotas(String usuarioConectado) throws Exception {
		Connection conexion = Conexiones.conectar();
		
		//Sentencia para seleccionar las notas del usuario conectado.
		String sentenciaListarNotas = "SELECT descripcion FROM notas WHERE username = ?";
		
		//Vacía el array de notas para no provocar duplicidades.
		Arrays.clearArrayNotas();
		
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = conexion.prepareStatement(sentenciaListarNotas);
			sentencia.setString(1, usuarioConectado);
			resultado = sentencia.executeQuery();
			
			while(resultado.next()) {
				Arrays.addIdToArrayNotas(resultado.getString(1));
			}
			
			sentencia.close();
			resultado.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		  
		}
		
		Conexiones.desconectar(conexion);
	}
	
	//----------Método para borrar notas----------
	public void borrarNota(String nota) throws Exception {
		Connection conexion = Conexiones.conectar();
		
		//Sentencia para borrar la nota indicada.
		String sentenciaDeleteNota = "DELETE FROM notas WHERE descripcion = ?";
		
		PreparedStatement sentencia = null;
		
		try {
			sentencia = conexion.prepareStatement(sentenciaDeleteNota);
			sentencia.setString(1, nota);
			sentencia.executeUpdate();
			
			Arrays.clearArrayNotas();
			
			listarNotas(usuarioConectado);
			
			sentencia.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		  
		}
		
		Conexiones.desconectar(conexion);
	}
}
