package com.itcube.maybejournal.service;

import com.itcube.maybejournal.dto.AttendanceRequestDTO;
import com.itcube.maybejournal.dto.AttendanceResponseDTO;
import com.itcube.maybejournal.entity.Attendance;
import com.itcube.maybejournal.entity.Group;
import com.itcube.maybejournal.entity.Student;
import com.itcube.maybejournal.exception.StudentNotFoundException;
import com.itcube.maybejournal.repository.AttendanceRepository;
import com.itcube.maybejournal.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final StudentRepository studentRepository;

    public AttendanceService(AttendanceRepository attendanceRepository,
                             StudentRepository studentRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
    }

    public List<AttendanceResponseDTO> getAllAttendance() {
        List<Attendance> attendances = attendanceRepository.findAll();
        return attendances
                .stream()
                .map(this::mapAttendanceToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<AttendanceResponseDTO> getAttendanceByGroupId(Long groupId) {
        List<Attendance> attendances = attendanceRepository.findAttendanceByGroupId(groupId);
        return attendances
                .stream()
                .map(this::mapAttendanceToResponseDTO)
                .collect(Collectors.toList());
    }

    public AttendanceResponseDTO addAttendance(Long id, AttendanceRequestDTO attendanceRequestDTO) {
        Attendance attendance = new Attendance();

        attendance.setAttendanceDate(attendanceRequestDTO.getAttendanceDate());
        attendance.setPresence(attendanceRequestDTO.getPresence());

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Unable to find student with id: " + id));

        attendance.setStudent(student);

        Attendance savedAttendance = attendanceRepository.save(attendance);

        student.getStudentAttendances().add(attendance);

        studentRepository.save(student);

        return mapAttendanceToResponseDTO(savedAttendance);
    }

    private AttendanceResponseDTO mapAttendanceToResponseDTO(Attendance attendance) {
        AttendanceResponseDTO responseDTO = new AttendanceResponseDTO();

        responseDTO.setAttendanceId(attendance.getId());
        responseDTO.setStudentId(attendance.getStudent().getId());
        responseDTO.setGroupId(attendance.getGroup().getId());
        responseDTO.setAttendanceDate(attendance.getAttendanceDate());
        responseDTO.setPresence(attendance.getPresence());

        return responseDTO;

    }
}
