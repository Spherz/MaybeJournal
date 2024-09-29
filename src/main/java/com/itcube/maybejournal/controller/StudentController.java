package com.itcube.maybejournal.controller;

import com.itcube.maybejournal.dto.group.GroupResponseDTO;
import com.itcube.maybejournal.dto.student.StudentRequestDTO;
import com.itcube.maybejournal.dto.student.StudentResponseDTO;
import com.itcube.maybejournal.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceImpl studentServiceImpl;

    @PostMapping("/students")
    public StudentResponseDTO createStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        return studentServiceImpl.createStudent(studentRequestDTO);
    }

    @GetMapping("/students/{id}")
    public Optional<StudentResponseDTO> getStudent(@PathVariable("id") Long studentId) {
        return studentServiceImpl.getStudentById(studentId);
    }

    @GetMapping("/students/{studentId}/groups")
    public ResponseEntity<List<GroupResponseDTO>> getGroupsByStudentId(@PathVariable("studentId") Long studentId) {
        List<GroupResponseDTO> groups = studentServiceImpl.getGroupsByStudentId(studentId);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @PutMapping("/students/update/{studentId}")
    public StudentResponseDTO updateStudent(@PathVariable("studentId") Long studentId,
                                            @RequestBody StudentRequestDTO studentRequestDTO) {
        return studentServiceImpl.updateStudent(studentId, studentRequestDTO);
    }

    @DeleteMapping("/students/{studentId}")
    public HttpStatus deleteStudent(@PathVariable Long studentId) {
        studentServiceImpl.deleteById(studentId);
        return HttpStatus.NO_CONTENT;
    }

    @GetMapping("/students")
    public List<StudentResponseDTO> getAllStudents() {
        return studentServiceImpl.getAllStudents();
    }
}
