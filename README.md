# Instructor Sleepy

## Overview
This Java program simulates a scenario where a instructor assists students with programming tasks in a small office. The instructor alternates between helping students and taking naps. Students arrive at the office seeking assistance, and if the instructor is available, they get help; otherwise, they wait or return later.

## Features
- **instructor and Students Threads**: The instructor and each student are represented as separate threads in the program.
- **Semaphore Usage**: Semaphores are utilized to control access to shared resources such as chairs and to manage the synchronization between the instructor and students.
- **Queue**: A queue is used to maintain the order of students waiting for assistance from the instructor.
- **Randomized Wait Times**: Both students and the instructor have randomized wait times to simulate realistic behavior.

## How to Run
To execute the program, simply compile and run the `App.java` file. The program will automatically create a instructor and a specified number of students. Students will arrive at random intervals and either receive assistance or wait in the corridor if the instructor is busy.

## Example Output
 ``` bash
Instructor is sleeping
The instructor has woken up
Arrive the student 1
Instructor is helping the student 1
Busy Instructor, arrive the student 2
Student 2 sit in the chair 1
Instructor has helped the student 1
Instructor is helping the next student in queue: 2
Busy Instructor, arrive the student 3
Student 3 sit in the chair 1
Busy Instructor, arrive the student 4
Student 4 sit in the chair 2
Busy Instructor, arrive the student 5
Student 5 sit in the chair 3
Arrive the student 6 All chairs occupied - back to programming
Instructor has helped the student 2
Instructor is helping the next student in queue: 3
Busy Instructor, arrive the student 7
Student 7 sit in the chair 3
Instructor has helped the student 3
Instructor is helping the next student in queue: 4
Instructor has helped the student 4
Instructor is helping the next student in queue: 5
Busy Instructor, arrive the student 8
Student 8 sit in the chair 2
Instructor has helped the student 5
Instructor is helping the next student in queue: 7
Busy Instructor, arrive the student 6
Student 6 sit in the chair 2
Busy Instructor, arrive the student 9
Student 9 sit in the chair 3
Instructor has helped the student 7
Instructor is helping the next student in queue: 8
Instructor has helped the student 8
Instructor is helping the next student in queue: 6
Busy Instructor, arrive the student 10
Student 10 sit in the chair 2
Instructor has helped the student 6
Instructor is helping the next student in queue: 9
Instructor has helped the student 9
Instructor is helping the next student in queue: 10
Instructor has helped the student 10
No more students waiting, Instructor is sleeping
 ```


## Authors

* Diana Balanta
* Danna Espinosa
* George Trujillo
