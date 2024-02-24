package com.system4.test_task.user_storage.arguments;

import com.system4.test_task.user_storage.dto.UserRequest;
import com.system4.test_task.user_storage.dto.UserResponse;
import com.system4.test_task.user_storage.entities.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public class UsersAddArguments implements ArgumentsProvider {

    public static final User USER1 = User.builder()
            .id(2L)
            .name("Dmitry")
            .surname("Betska")
            .login("qwerty2134")
            .build();

    public static final User USER2 = User.builder()
            .id(3L)
            .name("Alex")
            .surname("Black")
            .login("alBlack")
            .build();

    public static final User USER3 = User.builder()
            .id(4L)
            .name("Angela")
            .surname("Merckel")
            .login("MerckelAng")
            .build();

    public static final UserRequest USER_REQUEST1 = UserRequest.builder()
            .name("Dmitry")
            .surname("Betska")
            .login("qwerty2134")
            .build();

    public static final UserRequest USER_REQUEST2 = UserRequest.builder()
            .name("Alex")
            .surname("Black")
            .login("alBlack")
            .build();

    public static final UserRequest USER_REQUEST3 = UserRequest.builder()
            .name("Angela")
            .surname("Merckel")
            .login("MerckelAng")
            .build();


    public static final UserResponse USER_RESPONSE1 = UserResponse.builder()
            .id(2L)
            .name("Dmitry")
            .surname("Betska")
            .login("qwerty2134")
            .build();

    public static final UserResponse USER_RESPONSE2 = UserResponse.builder()
            .id(3L)
            .name("Alex")
            .surname("Black")
            .login("alBlack")
            .build();

    public static final UserResponse USER_RESPONSE3 = UserResponse.builder()
            .id(4L)
            .name("Angela")
            .surname("Merckel")
            .login("MerckelAng")
            .build();

    public static final List<UserRequest> USER_REQUESTS = List.of(USER_REQUEST1, USER_REQUEST2, USER_REQUEST3);
    public static final List<User> USERS = List.of(USER1, USER2, USER3);
    public static final List<UserResponse> USER_RESPONSES = List.of(USER_RESPONSE1, USER_RESPONSE2, USER_RESPONSE3);

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(USER_REQUESTS, USERS, USER_RESPONSES)
        );
    }
}
