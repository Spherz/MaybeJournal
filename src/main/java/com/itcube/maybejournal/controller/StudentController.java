package com.itcube.maybejournal.controller;

import com.itcube.maybejournal.dto.GroupResponseDTO;
import com.itcube.maybejournal.dto.StudentRequestDTO;
import com.itcube.maybejournal.dto.StudentResponseDTO;
import com.itcube.maybejournal.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDTO createStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        return studentService.createStudent(studentRequestDTO);
    }

    @GetMapping("/students/{id}")
    public Optional<StudentResponseDTO> getStudent(@PathVariable("id") Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/students/{studentId}/groups")
    public ResponseEntity<List<GroupResponseDTO>> getGroupsByStudentId(@PathVariable("studentId") Long studentId) {
        List<GroupResponseDTO> groups = studentService.getGroupsByStudentId(studentId);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @PutMapping("/students/update/{studentId}")
    public StudentResponseDTO updateStudent(@PathVariable("studentId") Long studentId,
                                            @RequestBody StudentRequestDTO studentRequestDTO) {
        return studentService.updateStudent(studentId, studentRequestDTO);
    }

    @DeleteMapping("/students/{studentId}")
    public HttpStatus deleteStudent(@PathVariable Long studentId) {
        studentService.deleteById(studentId);
        return HttpStatus.NO_CONTENT;
    }

    @GetMapping("/students")
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }
}
