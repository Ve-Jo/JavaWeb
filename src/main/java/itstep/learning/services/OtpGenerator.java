package itstep.learning.services;

import com.google.inject.Singleton;

import java.util.Random;

@Singleton
public class OtpGenerator implements RandomStringGenerator {
    private static final int OTP_LENGTH = 6;

    @Override
    public String generate() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}