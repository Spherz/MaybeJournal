package com.itcube.maybejournal.service.impl;

import com.itcube.maybejournal.dto.attendance.AttendanceResponseDTO;
import com.itcube.maybejournal.model.Attendance;
import com.itcube.maybejournal.repository.AttendanceRepository;
import com.itcube.maybejournal.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Override
    public List<AttendanceResponseDTO> findAttendanceByGroupId(Long groupId) {
        List<Attendance> attendances = attendanceRepository.findAttendanceByGroupId(groupId);
        return attendances.stream()
                .map(this::mapAttendanceToAttendanceResponseDTO)
                .toList();
    }

    @Override
    public List<AttendanceResponseDTO> findAttendanceByStudentId(Long studentId) {
        List<Attendance> attendances = attendanceRepository.findAttendanceByStudent_Id(studentId);
        return attendances.stream()
                .map(this::mapAttendanceToAttendanceResponseDTO)
                .toList();
    }

    private AttendanceResponseDTO mapAttendanceToAttendanceResponseDTO(Attendance attendance) {
        AttendanceResponseDTO attendanceResponseDTO = new AttendanceResponseDTO();
        attendanceResponseDTO.setAttendanceId(attendance.getId());
        attendanceResponseDTO.setStudent(attendance.getStudent());
        attendanceResponseDTO.setGroup(attendance.getGroup());
        attendanceResponseDTO.setAttendanceDate(attendance.getAttendanceDate());
        attendanceResponseDTO.setPresence(attendance.getPresence());
        return attendanceResponseDTO;
    }
}
