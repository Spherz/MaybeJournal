package com.itcube.maybejournal.controller;

import com.itcube.maybejournal.dto.ScheduleResponseDTO;
import com.itcube.maybejournal.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedule/{groupId}")
    public List<ScheduleResponseDTO> getScheduleByGroupId(@PathVariable("groupId") Long groupId) {
        return scheduleService.getScheduleByGroupId(groupId);
    }

    @GetMapping("/{groupId}/generate-dates")
    public List<String> generateLessonDates(@PathVariable("groupId") Long groupId, @RequestParam int numLessons) {
        return scheduleService.generateLessonsDates(groupId, numLessons);
    }
}
