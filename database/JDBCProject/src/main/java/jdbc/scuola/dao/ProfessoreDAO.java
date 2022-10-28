package jdbc.scuola.dao;

import java.util.List;
import jdbc.scuola.modello.Professore;

public interface ProfessoreDAO {

	void inserisci(Professore p) throws DAOException;
	
	void modifica(Professore p) throws DAOException;
	
	void elimina(Professore p) throws DAOException;
	
	void cercaProfessore(Integer id);
	
	List<Professore> listaProfessori();
}
