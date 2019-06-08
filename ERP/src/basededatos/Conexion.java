package basededatos;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Conexion {
	
	public static final String controlador = "com.mysql.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/mypymeserpdatabase";
	public static final String user = "root";
	public static final String password = "";
		
	static {
		try {
			Class.forName(controlador);
		} catch (ClassNotFoundException e) {
			System.out.println("Error en el controlador");
			e.printStackTrace();
		}
	}
		
		public Connection conectar()
		{
			Connection conexion = null;
			
			try {
				
				conexion = (Connection) DriverManager.getConnection(url, user, password);
				
				System.out.println("Se conectó");
			
			} catch (SQLException e) {
				System.out.println("Error en la conexión");
				e.printStackTrace();
			}
			
			//retorna la informacion de la conexión
			return conexion;
		}
}
