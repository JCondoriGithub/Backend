package jdbc.scuola.dao;

import java.util.List;
import jdbc.scuola.modello.Matricole;

public interface MatricoleDAO {

	void inserisci(Matricole m) throws DAOException;
	
	void modificaVoto(int v, Matricole m) throws DAOException;
	
	void elimina(Matricole m) throws DAOException;
	
	Matricole cercaMatricola(Matricole mat) throws DAOException;
	
	List<Matricole> listaMatricole() throws DAOException;
}
