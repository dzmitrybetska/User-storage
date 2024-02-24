package com.system4.test_task.user_storage.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class UserResponse {

    private Long id;
    private String name;
    private String surname;
    private String login;
}
