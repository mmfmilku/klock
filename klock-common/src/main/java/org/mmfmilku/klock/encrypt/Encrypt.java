package org.mmfmilku.klock.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

public class Encrypt {

//    static final String KEY = "1234abcd";

    static final String ENCODE = "UTF-8";

    // DES对称加密
    static final String DES = "DES";

    public static byte[] encrypt(byte[] data, String key) {
        return calculate(Cipher.ENCRYPT_MODE, data, key);
    }

    public static byte[] decrypt(byte[] data, String key) {
        return calculate(Cipher.DECRYPT_MODE, data, key);
    }

    private static byte[] calculate(int ENCRYPT_MODE, byte[] data, String key) {
        byte[] res = new byte[0];
        SecureRandom sr = new SecureRandom();
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes(ENCODE));

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey secretKey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance(DES);

            cipher.init(ENCRYPT_MODE, secretKey, sr);

            res = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}
