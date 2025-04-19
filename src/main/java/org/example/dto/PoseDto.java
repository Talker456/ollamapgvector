package org.example.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PoseDto {
    private String name;
    private String description;
    private String photoUrl;
}
