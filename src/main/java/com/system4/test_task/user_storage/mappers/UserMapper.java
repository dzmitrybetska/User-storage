package com.system4.test_task.user_storage.mappers;

import com.system4.test_task.user_storage.dto.UserRequest;
import com.system4.test_task.user_storage.dto.UserResponse;
import com.system4.test_task.user_storage.entities.User;
import org.mapstruct.Mapper;

/**
 * The interface uses Mapstruct to map entities to DTOs and back.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Mapping userRequest to entity.
     *
     * @param userRequest user details
     * @return entity
     */
    User mapToUser(UserRequest userRequest);

    /**
     * Mapping entity to UserResponse
     *
     * @param user user details from the service
     * @return UserResponse
     */
    UserResponse mapToUserResponse(User user);
}
