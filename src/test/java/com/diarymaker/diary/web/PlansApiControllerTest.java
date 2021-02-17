package com.diarymaker.diary.web;

import com.diarymaker.diary.domain.plans.Category;
import com.diarymaker.diary.domain.plans.Plans;
import com.diarymaker.diary.domain.plans.PlansRepository;
import com.diarymaker.diary.web.dto.plans.PlansSaveRequestDto;
import com.diarymaker.diary.web.dto.plans.PlansUpdateRequestDto;
import com.diarymaker.diary.web.dto.plans.PlansResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlansApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    PlansRepository repository;

    @Autowired
    MockMvc mvc;


    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void plan이_등록된다() {
        //given
        String url = "http://localhost:" + port + "/api/v1/plans";


        PlansSaveRequestDto requestDto = getPlansSaveRequestDto();

        //when
        ResponseEntity<Long> response = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(repository.findAll().get(0).getTitle()).isEqualTo("saved plan");

    }

    private PlansSaveRequestDto getPlansSaveRequestDto() {
        String title = "saved plan";
        LocalDateTime startTime = LocalDateTime.of(2021, 2, 14, 9, 00);
        LocalDateTime endTime = LocalDateTime.of(2021, 2, 14, 21, 0);
        PlansSaveRequestDto requestDto = PlansSaveRequestDto.builder()
                .title(title)
                .checked(false)
                .startTime(startTime)
                .endTime(endTime)
                .category(Category.TODO)
                .email("verysunday")
                .content("추상 클래스의 활용")
                .build();
        return requestDto;
    }

    @Test
    public void plan이_조회된다() {
        //given
        Plans savedPlan = getPlansSaveRequestDto().toEntity();
        repository.save(savedPlan);
        Long savedPlanId = savedPlan.getId();

        String url = "/api/v1/plans/" + savedPlanId;

        //when
        PlansResponseDto responseDto = restTemplate.getForObject(url,PlansResponseDto.class);

        //then
        assertThat(responseDto.getTitle()).isEqualTo("saved plan");
        assertThat(responseDto.getContent()).isEqualTo("추상 클래스의 활용");
    }

    @Test
    public void plan이_수정된다() {

        //given
        Plans savedPlan = getPlansSaveRequestDto().toEntity();

        repository.save(savedPlan);

        Long updateId = savedPlan.getId();
        String expectedTtile = "updated plan!";
        boolean expectedChecked = true;
        LocalDateTime expectedStartTime = LocalDateTime.of(2021, 2, 18, 18, 0);
        LocalDateTime expectedEndTime = LocalDateTime.of(2021, 2, 18, 23, 0);

        String requestURL = "http://localhost:" + port + "/api/v1/plans/" + updateId;

        PlansUpdateRequestDto updateDto = PlansUpdateRequestDto.builder()
                .title(expectedTtile)
                .checked(expectedChecked)
                .startTime(expectedStartTime)
                .endTime(expectedEndTime)
                .category(Category.TODO)
                .email("verysunday")
                .content("정적 팩토리 메서드")
                .build();
        //when
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PlansUpdateRequestDto> requestEntity = new HttpEntity<>(updateDto, headers);
        ResponseEntity<Long> responseEntity = restTemplate.exchange(requestURL, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(repository.findAll().get(0).getTitle()).isEqualTo(expectedTtile);
        assertThat(repository.findAll().get(0).getEndTime()).isEqualTo(expectedEndTime);

    }

    @Test
    public void plan이_삭제된다() throws Exception {
        //given
        Plans savedPlan = repository.save(getPlansSaveRequestDto().toEntity());
        Long removeId = savedPlan.getId();
        String url = "/api/v1/plans/" + removeId;
        //when,then
        mvc.perform(delete(url))
                .andExpect(status().isOk());

    }
}
