/**
 * The Student class represents a student in a classroom.
 * It extends the Thread class to allow for concurrent execution.
 */
class Student extends Thread {
    private int id;
    private Instructor instructor;

    /**
     * Constructs a new Student object with the given id and instructor.
     *
     * @param id         the unique identifier for the student
     * @param instructor the instructor object responsible for the classroom
     */
    public Student(int id, Instructor instructor) {
        this.id = id;
        this.instructor = instructor;
    }

    /**
     * The run method is called when the thread starts.
     * It simulates the student arriving, waiting for help, and then leaving.
     */
    @Override
    public void run() {
        try {
            instructor.studentArrived(id);
            Thread.sleep((int) (Math.random() * 4000)); // Simulate random help time
            instructor.studentLeft();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
