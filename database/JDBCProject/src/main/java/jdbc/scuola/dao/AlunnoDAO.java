package jdbc.scuola.dao;

import java.sql.SQLException;
import java.util.List;
import jdbc.scuola.modello.Alunno;

public interface AlunnoDAO {

	void inserisci(Alunno a) throws DAOException;
	
	void modifica(Alunno a) throws DAOException;
	
	void elimina(Alunno a) throws DAOException;
	
	Alunno cercaAlunno(Integer id) throws DAOException;
	
	List<Alunno> listaAlunni() throws DAOException;
}
