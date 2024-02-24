package com.system4.test_task.user_storage.services;

import com.system4.test_task.user_storage.converters.Convertable;
import com.system4.test_task.user_storage.dto.UserRequest;
import com.system4.test_task.user_storage.generators.JsonGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonUserRequestsServiceImpl implements JsonService<UserRequest> {

    private final Convertable<UserRequest> convertable;
    private final JsonGenerator<UserRequest> jsonGenerator;

    @Override
    public List<UserRequest> convertJsonToEntities(MultipartFile file) {
        return convertable.convertJsonToEntities(file);
    }

    @Override
    public File generateFile(List<UserRequest> userRequests) {
        return jsonGenerator.generateFile(userRequests);
    }
}
