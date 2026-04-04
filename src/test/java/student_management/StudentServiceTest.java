package student_management;

import student_management.model.student;
import student_management.service.studentservice;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentServiceTest {

    private studentservice service;

    @BeforeMethod
    public void setup() {
        service = new studentservice();
    }

    @Test
    public void testAddStudent() {
        service.addStudent(new student(1, "Test", "CSE"));
        Assert.assertEquals(service.getAllStudents().size(), 1);
    }

    @Test
    public void testGetStudentById() {
        service.addStudent(new student(1, "Test", "CSE"));
        Assert.assertNotNull(service.getStudentById(1));
    }

    @Test
    public void testUpdateStudent() {
        service.addStudent(new student(1, "Test", "CSE"));
        boolean updated = service.updateStudent(1, "Updated", "ISE");
        Assert.assertTrue(updated);
    }

    @Test
    public void testDeleteStudent() {
        service.addStudent(new student(1, "Test", "CSE"));
        boolean deleted = service.deleteStudent(1);
        Assert.assertTrue(deleted);
    }
}