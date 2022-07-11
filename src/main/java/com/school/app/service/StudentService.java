package com.school.app.service;

import com.school.app.model.Student;

import java.util.List;

public interface StudentService {

    Student getStudentById(Long studentId);

    List<Student> getAllStudents();

    List<Student> getStudentsWithoutAnyCourse();

    List<Student> getStudentsByCourseId(Long courseId);

    Student createStudent(Student student);

    Student updateStudent(Long studentId, Student student);

    void deleteStudent(Long studentId);

    void registerCoursesToStudent(Long studentId, Long courseId) throws Exception;

}
