package com.itcube.maybejournal.controller;

import com.itcube.maybejournal.dto.schedule.ScheduleResponseDTO;
import com.itcube.maybejournal.service.impl.ScheduleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleServiceImpl scheduleServiceImpl;

    @GetMapping("/schedule/{groupId}")
    public List<ScheduleResponseDTO> getScheduleByGroupId(@PathVariable("groupId") Long groupId) {
        return scheduleServiceImpl.getScheduleByGroupId(groupId);
    }

    @GetMapping("/{groupId}/generate-dates")
    public List<String> generateLessonDates(@PathVariable("groupId") Long groupId, @RequestParam int numLessons) {
        return scheduleServiceImpl.generateLessonsDates(groupId, numLessons);
    }
}
