package com.thoughtworks.capability.gtb.restfulapidesign.model.addStudent;

import lombok.*;

/**
 * Created by wudibin
 * 2020/11/5
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddStudentRequest {

    @NonNull
    private String name;

    @NonNull
    private String gender;

    @NonNull
    private String note;

}
