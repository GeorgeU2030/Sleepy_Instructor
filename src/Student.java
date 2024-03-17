class Student extends Thread {
    private int id;
    private Instructor instructor;

    public Student(int id, Instructor instructor) {
        this.id = id;
        this.instructor = instructor;
    }

    public void run() {
        try {
            instructor.studentArrived(id);
            Thread.sleep((int) (Math.random() * 3000)); // Simulate programming time
            instructor.studentLeft(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
