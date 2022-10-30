package jdbc.scuola.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import jdbc.scuola.dao.DAOException;
import jdbc.scuola.dao.mysql.MYSQLDAOManager;
import jdbc.scuola.modello.*;

public class Main {

	public static void main(String[] args) throws SQLException, DAOException {
		
			MYSQLDAOManager manager = new MYSQLDAOManager("localhost", "root", "PuchiPuchi25!", "JdbcSchema");
			
			List<Alunno> alunni = manager.getAlunnoDAO().listaAlunni();
			Alunno alunno = manager.getAlunnoDAO().cercaAlunno(6);
			manager.getAlunnoDAO().inserisci(alunno);
			alunno.setNombre("Julio");
			manager.getAlunnoDAO().modifica(alunno);
			manager.getAlunnoDAO().elimina(alunno);
			System.out.println(alunni);
			System.out.println(alunno);
			
			List<Professore> professori = manager.getProfessoreDAO().listaProfessori();
			Professore professore = manager.getProfessoreDAO().cercaProfessore(4);
			manager.getProfessoreDAO().inserisci(professore);
			professore.setNombre("Mario");
			manager.getProfessoreDAO().modifica(professore);
			manager.getProfessoreDAO().elimina(professore);
			System.out.println(professori);
			System.out.println(professore);
			
			List<Materie> materie = manager.getMaterieDAO().listaMaterie();
			Materie materia = manager.getMaterieDAO().cercaMateria(9);
			manager.getMaterieDAO().inserisci(materia);
			materia.setNombre("Algoritmos base");
			manager.getMaterieDAO().modifica(materia);
			manager.getMaterieDAO().elimina(materia);
			System.out.println(materia);
			System.out.println(materia);
			
			List<Matricole> matricole = manager.getMatricoleDAO().listaMatricole();
			Matricole matricola = new Matricole (100);
			matricola.setId(matricola.new IdMatricola(5, 8, 2016));
			Matricole matricolaC = manager.getMatricoleDAO().cercaMatricola(matricola);
			manager.getMatricoleDAO().elimina(matricolaC);
			manager.getMatricoleDAO().inserisci(matricola);
			manager.getMatricoleDAO().modificaVoto(80, matricola);
			System.out.println(matricola);
			System.out.println(matricole);
	}

}
