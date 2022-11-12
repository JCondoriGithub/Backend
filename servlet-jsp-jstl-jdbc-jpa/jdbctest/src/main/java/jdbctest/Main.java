package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");	// verficare che la classe esista e sia registrata
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ecommerce?user=root&password=PuchiPuchi25!");
		System.out.println("connessione aperta");

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from products");
		while (rs.next()) {
			String name = rs.getString("name");
			int price = rs.getInt("price");
			int code = rs.getInt("code");
			System.out.println(code + " " + name + " costo= " + price);
		}
		rs.close();		// si chiude il cursore
		stmt.close();	// si chiude lo statement
		con.close();	// si chiude la connessione
	}

}
