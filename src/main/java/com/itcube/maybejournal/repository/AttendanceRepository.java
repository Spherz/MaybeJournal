package com.itcube.maybejournal.repository;

import com.itcube.maybejournal.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT a FROM Attendance  a JOIN a.student s WHERE s.id = :studentId")
    List<Attendance> findAttendanceByStudent_Id(@Param("studentId") Long studentId);
}
