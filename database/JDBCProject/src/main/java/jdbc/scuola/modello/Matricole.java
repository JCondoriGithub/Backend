package jdbc.scuola.modello;

public class Matricole {
	
	public class IdMatricola {
		
		private Integer alumno;
		private Integer asignatura;
		private int fecha;
		
		public IdMatricola(Integer alumno, Integer asignatura, int fecha) {
			
			this.alumno = alumno;
			this.asignatura = asignatura;
			this.fecha = fecha;
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

		@Override
		public String toString() {
			return "IdMatricola [alumno=" + alumno + ", asignatura=" + asignatura + ", fecha=" + fecha + "]";
		}
		
	}

	private IdMatricola id;
	private int nota;
	
	public Matricole(int nota) {
		this.nota = nota;
	}

	public IdMatricola getId() {
		return id;
	}

	public void setId(IdMatricola id) {
		this.id = id;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Matricole [id=" + id + ", nota=" + nota + "]";
	}
}
