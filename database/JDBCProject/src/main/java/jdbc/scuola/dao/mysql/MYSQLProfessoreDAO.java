package jdbc.scuola.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.scuola.dao.DAOException;
import jdbc.scuola.dao.ProfessoreDAO;
import jdbc.scuola.modello.Alunno;
import jdbc.scuola.modello.Professore;

public class MYSQLProfessoreDAO implements ProfessoreDAO {

	final String INSERT = "INSERT INTO JdbcSchema.profesores(id_profesor, nombre, apellidos) VALUES(?, ?, ?);";
	final String UPDATE = "UPDATE JdbcSchema.profesores SET nombre = ?, apellidos = ? WHERE id_profesor = ?;";
	final String DELETE = "DELETE FROM JdbcSchema.profesores WHERE id_profesor = ?;";
	final String GETONE = "SELECT id_profesor, nombre, apellidos FROM JdbcSchema.profesores WHERE id_profesor = ?;";
	final String GETALL = "SELECT id_profesor, nombre, apellidos FROM JdbcSchema.profesores;";
	
	private Connection conn;
	
	public MYSQLProfessoreDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void inserisci(Professore p) throws DAOException {

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT);
			stmt.setInt(1, p.getId_profesor());
			stmt.setString(2, p.getNombre());
			stmt.setString(3, p.getApellidos());
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

	public void modifica(Professore p) throws DAOException {

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, p.getNombre());
			stmt.setString(2, p.getApellidos());
			stmt.setInt(3, p.getId_profesor());
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

	public void elimina(Professore p) throws DAOException {

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, p.getId_profesor());
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
	
	private Professore converti(ResultSet rs) throws SQLException {
		
		String nome = rs.getString("nombre");
		String cognome = rs.getString("apellidos");
		
		Professore professore = new Professore(nome, cognome);
		professore.setId_profesor(rs.getInt("id_profesor"));
		return professore;
	}

	public Professore cercaProfessore(Integer id) throws DAOException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Professore p = null;
		try {
			stmt = conn.prepareStatement(GETONE);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				p = converti(rs);
			} else {
				throw new DAOException("non si è trovato il prof!");
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
		return p;
	}

	public List<Professore> listaProfessori() throws DAOException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Professore> professori = new ArrayList<Professore>();
		try {
			stmt = conn.prepareStatement(GETALL);
			rs = stmt.executeQuery();
			while(rs.next()) {
				professori.add(converti(rs));
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
		return professori;		
	}	
}
