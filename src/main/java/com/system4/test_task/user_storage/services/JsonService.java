package com.system4.test_task.user_storage.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * Interface for converting a JSON file into a list of entities and generating a new JSON file with a list of entities
 *
 * @param <T> entity type
 */
public interface JsonService<T> {

    /**
     * @param file entity data file
     * @return entity list
     */
    List<T> convertJsonToEntities(MultipartFile file);

    /**
     * @param t entity list
     * @return JSON file with a list of entities
     */
    File generateFile(List<T> t);
}
