/**
 * The main class that starts the program and creates student threads.
 */
public class App {
    /**
        * The main method of the program.
        * 
        * @param args the command line arguments
        * @throws InterruptedException if the thread is interrupted while sleeping
        */
    public static void main(String[] args) throws InterruptedException {
        Instructor monitor = new Instructor();
        System.out.println("Instructor is sleeping");

        int numStudents = 10;

        // Create students
        for (int i = 1; i <= numStudents; i++) {
            Student student = new Student(i, monitor);
            student.start();
            Thread.sleep((int) (Math.random() * 2000)); // Simulate random arrival time of students
        }
    }
}
