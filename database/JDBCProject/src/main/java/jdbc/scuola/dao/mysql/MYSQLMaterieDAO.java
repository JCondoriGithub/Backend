package jdbc.scuola.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import jdbc.scuola.dao.DAOException;
import jdbc.scuola.dao.MaterieDAO;
import jdbc.scuola.modello.Materie;

public class MYSQLMaterieDAO implements MaterieDAO {

	final String INSERT = "INSERT INTO JdbcSchema.asignaturas(id_asignatura, nombre, profesor) VALUES(?, ?, ?);";
	final String UPDATE = "UPDATE JdbcSchema.asignaturas SET nombre = ?, profesor = ? WHERE id_asignatura = ?;";
	final String DELETE = "DELETE FROM JdbcSchema.asignaturas WHERE id_asignatura = ?;";
	final String GETONE = "SELECT id_asignatura, nombre, profesor FROM JdbcSchema.asignaturas WHERE id_asignatura = ?;";
	final String GETALL = "SELECT id_asignatura, nombre, profesor FROM JdbcSchema.asignaturas;";
	
	private Connection conn;
	
	public MYSQLMaterieDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void inserisci(Materie m) throws DAOException {

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT);
			stmt.setInt(1, m.getId_asignatura());
			stmt.setString(2, m.getNombre());
			stmt.setInt(3, m.getProfesor());
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

	public void modifica(Materie m) throws DAOException {

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, m.getNombre());
			stmt.setInt(2, m.getProfesor());
			stmt.setInt(3, m.getId_asignatura());
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

	public void elimina(Materie m) throws DAOException {

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, m.getId_asignatura());
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

	public void cercaMateria(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public List<Materie> listaMaterie() {
		// TODO Auto-generated method stub
		return null;
	}

}
