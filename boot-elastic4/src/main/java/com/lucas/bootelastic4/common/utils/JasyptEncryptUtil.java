package com.lucas.bootelastic4.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JasyptEncryptUtil {

    private static StringEncryptor stringEncryptor;

    @Autowired
    public void JasyptEncryptUtil(StringEncryptor stringEncryptor) {
        this.stringEncryptor = stringEncryptor;
    }

    public static String encrypt(String data) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        return stringEncryptor.encrypt(data);
    }

    public static String decrypt(String data) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        return stringEncryptor.decrypt(data);
    }
}
