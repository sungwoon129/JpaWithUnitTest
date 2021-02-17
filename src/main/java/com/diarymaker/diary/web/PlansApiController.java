package com.diarymaker.diary.web;

import com.diarymaker.diary.service.plans.PlansService;
import com.diarymaker.diary.web.dto.plans.PlansResponseDto;
import com.diarymaker.diary.web.dto.plans.PlansSaveRequestDto;
import com.diarymaker.diary.web.dto.plans.PlansUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PlansApiController {

    private final PlansService plansService;

    @GetMapping("/api/v1/plans/{id}")
    public PlansResponseDto findById(@PathVariable Long id) {
        return plansService.findById(id);

    }

    @PostMapping("/api/v1/plans")
    public Long save(@RequestBody PlansSaveRequestDto requestDto) {

        return plansService.save(requestDto);
    }

    @PutMapping("/api/v1/plans/{id}")
    public Long update(@PathVariable Long id,@RequestBody PlansUpdateRequestDto requestDto) {
        return plansService.update(id,requestDto);
    }

    @DeleteMapping("/api/v1/plans/{id}")
    public Long delete(@PathVariable Long id) {
        plansService.delete(id);

        return id;
    }
}
