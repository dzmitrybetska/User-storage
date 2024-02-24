package com.system4.test_task.user_storage.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system4.test_task.user_storage.dto.UserRequest;
import com.system4.test_task.user_storage.exceptions.JsonConversionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.system4.test_task.user_storage.utils.Constants.FILE_CONVERSION_ERROR;

@Component
@RequiredArgsConstructor
public class UserConverter implements Convertable<UserRequest> {

    private final ObjectMapper objectMapper;

    @Override
    public List<UserRequest> convertJsonToEntities(MultipartFile file) {
        try {
            return objectMapper.readValue(file.getBytes(), new TypeReference<List<UserRequest>>() {
            });
        } catch (IOException ex) {
            throw new JsonConversionException(FILE_CONVERSION_ERROR);
        }
    }
}
