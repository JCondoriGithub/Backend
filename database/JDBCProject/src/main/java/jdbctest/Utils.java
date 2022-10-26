package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {
	
	static void cercaAlunno(Connection dbConnection, String n) throws SQLException {
		
		String query = "SELECT nombre, apellidos, fecha_nac\n"
				+ "FROM JdbcSchema.alumnos\n"
				+ "WHERE nombre= ?;";
		PreparedStatement cmd = dbConnection.prepareStatement(query);
		cmd.setString(1,n);
		ResultSet res = cmd.executeQuery();
		
		boolean esiste = res.next();
			
		String nome = res.getString("nombre");
		String cognome = res.getString("apellidos");
		String dataNascita = res.getString("fecha_nac");
		System.out.println("nome: "+nome+" cognome: "+cognome+" data di nascita: "+dataNascita);
			
		cmd.close();
		res.close();
	}
	
	static void cercaProfessore(Connection dbConnection, String n) throws SQLException {
		
		String query = "SELECT nombre, apellidos\n"
				+ "FROM JdbcSchema.profesores\n"
				+ "WHERE nombre= ?";
		PreparedStatement cmd = dbConnection.prepareStatement(query);
		cmd.setString(1, n);
		ResultSet res = cmd.executeQuery();
		
		boolean esiste = res.next();
			
		String nome = res.getString("nombre");
		String cognome = res.getString("apellidos");
		System.out.println("nome: "+nome+" cognome: "+cognome);
		
		cmd.close();
		res.close();
	}
	
	static void materie(Connection dbConnection) throws SQLException {
		
		String query = "SELECT asignaturas.nombre, profesores.nombre, profesores.apellidos\n"
				+ "FROM JdbcSchema.asignaturas, profesores\n"
				+ "WHERE asignaturas.profesor=profesores.id_profesor";
		PreparedStatement cmd = dbConnection.prepareStatement(query);
		ResultSet res = cmd.executeQuery();
		
		boolean esiste = res.next();
		while(esiste) {
			
			String nomeM = res.getString("nombre");
			String nome = res.getString("profesores.nombre");
			String cognome = res.getString("apellidos");
			System.out.println("nome-materia: "+nomeM+" nome-prof: "+nome+" cognome-prof: "+cognome);
			esiste = res.next();
		}
		cmd.close();
		res.close();
	}
	
	static void matricole(Connection dbConnection) throws SQLException {
		
		String query = "SELECT alumno, asignatura, fecha, nota\n"
				+ "FROM JdbcSchema.matriculas";
		PreparedStatement cmd = dbConnection.prepareStatement(query);
		ResultSet res = cmd.executeQuery();
		
		boolean esiste = res.next();
		while(esiste) {
			
			String alunno = res.getString("alumno");
			String assegnatura = res.getString("asignatura");
			String data = res.getString("fecha");
			int nota = res.getInt("nota");
			System.out.println("alunno: "+alunno+" assegnatura: "+assegnatura+" voto: "+nota);
			esiste = res.next();
		}
		cmd.close();
		res.close();
	}
	
	static void cercaMatricola(Connection dbConnection, int i) throws SQLException {
		
		String query = "SELECT alumnos.nombre, alumnos.apellidos, asignaturas.nombre, profesores.nombre, profesores.apellidos, matriculas.fecha, matriculas.nota\n"
				+ "FROM alumnos, matriculas, asignaturas, profesores\n"
				+ "WHERE matriculas.alumno = ? AND matriculas.alumno = alumnos.id_alumno AND matriculas.asignatura = asignaturas.id_asignatura AND asignaturas.profesor = profesores.id_profesor;";
		PreparedStatement cmd = dbConnection.prepareStatement(query);
		cmd.setInt(1,i);
		ResultSet res = cmd.executeQuery();
		
		boolean esiste = res.next();
		String nomeAlunno = res.getString("alumnos.nombre");
		String cognomeAlunno = res.getString("alumnos.apellidos");
		String nomeMateria = res.getString("asignaturas.nombre");
		String nomeProf = res.getString("profesores.nombre");
		String cognomeProf = res.getString("profesores.apellidos");
		int data = res.getInt("matriculas.fecha");
		int voto = res.getInt("matriculas.nota");

		System.out.println("nome-alunno: "+nomeAlunno+" cognome-alunno: "+cognomeAlunno+" nome-materia: "+nomeMateria+" nome-prof: "+nomeProf+" cognome-prof: "+cognomeProf+" data-voto: "+data+" voto: "+voto);
	
		cmd.close();
		res.close();
	}
	
	static void aggiungiStudente(Connection dbConnection, int id, String nome, String cognome, String dataNascita) throws SQLException {

		String query = "INSERT INTO JdbcSchema.alumnos(id_alumno, nombre, apellidos, fecha_nac)\n"
				+ "VALUES(?, ?, ?, ?);";
		PreparedStatement cmd = dbConnection.prepareStatement(query);
		cmd.setInt(1, id);
		cmd.setString(2, nome);
		cmd.setString(3, cognome);
		cmd.setString(4, dataNascita);
		cmd.executeUpdate();
	}
	
	static void aggiungiProfessore(Connection dbConnection, int id, String nome, String cognome) throws SQLException {
		
		String query = "INSERT INTO JdbcSchema.profesores(id_profesor, nombre, apellidos)\n"
				+ "VALUES(?, ?, ?);";
		PreparedStatement cmd = dbConnection.prepareStatement(query);
		cmd.setInt(1, id);
		cmd.setString(2, nome);
		cmd.setString(3, cognome);
		cmd.executeUpdate();
	}
	
	static void cancellaAlunno(Connection dbConnection, int id) throws SQLException {
		
		String query = "DELETE FROM JdbcSchema.alumnos WHERE id_alumno = ?;";
		PreparedStatement cmd = dbConnection.prepareStatement(query);
		cmd.setInt(1, id);
		cmd.executeUpdate();
	}
	
	static void cancellaProfessore(Connection dbConnection, int id) throws SQLException {
		
		String query = "DELETE FROM JdbcSchema.profesores WHERE id_profesor = ?;";
		PreparedStatement cmd = dbConnection.prepareStatement(query);
		cmd.setInt(1, id);
		cmd.executeUpdate();
	}
}