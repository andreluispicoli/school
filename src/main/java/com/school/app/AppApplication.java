package com.school.app;

import com.school.app.model.Course;
import com.school.app.model.Student;
import com.school.app.service.CourseService;
import com.school.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class AppApplication {

	@Autowired
	CourseService courseService;

	@Autowired
	StudentService studentService;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData() {
		return (args) -> {
			Course courseMath = new Course("Math");
			Course courseEnglish = new Course("English");
			Course courseBiology = new Course("Biology");
			Course courseScience = new Course("Science");
			Course courseChemistry = new Course("Chemistry");

			courseService.createCourse(courseMath);
			courseService.createCourse(courseEnglish);
			courseService.createCourse(courseBiology);
			courseService.createCourse(courseScience);
			courseService.createCourse(courseChemistry);

			Student jorge = new Student("Jorge");
			Student charles = new Student("Charles");
			Student mike = new Student("Mike");
			Student christine = new Student("Christine");
			Student joshua = new Student("Joshua");

			studentService.createStudent(jorge);
			studentService.createStudent(charles);
			studentService.createStudent(mike);
			studentService.createStudent(christine);
			studentService.createStudent(joshua);

		};
	}

}
