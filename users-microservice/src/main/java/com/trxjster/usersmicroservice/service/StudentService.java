package com.trxjster.usersmicroservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trxjster.usersmicroservice.dto.StudentDTO;
import com.trxjster.usersmicroservice.model.Student;
import com.trxjster.usersmicroservice.repository.StudentRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService {

	private StudentRepo studentRepo;

	@Transactional(readOnly = true)
	public List<Student> getAll() {
		return studentRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Student findById(Long studentId) {
		return studentRepo.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student with id " + studentId + " not found."));
	}

	public Student updateStudent(Long studentId, StudentDTO student) {
		Student s = findById(studentId);
		s.setName(student.name());
		s.setLastName(student.lastName());
		s.setEmail(student.email());
		return studentRepo.saveAndFlush(s);
	}

}
