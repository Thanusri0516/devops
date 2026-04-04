package student_management.service;

import student_management.model.student;
import java.util.ArrayList;
import java.util.List;

public class studentservice {

    private List<student> students = new ArrayList<>();

    // Add student
    public void addStudent(student student) {
        students.add(student);
    }

    // View all students
    public List<student> getAllStudents() {
        return students;
    }

    // Get student by ID
    public student getStudentById(int id) {
        for (student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    // Update student
    public boolean updateStudent(int id, String name, String dept) {
        student s = getStudentById(id);
        if (s != null) {
            s.setName(name);
            s.setDepartment(dept);
            return true;
        }
        return false;
    }

    // Delete student
    public boolean deleteStudent(int id) {
        student s = getStudentById(id);
        if (s != null) {
            students.remove(s);
            return true;
        }
        return false;
    }
}