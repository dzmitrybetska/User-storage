package com.system4.test_task.user_storage.services;

import com.system4.test_task.user_storage.dto.UserRequest;
import com.system4.test_task.user_storage.dto.UserResponse;
import com.system4.test_task.user_storage.encription.EncryptionService;
import com.system4.test_task.user_storage.entities.User;
import com.system4.test_task.user_storage.mappers.UserMapper;
import com.system4.test_task.user_storage.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final EncryptionService encryptionService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<UserResponse> addUsers(List<UserRequest> userRequests) {
        List<User> users = userRequests.stream()
                .map(userMapper::mapToUser)
                .toList();
        return userRepository.saveAll(users).stream()
                .map(userMapper::mapToUserResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> readAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage
                .map(userMapper::mapToUserResponse)
                .map(this::changeSurname);
    }

    private UserResponse changeSurname(UserResponse userResponse) {
        String surname = userResponse.getSurname();
        return userResponse.setSurname(surname + encryptionService.encrypt(userResponse.getName()));
    }
}
