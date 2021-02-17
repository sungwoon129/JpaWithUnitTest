package com.diarymaker.diary.domain.plans;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlansRepository extends JpaRepository<Plans,Long> {

    List<Plans> findByEmail(String email);

}
