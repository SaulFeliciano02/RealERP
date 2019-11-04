package basededatos;

/**import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;**/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	/**public static final String controlador = "com.mysql.jdbc.Driver";
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
		}**/
	
	private static Conexion instancia;
	private String url = "jdbc:h2:~/erp";
	
	 public  Conexion(){
         registrarDriver();
     }

     /**
      * Retornando la instancia.
      * @return
      */
     public static Conexion getInstancia(){
         if(instancia==null){
             instancia = new Conexion();
         }
         return instancia;
     }

     /**
      * Metodo para el registro de driver de conexión.
      */
     private void registrarDriver() {
         try {
             Class.forName("org.h2.Driver");
         } catch (ClassNotFoundException ex) {
        	 ex.printStackTrace();
         }
     }

     public Connection conectar() {
         Connection con = null;
         try {
             con = DriverManager.getConnection(url, "sa", "");
             System.out.println("Conexion exitosa");
         } catch (SQLException ex) {
        	 ex.printStackTrace();
        	 
         }
         return con;
     }
}
