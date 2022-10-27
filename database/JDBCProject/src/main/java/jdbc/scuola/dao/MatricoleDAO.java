package jdbc.scuola.dao;

import java.util.List;
import jdbc.scuola.modello.Matricole;

public interface MatricoleDAO {

	void inserisci(Matricole m);
	
	void modifica(Matricole m);
	
	void elimina(Matricole m);
	
	void cercaMatricola(Integer id);
	
	List<Matricole> listaMatricole();
}
