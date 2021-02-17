package com.diarymaker.diary.web.dto.plans;


import com.diarymaker.diary.domain.plans.Category;
import com.diarymaker.diary.domain.plans.Plans;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PlansResponseDto {
    private Long id;
    private String title;
    private boolean checked;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String email;
    private Category category;
    private String content;


    public PlansResponseDto(Plans entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.checked = entity.isChecked();
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
        this.email = entity.getEmail();
        this.category = entity.getCategory();
        this.content = entity.getContent();

    }
}
