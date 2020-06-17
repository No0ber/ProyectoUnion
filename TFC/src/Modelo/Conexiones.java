package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexiones {
private static Connection conexion = null;
	
	//----------Método de conexión----------
	public static Connection conectar() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tfc", 
					"admin", "admin");
			
			System.out.println("Conectado a la BBDD.");
			
			return conexion;
			
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
			
		}catch(InstantiationException ie) {
			ie.printStackTrace();
			
		}catch(IllegalAccessException iae) {
			iae.printStackTrace();
			
		}
		return null;
		
	}
	
	//----------Método de desconexión----------
	public static void desconectar(Connection conex) {
		try {
			conex.close();
			conex = null;
			System.out.println("Desconectado de la BBDD.");
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
			
		}
	}
}