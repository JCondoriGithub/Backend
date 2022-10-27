package jdbc.scuola.modello;

public class Professore {

	private Integer id_profesor = null;
	private String nombre;
	private String apellidos;
	
	public Professore(String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	public Integer getId_profesor() {
		return id_profesor;
	}
	
	public void setId_profesor(Integer id_profesor) {
		this.id_profesor = id_profesor;
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
	
	@Override
	public String toString() {
		return "Professore [id_profesor=" + id_profesor + ", nombre=" + nombre + ", apellidos=" + apellidos + "]";
	}
}
