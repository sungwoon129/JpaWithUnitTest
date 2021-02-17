package com.diarymaker.diary.web.dto.plans;

import com.diarymaker.diary.domain.plans.Category;
import com.diarymaker.diary.domain.plans.Plans;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PlansUpdateRequestDto {
    private String title;
    private boolean checked;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String email;
    private Category category;
    private String content;

    @Builder
    public  PlansUpdateRequestDto(String title, boolean checked, LocalDateTime startTime, LocalDateTime endTime, Category category, String email, String content) {
        this.title = title;
        this.checked = checked;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.email = email;
        this.content = content;

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
