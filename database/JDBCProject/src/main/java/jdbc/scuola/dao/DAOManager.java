package jdbc.scuola.dao;

public interface DAOManager {

	AlunnoDAO getAlunnoDAO();
	
	MaterieDAO getMaterieDAO();
	
	MatricoleDAO getMatricoleDAO();
	
	ProfessoreDAO getProfessoreDAO();
}
