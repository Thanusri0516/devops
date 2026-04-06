package student_management.app;

import student_management.model.student;
import student_management.service.studentservice;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentUI extends JFrame {

    private studentservice service = new studentservice();

    private JTextField idField, nameField, deptField;
    private JTextArea displayArea;

    public StudentUI() {

        setTitle("Student Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        // ===== FORM PANEL =====
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        formPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Department:"));
        deptField = new JTextField();
        formPanel.add(deptField);

        add(formPanel, BorderLayout.NORTH);

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel();

        JButton addBtn = new JButton("Add");
        JButton viewBtn = new JButton("View All");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        add(buttonPanel, BorderLayout.CENTER);

        // ===== DISPLAY AREA =====
        displayArea = new JTextArea(10, 50);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        add(new JScrollPane(displayArea), BorderLayout.SOUTH);

        // ================= BUTTON ACTIONS =================

        // ADD
        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                String dept = deptField.getText().trim();

                if (name.isEmpty() || dept.isEmpty()) {
                    displayArea.setText("❌ Name & Department cannot be empty\n");
                    return;
                }

                service.addStudent(new student(id, name, dept));
                displayArea.setText("✅ Student Added Successfully\n");

                clearFields();

            } catch (NumberFormatException ex) {
                displayArea.setText("❌ ID must be a number\n");
            }
        });

        // VIEW ALL
        viewBtn.addActionListener(e -> {
            List<student> students = service.getAllStudents();

            if (students.isEmpty()) {
                displayArea.setText("⚠ No students available\n");
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("===== STUDENT LIST =====\n");

            for (student s : students) {
                sb.append(s.toString()).append("\n");
            }

            displayArea.setText(sb.toString());
        });

        // UPDATE
        updateBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                String dept = deptField.getText().trim();

                boolean updated = service.updateStudent(id, name, dept);

                if (updated)
                    displayArea.setText("✅ Student Updated Successfully\n");
                else
                    displayArea.setText("❌ Student Not Found\n");

                clearFields();

            } catch (NumberFormatException ex) {
                displayArea.setText("❌ ID must be a number\n");
            }
        });

        // DELETE
        deleteBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());

                boolean deleted = service.deleteStudent(id);

                if (deleted)
                    displayArea.setText("✅ Student Deleted Successfully\n");
                else
                    displayArea.setText("❌ Student Not Found\n");

                clearFields();

            } catch (NumberFormatException ex) {
                displayArea.setText("❌ ID must be a number\n");
            }
        });
    }

    // ===== CLEAR INPUT FIELDS =====
    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        deptField.setText("");
    }

    // ===== MAIN =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentUI().setVisible(true);
        });
    }
}