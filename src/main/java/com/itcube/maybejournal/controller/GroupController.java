package com.itcube.maybejournal.controller;

import com.itcube.maybejournal.dto.group.GroupRequestDTO;
import com.itcube.maybejournal.dto.group.GroupResponseDTO;
import com.itcube.maybejournal.dto.student.StudentResponseDTO;
import com.itcube.maybejournal.service.impl.GroupServiceImpl;
import com.itcube.maybejournal.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GroupController {

    private final GroupServiceImpl groupServiceImpl;

    private final StudentServiceImpl studentServiceImpl;

    @GetMapping("/groups")
    public List<GroupResponseDTO> getAllGroups() {
        return groupServiceImpl.getAllGroups();
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<GroupResponseDTO> getGroupById(@PathVariable("groupId") Long groupId) {
        Optional<GroupResponseDTO> group = groupServiceImpl.getGroupById(groupId);
        return group.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/groups/{groupId}/students")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByGroupId(@PathVariable("groupId") Long groupId) {
        List<StudentResponseDTO> students = studentServiceImpl.getStudentsByGroupId(groupId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/groups")
    public ResponseEntity<GroupResponseDTO> createGroup(@RequestBody GroupRequestDTO groupRequestDTO) {
        GroupResponseDTO createdGroup = groupServiceImpl.createGroup(groupRequestDTO);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @PutMapping("/groups/update/{groupId}")
    public ResponseEntity<GroupResponseDTO> updateGroup(@PathVariable("groupId") Long groupId,
                                                        @RequestBody GroupRequestDTO groupRequestDTO) {
        GroupResponseDTO updatedGroup = groupServiceImpl.updateGroup(groupId, groupRequestDTO);
        if(updatedGroup != null) {
            return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable("groupId") Long groupId) {
        groupServiceImpl.deleteGroupById(groupId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
