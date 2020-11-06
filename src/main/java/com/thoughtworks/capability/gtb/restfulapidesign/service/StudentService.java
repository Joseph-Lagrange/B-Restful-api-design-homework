package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.common.errors.ErrorCode;
import com.thoughtworks.capability.gtb.restfulapidesign.common.exception.BadRequestException;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.model.addStudent.AddStudentRequest;
import com.thoughtworks.capability.gtb.restfulapidesign.model.StudentResponse;
import com.thoughtworks.capability.gtb.restfulapidesign.model.updateStudent.UpdateStudentRequest;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by wudibin
 * 2020/11/5
 */
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponse save(AddStudentRequest request) {
        Student student = Student.builder()
                .name(request.getName())
                .gender(request.getGender())
                .note(request.getNote())
                .build();
        student = studentRepository.save(student);
        return student2Vo(student);
    }

    private StudentResponse student2Vo(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .gender(student.getGender())
                .note(student.getNote())
                .build();
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public void delete(int id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findByGender(String gender) {
        return studentRepository.findByGender(gender);
    }

    public StudentResponse getStudent(int id) {
        Optional<Student> optional = studentRepository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException(ErrorCode.STUDENT_IS_NOT_EXIST);
        }
        return student2Vo(optional.get());
    }


    public StudentResponse updateStudent(UpdateStudentRequest request) {
        Optional<Student> optional = studentRepository.findById(request.getId());
        if (!optional.isPresent()) {
            throw new BadRequestException(ErrorCode.STUDENT_IS_NOT_EXIST);
        }
        Student student = optional.get();
        student.setGender(request.getGender())
                .setName(request.getName())
                .setNote(request.getNote());
        studentRepository.save(student);
        return student2Vo(student);
    }
}
