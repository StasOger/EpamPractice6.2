package repository;

import model.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final String ADDRESS_FILE = "src/main/resources/Users.csv";

    public Student getStudent (String login, String password){
        List<Student> students = readUsersFromCSV(ADDRESS_FILE);
        for (Student s : students) {
            if (login.equals(s.getLogin())&&password.equals(s.getPassword())){
                return s;
            }
        }
        return null;
    }

    public List<Student> selectAllStudents () {
        List<Student> students = readUsersFromCSV(ADDRESS_FILE);
//        let's print all the person read from CSV file
        return students;
    }

    public List<Student>  getAllStudents (){
        return readUsersFromCSV(ADDRESS_FILE);
    }

    private static List<Student> readUsersFromCSV(String fileName) {
        List<Student> students = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {

            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Student student = createStudent(attributes);
                students.add(student);
                line = br.readLine();
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return students;
    }

    private static Student createStudent(String[] metadata) {
        int id = Integer.parseInt(metadata[0]);
        String firstName = metadata[1];
        String lastName = metadata[2];
        int age = Integer.parseInt(metadata[3]);
        String faculty = metadata[4];
        int course = Integer.parseInt(metadata[5]);
        int yearOfAdmission = Integer.parseInt(metadata[6]);
        String login = metadata[7];
        String password = metadata[8];
        return new Student(id, firstName, lastName, age, faculty, course, yearOfAdmission, login, password);
    }

    public void deleteStudent(List<Student> studentList) {
        try(FileWriter writer = new FileWriter(ADDRESS_FILE, false))
        {
            for (int i = 0; i < studentList.size(); i++) {
                if (i!=0) {
                    writer.append("\n");
                }
                Student student = studentList.get(i);
                writer.append(String.valueOf(student.getId()));
                writer.append(",");
                writer.append(student.getFirstName());
                writer.append(",");
                writer.append(student.getLastName());
                writer.append(",");
                writer.append(String.valueOf(student.getAge()));
                writer.append(",");
                writer.append(student.getFaculty());
                writer.append(",");
                writer.append(String.valueOf(student.getCourse()));
                writer.append(",");
                writer.append(String.valueOf(student.getYearOfAdmission()));
                writer.append(",");
                writer.append(student.getLogin());
                writer.append(",");
                writer.append(student.getPassword());
            }
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void addStudent (Student student) throws FileNotFoundException {
        try(FileWriter writer = new FileWriter(ADDRESS_FILE, true))
        {
            writer.append("\n");
            writer.append(String.valueOf(student.getId()));
            writer.append(",");
            writer.append(student.getFirstName());
            writer.append(",");
            writer.append(student.getLastName());
            writer.append(",");
            writer.append(String.valueOf(student.getAge()));
            writer.append(",");
            writer.append(student.getFaculty());
            writer.append(",");
            writer.append(String.valueOf(student.getCourse()));
            writer.append(",");
            writer.append(String.valueOf(student.getYearOfAdmission()));
            writer.append(",");
            writer.append(student.getLogin());
            writer.append(",");
            writer.append(student.getPassword());

            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
