package com.system4.test_task.user_storage.encription;

/**
 * Interface for data encryption
 */
public interface EncryptionService {

    /**
     * @param parameter parameter for encryption
     * @return encrypted data
     */
    String encrypt(String parameter);
}
