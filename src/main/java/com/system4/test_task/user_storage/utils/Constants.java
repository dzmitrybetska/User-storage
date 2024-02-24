package com.system4.test_task.user_storage.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Constants {

    public static final String EXCEPTION = "Exception: {}";
    public static final String VALIDATION_MESSAGE = "Users have not been added to the database. " +
            "Users' data is filled in incorrectly!";
    public static final String SQL_VALIDATION_MESSAGE = "Violation of uniqueness: %s";
    public static final String VALIDATION_ERROR = "Validation error";
    public static final String FILE_CONVERSION_ERROR = "Error while converting JSON to UserRequests";
    public static final String FILE_GENERATION_ERROR = "An error occurred while creating the file!";
}
