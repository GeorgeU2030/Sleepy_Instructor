
public class App {
    public static void main(String[] args) throws Exception {
        Instructor monitor = new Instructor();
        System.out.println("Instructor is sleeping");

        // Crear estudiantes
        for (int i = 1; i <= 5; i++) {
            Student student = new Student(i, monitor);
            student.start();
            try {
                Thread.sleep((int) (Math.random() * 2000)); // Simulate random arrival time of students
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
