public class App {
    public static void main(String[] args) throws InterruptedException {
        Instructor monitor = new Instructor();
        System.out.println("Instructor is sleeping");

        int numStudents = 5;

        // Crear estudiantes
        for (int i = 1; i <= numStudents; i++) {
    Student student = new Student(i, monitor);
            student.start();
                    Thread.sleep((int) (Math.random() * 2000)); // Simular tiempo de llegada aleatorio de los estudiantes
                    }
                    }
                    }
