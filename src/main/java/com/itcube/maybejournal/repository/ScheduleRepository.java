package com.itcube.maybejournal.repository;

import com.itcube.maybejournal.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByGroupId(Long groupId);

    @Query("SELECT s.dayOfWeek FROM Schedule s JOIN s.group g WHERE g.id = :groupId")
    List<DayOfWeek> findScheduleDaysOfWeekByGroup(@Param("groupId") Long groupId);
}
