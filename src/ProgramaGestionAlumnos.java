import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProgramaGestionAlumnos {

	public static void main(String[] args) {

		// Variables opcion elegida, entrada por teclado y manejoArchivo
		String opcion = null;
		final BufferedReader entradaTexto = new BufferedReader(new InputStreamReader(System.in));
		final ManejoArchivo manejoArchivo = new ManejoArchivo();

		// Menu para el usuario
		do {
			boolean funciona = true;
			System.out.println("a. Crear el fichero con nombre alumnos.dat");
			System.out.println("b. Introducir un alumno");
			System.out.println("c. Modificar el número de asignaturas matriculadas");
			System.out.println("d. Eliminar un alumno");
			System.out.println("e. Mostrar datos");
			System.out.println("f. Salir");
			System.out.print("Elige una de estas opciones: ");

			try {
				opcion = entradaTexto.readLine().toLowerCase();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			// El siguiente switch ejecutará las diferentes opciones escogidas por el usuario.
			switch (opcion) {

			case "a": {

				if (manejoArchivo.crearArchivo() == true) {
					System.out.println("Ya has creado el archivo.");
				} else {
					System.out.println("Se ha creado el archivo.");

				}

				break;
			}

			case "b": {
				int identificadorAlumno = 0;
				String nombre = null;
				int numAsignaturas = 0;
				if (new File(manejoArchivo.getLocalizacionArchivo().toString()).exists() == false) {
					System.out.println("Aún no has creado el archivo.");
					System.out.print("¿Quieres crearlo? ");
					try {
						if (entradaTexto.readLine().equalsIgnoreCase("s")) {
							if (manejoArchivo.crearArchivo() == false) {
								System.out.println("Se ha creado el archivo.");
							}
						} else {
							System.out.println("Fin de la ejecución");
							System.exit(1);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				while (funciona) {
					while (funciona) {
						System.out.print("ID del alumno: ");
						try {
							identificadorAlumno = Integer.parseInt(entradaTexto.readLine());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					while (funciona) {
						System.out.print("Nombre del alumno: ");
						try {
							nombre = entradaTexto.readLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					funciona = true;

					while (funciona) {
						System.out.print("Número de asignaturas: ");
						try {
							numAsignaturas = Integer.parseInt(entradaTexto.readLine());
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					ManejoAlumnos alumno = new ManejoAlumnos(identificadorAlumno, nombre, numAsignaturas);
					
					if (manejoArchivo.ingresarAlumno(alumno) == true) {
						System.out.println("Alumno ingresado.");
					} else {
						break;
					}

				}

				break;
			}

			case "c": {
				int nAsignaturas = 0;
				int nAsignaturas2 = 0;
				while (funciona) {
					ArrayList<ManejoAlumnos> a = new ArrayList<>(manejoArchivo.toArray());
					while (funciona) {
						System.out.print("Inserta numero de Alumno: ");
						try {
							nAsignaturas = Integer.parseInt(entradaTexto.readLine()) - 1;
							funciona = false;
						} catch (NumberFormatException e) {
							System.out.println("Introduce un numero.");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					funciona = true;
					while (funciona) {
						System.out.print("Introduce el nuevo numero de asignaturas: ");
						try {
							nAsignaturas2 = Integer.parseInt(entradaTexto.readLine());
							if (nAsignaturas2 <= 0) {
								System.out.println("No puedes introducir un valor negativo");
								funciona = true;
							} else {
								funciona = false;
							}
						} catch (NumberFormatException e) {
							System.out.println("Introduce un numero.");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					try {
						a.get(nAsignaturas).setAsignaturasMatri(nAsignaturas2);
						System.out.println("Alumno modificado");	
					} catch (IndexOutOfBoundsException e) {
						System.out.println("Alumno no existente.");
						funciona = true;
					}
				}

				break;
			}

			case "d": {
				int numA = 0;
				while (funciona) {
					ArrayList<ManejoAlumnos> a = new ArrayList<>(manejoArchivo.toArray());

					if (a.isEmpty()) {
						System.out.println("El archivo está vacio.");
						funciona = false;
					} else {
						for (int i = 0; i < a.size(); i++) {
							System.out.println(a.get(i).getIdentificador() + " " + a.get(i).getNombre() + " "
									+ a.get(i).getAsignaturasMatri());
						}
						funciona = true;
						while (funciona) {
							System.out.print("ID del Alumno: ");
							try {
								numA = Integer.parseInt(entradaTexto.readLine()) - 1;
								funciona = false;
							} catch (NumberFormatException e) {
								System.out.println("Introduce un numero.");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						try {
							a.remove(a.get(numA));
							for (int i = 0; i < a.size(); i++) {
								a.get(i).setIdentificador(i + 1);
							}
							System.out.println("Alumno borrado");
						} catch (IndexOutOfBoundsException e) {
							System.out.println("El alumno no existe.");
							funciona = true;
						}
					}
				}
				break;

			}

			case "e": {

				ArrayList<ManejoAlumnos> a = new ArrayList<>(manejoArchivo.toArray());
				if (a.isEmpty()) {
					System.out.println("El archivo está vacio.");
				} else {
					for (int i = 0; i < a.size(); i++) {
						System.out.println(a.get(i).getIdentificador() + " " + a.get(i).getNombre() + " "
								+ a.get(i).getAsignaturasMatri());
					}
				}

				break;
			}
			case "f": {
				System.out.println("Fin de la ejecución");
				break;
			}
			}

		} while (!opcion.equalsIgnoreCase("f"));
	}
}