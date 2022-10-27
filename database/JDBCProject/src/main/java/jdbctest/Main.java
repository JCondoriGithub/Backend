package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Connection dbConnection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/JdbcSchema?user=root&passowrd=PuchiPuchi25!";
			dbConnection = DriverManager.getConnection(url, "root", "PuchiPuchi25!");
			dbConnection.setAutoCommit(false);
			System.out.println("connessione aperta");
			
			/*Utils.cercaAlunno(dbConnection, "Ana");
			Utils.cercaProfessore(dbConnection, "Agustín");
			Utils.materie(dbConnection);
			Utils.matricole(dbConnection);
			Utils.cercaMatricola(dbConnection, 3);
			Utils.aggiungiStudente(dbConnection, 7, "Giovanni", "Muciaccia", "1969-12-26");
			Utils.aggiungiProfessore(dbConnection, 4, "Egidio", "Rotta");
			Utils.cancellaAlunno(dbConnection, 8);
			Utils.cancellaProfessore(dbConnection, 5);*/
			Utils.transazione(dbConnection);
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			dbConnection.close();
		}
		
	}

}
