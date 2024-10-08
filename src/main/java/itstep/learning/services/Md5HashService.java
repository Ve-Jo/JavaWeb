package itstep.learning.services;

import com.google.inject.Singleton;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Singleton
public class Md5HashService implements HashService {
    @Override
    public String digest(String message) {
        try {
            StringBuilder sb = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update( message.getBytes(StandardCharsets.UTF_8) );
            for (byte b : md.digest()) {
                sb.append( Integer.toHexString( b & 0xff) );
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
