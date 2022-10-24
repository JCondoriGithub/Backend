package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {
	
	static void listaAlunni(Connection dbConnection) throws SQLException {
		
		String query = "SELECT nombre, apellidos, fecha_nac\n"
				+ "FROM JdbcSchema.alumnos;";
		PreparedStatement cmd = dbConnection.prepareStatement(query);
		ResultSet res = cmd.executeQuery();
		
		boolean esiste = res.next();
		while(esiste) {
			
			String nome = res.getString("nombre");
			String cognome = res.getString("apellidos");
			String dataNascita = res.getString("fecha_nac");
			System.out.println("nome: "+nome+" cognome: "+cognome+" data di nascita: "+dataNascita);
			esiste = res.next();
		}
	}
	
	static void listaProfessori(Connection dbConnection) throws SQLException {
		
		String query = "SELECT nombre, apellidos\n"
				+ "FROM JdbcSchema.profesores;";
		PreparedStatement cmd = dbConnection.prepareStatement(query);
		ResultSet res = cmd.executeQuery();
		
		boolean esiste = res.next();
		while(esiste) {
			
			String nome = res.getString("nombre");
			String cognome = res.getString("apellidos");
			System.out.println("nome: "+nome+" cognome: "+cognome);
			esiste = res.next();
		}
	}
	
	static void materie(Connection dbConnection) throws SQLException {
		
		String query = "SELECT nombre, profesor\n"
				+ "FROM JdbcSchema.asignaturas";
		PreparedStatement cmd = dbConnection.prepareStatement(query);
		ResultSet res = cmd.executeQuery();
		
		boolean esiste = res.next();
		while(esiste) {
			
			String nome = res.getString("nombre");
			String prof = res.getString("profesor");
			System.out.println("nome: "+nome+" professore: "+prof);
			esiste = res.next();
		}
	}
}