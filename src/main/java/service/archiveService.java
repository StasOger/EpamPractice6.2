package service;

import model.Student;
import repository.UserRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;

public class archiveService {
    private UserRepository userRepository = new UserRepository();

    public void run() {
        try {
            System.out.println("Enter login");
            Scanner in1 = new Scanner(System.in);
            String login = in1.nextLine();
            System.out.println("Enter password");
            String password = in1.nextLine();
// проверка кто вы админ или юзер

            if (login.equals("admin") && password.equals("admin")) {
                adminOperation();
            } else {
                Student student = userRepository.getStudent(login, password);
                userOperation();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void adminOperation() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        String start = "\n" +
                "- Enter \"1\" for showInformation;\n" +
                "- Enter \"2\" for addStudent;\n" +
                "- Enter \"3\" for editStudent;\n" +
                "- Enter \"4\" for deleteStudent;\n" +
                "for end enter \"exit\"\n";
        System.out.println(start);
        while (!choice.equalsIgnoreCase("exit")) {
            System.out.print("Enter command: ");
            choice = reader.readLine();
            switch (choice) {
                case "1":
                    showInformation();
                    break;
                case "2":
                    addStudent();
                    break;
                case "3":
                    editStudent();
                    break;
                case "4":
                    deleteStudent();
                    break;
                case "exit":
                    System.out.println("\nOver.");
                    break;
                default:
                    System.out.println("Incorrect. Please repeat one more time.\n");
            }
        }
    }

    private void userOperation() throws IOException, ParseException {
        System.out.println("gay");
    }

    private void deleteStudent() {
        List<Student> studentList = userRepository.selectAllStudents();
        List<Student> newStudentList = new ArrayList<>();
        for (Student s:  studentList){
            System.out.println(s);
        }

        System.out.println("Enter id of student which you want to delete: ");
        Scanner in1 = new Scanner(System.in);
        int idOfDeletedBook = in1.nextInt();
        for (Student s: studentList){
            if (s.getId() != idOfDeletedBook){
                newStudentList.add(s);
            }
        }
        userRepository.deleteStudent(newStudentList);
        System.out.println("Student deleted!");
    }

    private void editStudent() {

    }

    private void addStudent() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter firstname of new student: ");
        Scanner in1 = new Scanner(System.in);
        String newFirstName = in1.nextLine();

        System.out.println("Enter lastname of new student: ");
        String newLastName = in1.nextLine();

        System.out.println("Enter age of new student: ");
        int newAge = in1.nextInt();

        System.out.println("Enter faculty of new student: ");
        String newFaculty = in1.nextLine();

        System.out.println("Enter course of new student: ");
        int newCourse = in1.nextInt();

        System.out.println("Enter year of admission of new student: ");
        int newYearOfAdmission = in1.nextInt();

        System.out.println("Enter new login of new student: ");
        String newLogin = in1.nextLine();

        System.out.println("Enter new password of new student: ");
        String newPassword = in1.nextLine();

        List<Student> studentList = userRepository.selectAllStudents();

        userRepository.addStudent(new Student(studentList.size()+1, newFirstName, newLastName, newAge, newFaculty, newCourse, newYearOfAdmission, newLogin, newPassword));
        System.out.println("Student added!");
    }

    private void showInformation() {
        List<Student> studentList = userRepository.selectAllStudents();
        for (Student b: studentList){
            System.out.println(b);
        }
    }
}


