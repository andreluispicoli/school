package com.school.app.service.Impl;

import com.school.app.exception.CourseNotFoundException;
import com.school.app.model.Course;
import com.school.app.repository.CourseRepository;
import com.school.app.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getCoursesWithoutAnyStudent() {
        return courseRepository.findCoursesByStudentsIsNull();
    }

    @Override
    public List<Course> getCourseByStudentId(Long studentId) {
        return courseRepository.findCoursesByStudentsId(studentId);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long courseId, Course course) {
        Course existentCourse = getCourseById(courseId);
        existentCourse.setName(course.getName());
        courseRepository.save(existentCourse);
        return existentCourse;
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

}
