package com.system4.test_task.user_storage.services;

import com.system4.test_task.user_storage.arguments.UsersAddArguments;
import com.system4.test_task.user_storage.arguments.UsersGetArguments;
import com.system4.test_task.user_storage.dto.UserRequest;
import com.system4.test_task.user_storage.dto.UserResponse;
import com.system4.test_task.user_storage.encription.EncryptionService;
import com.system4.test_task.user_storage.entities.User;
import com.system4.test_task.user_storage.mappers.UserMapper;
import com.system4.test_task.user_storage.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("Testing methods of the UserService")
public class UserServiceTest {

    private UserService userService;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private UserMapper userMapper;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        userService = new UserServiceImpl(userMapper, encryptionService, userRepository);
    }

    @ParameterizedTest
    @ArgumentsSource(UsersAddArguments.class)
    void addUsers(List<UserRequest> userRequests, List<User> users, List<UserResponse> userResponses) {
        when(userRepository.saveAll(anyList()))
                .thenReturn(users);
        List<UserResponse> actualUserResponses = userService.addUsers(userRequests);
        assertEquals(userResponses, actualUserResponses);
    }

    @ParameterizedTest
    @ArgumentsSource(UsersGetArguments.class)
    void readAll(List<User> users, List<UserResponse> userResponses, Pageable pageable) {
        List<UserResponse> responsesModified = userResponses.stream()
                .map(userResponse -> userResponse.setSurname(
                        userResponse.getSurname() + encryptionService.encrypt(userResponse.getName()))
                )
                .toList();
        Page<UserResponse> page = new PageImpl<>(responsesModified, pageable, 3);
        when(userRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(users, pageable, 3));
        Page<UserResponse> actualPage = userService.readAll(pageable);
        assertEquals(page, actualPage);
    }
}
