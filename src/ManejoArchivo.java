import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ManejoArchivo {
	
	/* Esta clase es la encargada de crear el archivo con el que se va a trabajar, conectar con el y agregar/modificar
	 * los datos que contiene
	 */

	private final Path locat = Paths.get(System.getProperty("user.dir"));
	private final Path locatFile = Paths.get(locat.toString(), "Alumnos.dat");
	private Writer write = null;
	private Scanner s = null;

	private ArrayList<ManejoAlumnos> alumnos = new ArrayList<>();

	public boolean crearArchivo() {
		if (new File(locatFile.toString()).exists()) {
			return true;
		} else {
			try {
				File archivo = new File(locatFile.toString());
				archivo.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}

	
	public boolean ingresarAlumno(ManejoAlumnos alumno) {

		try {
			write = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(locatFile.toString(), true), "UTF-8"));
			try {
				write.write(alumno.getIdentificador() + "\n" + alumno.getNombre() + "\n" + alumno.getAsignaturasMatri()
						+ "\n");
				write.close();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	public ArrayList<ManejoAlumnos> toArray() {
		try {
			alumnos.clear();
			ArrayList<String> valores = new ArrayList<>();
			s = new Scanner(new File(locatFile.toString()));
			while (s.hasNextLine()) {
				String linea = s.nextLine(); 
				valores.add(linea);
				if (valores.size() == 3) {
					alumnos.add(new ManejoAlumnos(Integer.parseInt(valores.get(0)), valores.get(1),
							Integer.parseInt(valores.get(2))));
					valores.clear();
				}
			}
			s.close();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			return null;
		}
		return alumnos;
	}

	public boolean modificarAlumno(ArrayList<ManejoAlumnos> a) {

		try {
			write = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(locatFile.toString(), false), "UTF-8"));
			try {
				for (int i = 0; i < a.size(); i++) {
					write.write(a.get(i).getIdentificador() + "\n" + a.get(i).getNombre() + "\n"
							+ a.get(i).getAsignaturasMatri() + "\n");
				}
				alumnos.clear();
				write.close();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	public Path getLocalizacionArchivo() {
		return locatFile;
	}
}
