package com.system4.test_task.user_storage.encription;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class MD5Encryption implements EncryptionService {

    @Override
    public String encrypt(String parameter) {
        return "_" + DigestUtils.md5Hex(parameter);
    }
}
