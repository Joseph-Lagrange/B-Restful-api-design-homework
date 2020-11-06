package com.thoughtworks.capability.gtb.restfulapidesign.model.updateStudent;

import lombok.*;

/**
 * Created by wudibin
 * 2020/11/6
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentRequest {

    @NonNull
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String gender;

    @NonNull
    private String note;

}
