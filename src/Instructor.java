import java.util.concurrent.Semaphore;

public class Instructor {

    private Semaphore mutex = new Semaphore(1);
    private Semaphore studentWaiting = new Semaphore(0);
    private Semaphore chairAvailable = new Semaphore(3);
    private int studentCount = 0;
    private int lastStudentId = -1; 

    public void studentArrived(int id) throws InterruptedException {
        mutex.acquire();
        if (studentCount == 0) {
            System.out.println("Arrive the student " + id);
            System.out.println("Instructor is helping the student " + id);
            studentCount++;
            mutex.release();
        } else if (studentCount < 3) {
            System.out.println("Busy Instructor, arrive the student " + id);
            chairAvailable.acquire();
            System.out.println("Student " + id + " sit in the chair " + studentCount);
            studentCount++;
            studentWaiting.release();
            mutex.release();
        } else {
            System.out.println("Arrive the student " + id + " 3 chairs occupied - back to programming");
            mutex.release();
        }
    }

    public void studentLeft(int id) throws InterruptedException {
        mutex.acquire();
        studentCount--;
        System.out.println("Instructor has helped the student " + id);
        lastStudentId = id; 
        chairAvailable.release();
        if (studentCount > 0) {
            System.out.println("Instructor is helping the next student");
            studentWaiting.release();
        }
        mutex.release();
    }

}
