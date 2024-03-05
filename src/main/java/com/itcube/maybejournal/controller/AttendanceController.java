package com.itcube.maybejournal.controller;

import com.itcube.maybejournal.dto.AttendanceRequestDTO;
import com.itcube.maybejournal.dto.AttendanceResponseDTO;
import com.itcube.maybejournal.service.AttendanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/attendance")
    public List<AttendanceResponseDTO> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    @GetMapping("/attendance/{groupId}")
    public List<AttendanceResponseDTO> getAttendanceByGroup(@PathVariable("groupId") Long groupId) {
        return attendanceService.getAttendanceByGroupId(groupId);
    }

    @PostMapping("/students/{studentId}/add-attendance")
    public AttendanceResponseDTO addAttendance(@PathVariable("studentId") Long id, @RequestBody AttendanceRequestDTO attendanceRequestDTO) {
        return attendanceService.addAttendance(id, attendanceRequestDTO);
    }
}
