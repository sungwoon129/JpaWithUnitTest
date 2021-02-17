package com.diarymaker.diary.web.dto.plans;

import com.diarymaker.diary.domain.plans.Category;
import com.diarymaker.diary.domain.plans.Plans;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PlansSaveRequestDto {
    private String title;
    private boolean checked;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String email;
    private String content;
    private Category category;

    @Builder
    public PlansSaveRequestDto(String title, boolean checked, LocalDateTime startTime, LocalDateTime endTime,
                 String email, String content, Category category) {
        this.title = title;
        this.checked = checked;
        this.startTime = startTime;
        this.endTime = endTime;
        this.email = email;
        this.content = content;
        this.category = category;

    }

    public Plans toEntity() {
        return Plans.builder()
                .title(title)
                .checked(checked)
                .startTime(startTime)
                .endTime(endTime)
                .email(email)
                .content(content)
                .category(category)
                .build();
    }
}
