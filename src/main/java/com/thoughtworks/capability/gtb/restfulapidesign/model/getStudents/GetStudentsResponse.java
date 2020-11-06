package com.thoughtworks.capability.gtb.restfulapidesign.model.getStudents;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by wudibin
 * 2020/11/5
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentsResponse {

    private List<Student> students;

}
