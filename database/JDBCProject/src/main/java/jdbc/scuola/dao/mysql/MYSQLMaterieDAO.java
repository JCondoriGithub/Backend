package jdbc.scuola.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.scuola.dao.DAOException;
import jdbc.scuola.dao.MaterieDAO;
import jdbc.scuola.modello.Materie;
import jdbc.scuola.modello.Professore;

public class MYSQLMaterieDAO implements MaterieDAO {

	final String INSERT = "INSERT INTO JdbcSchema.asignaturas(nombre, profesor) VALUES(?, ?);";
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
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(INSERT);
			stmt.setString(2, m.getNombre());
			stmt.setInt(3, m.getProfesor());
			if(stmt.executeUpdate() == 0) {
				throw new DAOException("l'insert non è stato eseguito!");
			}
			
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				m.setId_asignatura(rs.getInt(1));
			} else {
				throw new DAOException("l'id della materia non è stata assegnata");
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

	private Materie converti(ResultSet rs) throws SQLException {
		
		String nome = rs.getString("nombre");
		Integer professore = rs.getInt("profesor");
		
		Materie materia = new Materie(nome, professore);
		materia.setId_asignatura(rs.getInt("id_asignatura"));
		return materia;
	}
	
	public Materie cercaMateria(Integer id) throws DAOException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Materie m = null;
		try {
			stmt = conn.prepareStatement(GETONE);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				m = converti(rs);
			} else {
				throw new DAOException("non si è trovato la materia!");
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

	public List<Materie> listaMaterie() throws DAOException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Materie> materie = new ArrayList<Materie>();
		try {
			stmt = conn.prepareStatement(GETALL);
			rs = stmt.executeQuery();
			while(rs.next()) {
				materie.add(converti(rs));
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
		return materie;
	}

}
