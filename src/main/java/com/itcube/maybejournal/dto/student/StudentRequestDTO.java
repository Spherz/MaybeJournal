package com.itcube.maybejournal.dto.student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class StudentRequestDTO {

    private String firstName;

    private String surName;

    private String fatherName;

    private List<Long> groupIds;
}
