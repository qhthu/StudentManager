package com.example.apidemo;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ApidemoApplicationTests {

	@Test
	void contextLoads() {

	}

	@Mock
	Database databaseMock;
	public void studentNotNull(){
		Student test = new Student();
		assertNotNull(test, "not null");

	}

	public void studentAgeBetween18and80(){
		Student test = new Student();

		assertEquals(1990, test.getDayofbirth().getYear());
		assertNotNull(test.getDayofbirth());
	}
	@InjectMocks
	StudentController studentController;

	@Mock
	StudentRepositories studentRepositories;
	@Test
	public void testGetStudentById() {
		Student u = new Student();
		u.setId("std100");
		Student student = null;
		when(studentRepositories.findById("std100")).thenReturn(Optional.of(u));

		List<Student> list = studentController.getListStudent();

		for (Student s: list) {
			if(s.getId().equals("std100")){
				student = s;
			}
		}
		verify(studentRepositories).findById("std100");

		assertEquals("std100", student.getId());
	}

	@Test
	public void testQuery()  {
		assertNotNull(databaseMock);
		when(databaseMock.isAvailable()).thenReturn(true);
		Service t  = new Service(databaseMock);
		boolean check = t.query("* from t");
		assertTrue(check);
	}

}
