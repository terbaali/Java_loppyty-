package com.loppuhajrjoitus.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.loppuhajrjoitus.demo.FileService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {
    @Autowired
    FileService fileservice;

    private List<Students> students = new ArrayList<>();
    private List<Courses> courses = new ArrayList<>();
    private List<Enrol> enrols = new ArrayList<>();

    /**
     *  Konstruktori, jossa haetaan tiedostton tallennetut opiskelijoiden tiedot
     */
    public DataService() {
        FileService fs = new FileService();
        students = fs.studentsFromFile();
    }

    /**
     *  Haetaan kaikki opiskelijat ja palautetaan opiskelijalista RESTAPI-kontrollerille
     * @return
     */
    public String getStudents(){
        StringBuilder studentList = new StringBuilder();
        for (Students s : students) {
            studentList.append("ID: "+s.getStudentID()+" "+"Name: "+s.getFname()+" "+s.getLname()+" "+"Class: "+s.getClassID()+"<br>");
        }
        return ""+studentList;
    }

    /**
     *  Haetaan opiskelijakoosteesta opiskelijan tiedot ID:n perusteella ja palautetaan tiedot RESTAPI-kontrollerille
     * @param id
     * @return
     */
    public String getStudentByID(int id) {
        Students s = students.get(id-1);
        return "ID: "+s.getStudentID()+" "+"Name: "+s.getFname()+" "+s.getLname()+" "+"Class: "+s.getClassID();

    }

    /**
     *  Luodaan uusi opiskelija saatujen parametrien mukaisesti. Uusi opiskelija tallenetaan opiskelijakoosteeseen ja tiedostoon students.txt
     * @param fName
     * @param lName
     * @param classID
     * @return
     */
    public String addStudent(String fName, String lName, String classID){
        Students s = new Students(fName, lName, classID);
        students.add(s);
        fileservice.studentToFile(s);
        return "Student added";
    }

    /**
     *
     * @return
     */
    public String getCourses(){
        StringBuilder courseList = new StringBuilder();
        for (Courses c : courses) {
            courseList.append("ID: "+c.getCourseID()+" "+"Name: "+c.getName()+" "+"Teacher: "+c.getTeacher()+" "+"Credits: "+c.getCredits()+"<br>");
        }
        return ""+courseList;
    }

    /**
     *
     * @param id
     * @return
     */
    public String getCoursesByID(int id) {
        Courses c = courses.get(id-1);
        return "ID: "+c.getCourseID()+" "+"Name: "+c.getName()+" "+"Teacher: "+c.getTeacher()+" "+"Credits: "+c.getCredits()+"<br>";
    }

    /**
     *
     * @param name
     * @param teacher
     * @param credits
     * @return
     */
    public String addCourse(String name, String teacher, int credits){
        Courses c = new Courses(name,teacher, credits);
        courses.add(c);
        return "Course added";
    }

    /**
     *
     * @param id
     * @param name
     * @param teacher
     * @param credits
     * @return
     */
    public String editCourse(int id, String name, String teacher, int credits){
        for (Courses c : courses) {
            if (c.getCourseID() == id){
                if(name.length()>5){
                    c.setName(name);
                }else if(teacher.length()>5){
                    c.setTeacher(teacher);
                }else if(credits > 0){
                    c.setCredits(credits);
                }
                return "Course updated";
            }
        }
        return "Incorrect course ID";
    }

    /**
     *
     * @param courseID
     * @param studentID
     * @return
     */
    public String enrol(int courseID, int studentID){
        for (Courses c : courses) {
            if (c.getCourseID() == courseID){
                for (Students s : students) {
                    if (s.getStudentID() == studentID){
                        Enrol e = new Enrol(courseID,studentID);
                        enrols.add(e);
                        return "Enrol succeeded";
                    }
                }
                return "Incorrect student ID";
            }
        }
        return "Incorrect course ID";
    }

    /**
     *
     * @param courseID
     * @return
     */
    public String cuorseStudents(int courseID){
        Courses c = new Courses();
        StringBuilder studentList = new StringBuilder("Course "+getCoursesByID(courseID)+" students:<br>");
        for (Enrol e : enrols) {
            if(e.getCourseID() == courseID) {
                studentList.append(getStudentByID(e.getStudentID()));
            }
        }
        return ""+studentList;
    }

    /**
     *
     * @param studentID
     * @return
     */
    public String studentsCourses(int studentID) {
        Courses c = new Courses();
        StringBuilder courseList = new StringBuilder("Student " + getStudentByID(studentID) + " courses:<br>");
        for (Enrol e : enrols) {
            if (e.getStudentID() == studentID) {
                courseList.append(getCoursesByID(e.getCourseID()));
            }
        }
        return ""+courseList;
    }

}
