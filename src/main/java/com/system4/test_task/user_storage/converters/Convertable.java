package com.system4.test_task.user_storage.converters;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Interface for converting a JSON file into a list of entities
 *
 * @param <T> entity type
 */
public interface Convertable<T> {

    /**
     * @param file entity data file
     * @return entity list
     */
    List<T> convertJsonToEntities(MultipartFile file);
}
