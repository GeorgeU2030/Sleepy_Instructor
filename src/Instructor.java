import java.util.concurrent.Semaphore;
import java.util.Queue;
import java.util.LinkedList;

class Instructor {
    private Semaphore mutex = new Semaphore(1);
    private Semaphore studentWaiting = new Semaphore(0);
    private Semaphore chairAvailable = new Semaphore(3);
    private Semaphore monitorSleeping = new Semaphore(0); // Semáforo para despertar al monitor
    private int studentCount = 0;
    private int lastStudentId = -1;
    private Queue<Integer> studentQueue = new LinkedList<>(); // Cola para mantener el orden de llegada de los estudiantes

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
            studentQueue.add(id); // Agregar estudiante a la cola
            studentCount++;
            studentWaiting.release();
            mutex.release();
        } else {
            System.out.println("Arrive the student " + id + " 3 chairs occupied - back to programming");
            mutex.release();
            Thread.sleep(2000);
            studentArrived(id);
        }
    }

    public void studentLeft() throws InterruptedException {
        mutex.acquire();
        studentCount--;
        System.out.println("Instructor has helped the student " + lastStudentId);

        chairAvailable.release();
        if (!studentQueue.isEmpty()) { // Si hay estudiantes esperando en la cola
            int nextStudent = studentQueue.poll(); // Obtener el siguiente estudiante de la cola
            System.out.println("Instructor is helping the next student in queue: " + nextStudent);
            lastStudentId = nextStudent;
            studentWaiting.release();
        } else {
            System.out.println("No more students waiting, Instructor is sleeping");
            monitorSleeping.release(); // Permitir que el monitor vuelva a dormir si no hay estudiantes esperando
        }
        mutex.release();
    }
}
