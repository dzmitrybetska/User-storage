package com.system4.test_task.user_storage.generators;

import com.github.javafaker.Faker;
import com.system4.test_task.user_storage.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class UserRequestsGenerator implements EntitiesGenerator<UserRequest> {

    private final Faker faker;

    @Override
    public List<UserRequest> generate(int quantity) {
        Set<String> logins = new HashSet<>();
        return IntStream.range(0, quantity)
                .mapToObj(i -> generateUserRequest(logins))
                .toList();
    }

    private UserRequest generateUserRequest(Set<String> logins) {
        String login = generateUniqueLogin(logins);
        logins.add(login);
        return UserRequest.builder()
                .name(generateName())
                .surname(generateSurname())
                .login(login)
                .build();
    }

    private String generateName() {
        return faker.name().firstName();
    }

    private String generateSurname() {
        return faker.name().lastName();
    }

    private String generateUniqueLogin(Set<String> logins) {
        String login = faker.name().username();
        while (logins.contains(login)) {
            login = faker.name().username();
        }
        return login;
    }
}
