import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.BevelBorder;
import java.util.ArrayList;


class Student {
    String name;
    int id;
    String grade;

    Student(String name, int id, String grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    String getDetails() {
        return id + "\t" + name + "\t\t" + grade;
    }
}

class Teacher {
    String name;
    int id;
    String subject;

    Teacher(String name, int id, String subject) {
        this.name = name;
        this.id = id;
        this.subject = subject;
    }

    String getDetails() {
        return id + "\t" + name + "\t\t" + subject;
    }
}

public class SchoolManagementSystemGUI extends JFrame {
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Teacher> teachers = new ArrayList<>();

    private JTextArea displayArea;
    private JTextField studentNameField, studentIdField, studentGradeField;
    private JTextField teacherNameField, teacherIdField, teacherSubjectField;

    public SchoolManagementSystemGUI() {
        // Set up the frame
        setTitle("School Management System");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the text area to display output
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        displayArea.setBackground(new Color(240, 240, 240)); // Light background for text area
        displayArea.setForeground(Color.BLACK); // Dark text for readability
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        // Create panel for student and teacher input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10)); // Added gap between components
        inputPanel.setBackground(new Color(255, 255, 255)); // White background for the panel

        // Student inputs
        studentNameField = new JTextField(10);
        studentIdField = new JTextField(10);
        studentGradeField = new JTextField(10);
        customizeTextField(studentNameField);
        customizeTextField(studentIdField);
        customizeTextField(studentGradeField);

        inputPanel.add(new JLabel("Student Name:"));
        inputPanel.add(studentNameField);
        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(studentIdField);
        inputPanel.add(new JLabel("Student Grade:"));
        inputPanel.add(studentGradeField);

        // Teacher inputs
        teacherNameField = new JTextField(10);
        teacherIdField = new JTextField(10);
        teacherSubjectField = new JTextField(10);
        customizeTextField(teacherNameField);
        customizeTextField(teacherIdField);
        customizeTextField(teacherSubjectField);

        inputPanel.add(new JLabel("Teacher Name:"));
        inputPanel.add(teacherNameField);
        inputPanel.add(new JLabel("Teacher ID:"));
        inputPanel.add(teacherIdField);
        inputPanel.add(new JLabel("Teacher Subject:"));
        inputPanel.add(teacherSubjectField);

        add(inputPanel, BorderLayout.NORTH);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(200, 200, 255)); // Soft background color for the buttons

        JButton addStudentButton = createStyledButton("Add Student");
        JButton addTeacherButton = createStyledButton("Add Teacher");
        JButton viewStudentsButton = createStyledButton("View Students");
        JButton viewTeachersButton = createStyledButton("View Teachers");
        JButton clearButton = createStyledButton("Clear Fields");

        buttonPanel.add(addStudentButton);
        buttonPanel.add(addTeacherButton);
        buttonPanel.add(viewStudentsButton);
        buttonPanel.add(viewTeachersButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        addTeacherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTeacher();
            }
        });

        viewStudentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewStudents();
            }
        });

        viewTeachersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewTeachers();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    // Method to add student
    private void addStudent() {
        try {
            String name = studentNameField.getText().trim();
            int id = Integer.parseInt(studentIdField.getText().trim());
            String grade = studentGradeField.getText().trim();

            if (name.isEmpty() || grade.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
                return;
            }

            Student student = new Student(name, id, grade);
            students.add(student);

            JOptionPane.showMessageDialog(this, "Student added successfully!");
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID.");
        }
    }

    // Method to add teacher
    private void addTeacher() {
        try {
            String name = teacherNameField.getText().trim();
            int id = Integer.parseInt(teacherIdField.getText().trim());
            String subject = teacherSubjectField.getText().trim();

            if (name.isEmpty() || subject.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
                return;
            }

            Teacher teacher = new Teacher(name, id, subject);
            teachers.add(teacher);

            JOptionPane.showMessageDialog(this, "Teacher added successfully!");
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID.");
        }
    }

    // Method to view students
    private void viewStudents() {
        displayArea.setText("");  // Clear previous content
        if (students.isEmpty()) {
            displayArea.append("No students added yet.\n");
        } else {
            displayArea.append("ID\tName\t\tGrade\n");
            displayArea.append("---------------------------------\n");
            for (Student student : students) {
                displayArea.append(student.getDetails() + "\n");
            }
        }
    }

    // Method to view teachers
    private void viewTeachers() {
        displayArea.setText("");  // Clear previous content
        if (teachers.isEmpty()) {
            displayArea.append("No teachers added yet.\n");
        } else {
            displayArea.append("ID\tName\t\tSubject\n");
            displayArea.append("---------------------------------\n");
            for (Teacher teacher : teachers) {
                displayArea.append(teacher.getDetails() + "\n");
            }
        }
    }

    // Clear input fields
    private void clearFields() {
        studentNameField.setText("");
        studentIdField.setText("");
        studentGradeField.setText("");
        teacherNameField.setText("");
        teacherIdField.setText("");
        teacherSubjectField.setText("");
    }

    // Create a styled button with a 3D effect
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Times New Roman", Font.BOLD, 14));
        button.setBackground(new Color(100, 149, 237)); // Cornflower blue for buttons
        button.setForeground(Color.WHITE);  // White text on buttons
        button.setBorder(new BevelBorder(BevelBorder.RAISED));  // 3D effect
        return button;
    }

    // Customize text field with a 3D effect
    private void customizeTextField(JTextField textField) {
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        textField.setBackground(new Color(230, 230, 250));  // Light lavender background
        textField.setForeground(Color.BLACK);  // Dark text
        textField.setBorder(new BevelBorder(BevelBorder.LOWERED)); // 3D effect
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SchoolManagementSystemGUI app = new SchoolManagementSystemGUI();
                app.setVisible(true);
            }
        });
    }
}
