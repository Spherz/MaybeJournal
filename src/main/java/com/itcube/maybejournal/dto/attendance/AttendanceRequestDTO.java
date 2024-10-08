package com.itcube.maybejournal.dto.attendance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class AttendanceRequestDTO {

    private String student;

    private LocalDate attendanceDate;

    private String presence;
}
