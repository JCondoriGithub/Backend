package jdbc.scuola.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import jdbc.scuola.dao.*;
import jdbc.scuola.modello.*;

public class MYSQLDAOManager implements DAOManager {

	private Connection conn;
	
	private AlunnoDAO alunni = null;
	private MaterieDAO materie = null;
	private MatricoleDAO matricole = null;
	private ProfessoreDAO professori = null;
	
	public MYSQLDAOManager(String host, String username, String password, String database) throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database + "?user=" + username + "&password=" + password);
		System.out.println("connessione ok");
	}

	public AlunnoDAO getAlunnoDAO() {

		if(alunni == null)
			alunni = new MYSQLAlunnoDAO(conn);
		return alunni;
	}

	public MaterieDAO getMaterieDAO() {

		if(materie == null)
			materie = new MYSQLMaterieDAO(conn);
		return materie;
	}

	public MatricoleDAO getMatricoleDAO() {

		if(matricole == null)
			matricole = new MYSQLMatricoleDAO(conn);
		return matricole;
	}

	public ProfessoreDAO getProfessoreDAO() {

		if(professori == null)
			professori = new MYSQLProfessoreDAO(conn);
		return professori;
	}
	
	public static void main(String[] args) throws SQLException, DAOException {
		MYSQLDAOManager managerDao = new MYSQLDAOManager("localhost", "root", "PuchiPuchi25!", "JdbcSchema");
		/*List<Alunno> alunni = managerDao.getAlunnoDAO().listaAlunni();
		Alunno alunno = managerDao.getAlunnoDAO().cercaAlunno(16);
		//managerDao.getAlunnoDAO().inserisci(alunno);
		managerDao.getAlunnoDAO().elimina(alunno);
		System.out.println(alunni);
		System.out.println(alunno);*/
		
		/*List<Materie> materie = managerDao.getMaterieDAO().listaMaterie();
		System.out.println(materie);
		Materie materia = managerDao.getMaterieDAO().cercaMateria(11);
		//managerDao.getMaterieDAO().inserisci(materia);
		managerDao.getMaterieDAO().elimina(materia);*/
		
		List<Matricole> matricole = managerDao.getMatricoleDAO().listaMatricole();
		Matricole matricola = new Matricole (100);
		matricola.setId(matricola.new IdMatricola(5, 8, 2016));
		//Matricole matricolaC = managerDao.getMatricoleDAO().cercaMatricola(matricola);
		System.out.println(matricole);
		//managerDao.getMatricoleDAO().elimina(matricolaC);
		//managerDao.getMatricoleDAO().inserisci(matricola);
		System.out.println(matricole);
		managerDao.getMatricoleDAO().modificaVoto(80, matricola);
	}
}
