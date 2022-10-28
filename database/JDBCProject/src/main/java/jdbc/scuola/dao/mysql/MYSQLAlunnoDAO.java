package jdbc.scuola.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import jdbc.scuola.dao.AlunnoDAO;
import jdbc.scuola.dao.DAOException;
import jdbc.scuola.modello.Alunno;

public class MYSQLAlunnoDAO implements AlunnoDAO {
	
	final String INSERT = "INSERT INTO JdbcSchema.alumnos(id_alumno, nombre, apellidos, fecha_nac) VALUES(?, ?, ?, ?);";
	final String UPDATE = "UPDATE JdbcSchema.alumnos SET nombre = ?, apellidos = ?, fecha_nac = ? WHERE id_alumno = ?;";
	final String DELETE = "DELETE FROM JdbcSchema.alumnos WHERE id_alumno = ?;";
	final String GETONE = "SELECT id_alumno, nombre, apellidos, fecha_nac FROM JdbcSchema.alumnos WHERE id_alumno = ?;";
	final String GETALL = "SELECT id_alumno, nombre, apellidos, fecha_nac FROM JdbcSchema.alumnos;";
	
	private Connection conn;
	
	public MYSQLAlunnoDAO(Connection conn) {
		this.conn = conn;
	}

	public void inserisci(Alunno a) throws DAOException {

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT);
			stmt.setInt(1, a.getId_alumno());
			stmt.setString(2, a.getNombre());
			stmt.setString(3, a.getApellidos());
			stmt.setString(4, a.getFecha_nac());
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

	public void modifica(Alunno a) throws DAOException {

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, a.getNombre());
			stmt.setString(2, a.getApellidos());
			stmt.setString(3, a.getFecha_nac());
			stmt.setInt(4, a.getId_alumno());
			if(stmt.executeUpdate() == 0) {
				throw new DAOException("l'update non è stato eseguito");
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
	public void elimina(Alunno a) throws DAOException {

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, a.getId_alumno());
			if(stmt.executeUpdate() == 0) {
				throw new DAOException("l'eliminazione non è stata eseguita");
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
	

	public void cercaAlunno(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public List<Alunno> listaAlunni() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
