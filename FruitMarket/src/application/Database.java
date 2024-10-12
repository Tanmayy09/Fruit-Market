package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database 
{
	public static Connection connectdb() {
	
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/fruit_market","root","T@nmay83##");
		return connect;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return null;
	}
}
