package com.itcube.maybejournal.dto.attendance;

import com.itcube.maybejournal.model.Group;
import com.itcube.maybejournal.model.Student;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Data
public class AttendanceResponseDTO {
    private Long attendanceId;

    private Student student;

    private Group group;

    private OffsetDateTime attendanceDate;

    private String presence;
}
