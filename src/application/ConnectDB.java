package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	private static String className = "com.mysql.cj.jdbc.Driver";
	//Hier müssen Ihre Daten für name und kennwort eingetragen werden, bitte nicht root benutzen
	private static String dbString = "jdbc:mysql://localhost:3306/pizzaservice?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&user=caro";
	private static Connection connection;

	public static Connection createConnection(){
		//geändert, um das Registeren der Klasse des DB-Treibers nicht 100 mal zu machen
		try {
			if(connection == null || connection.isClosed()){
				try{
					Class.forName(className);
					connection = ((Connection) DriverManager.getConnection(dbString));
					return connection;
				}
				catch(ClassNotFoundException e){
					e.printStackTrace();
				}

				catch(SQLException  e){
					e.printStackTrace();
				}
			}
			else{
				return connection;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
