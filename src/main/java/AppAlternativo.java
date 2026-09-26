import excepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Inscripcion;
import modelo.Sala;
import modelo.actividades.Actividad;
import modelo.certificacion.Certificable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class AppAlternativo {
        public static void main(String[] args) {

            List<Estudiante> estudiantes = new ArrayList<>();

            estudiantes.add(new Estudiante("11111", "Marcela Madush"));
            estudiantes.add(new Estudiante("36333", "Elsa Lame"));
            estudiantes.add(new Estudiante("01996", "Michael Afton"));

            EventoUniversitario evento = new EventoUniversitario("001", "Clase de origamis",1500,true);

            Sala sala = new Sala(1, "Aula Grande");

            evento.asignarSala(sala);

            evento.crearActividad(1, "Clase de origamis",75,"taller" );
            evento.crearActividad(2, "Charla de FNAF",3,"charla" );


            try {
                evento.getActividades().get(0).inscribir(estudiantes.get(0));
                evento.getActividades().get(0).inscribir(estudiantes.get(1));
                evento.getActividades().get(0).inscribir(estudiantes.get(2));

                evento.getActividades().get(1).inscribir(estudiantes.get(1));
                evento.getActividades().get(1).inscribir(estudiantes.get(2));
            } catch (CupoExcedidoException e) {
                    System.out.println("Error al inscribir: " + e.getMessage());
            }

            evento.mostrarDatos();


            // Emitir certificados locos
            for (Actividad actividad: evento.getActividades()){
                if (actividad instanceof Certificable certificable){
                    System.out.println("CERTIFICADOS DE LA ACTIVIDAD " + actividad.getTitulo());
                    for (Inscripcion inscripcion: actividad.getInscripciones()){
                        String certificado = certificable.generarCertificado(inscripcion.getEstudiante());
                        System.out.println(certificado);
                    }
                }
            }
        }


}
