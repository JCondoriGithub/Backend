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
	
}
