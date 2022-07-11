package com.school.app.exception;

public class CourseWithAllVacanciesFilledException extends RuntimeException {
    public CourseWithAllVacanciesFilledException(Long id) {
        super(String.format("Course with Id %d with all Vacancies already filled", id));
    }
}
