package com.itcube.maybejournal.controller;

import com.itcube.maybejournal.dto.attendance.AttendanceResponseDTO;
import com.itcube.maybejournal.service.impl.AttendanceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "/api")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceServiceImpl attendanceServiceImpl;

    @GetMapping("/attendance/{groupId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendanceByGroupId(@PathVariable Long groupId) {
        return new ResponseEntity<>(attendanceServiceImpl.findAttendanceByGroupId(groupId), HttpStatus.OK);
    }

    @GetMapping("/attendance")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendanceByStudentId(@RequestParam(required = false) Long studentId) {
        return new ResponseEntity<>(attendanceServiceImpl.findAttendanceByStudentId(studentId), HttpStatus.OK);
    }
}
