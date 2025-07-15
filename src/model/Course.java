package model;

public class Course {

    private int courseId;
    private String courseName;
    private int totalSeats;
    private int cutOffMarks;

    public Course() {
    }

    public Course(int courseId, String courseName, int totalSeats, int cutOffMarks) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.totalSeats = totalSeats;
        this.cutOffMarks = cutOffMarks;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getCutOffMarks() {
        return cutOffMarks;
    }

    public void setCutOffMarks(int cutOffMarks) {
        this.cutOffMarks = cutOffMarks;
    }
}
