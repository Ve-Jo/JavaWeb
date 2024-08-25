package itstep.learning.services;

import com.google.inject.Singleton;
import itstep.learning.services.RandomStringGenerator;

import java.security.SecureRandom;
import java.util.Base64;

@Singleton
public class CryptoSaltGenerator implements RandomStringGenerator {
    private static final int SALT_LENGTH = 16;

    @Override
    public String generate() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}