package com.thoughtworks.capability.gtb.restfulapidesign.model.divideStudents;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by wudibin
 * 2020/11/5
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DivideStudentsResponse {

    private TreeMap<String, List<Student>> group;

}
