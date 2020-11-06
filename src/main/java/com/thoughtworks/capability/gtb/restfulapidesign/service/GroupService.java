package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.common.errors.ErrorCode;
import com.thoughtworks.capability.gtb.restfulapidesign.common.exception.BadRequestException;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.model.GroupResponse;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.GroupRepository;

import java.util.Optional;

/**
 * Created by wudibin
 * 2020/11/6
 */
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public GroupResponse updateGroup(int id, String name) {
        Optional<Group> optional = groupRepository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException(ErrorCode.GROUP_IS_NOT_EXIST);
        }
        Group group = optional.get();
        group.setName(name);
        groupRepository.save(group);
        return group2Vo(group);
    }

    private GroupResponse group2Vo(Group group) {
        return GroupResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .note(group.getNote())
                .build();
    }

    public Group findById(int id) {
        Optional<Group> optional = groupRepository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException(ErrorCode.GROUP_IS_NOT_EXIST);
        }
        return optional.get();
    }
}
