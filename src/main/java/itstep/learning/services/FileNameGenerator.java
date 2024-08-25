package itstep.learning.services;

import com.google.inject.Singleton;
import itstep.learning.services.RandomStringGenerator;

import java.util.Random;

@Singleton
public class FileNameGenerator implements RandomStringGenerator {
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 10;

    @Override
    public String generate() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return sb.toString();
    }
}