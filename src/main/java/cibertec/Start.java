package cibertec;

import dao.SubjectDaoImpl;
import model.Subject;
import java.util.List;

public class Start {

    public static void main(String[] args) {

        SubjectDaoImpl dao = new SubjectDaoImpl();

        // Crear un nuevo Subject y agregarlo a la base de datos
        Subject newSubject = new Subject();
        newSubject.setIdSubject(1);
        newSubject.setSubjectName("Programación");
        newSubject.setCredits("3");
        
        System.out.println("Creando un nuevo subject...");
        dao.create(newSubject);

        // Leer todos los Subjects de la base de datos
        System.out.println("Leyendo subjects...");
        List<Subject> subjects = dao.read();
        for (Subject s : subjects) {
            System.out.println("Subject: " + s.getSubjectName() + ", Credits: " + s.getCredits());
        }

        // Actualizar un Subject
        System.out.println("Actualizando subject...");
        newSubject.setCredits("4"); // Cambiamos los créditos
        dao.update(newSubject);

        // Eliminar un Subject
        System.out.println("Eliminando subject...");
        dao.delete(newSubject.getIdSubject());
        
        // Cerrar el DAO
        dao.close();

        System.out.println("Operaciones finalizadas.");
    }
}
