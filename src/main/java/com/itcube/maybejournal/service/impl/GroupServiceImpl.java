package com.itcube.maybejournal.service.impl;

import com.itcube.maybejournal.dto.group.GroupRequestDTO;
import com.itcube.maybejournal.dto.group.GroupResponseDTO;
import com.itcube.maybejournal.model.Group;
import com.itcube.maybejournal.model.Student;
import com.itcube.maybejournal.repository.GroupRepository;
import com.itcube.maybejournal.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupResponseDTO createGroup(GroupRequestDTO groupRequestDTO) {
        Group group = new Group();
        group.setName(groupRequestDTO.getName());

        Group savedGroup = groupRepository.save(group);
        return mapGroupToResponseDTO(savedGroup);
    }

    public List<GroupResponseDTO> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups
                .stream()
                .map(this::mapGroupToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<GroupResponseDTO> getGroupById(Long groupId) {
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        return groupOptional.map(this::mapGroupToResponseDTO);
    }

    public GroupResponseDTO updateGroup(Long groupId, GroupRequestDTO groupRequestDTO) {
        Optional<Group> existingGroupOptional = groupRepository.findById(groupId);

        if(existingGroupOptional.isPresent()) {
            Group existingGroup = existingGroupOptional.get();
            existingGroup.setName(groupRequestDTO.getName());

            Group updatedGroup = groupRepository.save(existingGroup);
            return mapGroupToResponseDTO(updatedGroup);
        } else {
            return null;
        }
    }

    public void deleteGroupById(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    private GroupResponseDTO mapGroupToResponseDTO(Group group) {
        GroupResponseDTO responseDTO = new GroupResponseDTO();
        responseDTO.setGroupId(group.getId());
        responseDTO.setName(group.getName());

        List<Long> studentIds = group.getStudents() != null ?
        group.getStudents()
                .stream()
                .map(Student::getId)
                .collect(Collectors.toList()) : Collections.emptyList();
        responseDTO.setStudentIds(studentIds);
        return responseDTO;
    }
}
