package com.itcube.maybejournal.service;

import com.itcube.maybejournal.dto.AttendanceResponseDTO;
import com.itcube.maybejournal.entity.Attendance;
import com.itcube.maybejournal.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public List<AttendanceResponseDTO> getAllAttendance() {
        List<Attendance> attendances = attendanceRepository.findAll();
        return attendances
                .stream()
                .map(this::mapAttendanceToResponseDTO)
                .collect(Collectors.toList());
    }

    private AttendanceResponseDTO mapAttendanceToResponseDTO(Attendance attendance) {
        AttendanceResponseDTO responseDTO = new AttendanceResponseDTO();

        responseDTO.setAttendanceId(attendance.getId());
        responseDTO.setStudent(attendance.getStudent().getSurName());
        responseDTO.setGroup(attendance.getGroup().getName());
        responseDTO.setAttendanceDate(attendance.getAttendanceDate());
        responseDTO.setPresence(attendance.getPresence());

        return responseDTO;

    }
}
