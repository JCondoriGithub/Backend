package jdbc.scuola.modello;

public class Alunno {

	private Integer id_alumno = null;
	private String nombre;
	private String apellidos;
	private String fecha_nac;
	
	public Alunno(String nombre, String apellidos, String fecha_nac) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha_nac = fecha_nac;
	}

	public Integer getId_alumno() {
		return id_alumno;
	}

	public void setId_alumno(Integer id_alumno) {
		this.id_alumno = id_alumno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	@Override
	public String toString() {
		return "Alunno [id_alumno=" + id_alumno + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fecha_nac="
				+ fecha_nac + "]";
	}
}
