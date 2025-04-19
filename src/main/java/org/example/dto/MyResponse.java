package org.example.dto;

import lombok.Getter;

@Getter
public class MyResponse {
    private final String content;

    public MyResponse(String content) {
        this.content = content;
    }
}
