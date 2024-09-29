package com.itcube.maybejournal.dto.group;

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
