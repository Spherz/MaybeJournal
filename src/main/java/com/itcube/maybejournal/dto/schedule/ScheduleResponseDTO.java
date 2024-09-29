package com.itcube.maybejournal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleResponseDTO {

    private Long scheduleId;

    private String group;

    private String dayOfWeek;
}
