package jdbc.scuola.dao;

import java.util.List;
import jdbc.scuola.modello.Materie;

public interface MaterieDAO {

	void inserisci(Materie m);
	
	void modifica(Materie m);
	
	void elimina(Materie m);
	
	void cercaMateria(Integer id);
	
	List<Materie> listaMaterie();
}
