package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Parent Person Class
class Person {
    private String name;
    private String dateOfBirth; // Date of birth in "YYYY-MM-DD" format

    public Person(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return name + " (DOB: " + dateOfBirth + ")";
    }
}

// Teacher Class
class Teacher extends Person {
    private int teacherId;

    public Teacher(int teacherId, String name, String dateOfBirth) {
        super(name, dateOfBirth);
        this.teacherId = teacherId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    @Override
    public String toString() {
        return teacherId + ": " + getName() + " (DOB: " + getDateOfBirth() + ")";
    }
}

// Student Class
class Student extends Person {
    private String matricule;
    private String speciality;  // New speciality attribute
    private List<Note> notes; // List to store notes for each module

    public Student(String matricule, String name, String dateOfBirth, String speciality) {
        super(name, dateOfBirth);
        this.matricule = matricule;
        this.speciality = speciality;
        this.notes = new ArrayList<>();
    }

    public void addNote(Note note) {
        notes.add(note); // Add note to the student's list
    }

    public String getMatricule() {
        return matricule;
    }

    public String getSpeciality() {
        return speciality;
    }

    public List<Note> getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return matricule + ": " + getName() + " (DOB: " + getDateOfBirth() + ") - Speciality: " + speciality;
    }
}

// Module Class
class Module {
    private String moduleCode;
    private String moduleName;
    private Teacher teacher;
    private List<Note> notes; // List to store notes of students in this module

    public Module(String moduleCode, String moduleName, Teacher teacher) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.teacher = teacher;
        this.notes = new ArrayList<>();
    }

    public void addNote(Note note) {
        notes.add(note); // Add note to the module's list
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Note> getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return moduleCode + ": " + moduleName + " (Teacher: " + teacher.getName() + ")";
    }
}

// Note Class - Represents the relation between Student and Module with a score
class Note {
    private Student student;
    private Module module;
    private double score; // Score for the student in the module

    public Note(Student student, Module module, double score) {
        this.student = student;
        this.module = module;
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public Module getModule() {
        return module;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student: " + student.getName() + " | Module: " + module.getModuleName() + " | Score: " + score;
    }
}

public class Univmanagesys {
    private static List<Student> students = new ArrayList<>();
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<Module> modules = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== University Management System ===");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Teachers");
            System.out.println("3. Manage Modules");
            System.out.println("4. Assign Notes to Students");
            System.out.println("5. Display Information");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    manageStudents();
                    break;
                case 2:
                    manageTeachers();
                    break;
                case 3:
                    manageModules();
                    break;
                case 4:
                    assignNotes();
                    break;
                case 5:
                    displayInformation();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        System.out.println("Exiting the University Management System.");
    }

    private static void manageStudents() {
        System.out.println("\n=== Manage Students ===");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Edit Student");
        System.out.println("4. Delete Student");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                viewStudents();
                break;
            case 3:
                editStudent();
                break;
            case 4:
                deleteStudent();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void addStudent() {
        System.out.print("Enter matricule: ");
        String matricule = scanner.nextLine();

        System.out.print("Enter full name: ");
        String name = scanner.nextLine();

        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();

        System.out.print("Enter speciality (e.g., Computer Science, Mathematics): ");
        String speciality = scanner.nextLine();

        // Check if student already exists
        for (Student student : students) {
            if (student.getMatricule().equals(matricule)) {
                System.out.println("Student with this matricule already exists.");
                return;
            }
        }

        Student student = new Student(matricule, name, dateOfBirth, speciality);
        students.add(student);
        System.out.println("Student added successfully.");
    }

    private static void viewStudents() {
        System.out.println("\n=== Students ===");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void editStudent() {
        System.out.print("Enter matricule of student to edit: ");
        String matricule = scanner.nextLine();
        
        Student student = findStudentByMatricule(matricule);
        if (student != null) {
            System.out.print("Enter new full name: ");
            String newName = scanner.nextLine();

            System.out.print("Enter new date of birth (YYYY-MM-DD): ");
            String newDateOfBirth = scanner.nextLine();

            System.out.print("Enter new speciality: ");
            String newSpeciality = scanner.nextLine();

            student = new Student(matricule, newName, newDateOfBirth, newSpeciality); // Update the student details
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter matricule of student to delete: ");
        String matricule = scanner.nextLine();
        
        Student student = findStudentByMatricule(matricule);
        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static Student findStudentByMatricule(String matricule) {
        for (Student student : students) {
            if (student.getMatricule().equals(matricule)) {
                return student;
            }
        }
        return null;
    }

    private static void manageTeachers() {
        System.out.println("\n=== Manage Teachers ===");
        System.out.println("1. Add Teacher");
        System.out.println("2. View Teachers");
        System.out.println("3. Edit Teacher");
        System.out.println("4. Delete Teacher");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                addTeacher();
                break;
            case 2:
                viewTeachers();
                break;
            case 3:
                editTeacher();
                break;
            case 4:
                deleteTeacher();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void addTeacher() {
        System.out.print("Enter teacher ID: ");
        int teacherId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter full name: ");
        String name = scanner.nextLine();

        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();

        // Check if teacher already exists
        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId() == teacherId) {
                System.out.println("Teacher with this ID already exists.");
                return;
            }
        }

        Teacher teacher = new Teacher(teacherId, name, dateOfBirth);
        teachers.add(teacher);
        System.out.println("Teacher added successfully.");
    }

    private static void viewTeachers() {
        System.out.println("\n=== Teachers ===");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }

    private static void editTeacher() {
        System.out.print("Enter teacher ID of teacher to edit: ");
        int teacherId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        
        Teacher teacher = findTeacherById(teacherId);
        if (teacher != null) {
            System.out.print("Enter new full name: ");
            String newName = scanner.nextLine();

            System.out.print("Enter new date of birth (YYYY-MM-DD): ");
            String newDateOfBirth = scanner.nextLine();

            teacher = new Teacher(teacherId, newName, newDateOfBirth); // Update the teacher name and DOB
            System.out.println("Teacher updated successfully.");
        } else {
            System.out.println("Teacher not found.");
        }
    }

    private static void deleteTeacher() {
        System.out.print("Enter teacher ID of teacher to delete: ");
        int teacherId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        
        Teacher teacher = findTeacherById(teacherId);
        if (teacher != null) {
            teachers.remove(teacher);
            System.out.println("Teacher deleted successfully.");
        } else {
            System.out.println("Teacher not found.");
        }
    }

    private static Teacher findTeacherById(int teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId() == teacherId) {
                return teacher;
            }
        }
        return null;
    }

    private static void manageModules() {
        System.out.println("\n=== Manage Modules ===");
        System.out.println("1. Add Module");
        System.out.println("2. View Modules");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                addModule();
                break;
            case 2:
                viewModules();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void addModule() {
        System.out.print("Enter module code: ");
        String moduleCode = scanner.nextLine();

        System.out.print("Enter module name: ");
        String moduleName = scanner.nextLine();

        if (teachers.isEmpty()) {
            System.out.println("No teachers available to assign to a module.");
            return;
        }

        System.out.println("Select teacher for the module:");
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println(i + 1 + ". " + teachers.get(i));
        }
        int teacherChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (teacherChoice < 1 || teacherChoice > teachers.size()) {
            System.out.println("Invalid teacher selection.");
            return;
        }
        Teacher teacher = teachers.get(teacherChoice - 1);

        Module module = new Module(moduleCode, moduleName, teacher);
        modules.add(module);
        System.out.println("Module added successfully.");
    }



    private static void viewModules() {
        System.out.println("\n=== Modules ===");
        for (Module module : modules) {
            System.out.println(module);
        }
    }

    private static void assignNotes() {
        System.out.println("\n=== Assign Notes ===");
        if (students.isEmpty()) {
            System.out.println("No students available to assign notes.");
            return;
        }
        System.out.println("Select student:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(i + 1 + ". " + students.get(i));
        }
        int studentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (studentChoice < 1 || studentChoice > students.size()) {
            System.out.println("Invalid student selection.");
            return;
        }
        Student student = students.get(studentChoice - 1);

        if (modules.isEmpty()) {
            System.out.println("No modules available to assign notes.");
            return;
        }
        System.out.println("Select module:");
        for (int i = 0; i < modules.size(); i++) {
            System.out.println(i + 1 + ". " + modules.get(i));
        }
        int moduleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (moduleChoice < 1 || moduleChoice > modules.size()) {
            System.out.println("Invalid module selection.");
            return;
        }
        Module module = modules.get(moduleChoice - 1);

        System.out.print("Enter score for " + student.getName() + " in " + module.getModuleName() + ": ");
        double score = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        Note note = new Note(student, module, score);
        student.addNote(note);
        module.addNote(note);
        System.out.println("Note assigned successfully.");
    }



    private static void displayInformation() {
        System.out.println("\n=== All Information ===");
        System.out.println("Students:");
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("\nTeachers:");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }

        System.out.println("\nModules:");
        for (Module module : modules) {
            System.out.println(module);
        }
    }
}

