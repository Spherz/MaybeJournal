package com.itcube.maybejournal.service;

import com.itcube.maybejournal.dto.attendance.AttendanceResponseDTO;

import java.util.List;

public interface AttendanceService {

    List<AttendanceResponseDTO> findAttendanceByGroupId(Long groupId);
    List<AttendanceResponseDTO> findAttendanceByStudentId(Long studentId);
}
