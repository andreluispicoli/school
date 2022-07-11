package com.school.app.repository;

import com.school.app.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findCoursesByStudentsIsNull();

    List<Course> findCoursesByStudentsId(Long studentId);

}
