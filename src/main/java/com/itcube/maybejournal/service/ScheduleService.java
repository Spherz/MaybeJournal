package com.itcube.maybejournal.service;

import com.itcube.maybejournal.dto.ScheduleResponseDTO;
import com.itcube.maybejournal.entity.Schedule;
import com.itcube.maybejournal.repository.ScheduleRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<ScheduleResponseDTO> getScheduleByGroupId(Long groupId) {
        List<Schedule> schedule = scheduleRepository.findByGroupId(groupId);
        return schedule
                .stream()
                .map(this::mapScheduleToResponseDTO)
                .collect(Collectors.toList());
    }

    private ScheduleResponseDTO mapScheduleToResponseDTO(Schedule schedule) {
        ScheduleResponseDTO responseDTO = new ScheduleResponseDTO();
        Locale langRu = new Locale("ru");

        responseDTO.setScheduleId(schedule.getId());
        responseDTO.setGroup(schedule.getGroup().getName());
        responseDTO.setDayOfWeek(schedule.getDayOfWeek().getDisplayName(TextStyle.FULL, langRu));

        return responseDTO;
    }

    public List<String> generateLessonsDates(Long groupId, Integer numLessons) {
        List<LocalDate> lessonDates = new ArrayList<>();

        List<DayOfWeek> scheduleDaysOfWeek = scheduleRepository.findScheduleDaysOfWeekByGroup(groupId);

        for(DayOfWeek dayOfWeek : scheduleDaysOfWeek) {
            LocalDate currentDate = LocalDate.now().with(dayOfWeek);

            for(int i = 0; i < numLessons; i++) {
                lessonDates.add(currentDate);
                currentDate = currentDate.plusWeeks(1);
            }
        }
        Collections.sort(lessonDates);

        return convertDatesToString(lessonDates);
    }

    private List<String> convertDatesToString(List<LocalDate> dates) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<String> stringDates = dates.stream()
                .map(date -> date.format(formatter))
                .collect(Collectors.toList());

        return stringDates;
    }
}
