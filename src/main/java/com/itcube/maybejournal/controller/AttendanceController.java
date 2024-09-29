package com.itcube.maybejournal.controller;

import com.itcube.maybejournal.service.impl.AttendanceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/api")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceServiceImpl attendanceServiceImpl;

}
