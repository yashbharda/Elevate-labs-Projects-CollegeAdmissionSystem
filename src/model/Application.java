package model;

public class Application {

    private int applicationId;
    private int studentId;
    private int courseId;
    private String status;

    public Application() {
    }

    public Application(int applicationId, int studentId, int courseId, String status) {
        this.applicationId = applicationId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.status = status;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
