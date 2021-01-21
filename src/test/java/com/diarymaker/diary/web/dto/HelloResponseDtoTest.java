package com.diarymaker.diary.web.dto;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class HelloResponseDtoTest {

    @Test
    public void 롬복_테스트() {

        //given
        String name = "diary";
        String month = "1";

        //when
        HelloResponseDto dto = new HelloResponseDto(name,month);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getMonth()).isEqualTo(month);

    }
}
