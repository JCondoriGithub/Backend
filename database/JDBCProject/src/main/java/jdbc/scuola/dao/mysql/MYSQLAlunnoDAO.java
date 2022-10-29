package jdbc.scuola.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.scuola.dao.AlunnoDAO;
import jdbc.scuola.dao.DAOException;
import jdbc.scuola.modello.Alunno;

public class MYSQLAlunnoDAO implements AlunnoDAO {
	
	final String INSERT = "INSERT INTO JdbcSchema.alumnos(nombre, apellidos, fecha_nac) VALUES(?, ?, ?);";
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
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, a.getNombre());
			stmt.setString(2, a.getApellidos());
			stmt.setString(3, a.getFecha_nac());
			if(stmt.executeUpdate() == 0) {
				throw new DAOException("l'insert non è stato eseguito!");
			}

			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				a.setId_alumno(rs.getInt(1));
			} else {
				throw new DAOException("l'id dell'alunno non è stato assegnato");
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
			if(rs != null) {
				try {
					rs.close();
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
	
	private Alunno converti(ResultSet rs) throws SQLException {
		
		String nome = rs.getString("nombre");
		String cognome = rs.getString("apellidos");
		String dataNascita = rs.getString("fecha_nac");
		
		Alunno alunno = new Alunno(nome, cognome, dataNascita);
		alunno.setId_alumno(rs.getInt("id_alumno"));
		return alunno;
	}

	public Alunno cercaAlunno(Integer id) throws DAOException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Alunno a = null;
		try {
			stmt = conn.prepareStatement(GETONE);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				a = converti(rs);
			} else {
				throw new DAOException("non si è trovato l'alunno!");
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
		return a;
	}

	public List<Alunno> listaAlunni() throws DAOException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Alunno> alunni = new ArrayList<Alunno>();
		try {
			stmt = conn.prepareStatement(GETALL);
			rs = stmt.executeQuery();
			while(rs.next()) {
				alunni.add(converti(rs));
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
		return alunni;
	}
	
}
