package jdbc.scuola.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.scuola.dao.DAOException;
import jdbc.scuola.dao.MatricoleDAO;
import jdbc.scuola.modello.Materie;
import jdbc.scuola.modello.Matricole;

public class MYSQLMatricoleDAO implements MatricoleDAO {

	final String INSERT = "INSERT INTO JdbcSchema.matriculas(alumno, asignatura, fecha, nota) VALUES(?, ?, ?, ?);";
	final String UPDATE = "UPDATE JdbcSchema.matriculas SET alumno = ?, asignatura = ?; fecha = ?, nota = ? WHERE alumno = ?;";
	final String DELETE = "DELETE FROM JdbcSchema.matriculas WHERE alumno = ?;";
	final String GETONE = "SELECT alumno, asignatura, fecha, nota FROM JdbcSchema.matriculas WHERE alumno = ?;";
	final String GETALL = "SELECT alumno, asignatura, fecha, nota FROM JdbcSchema.matriculas;";
	
	private Connection conn;
	
	public MYSQLMatricoleDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void inserisci(Matricole m) throws DAOException {

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT);
			stmt.setInt(1, m.getAlumno());
			stmt.setInt(2, m.getAsignatura());
			stmt.setInt(3, m.getFecha());
			stmt.setInt(4, m.getNota());
			if(stmt.executeUpdate() == 0) {
				throw new DAOException("l'insert non è stato eseguito!");
			}
		} catch (SQLException ex) {
			throw new DAOException("Error in SQL", ex);
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
					throw new DAOException("Error in SQL", ex);
				}
			}
		}
	}

	public void modifica(Matricole m) throws DAOException {

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(UPDATE);
			stmt.setInt(1, m.getAlumno());
			stmt.setInt(2, m.getAsignatura());
			stmt.setInt(3, m.getFecha());
			stmt.setInt(4, m.getNota());
			stmt.setInt(5, m.getAlumno());
			if(stmt.executeUpdate() == 0) {
				throw new DAOException("l'update non è stato eseguito!");
			}
		} catch (SQLException ex) {
			throw new DAOException("Error in SQL", ex);
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
					throw new DAOException("Error in SQL", ex);
				}
			}
		}
	}

	public void elimina(Matricole m) throws DAOException {

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, m.getAlumno());
			if(stmt.executeUpdate() == 0) {
				throw new DAOException("l'eliminazione non è stata eseguita!");
			}
		} catch (SQLException ex) {
			throw new DAOException("Error in SQL", ex);
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
					throw new DAOException("Error in SQL", ex);
				}
			}
		}
	}

	private Matricole converti(ResultSet rs) throws SQLException {
		
		Integer alunno = rs.getInt("alumno");
		Integer materia = rs.getInt("asignatura");
		Integer data = rs.getInt("fecha");
		Integer voto = rs.getInt("nota");
		
		Matricole matricola = new Matricole(alunno, materia, data, voto);
		return matricola;
	}

	public Matricole cercaMatricola(Integer id) throws DAOException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Matricole m = null;
		try {
			stmt = conn.prepareStatement(GETONE);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				m = converti(rs);
			} else {
				throw new DAOException("non si è trovato la matricola!");
			}
		} catch (SQLException ex) {
			throw new DAOException("Error in SQL", ex);
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					throw new DAOException("Error in SQL", ex);
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
					throw new DAOException("Error in SQL", ex);
				}
			}
		}
		return m;
	}

	public List<Matricole> listaMatricole() throws DAOException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Matricole> matricole = new ArrayList<Matricole>();
		try {
			stmt = conn.prepareStatement(GETALL);
			rs = stmt.executeQuery();
			while(rs.next()) {
				matricole.add(converti(rs));
			}
		} catch (SQLException ex) {
			throw new DAOException("Error in SQL", ex);
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					throw new DAOException("Error in SQL", ex);
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
					throw new DAOException("Error in SQL", ex);
				}
			}
		}		
		return matricole;
	}	
}
