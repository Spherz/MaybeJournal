package com.itcube.maybejournal.dto;

import com.itcube.maybejournal.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupResponseDTO {

    private Long groupId;

    private String name;

    private List<Long> studentIds;
}
