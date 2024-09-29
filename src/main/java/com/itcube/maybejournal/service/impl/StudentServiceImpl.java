package com.itcube.maybejournal.service.impl;

import com.itcube.maybejournal.dto.group.GroupResponseDTO;
import com.itcube.maybejournal.dto.student.StudentRequestDTO;
import com.itcube.maybejournal.dto.student.StudentResponseDTO;
import com.itcube.maybejournal.model.Group;
import com.itcube.maybejournal.model.Student;
import com.itcube.maybejournal.repository.GroupRepository;
import com.itcube.maybejournal.repository.StudentRepository;
import com.itcube.maybejournal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final GroupRepository groupRepository;

    private StudentResponseDTO mapStudentToResponseDTO(Student student) {
        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setStudentId(student.getId());
        responseDTO.setFirstName(student.getFirstName());
        responseDTO.setSurName(student.getSurName());
        responseDTO.setFatherName(student.getFatherName());
        responseDTO.setGroupIds(student.getGroups()
                .stream()
                .map(Group::getId)
                .collect(Collectors.toList()));

        return responseDTO;
    }

    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        Student student = new Student();
        student.setFirstName(studentRequestDTO.getFirstName());
        student.setSurName(studentRequestDTO.getSurName());
        student.setFatherName(studentRequestDTO.getFatherName());

        List<Group> groups = groupRepository.findAllById(studentRequestDTO.getGroupIds());
        student.setGroups(groups);

        Student savedStudent = studentRepository.save(student);

        return mapStudentToResponseDTO(savedStudent);
    }

    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students
                .stream()
                .map(this::mapStudentToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<GroupResponseDTO> getGroupsByStudentId(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if(studentOptional.isPresent()) {
            Student student = studentOptional.get();

            return student.getGroups()
                    .stream()
                    .map(this::mapGroupToResponseDTO)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public List<StudentResponseDTO> getStudentsByGroupId(Long groupId) {
        return studentRepository.findByGroupId(groupId)
                .stream()
                .map(this::mapStudentToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<StudentResponseDTO> getStudentById(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        return studentOptional.map(this::mapStudentToResponseDTO);
    }

    public StudentResponseDTO updateStudent(Long studentId, StudentRequestDTO studentRequestDTO) {
        Optional<Student> existingStudentOptional = studentRepository.findById(studentId);

        if(existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();
            existingStudent.setFirstName(studentRequestDTO.getFirstName());
            existingStudent.setSurName(studentRequestDTO.getSurName());
            existingStudent.setFatherName(studentRequestDTO.getFatherName());

            List<Group> groups = groupRepository.findAllById(studentRequestDTO.getGroupIds());
            existingStudent.setGroups(groups);

            Student updatedStudent = studentRepository.save(existingStudent);
            return mapStudentToResponseDTO(updatedStudent);
        } else {
            return null;
        }
    }


    public void deleteById(Long studentId) {
        studentRepository.deleteById(studentId);
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
