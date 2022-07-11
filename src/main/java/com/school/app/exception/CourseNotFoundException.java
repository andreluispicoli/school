package com.school.app.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long id) {
        super(String.format("Course with Id %d not found", id));
    }
}
