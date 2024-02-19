package com.itcube.maybejournal.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AttendanceResponseDTO {
    private Long attendanceId;

    private String student;

    private String group;

    private LocalDate attendanceDate;

    private String presence;
}