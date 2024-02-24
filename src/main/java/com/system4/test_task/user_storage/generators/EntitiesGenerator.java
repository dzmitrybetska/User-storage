package com.system4.test_task.user_storage.generators;

import java.util.List;

/**
 * Interface for generating a list of entities
 *
 * @param <T> entity type
 */
public interface EntitiesGenerator<T> {

    /**
     * @param quantity number of entities
     * @return entity list
     */
    List<T> generate(int quantity);
}
