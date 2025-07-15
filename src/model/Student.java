package model;

public class Student {

    private int studentId;
    private String name;
    private String dob;
    private String email;
    private int marks;
    private String category;

    public Student(int studentId, String name, String dob, String email, int marks, String category) {
        this.studentId = studentId;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.marks = marks;
        this.category = category;
    }

    public Student() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
