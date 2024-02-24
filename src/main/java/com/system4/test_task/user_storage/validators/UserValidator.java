package com.system4.test_task.user_storage.validators;

import com.system4.test_task.user_storage.dto.UserRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.system4.test_task.user_storage.utils.Constants.VALIDATION_ERROR;

@Component
public class UserValidator implements Validatable<UserRequest> {

    @Override
    public void validate(List<UserRequest> userRequests) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserRequest>> violations = userRequests.stream()
                .flatMap(userRequest -> validator.validate(userRequest).stream())
                .collect(Collectors.toSet());
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(VALIDATION_ERROR, violations);
        }
    }
}
