package com.itcube.maybejournal.dto.schedule;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleResponseDTO {

    private Long scheduleId;

    private String group;

    private String dayOfWeek;
}
