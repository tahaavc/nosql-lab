package app.model;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String student_no;
    private String name;
    private String department;

    public Student(String student_no, String name, String department) {
        this.student_no = student_no;
        this.name = name;
        this.department = department;
    }


    public String getStudent_no() { return student_no; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
}