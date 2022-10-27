package jdbc.scuola.modello;

public class Materie {

	private Integer id_asignatura = null;
	private String nombre;
	private Integer profesor;
	
	public Materie(String nombre, Integer profesor) {
		super();
		this.nombre = nombre;
		this.profesor = profesor;
	}

	public Integer getId_asignatura() {
		return id_asignatura;
	}

	public void setId_asignatura(Integer id_asignatura) {
		this.id_asignatura = id_asignatura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getProfesor() {
		return profesor;
	}

	public void setProfesor(Integer profesor) {
		this.profesor = profesor;
	}

	@Override
	public String toString() {
		return "Materie [id_asignatura=" + id_asignatura + ", nombre=" + nombre + ", profesor=" + profesor + "]";
	}
}
