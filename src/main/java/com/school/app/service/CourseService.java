package com.school.app.service;

import com.school.app.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course getCourseById(Long courseId);

    List<Course> getAllCourses();

    List<Course> getCoursesWithoutAnyStudent();

    List<Course> getCourseByStudentId(Long studentId);

    Course createCourse(Course course);

    Course updateCourse(Long courseId, Course course);

    void deleteCourse(Long courseId);

}
