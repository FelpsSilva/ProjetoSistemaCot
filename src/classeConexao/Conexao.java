package classeConexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection fazConexao() throws SQLException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			return DriverManager.getConnection("jdbc:mysql://localhost/db_sistemacot1?useTimezone=true&serverTimezone=UTC","root","");	
		} 
		
		catch (ClassNotFoundException e) {
			throw new SQLException(e.getException());
		}
		
		
		
	}

}
