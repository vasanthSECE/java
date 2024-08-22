import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Course {
    private String courseId;
    private String courseName;
    private double coursePrice;
    private List<Student> enrolledStudents;

    public Course(String courseId, String courseName, double coursePrice) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.coursePrice = coursePrice;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getCoursePrice() {
        return coursePrice;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", coursePrice=" + coursePrice +
                '}';
    }
}

class Student {
    private String studentId;
    private String studentName;
    private List<Course> enrolledCourses;
    private double balance;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.enrolledCourses = new ArrayList<>();
        this.balance = 0.0;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
        balance += course.getCoursePrice();
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public double getBalance() {
        return balance;
    }

    public void makePayment(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Payment of $" + amount + " successful. Remaining balance: $" + balance);
        } else {
            System.out.println("Payment amount exceeds balance.");
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", balance=" + balance +
                '}';
    }
}

public class RegistrationSystem {
    private Map<String, Student> students;
    private Map<String, Course> courses;
    private Scanner scanner;

    public RegistrationSystem() {
        students = new HashMap<>();
        courses = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void registerStudent(String studentId, String studentName) {
        Student student = new Student(studentId, studentName);
        students.put(studentId, student);
    }

    public void addCourse(String courseId, String courseName, double coursePrice) {
        Course course = new Course(courseId, courseName, coursePrice);
        courses.put(courseId, course);
    }

    public void enrollStudentInCourse(String studentId, String courseId) {
        Student student = students.get(studentId);
        Course course = courses.get(courseId);

        if (student != null && course != null) {
            student.enrollCourse(course);
            course.enrollStudent(student);
        } else {
            System.out.println("Invalid student ID or course ID");
        }
    }

    public void listStudentCourses(String studentId) {
        Student student = students.get(studentId);
        if (student != null) {
            System.out.println("Courses for student " + student.getStudentName() + ":");
            for (Course course : student.getEnrolledCourses()) {
                System.out.println(course);
            }
        } else {
            System.out.println("Student not found");
        }
    }

    public void listCourseStudents(String courseId) {
        Course course = courses.get(courseId);
        if (course != null) {
            System.out.println("Students enrolled in course " + course.getCourseName() + ":");
            for (Student student : course.getEnrolledStudents()) {
                System.out.println(student);
            }
        } else {
            System.out.println("Course not found");
        }
    }

    public void processPayment(String studentId, double amount) {
        Student student = students.get(studentId);
        if (student != null) {
            student.makePayment(amount);
        } else {
            System.out.println("Student not found");
        }
    }

    public void start() {
        while (true) {
            System.out.println("\nOnline Course Registration System");
            System.out.println("1. Register Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. List Student's Courses");
            System.out.println("5. List Course's Students");
            System.out.println("6. Process Payment");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    registerStudent(studentId, studentName);
                    break;
                case 2:
                    System.out.print("Enter course ID: ");
                    String courseId = scanner.nextLine();
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter course price: ");
                    double coursePrice = scanner.nextDouble();
                    addCourse(courseId, courseName, coursePrice);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("Enter course ID: ");
                    courseId = scanner.nextLine();
                    enrollStudentInCourse(studentId, courseId);
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    listStudentCourses(studentId);
                    break;
                case 5:
                    System.out.print("Enter course ID: ");
                    courseId = scanner.nextLine();
                    listCourseStudents(courseId);
                    break;
                case 6:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("Enter payment amount: ");
                    double amount = scanner.nextDouble();
                    processPayment(studentId, amount);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        RegistrationSystem system = new RegistrationSystem();
        system.start();
    }
}
