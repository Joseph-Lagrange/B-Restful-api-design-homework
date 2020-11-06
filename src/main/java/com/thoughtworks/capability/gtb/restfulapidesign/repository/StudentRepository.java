package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by wudibin
 * 2020/11/5
 */
public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Override
    List<Student> findAll();

    List<Student> findByGender(String gender);

    Student findByName(String name);
}
