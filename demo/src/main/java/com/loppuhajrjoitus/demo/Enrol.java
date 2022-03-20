package com.loppuhajrjoitus.demo;

public class Enrol {
    private int courseID;
    private int studentID;

    public Enrol(int courseID, int studentID) {
        this.courseID = courseID;
        this.studentID = studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getStudentID() {
        return studentID;
    }
}
