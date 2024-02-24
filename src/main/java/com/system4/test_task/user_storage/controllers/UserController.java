package com.system4.test_task.user_storage.controllers;

import com.system4.test_task.user_storage.dto.UserRequest;
import com.system4.test_task.user_storage.dto.UserResponse;
import com.system4.test_task.user_storage.generators.EntitiesGenerator;
import com.system4.test_task.user_storage.services.JsonService;
import com.system4.test_task.user_storage.services.UserService;
import com.system4.test_task.user_storage.validators.Validatable;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final Validatable<UserRequest> validatable;
    private final JsonService<UserRequest> jsonService;
    private final EntitiesGenerator<UserRequest> generator;

    @PostMapping(value = "/uploadJson")
    public List<UserResponse> addUsers(@RequestParam("file") MultipartFile file) {
        List<UserRequest> userRequests = jsonService.convertJsonToEntities(file);
        validatable.validate(userRequests);
        return userService.addUsers(userRequests);
    }

    @GetMapping(value = "/getUsers")
    public Page<UserResponse> readUsers(Pageable pageable) {
        return userService.readAll(pageable);
    }

    @GetMapping(value = "/downloadUsersJson/{quantity}")
    public ResponseEntity<Resource> downloadUsersJson(@PathVariable int quantity) {
        List<UserRequest> userRequests = generator.generate(quantity);
        File file = jsonService.generateFile(userRequests);
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_DISPOSITION, "attachment; filename=users.json");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(APPLICATION_OCTET_STREAM)
                .body(new FileSystemResource(file));
    }
}
