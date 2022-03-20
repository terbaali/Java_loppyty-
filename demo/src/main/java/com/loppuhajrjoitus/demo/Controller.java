package com.loppuhajrjoitus.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.loppuhajrjoitus.demo.DataService;

@RestController
public class Controller {
    @Autowired
    DataService service;

    String returnButton = "<br><a href='http://localhost:8080'><button>Return</button></a>";

    /**
     *
     * @return
     */
    @GetMapping("students")
    public String getStudents(){
        return service.getStudents() + returnButton;
    }

    /**
     *
     * @param ID
     * @return
     */
    @GetMapping("student/")
    public String getStudent(@RequestParam String ID) {
        try {
            int studentId = Integer.parseInt(ID);
            return service.getStudentByID(studentId) + returnButton;
        }
        catch (Exception e){
            return "Incorrect ID" + returnButton;
        }
    }

    /**
     *
     * @param fName
     * @param lName
     * @param classID
     * @return
     */
    @PostMapping("addstudent")
    public String addStudent(
            @RequestParam String fName,
            @RequestParam String lName,
            @RequestParam String classID){
        if(fName.length()<2){
            return "Incorrect student fist name";
        }else if(lName.length()<2){
            return "Incorrect student last name";
        }else if(classID.length()<2){
            return "Incorrect student class id";
        }else {
            return service.addStudent(fName, lName, classID) + returnButton;
        }
    }

    /**
     *
     * @return
     */
    @GetMapping("courses")
    public String getCourses(){
        return service.getCourses() + returnButton;
    }

    /**
     *
     * @param name
     * @param teacher
     * @param credits
     * @return
     */
    @PostMapping("addcourse")
    public String addCourse(
            @RequestParam String name,
            @RequestParam String teacher,
            @RequestParam int credits){
        if(name.length()<5){
            return "Incorrect course name";
        }else if(teacher.length()<5){
            return "Incorrect course teacher";
        }else {
            return service.addCourse(name, teacher, credits) + returnButton;
        }
    }

    /**
     *
     * @param id
     * @param name
     * @param teacher
     * @param credits
     * @return
     */
    @PostMapping("editcourse")
    public String editCourse(
            @RequestParam String id,
            @RequestParam(name="name",required = false) String name,
            @RequestParam(name="teacher",required = false) String teacher,
            @RequestParam(value = "credits", required = false, defaultValue = "0") int credits){
        try {
            int courseID = Integer.parseInt(id);
            return service.editCourse(courseID, name, teacher, credits) + returnButton;
        }
        catch (Exception e){
            return "Incorrect ID" + returnButton;
        }
    }

    /**
     *
     * @param courseID
     * @param studentID
     * @return
     */
    @PostMapping("enrol")
    public String enrol(
            @RequestParam String courseID,
            @RequestParam String studentID){
        try {
            int studentId = Integer.parseInt(studentID);
            int courseId = Integer.parseInt(courseID);
            return service.enrol(courseId, studentId) + returnButton;
        }
        catch (Exception e){
            return "Incorrect ID" + returnButton;
        }
    }

    /**
     *
     * @param ID
     * @return
     */
    @GetMapping("cuorseStudents/")
    public String cuorseStudents(@RequestParam String ID) {
        try {
            int courseID = Integer.parseInt(ID);
            return service.cuorseStudents(courseID) + returnButton;
        }
        catch (Exception e){
            return "Incorrect ID" + returnButton;
        }
    }

    /**
     *
     * @param ID
     * @return
     */
    @GetMapping("studentsCourses/")
    public String studentsCourses(@RequestParam String ID) {
        try {
            int studentID = Integer.parseInt(ID);
            return service.studentsCourses(studentID) + returnButton;
        }
        catch (Exception e){
            return "Incorrect ID" + returnButton;
        }
    }

}
