package com.school.app.service.Impl;

import com.school.app.exception.CourseNotFoundException;
import com.school.app.model.Course;
import com.school.app.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseServiceImpl;

    @Test
    void shouldGetCourseById() {
        final Course test = new Course("test");

        given(courseRepository.findById(anyLong())).willReturn(Optional.of(test));

        Course course = courseServiceImpl.getCourseById(anyLong());

        assertThat(course).isNotNull();

        verify(courseRepository).findById(anyLong());
    }

    @Test
    void shouldThrowCourseNotFoundException() {
        final Course test = new Course("test");

        given(courseRepository.findById(anyLong())).willThrow(CourseNotFoundException.class);

        assertThrows(
                CourseNotFoundException.class,
                () -> courseServiceImpl.getCourseById(1L),
                "Course with Id 1 not found"
        );
    }

    @Test
    void shouldGetAllCourses() {
        final Course test = new Course("test");
        final Course test1 = new Course("test1");

        List<Course> tests = new ArrayList<>();
        tests.add(test);
        tests.add(test1);

        given(courseRepository.findAll()).willReturn(tests);

        List<Course> courses = courseServiceImpl.getAllCourses();

        assertThat(courses).isNotNull();

        verify(courseRepository).findAll();

    }

    @Test
    void shouldGetCoursesWithoutAnyStudent() {
        final Course test = new Course("test");
        final Course test1 = new Course("test1");

        List<Course> tests = new ArrayList<>();
        tests.add(test);
        tests.add(test1);

        given(courseRepository.findCoursesByStudentsIsNull()).willReturn(tests);

        List<Course> courses = courseServiceImpl.getCoursesWithoutAnyStudent();

        assertThat(courses).isNotNull();

        verify(courseRepository).findCoursesByStudentsIsNull();
    }

    @Test
    void shouldGetCourseByStudentId() {
        final Course test = new Course("test");
        final Course test1 = new Course("test1");

        List<Course> tests = new ArrayList<>();
        tests.add(test);
        tests.add(test1);

        given(courseRepository.findCoursesByStudentsId(anyLong())).willReturn(tests);

        List<Course> courses = courseServiceImpl.getCourseByStudentId(anyLong());

        assertThat(courses).isNotNull();

        verify(courseRepository).findCoursesByStudentsId(anyLong());
    }

    @Test
    void shouldCreateCourse() {
        final Course course = new Course("test");

        given(courseRepository.save(course)).willReturn(course);

        Course newCourse = courseServiceImpl.createCourse(course);

        assertThat(newCourse).isNotNull();

        verify(courseRepository).save(any(Course.class));
    }

    @Test
    void shouldUpdateCourse() {
        Course course = new Course("test");

        Course newCourse = courseServiceImpl.createCourse(course);
        given(courseRepository.findById(anyLong())).willReturn(Optional.of(course));

        course.setName("test1");

        Course updatedCourse = courseServiceImpl.updateCourse(anyLong(), course);

        assertThat(updatedCourse).isNotNull();

        verify(courseRepository, times(2)).save(any());
    }

    @Test
    void shouldDeleteCourse() {

        doNothing().when(courseRepository).deleteById(anyLong());

        courseServiceImpl.deleteCourse(anyLong());

        verify(courseRepository).deleteById(anyLong());

    }
}