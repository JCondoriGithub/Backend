package jdbc.scuola.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.scuola.dao.DAOException;
import jdbc.scuola.dao.MatricoleDAO;
import jdbc.scuola.modello.*;
import jdbc.scuola.modello.Matricole.IdMatricola;

public class MYSQLMatricoleDAO implements MatricoleDAO {

	final String INSERT = "INSERT INTO JdbcSchema.matriculas(alumno, asignatura, fecha, nota) VALUES(?, ?, ?, ?);";
	final String UPDATE = "UPDATE JdbcSchema.matriculas SET nota = ? WHERE alumno = ? AND asignatura = ? AND fecha = ?;";
	final String DELETE = "DELETE FROM JdbcSchema.matriculas WHERE alumno = ? AND asignatura = ? AND fecha = ?;";
	final String GETONE = "SELECT alumno, asignatura, fecha, nota FROM JdbcSchema.matriculas WHERE alumno = ? AND asignatura = ? AND fecha = ?;";
	final String GETALL = "SELECT alumno, asignatura, fecha, nota FROM JdbcSchema.matriculas;";
	
	private Connection conn;
	
	public MYSQLMatricoleDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void inserisci(Matricole m) throws DAOException {

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, m.getId().getAlumno());
			stmt.setInt(2, m.getId().getAsignatura());
			stmt.setInt(3, m.getId().getFecha());
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

	public void modificaVoto(int v, Matricole m) throws DAOException {

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(UPDATE);
			stmt.setInt(1, v);
			stmt.setInt(2, m.getId().getAlumno());
			stmt.setInt(3, m.getId().getAsignatura());
			stmt.setInt(4, m.getId().getFecha());
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
			stmt.setInt(1, m.getId().getAlumno());
			stmt.setInt(2, m.getId().getAsignatura());
			stmt.setInt(3, m.getId().getFecha());
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
		
		Matricole matricola = new Matricole(voto);
		matricola.setId(matricola.new IdMatricola(alunno, materia, data));
		return matricola;
	}

	public Matricole cercaMatricola(Matricole mat) throws DAOException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Matricole m = null;
		try {
			stmt = conn.prepareStatement(GETONE);
			stmt.setInt(1, mat.getId().getAlumno());
			stmt.setInt(2, mat.getId().getAsignatura());
			stmt.setInt(3, mat.getId().getFecha());
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
