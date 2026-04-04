package student_management.app;

import student_management.model.student;
import student_management.service.studentservice;

public class mainapp {

    public static void main(String[] args) {

        studentservice service = new studentservice();

        // Add students
        service.addStudent(new student(1, "Alice", "CSE"));
        service.addStudent(new student(2, "Bob", "ECE"));

        // Display all
        System.out.println("All Students:");
        for (student s : service.getAllStudents()) {
            System.out.println(s.getId() + " " + s.getName() + " " + s.getDepartment());
        }

        // Update
        service.updateStudent(1, "Alice Updated", "ISE");

        // Delete
        service.deleteStudent(2);

        // Final list
        System.out.println("\nAfter Update & Delete:");
        for (student s : service.getAllStudents()) {
            System.out.println(s.getId() + " " + s.getName() + " " + s.getDepartment());
        }
    }
}