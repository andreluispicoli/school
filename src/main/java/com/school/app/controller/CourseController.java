package com.school.app.controller;

import com.school.app.model.Course;
import com.school.app.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    private Course getCourseById(@PathVariable("id") Long studentId){
        return courseService.getCourseById(studentId);
    }

    @GetMapping("/")
    private List<Course> listAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/empty")
    private List<Course> listAllCoursesWithoutStudents(){
        return courseService.getCoursesWithoutAnyStudent();
    }

    @GetMapping("student/{id}")
    private List<Course> listCoursesByStudentId(@PathVariable("id") Long studentId) {
        return courseService.getCourseByStudentId(studentId);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    private Course createCourse(@Validated @RequestBody Course course) throws Exception{
        return courseService.createCourse(course);
    }

    @PatchMapping("/{id}")
    private Course updateCourse(@PathVariable("id") Long id, @Validated @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    private void deleteCourse(@PathVariable("id") Long courseId) {
        courseService.deleteCourse(courseId);
    }

}
