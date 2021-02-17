package com.diarymaker.diary.domain.plans;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Plans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean checked;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Builder
    public Plans(String title, boolean checked, LocalDateTime startTime, LocalDateTime endTime,
                 String email, String content, Category category) {
        this.title = title;
        this.checked = checked;
        this.startTime = startTime;
        this.endTime = endTime;
        this.email = email;
        this.content = content;
        this.category = category;

    }


    public void update(String title, Category category, String content, String email, LocalDateTime startTime, LocalDateTime endTime, boolean checked) {
        this.title = title;
        this.checked = checked;
        this.startTime = startTime;
        this.endTime = endTime;
        this.email = email;
        this.content = content;
        this.category = category;

    }


}
