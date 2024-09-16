package com.lucas.bootelastic4.util;

import com.lucas.bootelastic4.common.utils.JasyptEncryptUtil;
import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EncryptTest {

    @Test
    @DisplayName("1. Encrypt Test")
    void use_1() {
        String encryptKey = "encryptKeyTestKey";
        String targetStringKey = "Test 1234";

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(encryptKey);

        String encryptedText = encryptor.encrypt(targetStringKey);
        System.out.println("encryptedText = " + encryptedText);
        Assertions.assertThat(encryptedText).isNotEmpty();

        String decryptedText = encryptor.decrypt(encryptedText);
        Assertions.assertThat(decryptedText).isEqualTo(targetStringKey);
        System.out.println("decryptedText = " + decryptedText);
    }

    // -----------

    @Test
    @DisplayName("2. Encrypt Util Test")
    void use_2() {
        String targetString = "test1234";

        String encrypt = JasyptEncryptUtil.encrypt(targetString);
        System.out.println("encrypt = " + encrypt);

        String decrypt = JasyptEncryptUtil.decrypt(encrypt);
        System.out.println("decrypt = " + decrypt);
        Assertions.assertThat(decrypt).isEqualTo(targetString);
    }
}
