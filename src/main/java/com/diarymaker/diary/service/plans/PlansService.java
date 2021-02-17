package com.diarymaker.diary.service.plans;


import com.diarymaker.diary.domain.plans.Plans;
import com.diarymaker.diary.domain.plans.PlansRepository;
import com.diarymaker.diary.web.dto.plans.PlansResponseDto;
import com.diarymaker.diary.web.dto.plans.PlansSaveRequestDto;
import com.diarymaker.diary.web.dto.plans.PlansUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PlansService {

    private final PlansRepository repository;

    @Transactional
    public Long save(PlansSaveRequestDto requestDto) {

        return repository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PlansUpdateRequestDto requestDto) {

        Plans plan = repository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 없습니다. id=" + id ));

        plan.update(requestDto.getTitle(),requestDto.getCategory(),requestDto.getContent(),
                requestDto.getEmail(),requestDto.getStartTime(),requestDto.getEndTime(),requestDto.isChecked());

        return id;

    }


    public PlansResponseDto findById(Long id) {
        Plans plans = repository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 없습니다. id=" + id ));

        return new PlansResponseDto(plans);
    }

    public void delete(Long id) {
        Plans plans = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 없습니다. id=" + id ));

        repository.delete(plans);

    }
}
