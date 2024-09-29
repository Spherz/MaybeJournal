package com.itcube.maybejournal.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;

@NoArgsConstructor
@Getter
@Setter
public class ScheduleRequestDTO {

    private String group;

    private DayOfWeek dayOfWeek;
}
