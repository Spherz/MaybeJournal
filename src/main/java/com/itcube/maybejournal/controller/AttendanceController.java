package com.itcube.maybejournal.controller;

import com.itcube.maybejournal.dto.AttendanceResponseDTO;
import com.itcube.maybejournal.service.AttendanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
