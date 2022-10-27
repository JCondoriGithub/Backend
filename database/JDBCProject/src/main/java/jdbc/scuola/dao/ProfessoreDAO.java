package jdbc.scuola.dao;

import java.util.List;
import jdbc.scuola.modello.Professore;

public interface ProfessoreDAO {

	void inserisci(Professore p);
	
	void modifica(Professore p);
	
	void elimina(Professore p);
	
	void cercaProfessore(Integer id);
	
	List<Professore> listaProfessori();
}
