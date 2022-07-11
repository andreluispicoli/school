package com.school.app.exception;

public class StudentAlreadySubscribedInFiveCoursesException extends RuntimeException {

    public StudentAlreadySubscribedInFiveCoursesException(Long id) {
        super(String.format("Student with Id %d is already subscribed in 5 courses", id));
    }
}
