package com.diarymaker.diary.domain.plans;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlansRepositoryTest {

    @Autowired
    PlansRepository plansRepository;

    @AfterEach
    public void cleanUp() {
        plansRepository.deleteAll();
    }

    @Test
    public void 플랜저장_불러오기() {
        //given
        String title = "자바 공부";
        LocalDateTime startTime = LocalDateTime.of(2021,2,5,16,0);
        LocalDateTime endTime = LocalDateTime.of(2021,2,5,17,0);
        Plans samplePlan = Plans.builder()
                                    .title(title)
                                    .checked(false)
                                    .startTime(startTime)
                                    .endTime(endTime)
                                    .category(Category.TODO)
                                    .email("verysunday")
                                    .content("추상 클래스의 활용")
                                    .build();
        plansRepository.save(samplePlan);

        //when
        List<Plans> plansList = plansRepository.findByEmail(samplePlan.getEmail());

        //then
        Plans plans = plansList.get(0);
        assertThat(plans.getTitle()).isEqualTo(title);
        assertThat(plans.getStartTime()).isEqualTo(startTime);
        assertThat(plans.getEndTime()).isEqualTo(endTime);

    }

}
