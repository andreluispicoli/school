package com.school.app.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super(String.format("Student with Id %d not found", id));
    }
}
