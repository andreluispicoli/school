package com.school.app.service.Impl;

import com.school.app.exception.CourseWithAllVacanciesFilledException;
import com.school.app.exception.StudentAlreadySubscribedInFiveCoursesException;
import com.school.app.exception.StudentNotFoundException;
import com.school.app.model.Course;
import com.school.app.model.Student;
import com.school.app.repository.StudentRepository;
import com.school.app.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    StudentRepository studentRepository;

    @Mock
    CourseService courseService;

    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @Test
    void shouldGetStudentById() {
        final Student test = new Student("test");

        given(studentRepository.findById(anyLong())).willReturn(Optional.of(test));

        Student student = studentServiceImpl.getStudentById(anyLong());

        assertThat(student).isNotNull();

        verify(studentRepository).findById(anyLong());
    }

    @Test
    void shouldThrowStudentNotFoundException() {
        final Student test = new Student("test");

        given(studentRepository.findById(anyLong())).willThrow(StudentNotFoundException.class);

        assertThrows(
                StudentNotFoundException.class,
                () -> studentServiceImpl.getStudentById(1L),
                "Student with Id 1 not found"
        );
    }

    @Test
    void shouldGetAllStudents() {
        final Student test = new Student("test");
        final Student test1 = new Student("test1");

        List<Student> studentList = new ArrayList<>();
        studentList.add(test);
        studentList.add(test1);

        given(studentRepository.findAll()).willReturn(studentList);

        List<Student> students = studentServiceImpl.getAllStudents();

        assertThat(students).isNotNull();

        verify(studentRepository).findAll();
    }

    @Test
    void shouldGetStudentsWithoutAnyCourse() {
        final Student test = new Student("test");
        final Student test1 = new Student("test1");

        List<Student> studentList = new ArrayList<>();
        studentList.add(test);
        studentList.add(test1);

        given(studentRepository.findStudentsByCoursesIsNull()).willReturn(studentList);

        List<Student> students = studentServiceImpl.getStudentsWithoutAnyCourse();

        assertThat(students).isNotNull();

        verify(studentRepository).findStudentsByCoursesIsNull();
    }

    @Test
    void shouldGetStudentsByCourseId() {
        final Student test = new Student("test");
        final Student test1 = new Student("test1");

        List<Student> studentList = new ArrayList<>();
        studentList.add(test);
        studentList.add(test1);

        given(studentRepository.findStudentsByCoursesId(anyLong())).willReturn(studentList);

        List<Student> students = studentServiceImpl.getStudentsByCourseId(anyLong());

        assertThat(students).isNotNull();

        verify(studentRepository).findStudentsByCoursesId(anyLong());
    }

    @Test
    void shouldCreateStudent() {
        final Student test = new Student("test");

        given(studentRepository.save(test)).willReturn(test);

        Student newStudent = studentServiceImpl.createStudent(test);

        assertThat(newStudent).isNotNull();

        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void shouldUpdateStudent() {
        Student student = new Student("test");

        Student newStudent = studentServiceImpl.createStudent(student);
        given(studentRepository.findById(anyLong())).willReturn(Optional.of(student));

        student.setName("test1");

        Student updatedStudent = studentServiceImpl.updateStudent(anyLong(), student);

        assertThat(updatedStudent).isNotNull();

        verify(studentRepository, times(2)).save(any(Student.class));

    }

    @Test
    void shouldDeleteStudent() {
        doNothing().when(studentRepository).deleteById(anyLong());

        studentServiceImpl.deleteStudent(anyLong());

        verify(studentRepository).deleteById(anyLong());
    }

    @Test
    void shouldRegisterCoursesToStudent() throws Exception {
        Student student = new Student("Test");
        Course course = new Course("Test");

        given(studentRepository.findById(anyLong())).willReturn(Optional.of(student));
        given(courseService.getCourseById(anyLong())).willReturn(course);

        studentServiceImpl.registerCoursesToStudent(student.getId(), course.getId());

        verify(studentRepository, times(1)).save(any(Student.class));

    }

    @Test
    void shouldThrowCourseWithAllVacanciesFilledRegisterCoursesToStudent() throws Exception {
        Student student = new Student("Test");
        Course course = new Course("Test");

        Set<Student> students = mock(Set.class);
        course.setStudents(students);

        given(studentRepository.findById(anyLong())).willReturn(Optional.of(student));
        given(courseService.getCourseById(anyLong())).willReturn(course);
        when(students.size()).thenReturn(51);

        assertThrows(
                CourseWithAllVacanciesFilledException.class,
                () -> studentServiceImpl.registerCoursesToStudent(student.getId(), course.getId()),
                "Course with Id 0 with all Vacancies already filled"
        );
    }

    @Test
    void shouldThrowStudentAlreadySubscribedRegisterCoursesToStudent() throws Exception {
        Student student = new Student("Test");
        Course course = new Course("Test");

        Set<Course> courses = mock(Set.class);
        student.setCourses(courses);

        given(studentRepository.findById(anyLong())).willReturn(Optional.of(student));
        given(courseService.getCourseById(anyLong())).willReturn(course);
        when(courses.size()).thenReturn(5);

        assertThrows(
                StudentAlreadySubscribedInFiveCoursesException.class,
                () -> studentServiceImpl.registerCoursesToStudent(student.getId(), course.getId()),
                "Student with Id 0 is already subscribed in 5 courses"
        );
    }
}