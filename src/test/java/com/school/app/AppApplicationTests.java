package com.school.app;

import com.school.app.controller.CourseController;
import com.school.app.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AppApplicationTests {

    @Autowired
    private StudentController studentController;

    @Autowired
    private CourseController courseController;

	@Test
	void contextLoads() {
        assertThat(studentController).isNotNull();
        assertThat(courseController).isNotNull();
	}

}
