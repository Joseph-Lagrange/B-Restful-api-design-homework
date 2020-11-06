package com.thoughtworks.capability.gtb.restfulapidesign.web;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.capability.gtb.restfulapidesign.common.constant.Constant;
import com.thoughtworks.capability.gtb.restfulapidesign.common.errors.ErrorCode;
import com.thoughtworks.capability.gtb.restfulapidesign.common.exception.BadRequestException;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.model.GroupResponse;
import com.thoughtworks.capability.gtb.restfulapidesign.model.addStudent.AddStudentRequest;
import com.thoughtworks.capability.gtb.restfulapidesign.model.StudentResponse;
import com.thoughtworks.capability.gtb.restfulapidesign.model.divideStudents.DivideStudentsResponse;
import com.thoughtworks.capability.gtb.restfulapidesign.model.getStudents.GetStudentsResponse;
import com.thoughtworks.capability.gtb.restfulapidesign.model.updateStudent.UpdateStudentRequest;
import com.thoughtworks.capability.gtb.restfulapidesign.service.GroupService;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by wudibin
 * 2020/11/5
 */
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    GroupService groupService;

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse addStudent(@RequestBody @Valid AddStudentRequest request) {
        return studentService.save(request);
    }

    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable("id") int id) {
        studentService.delete(id);
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public GetStudentsResponse getStudents(@RequestParam(name = "gender", required = false) String gender) {
        List<Student> students;
        if (!Strings.isNullOrEmpty(gender)) {
            students = studentService.findByGender(gender);
        } else {
            students = studentService.findAll();
        }
        return GetStudentsResponse.builder()
                .students(students)
                .build();
    }

    @GetMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponse getStudent(@PathVariable("id") int id) {
        return studentService.getStudent(id);
    }

    @PutMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponse updateStudent(@RequestBody @Valid UpdateStudentRequest request) {
        return studentService.updateStudent(request);
    }


    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public DivideStudentsResponse divideStudents() {
        List<Student> students = studentService.findAll();
        if (students.isEmpty()) {
            throw new BadRequestException(ErrorCode.STUDENTS_IS_EMPTY);
        }
        int sequence = 1;
        TreeMap<String, List<Student>> groups = Maps.newTreeMap();
        Collections.shuffle(students);
        Group group;
        for (Student s : students) {
            if (sequence % Constant.GROUP_NUMBER == 0) {
                sequence = 1;
            }
            group = groupService.findById(sequence);
            if (!groups.containsKey(group.getName())) {
                groups.put(group.getName(), Lists.newArrayList());
            }
            groups.get(group.getName()).add(s);
            sequence++;
        }
        return DivideStudentsResponse.builder()
                .group(groups)
                .build();
    }

    @PatchMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupResponse updateGroup(@PathVariable("id") int id, @RequestParam(name = "name") String name) {
        return groupService.updateGroup(id, name);
    }

}
