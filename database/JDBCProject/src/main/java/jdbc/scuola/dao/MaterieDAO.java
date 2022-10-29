package jdbc.scuola.dao;

import java.util.List;
import jdbc.scuola.modello.Materie;

public interface MaterieDAO {

	void inserisci(Materie m) throws DAOException;
	
	void modifica(Materie m) throws DAOException;
	
	void elimina(Materie m) throws DAOException;
	
	Materie cercaMateria(Integer id) throws DAOException;
	
	List<Materie> listaMaterie() throws DAOException;
}
