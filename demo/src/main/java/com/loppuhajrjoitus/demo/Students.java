package com.loppuhajrjoitus.demo;

public class Students {
    private String fname;
    private String lname;
    private int studentID;
    private String classID;
    private static int IDCounter = 1;

    public Students(){
    }

    public Students(String fname, String lname, String classID) {
        this.fname = fname;
        this.lname = lname;
        this.studentID = IDCounter++;
        this.classID = classID;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getClassID() {
        return classID;
    }

}
