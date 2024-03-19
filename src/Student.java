class Student extends Thread {
    private int id;
    private Instructor instructor;

    public Student(int id, Instructor instructor) {
        this.id = id;
        this.instructor = instructor;
    }

    @Override
    public void run() {
        try {
            instructor.studentArrived(id);
            Thread.sleep((int) (Math.random() * 4000))        ; // Simular tiempo de programación aleatorio
            instructor.studentLeft();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
