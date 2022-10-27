package jdbc.scuola.dao;

import java.util.List;
import jdbc.scuola.modello.Alunno;

public interface AlunnoDAO {

	void inserisci(Alunno a);
	
	void modifica(Alunno a);
	
	void elimina(Alunno a);
	
	void cercaAlunno(Integer id);
	
	List<Alunno> listaAlunni();
}
