package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Controlador.Controlador;

public class Listener implements ActionListener {
	public Registro venRegistro = new Registro(this);
	public Bienvenido venBienvenido = new Bienvenido(this);
	public Contactos venContactos = new Contactos(this);
	public Notas venNotas = new Notas(this);
	public Eventos venEventos = new Eventos(this);
	public ComentariosEvento venComen = new ComentariosEvento(this);
	public Chateo venChat = new Chateo(this);
	
	public Listener() {
		venBienvenido.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			//Aquí se recogen todas las acciones de los botones de todas las ventanas.
			case "Contactos":
				venContactos.clearListContactos();
				venContactos.setListContactos();
				venContactos.setVisible(true);
				
				break;
				
			case "AñadirContacto":
				try {
					if(venContactos.getNewContacto().equals("")) {
						JOptionPane.showMessageDialog(null, "No puede existir un usuario en blanco.");
						
					} else if(Controlador.comprobarContacto(venContactos.getNewContacto())) {
						JOptionPane.showMessageDialog(null, "Este usuario ya es un contacto.");
						
					} else {
						Controlador.addContactos(venContactos.getNewContacto());
						
						JOptionPane.showMessageDialog(null, "Contacto añadido.");
						
						venContactos.clearListContactos();
						venContactos.setListContactos();
						venContactos.clearTxtNewContacto();
						
						Interfaz.clearComBox();
						Interfaz.setComBoxContactos();
					}
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
				break;
				
			case "PasarContacto":
				Interfaz.addContactoToList();
				
				break;
				
			case "ClearListaContactos":
				Interfaz.clearListContactos();
				
				break;
				
			case "MostrarNotas":
				venNotas.setListNotasWithNumbers();
				venNotas.setVisible(true);
				
				break;
				
			case "AddNota":
				try {
					if(venNotas.getNota().equals("")) {
						JOptionPane.showMessageDialog(null, "No puedes añadir una nota vacía.");
						
					} else {
						Controlador.crearNota(venNotas.getNota());
						
						JOptionPane.showMessageDialog(null, "Nota añadida correctamente.");
						
						venNotas.clearListNotas();
						venNotas.setListNotasWithNumbers();
						venNotas.limpiarTextField();
						
					}
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				break;
				
			case "BorrarNota":
				try {
					boolean bucle = false;
					
					while(!bucle) {
						int idNota = Integer.parseInt(JOptionPane.showInputDialog("¿Qué nota vas a borrar? Introduce su número."));
					
						if(idNota>venNotas.getSizeListNotas() || idNota<1) {
							JOptionPane.showMessageDialog(null, "No has introducido un número valido.");
							
						} else {
							venNotas.clearListNotas();
							venNotas.setListNotas();
							
							String nota = venNotas.getListNota(idNota);
							
							Controlador.borrarNota(nota);
							
							venNotas.deteleNota(idNota);
							venNotas.clearListNotas();
							venNotas.setListNotasWithNumbers();
							
							bucle = true;
							
						}
					}
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				break;
				
			case "CrearEvento":
				try {
					//Comprobación de que los campos están rellenados.
					if(Interfaz.getTituloEvento().equals("") || Interfaz.getDescripcionEvento().equals("") 
							|| Interfaz.getFechaEvento().equals("") || Interfaz.getHoraEvento().equals("")) {
						JOptionPane.showMessageDialog(null, "No puedes dejar ningún campo en blanco.");
						
					} else if (Interfaz.getFechaEvento().length()<10 || Interfaz.getHoraEvento().length()<5) {
						JOptionPane.showMessageDialog(null, "Debes introducir una fecha y hora correcta.");
					
					} else if (Interfaz.getEventContactosListArray().size()==0){ //Crea un evento donde solo participa el usuario conectado.
						Controlador.crearEvento(Interfaz.getTituloEvento(), Interfaz.getDescripcionEvento(), 
								Interfaz.getFechaEvento(), Interfaz.getHoraEvento());

						JOptionPane.showMessageDialog(null, "Evento propio creado satisfactoriamente.");
						
						Interfaz.setCalendars();
						
					}else { //Si está todo correcto y añado miembros, crea un evento conjunto.
						Controlador.setArrayEventMiembros();
						Controlador.crearEvento(Interfaz.getTituloEvento(), Interfaz.getDescripcionEvento(), 
								Interfaz.getFechaEvento(), Interfaz.getHoraEvento());
						
						JOptionPane.showMessageDialog(null, "Evento conjunto creado satisfactoriamente.");
						
						Interfaz.setCalendars();
						Interfaz.limpiarTextFields();
						Interfaz.clearListContactos();
						
					}
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				break;
				
			case "VerEventos":
				try {
					Controlador.listarEventos();
					
					venEventos.clearListEventThings();
					venEventos.setListasEventThings();
					venEventos.setVisible(true);
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				break;
				
			case "VerDetallesEvento":
				Controlador.listarComentarios(venEventos.getSpecificEventName());
				
				venComen.clearListComentarios();
				venComen.setListComentUsers();
				venComen.setCamposEvent(venEventos.getSpecificEventName(), venEventos.getSpecificFecha(venEventos.getSelectedEvent()), 
						venEventos.getSpecificHora(venEventos.getSelectedEvent()), venEventos.getSpecificDescrip(venEventos.getSelectedEvent()));
				venComen.setVisible(true);
				
				break;
				
			case "NewComentario":
				try {
					boolean bucle = false;
					
					while(!bucle) {
						String asunto = JOptionPane.showInputDialog("Introduce el asunto de tu comentario.");
						String comentario = JOptionPane.showInputDialog("Añada su comentario.");
						
						if(asunto.equals("") || comentario.equals("")) {
							JOptionPane.showMessageDialog(null, "No puedes dejar los campos vacíos.");
							
						} else {
							Controlador.crearComentario(comentario, asunto, venEventos.getSpecificEventName());
							
							JOptionPane.showMessageDialog(null, "Comentario enviado.");
							
							bucle = true;
						}
					}
					
					Controlador.listarComentarios(venEventos.getSpecificEventName());
					
					venComen.clearListComentarios();
					venComen.setListComentUsers();
					venComen.setCamposEvent(venEventos.getSpecificEventName(), venEventos.getSpecificFecha(venEventos.getSelectedEvent()), 
							venEventos.getSpecificHora(venEventos.getSelectedEvent()), venEventos.getSpecificDescrip(venEventos.getSelectedEvent()));
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
				break;
				
			case "AbrirChat":
				try {
					venChat.setTitle("Chateando con "+venContactos.getContactoToChat());
					
					Controlador.listarMensajes(venContactos.getContactoToChat());
					
					venChat.clearListMensajes();
					venChat.setListMensajes();
					venChat.setVisible(true);
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
				break;
				
			case "EnviarMensaje":
				try {
					if(venChat.getMensaje().equals("")) {
						JOptionPane.showMessageDialog(null, "Enviar mensajes vacíos está feo.");
						
					} else {
						Controlador.enviarMensaje(venChat.getMensaje(), venContactos.getContactoToChat());
						Controlador.listarMensajes(venContactos.getContactoToChat());
						
						venChat.clearListMensajes();
						venChat.setListMensajes();
						venChat.clearTextFieldMensaje();
						
					}
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
				break;
			
			case "Logeando":
				try {
					if(venBienvenido.getUser().equals("") || venBienvenido.getPass().equals("")) { //Comprueba que se introduzcan datos de login.
						System.out.println(venBienvenido.getUser() + " " + venBienvenido.getPass());
						JOptionPane.showMessageDialog(null, "No puedes dejar nada en blanco.");
						
					} else if(!Controlador.login(venBienvenido.getUser(), venBienvenido.getPass())) { //Comprueba que los datos de login existan.
						JOptionPane.showMessageDialog(null, "Login incorrecto.");
						
					} else /*if(Controlador.login(venBienvenido.getUser(), venBienvenido.getPass()))*/ { //Login correcto, cambia a la vista principal.
						JOptionPane.showMessageDialog(null, "Login correcto.");
						
						venBienvenido.setVisible(false);
						
						venBienvenido.deleteContent();
						
						venEventos.clearListEventThings();
						venContactos.clearListContactos();
						venNotas.clearListNotas();
						venComen.clearListComentarios();

						Interfaz.setCalendars();
						Interfaz.crearCalendarios();
						Interfaz.clearComBox();
						Interfaz.setComBoxContactos();
						Interfaz.Visible(true);
						
					}
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
				break;
				
			case "Registro":
				try {
					//Los campos de registro no pueden estar vacíos.
					if(venRegistro.getUser().equals("") || venRegistro.getPass().equals("") || venRegistro.getPassConfirm().equals("")
							|| venRegistro.getTelefono().equals("") || venRegistro.getEmail().contentEquals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
						
					} else if(!venRegistro.getPassConfirm().equals(venRegistro.getPass())) { //Comprueba que las contraseñas son iguales.
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.");
						
					} else if(!venRegistro.getEmail().contains("@")) { //Comprueba que sea un correo.
						JOptionPane.showMessageDialog(null, "No has introducido un correo electrónico válido.");
						
					} else {
						Controlador.registro(venRegistro.getUser(), venRegistro.getPass(), venRegistro.getTelefono(), venRegistro.getEmail());
						
						JOptionPane.showMessageDialog(null, "Registrado satisfactoriamente.");
						
						venRegistro.setVisible(false);
						
					}
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
				break;
				
			case "Desconectar":
				Interfaz.Visible(false);
				
				venBienvenido.setVisible(true);
				
				break;
				
			case "Salir":
				System.out.println("Hasta luego, buenas tardes.");
				
				System.exit(0);
				
				break;
		}
	}
	
	//Este método sirve para mostrar la ventana de registro.
	public void showRegistrar() {
		venRegistro.setVisible(true);
		
	}
}
