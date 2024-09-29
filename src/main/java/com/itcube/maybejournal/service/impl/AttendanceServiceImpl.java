package com.itcube.maybejournal.service.impl;

import com.itcube.maybejournal.repository.AttendanceRepository;
import com.itcube.maybejournal.repository.StudentRepository;
import com.itcube.maybejournal.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final StudentRepository studentRepository;
}
