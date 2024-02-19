package com.itcube.maybejournal.dto;

import com.itcube.maybejournal.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentResponseDTO {
    private Long studentId;

    private String firstName;

    private String surName;

    private String fatherName;

    private List<Long> groupIds;
}
