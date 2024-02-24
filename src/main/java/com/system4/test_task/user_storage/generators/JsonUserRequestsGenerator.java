package com.system4.test_task.user_storage.generators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system4.test_task.user_storage.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.system4.test_task.user_storage.utils.Constants.FILE_GENERATION_ERROR;

@Component
@RequiredArgsConstructor
public class JsonUserRequestsGenerator implements JsonGenerator<UserRequest> {

    private final ObjectMapper objectMapper;

    @Override
    public File generateFile(List<UserRequest> userRequests) {
        File file = new File("users.json");
        try {
            objectMapper.writeValue(file, userRequests);
        } catch (IOException e) {
            throw new RuntimeException(FILE_GENERATION_ERROR);
        }
        return file;
    }
}

