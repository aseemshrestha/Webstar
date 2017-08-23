package com.webstar.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Security
{
    private static final Logger LOG = LoggerFactory.getLogger(Security.class);

    public static final String SALT = "0123456789";

    public static String generateHash(String input)
    {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f', 'i', 'j', 'k', 'm', 'n', 'o' };
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException ex) {
            LOG.debug("[Security - generateHash] Error generating hash", ex);
        }

        return hash.toString();
    }

}
