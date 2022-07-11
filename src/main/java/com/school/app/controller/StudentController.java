package com.school.app.controller;

import com.school.app.model.Course;
import com.school.app.model.Student;
import com.school.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    private Student getStudentById(@PathVariable("id") Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/")
    private List<Student> listAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/empty")
    private List<Student> listAllStudentsWithoutCourses(){
        return studentService.getStudentsWithoutAnyCourse();
    }

    @GetMapping("course/{id}")
    private List<Student> listStudentsByCourseId(@PathVariable("id") Long courseId) {
        return studentService.getStudentsByCourseId(courseId);
    }

    @PostMapping("/")
    private void createStudent(@Validated @RequestBody Student student) {
        studentService.createStudent(student);
    }

    @PatchMapping("/{id}")
    private void updateStudent(@PathVariable("id") Long id, @Validated @RequestBody Student student) {
        studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    private void deleteStudent(@PathVariable("id") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/register/{studentId}/course/{courseId}")
    private void registerStudentToCourse(
            @PathVariable("studentId") Long studentId,
            @PathVariable("courseId") Long courseId
    ) throws Exception {
        studentService.registerCoursesToStudent(studentId, courseId);
    }

}
