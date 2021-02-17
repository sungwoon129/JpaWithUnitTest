package com.diarymaker.diary.domain.plans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    EVENT("event","이벤트"),
    TODO("todo","할 일");

    private final String key;
    private final String title;

}
