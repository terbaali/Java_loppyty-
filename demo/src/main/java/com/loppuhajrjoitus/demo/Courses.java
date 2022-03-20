package com.loppuhajrjoitus.demo;

public class Courses {
    private String name;
    private String teacher;
    private int Credits;
    private int courseID;
    private static int IDCounter = 1;


    public Courses() {
    }

    public Courses(String name, String teacher, int Credits) {
        this.name = name;
        this.teacher = teacher;
        this.Credits = Credits;
        this.courseID = IDCounter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getCredits() {
        return Credits;
    }

    public void setCredits(int credits) {
        Credits = credits;
    }
}
