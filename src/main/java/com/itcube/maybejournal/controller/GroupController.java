package com.itcube.maybejournal.controller;

import com.itcube.maybejournal.dto.GroupRequestDTO;
import com.itcube.maybejournal.dto.GroupResponseDTO;
import com.itcube.maybejournal.dto.StudentResponseDTO;
import com.itcube.maybejournal.service.GroupService;
import com.itcube.maybejournal.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GroupController {

    private final GroupService groupService;

    private final StudentService studentService;

    public GroupController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @GetMapping("/groups")
    public List<GroupResponseDTO> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<GroupResponseDTO> getGroupById(@PathVariable("groupId") Long groupId) {
        Optional<GroupResponseDTO> group = groupService.getGroupById(groupId);
        return group.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/groups/{groupId}/students")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByGroupId(@PathVariable("groupId") Long groupId) {
        List<StudentResponseDTO> students = studentService.getStudentsByGroupId(groupId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/groups")
    public ResponseEntity<GroupResponseDTO> createGroup(@RequestBody GroupRequestDTO groupRequestDTO) {
        GroupResponseDTO createdGroup = groupService.createGroup(groupRequestDTO);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @PutMapping("/groups/update/{groupId}")
    public ResponseEntity<GroupResponseDTO> updateGroup(@PathVariable("groupId") Long groupId,
                                                        @RequestBody GroupRequestDTO groupRequestDTO) {
        GroupResponseDTO updatedGroup = groupService.updateGroup(groupId, groupRequestDTO);
        if(updatedGroup != null) {
            return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable("groupId") Long groupId) {
        groupService.deleteGroupById(groupId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
