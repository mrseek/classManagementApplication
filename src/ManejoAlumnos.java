
public class ManejoAlumnos {
	
	/* La clase ManejoAlumnos es la encargada de seleccionar los datos que se guardarán del alumno (id, name y AsignaturasMatri)
	 * Se utilizará esta clase tanto para agregar como para modificar datos de alumnos.
	 */
	private int id;
	private String name;
	private int AsignaturasMatri;
	
	
	public ManejoAlumnos(int identificadorAlumno, String nombre, int asignaturasMatri) {
		super();
		this.id = identificadorAlumno;
		this.name = nombre;
		this.AsignaturasMatri = asignaturasMatri;
	}

	public int getIdentificador() {
		return id;
	}
	public void setIdentificador(int identificador) {
		this.id = identificador;
	}
	public String getNombre() {
		return name;
	}
	public void setNombre(String nombre) {
		this.name = nombre;
	}
	public int getAsignaturasMatri() {
		return AsignaturasMatri;
	}
	public void setAsignaturasMatri(int asignaturasMatri) {
		AsignaturasMatri = asignaturasMatri;
	}
	public int autoIncrement(int numAlumnos) {
		if (numAlumnos==0) {
			return 1;
		} else {
			return numAlumnos + 1;
		}
	}
}
