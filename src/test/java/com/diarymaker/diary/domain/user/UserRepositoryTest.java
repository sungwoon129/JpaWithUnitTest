package com.diarymaker.diary.domain.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void user저장_불러오기() {
        //given
        String name = "user";
        String email = "email";
        Role role = Role.USER;

        User user = User.builder()
                .name(name)
                .email(email)
                .picture("")
                .role(role)
                .build();

        repository.save(user);

        //when
        User entity = repository.findByEmail(email)
                                .orElseThrow(() -> new IllegalArgumentException("그런 유저 없어요."));

        //then
        assertThat(entity.getRoleKey()).isEqualTo(role.getKey());

    }
}