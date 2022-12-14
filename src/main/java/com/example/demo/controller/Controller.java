package com.example.demo.controller;

import com.example.demo.controller.Models.Student;
import com.example.demo.controller.Models.Subject;
import com.example.demo.controller.Services.StudentService;
import com.example.demo.controller.Services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/createStudent")
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        studentService.save(student);
        return new ResponseEntity<>("Student record was created successfully, Student id: " + student.getId(), HttpStatus.OK);
    }

    @PostMapping("/createSubject")
    public String createSubject(@RequestBody Subject subject) {
        if(subject.getSubjectName().equalsIgnoreCase(subjectService.findByClassName(subject.getSubjectName()).getSubjectName())) {
            return "Subject already exists!";
        } else {
            return subjectService.save(subject);
        }
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable() Integer id) {
        studentService.deleteById(id);
        return new ResponseEntity<>("Student record was deleted Successfully, Student id: " + id,HttpStatus.OK);
    }

    @GetMapping("/findStudentById/{id}")
    public ResponseEntity<Student> findStudentByd(@PathVariable() Integer id) {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/updateStudentDetails/{id}")
    public void updateStudentDetails(@RequestBody() Student student, @PathVariable Integer id) {
        studentService.save(student);
    }

    @PutMapping("/updateSubjectDetails/{id}")
    public String updateStudentDetails(@RequestBody() Subject subject, @PathVariable Integer id) {
        return subjectService.save(subject);
    }

    @GetMapping("viewStudentSubjects/{id}")
    public ResponseEntity<String> viewStudentSubjects(@PathVariable() Integer id) {
        return new ResponseEntity<>("Student subjects: " + studentService.findById(id).getSubjects().toString(), HttpStatus.OK);
    }

    @GetMapping("viewAllStudentsByClassName/{className}")
    public List<Student> viewAllStudentsByClassName(@PathVariable String className) {
        return studentService.getAllStudentsByClassName(className);
    }

    @GetMapping("viewAllStudents")
    public List<Student> viewAllStudentsByClassName() {
        return studentService.findAllStudents();
    }

    @PutMapping("joinClass/{className}")
    public String joinClass(@PathVariable() String className, @RequestBody Student student) {
        //TODO
        return studentService.joinClass(className, student);
    }

    @PutMapping("dropClass/{className}")
    public String dropClass(@PathVariable() String className, @RequestBody Student student) {
        //TODO
        return studentService.dropClass(className, student);
    }

    @GetMapping("ViewAllSubjectsAvailable")
    public List<Subject> getAllSubjectsWithSpace() {
        return subjectService.getAllSubjectsWithSpace();
    }

    @GetMapping("ViewAllSubjects")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }
}
