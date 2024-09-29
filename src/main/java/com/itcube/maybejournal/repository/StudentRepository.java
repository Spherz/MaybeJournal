package com.itcube.maybejournal.repository;

import com.itcube.maybejournal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s JOIN s.groups g WHERE g.id = :groupId")
    List<Student> findByGroupId(@Param("groupId") Long groupId);
}
