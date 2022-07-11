package com.school.app.repository;

import com.school.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByCoursesIsNull();

    List<Student> findStudentsByCoursesId(Long courseId);

}
