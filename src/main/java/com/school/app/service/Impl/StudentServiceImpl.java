package com.school.app.service.Impl;

import com.school.app.exception.CourseWithAllVacanciesFilledException;
import com.school.app.exception.StudentAlreadySubscribedInFiveCoursesException;
import com.school.app.exception.StudentNotFoundException;
import com.school.app.model.Course;
import com.school.app.model.Student;
import com.school.app.repository.StudentRepository;
import com.school.app.service.CourseService;
import com.school.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseService courseService;

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentsWithoutAnyCourse() {
        return studentRepository.findStudentsByCoursesIsNull();
    }

    @Override
    public List<Student> getStudentsByCourseId(Long courseId) {
        return studentRepository.findStudentsByCoursesId(courseId);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long studentId, Student student) {
        Student existentStudent = getStudentById(studentId);
        existentStudent.setName(student.getName());
        studentRepository.save(existentStudent);
        return existentStudent;
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void registerCoursesToStudent(Long studentId, Long courseId) throws Exception {
        Student student = getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);

        checkIfCourseStillHaveVacancy(course);
        checkIfStudentCanSubscribeIntoACourse(student);

        student.registerCourseIntoStudent(course);
        studentRepository.save(student);

    }

    private void checkIfCourseStillHaveVacancy(Course course) throws Exception {
        if(course.getStudents().size() > 49) {
            throw new CourseWithAllVacanciesFilledException(course.getId());
        }
    }

    private void checkIfStudentCanSubscribeIntoACourse(Student student) throws Exception {
        if(student.getCourses().size() > 4) {
            throw new StudentAlreadySubscribedInFiveCoursesException(student.getId());
        }
    }

}
