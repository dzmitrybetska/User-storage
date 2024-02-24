package com.system4.test_task.user_storage.validators;

import java.util.List;

/**
 * Interface for data validation
 *
 * @param <T> entity type
 */
public interface Validatable<T> {

    /**
     * Method for validating data from a list of entities
     *
     * @param t entity list
     */
    void validate(List<T> t);
}
