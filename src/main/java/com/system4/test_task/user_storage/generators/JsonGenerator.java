package com.system4.test_task.user_storage.generators;

import java.io.File;
import java.util.List;

/**
 * Interface for generating a new JSON file with a list of entities
 *
 * @param <T> entity type
 */
public interface JsonGenerator<T> {

    /**
     * @param t entity list
     * @return JSON file with a list of entities
     */
    File generateFile(List<T> t);
}
