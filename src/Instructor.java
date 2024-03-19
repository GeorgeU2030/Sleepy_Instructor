import java.util.concurrent.Semaphore;
import java.util.Queue;
import java.util.LinkedList;

/**
 * The Instructor class represents an instructor who helps students in a queue.
 * It manages the arrival and departure of students, as well as the availability of chairs.
 */
class Instructor {
    private Semaphore mutex = new Semaphore(1);
    private Semaphore studentWaiting = new Semaphore(0);
    private Semaphore chairAvailable = new Semaphore(3);
    private Semaphore monitorSleeping = new Semaphore(0); // Semaphore to wake up the monitor
    private int studentCount = 0;
    private int lastStudentId = -1;
    private Queue<Integer> studentQueue = new LinkedList<>(); // Queue to maintain the arrival order of students

    /**
     * This method is called when a student arrives at the instructor's office.
     * If there are available chairs, the student is helped by the instructor.
     * If all chairs are occupied, the student goes back to programming and tries again after a delay.
     * 
     * @param id The ID of the student who arrived
     * @throws InterruptedException If the thread is interrupted while waiting
     */
    public void studentArrived(int id) throws InterruptedException {
        mutex.acquire();
        if (studentCount == 0) {
            if(studentQueue.isEmpty()){
                System.out.println("The instructor has woken up");
            }
            System.out.println("Arrive the student " + id);
            System.out.println("Instructor is helping the student " + id);
            studentCount++;
            lastStudentId=id;
            mutex.release();

        } else if (studentCount < 4) {
            System.out.println("Busy Instructor, arrive the student " + id);
            chairAvailable.acquire();
            System.out.println("Student " + id + " sit in the chair " + studentCount);
            studentQueue.add(id); // Add student to the queue
            studentCount++;
            studentWaiting.release();
            mutex.release();
        } else {
            System.out.println("Arrive the student " + id + " All chairs occupied - back to programming");
            mutex.release();
            Thread.sleep(2000);
            studentArrived(id);
        }
    }

    /**
     * Decreases the student count and performs necessary actions when a student leaves.
     * If there are students waiting in the queue, the instructor helps the next student.
     * If there are no more students waiting, the instructor goes to sleep.
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public void studentLeft() throws InterruptedException {
        mutex.acquire();
        studentCount--;
        System.out.println("Instructor has helped the student " + lastStudentId);

        chairAvailable.release();
        if (!studentQueue.isEmpty()) { // If there are students waiting in the queue
            int nextStudent = studentQueue.poll(); // Get the next student from the queue
            System.out.println("Instructor is helping the next student in queue: " + nextStudent);
            lastStudentId = nextStudent;
            studentWaiting.release();
        } else {
            System.out.println("No more students waiting, Instructor is sleeping");
                monitorSleeping.release(); // Allow the monitor to go back to sleep if there are no students waiting
            }
        mutex.release();
    }
}
