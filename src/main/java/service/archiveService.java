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
                    System.out.println("Enter student id");
                    Scanner in1 = new Scanner(System.in);
                    int id = in1.nextInt();
                    editStudent(id);
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

    private void editStudent(int id) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        String start = "\n" +
                "- Enter \"1\" for edit firstname;\n" +
                "- Enter \"2\" for edit lastName;\n" +
                "- Enter \"3\" for edit age;\n" +
                "- Enter \"4\" for edit faculty;\n" +
                "- Enter \"5\" for edit course;\n" +
                "- Enter \"6\" for edit yearOfAdmission;\n" +
                "- Enter \"7\" for edit login;\n" +
                "- Enter \"8\" for edit password;\n" +
                "for end enter \"exit\"\n";
        System.out.println(start);
        while (!choice.equalsIgnoreCase("exit")) {
            System.out.print("Enter command: ");
            choice = reader.readLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter new firstname: ");
                    Scanner in1 = new Scanner(System.in);
                    String newFirstname = in1.nextLine();
                    String parametr = "firstname";
                    changeParametr(parametr, newFirstname, id);
                    break;
                case "2":
                    System.out.println("Enter new lastName: ");
                    Scanner in2 = new Scanner(System.in);
                    String newLastName = in2.nextLine();
                    parametr = "lastname";
                    changeParametr(parametr, newLastName, id);
                    break;
                case "3":
                    System.out.println("Enter new age: ");
                    Scanner in3 = new Scanner(System.in);
                    String newAge = in3.nextLine();
                    parametr = "age";
                    changeParametr(parametr, newAge, id);
                    break;
                case "4":
                    System.out.println("Enter new faculty: ");
                    Scanner in4 = new Scanner(System.in);
                    String newFaculty = in4.nextLine();
                    parametr = "faculty";
                    changeParametr(parametr, newFaculty, id);
                    break;
                case "5":
                    System.out.println("Enter new course: ");
                    Scanner in5 = new Scanner(System.in);
                    String newCourse = in5.nextLine();
                    parametr = "course";
                    changeParametr(parametr, newCourse, id);
                    break;
                case "6":
                    System.out.println("Enter new yearOfAdmission: ");
                    Scanner in6 = new Scanner(System.in);
                    String newYearOfAdmission = in6.nextLine();
                    parametr = "yearOfAdmission";
                    changeParametr(parametr, newYearOfAdmission, id);
                    break;
                case "7":
                    System.out.println("Enter new login: ");
                    Scanner in7 = new Scanner(System.in);
                    String newLogin = in7.nextLine();
                    parametr = "login";
                    changeParametr(parametr, newLogin, id);
                    break;
                case "8":
                    System.out.println("Enter new password: ");
                    Scanner in8 = new Scanner(System.in);
                    String newPassword = in8.nextLine();
                    parametr = "password";
                    changeParametr(parametr, newPassword, id);
                    break;
                case "exit":
                    System.out.println("\nOver.");
                    break;
                default:
                    System.out.println("Incorrect. Please repeat one more time.\n");
            }
        }
    }

    private void changeParametr(String parametr, String newParametr, int id) {
        List<Student> studentList = userRepository.selectAllStudents();
        for (Student b: studentList){
            if (b.getId() == id){
                if (parametr == "firstname"){
                    b.setFirstName(newParametr);
                } else if (parametr == "lastName"){
                    b.setLastName(newParametr);
                } else if (parametr == "age"){
                    b.setLastName(String.valueOf(newParametr));
                } else if (parametr == "faculty"){
                    b.setLastName(newParametr);
                } else if (parametr == "course"){
                    b.setLastName(String.valueOf(newParametr));
                } else if (parametr == "yearOfAdmission"){
                    b.setLastName(String.valueOf(newParametr));
                } else if (parametr == "login"){
                    b.setLastName(newParametr);
                } else if (parametr == "password"){
                    b.setLastName(newParametr);
                }
            }
        }
        for (Student b: studentList){
            System.out.println(b);
        }
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


