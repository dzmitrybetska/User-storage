package com.system4.test_task.user_storage.arguments;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.stream.Stream;

import static com.system4.test_task.user_storage.arguments.UsersAddArguments.USERS;
import static com.system4.test_task.user_storage.arguments.UsersAddArguments.USER_RESPONSES;

public class UsersGetArguments implements ArgumentsProvider {

    public static final Pageable PAGEABLE = PageRequest.of(0, 20);

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(USERS, USER_RESPONSES, PAGEABLE)
        );
    }
}
