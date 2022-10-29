package jdbc.scuola.dao;

import java.util.List;
import jdbc.scuola.modello.Professore;

public interface ProfessoreDAO {

	void inserisci(Professore p) throws DAOException;
	
	void modifica(Professore p) throws DAOException;
	
	void elimina(Professore p) throws DAOException;
	
	Professore cercaProfessore(Integer id) throws DAOException;
	
	List<Professore> listaProfessori() throws DAOException;
}
