package com.itcube.maybejournal.repository;

import com.itcube.maybejournal.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
