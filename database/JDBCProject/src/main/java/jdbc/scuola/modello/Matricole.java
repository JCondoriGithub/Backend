package jdbc.scuola.modello;

public class Matricole {

	private Integer alumno;
	private Integer asignatura;
	private int fecha;
	private int nota;
	
	public Matricole(Integer alumno, Integer asignatura, int fecha, int nota) {
		super();
		this.alumno = alumno;
		this.asignatura = asignatura;
		this.fecha = fecha;
		this.nota = nota;
	}

	public Integer getAlumno() {
		return alumno;
	}

	public void setAlumno(Integer alumno) {
		this.alumno = alumno;
	}

	public Integer getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Integer asignatura) {
		this.asignatura = asignatura;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Matricole [alumno=" + alumno + ", asignatura=" + asignatura + ", fecha=" + fecha + ", nota=" + nota
				+ "]";
	}
}
