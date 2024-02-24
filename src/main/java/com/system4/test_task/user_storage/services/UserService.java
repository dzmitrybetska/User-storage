package com.system4.test_task.user_storage.services;

import com.system4.test_task.user_storage.dto.UserRequest;
import com.system4.test_task.user_storage.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service processes users data.
 */
public interface UserService {

    /**
     * Add new user to database
     *
     * @param userRequests user's data from controller
     * @return information about saved user
     */
    List<UserResponse> addUsers(List<UserRequest> userRequests);

    /**
     * Get information about all users from the database (a certain number on one page and in sorted form)
     *
     * @return Page with information about all users
     */
    Page<UserResponse> readAll(Pageable pageable);
}
