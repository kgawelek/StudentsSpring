package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){

        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long studentId) {
        boolean exists =  studentRepository.existsById(studentId);

        if(!exists){
            throw new IllegalStateException("Student with id " + studentId + " doesnt exists");
        }
        return studentRepository.findById(studentId);
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail =  studentRepository.findStudentByEmail(student.getEmail());

        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email already exists");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {

        boolean exists =  studentRepository.existsById(studentId);

        if(!exists){
            throw new IllegalStateException("Student with id " + studentId + " doesnt exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        boolean exists =  studentRepository.existsById(studentId);

        if(!exists){
            throw new IllegalStateException("Student with id " + studentId + " doesnt exists");
        }

        Optional<Student> studentByEmail =  studentRepository.findStudentByEmail(email);

        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Student with this email already exists");
        }

        Student updatedStudent = studentRepository.getOne(studentId);

        if(name != null && name.length() > 0){
            updatedStudent.setName(name);
        }

        if(email != null && email.length() > 0){
            updatedStudent.setEmail(email);
        }

        studentRepository.save(updatedStudent);
    }
}
